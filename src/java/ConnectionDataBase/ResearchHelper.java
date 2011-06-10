package ConnectionDataBase;

import ResearchPoints.ResearchDevelopment;
import ResearchPoints.ResearchTreeNode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import specializationsGenerator.SpecializationsGenerator;

public class ResearchHelper extends AbstractHelper {
    
    public void createResearch(String IdName, Integer researchNumber, int researchPoints) {
        
        ResearchId researchId = new ResearchId(IdName,researchNumber);
        Research research = new Research(researchId);
        
        createResearch(research);
    }

    void createResearch(Research research) {

        Session session = createNewSessionAndTransaction();
        session.save(research);
        commitTransaction(session);
    }

    public List<Integer> getFinishedResearches(String idname) {

        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Research where idname='"
                + idname + "'");

        List<Integer> idFinishedResearch = new ArrayList<Integer>();
        Iterator<Research> iterator = q.list().iterator();
        while(iterator.hasNext())
            idFinishedResearch.add(iterator.next().getId().getResearchid());

        return idFinishedResearch;
        
    }

    public void addResearches(List<Research> finishedResearches) {

        Iterator<Research> iterator = finishedResearches.iterator();
        while(iterator.hasNext()) {

            Session session = createNewSessionAndTransaction();

            session.saveOrUpdate(iterator.next());
            commitTransaction(session);

        } 
    }

    public void initializedResearchTree(String username) {

        for(int i = 0;i < SpecializationsGenerator.subjectList.length;i++) {
            List<ResearchTreeNode> list = ResearchDevelopment.getFirstResearch(i);
            Iterator<ResearchTreeNode> iterator = list.iterator();

            while(iterator.hasNext()) {
                ResearchTreeNode researchTreeNode = iterator.next();
                Integer researchId = researchTreeNode.getResearchInstance().getResearchid();
                Research research = new Research(new ResearchId(username,researchId));

                Session session = createNewSessionAndTransaction();
                session.saveOrUpdate(research);
                commitTransaction(session);
            }
        }
    }

    public void deleteAllResearches(String username) {

        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Research where idname='"
                + username + "'");

        Iterator<Research> iterator = q.list().iterator();

        while(iterator.hasNext()) {
            Research research = iterator.next();
            session = createNewSessionAndTransaction();
            session.delete(research);
            commitTransaction(session);
        }

    }

}
