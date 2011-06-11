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

    public List<Integer> getAvailableResearches(String idname) {

        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Research where idname='"
                + idname + "'");

        List<Integer> idAvailableResearch = new ArrayList<Integer>();
        Iterator<Research> iterator = q.list().iterator();

        while(iterator.hasNext())
            idAvailableResearch.add(iterator.next().getId().getResearchid());

        return idAvailableResearch;
        
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

    public void addResearches(String userid, List<Integer> availableResearch) {
        deleteAllResearches(userid);

        Session session;

        Iterator<Integer> iterator = availableResearch.iterator();
        while(iterator.hasNext()) {
            Integer id = iterator.next();
            ResearchId researchId = new ResearchId(userid,id);
            Research research = new Research(researchId);
            session = createNewSessionAndTransaction();
            session.save(research);
            commitTransaction(session);
        }
    }

    public void addResearch(Research researchInstance) {
            Session session = createNewSessionAndTransaction();
            session.save(researchInstance);
            commitTransaction(session);
    }

    public void deleteResearch(Research researchInstance) {
           
    }

}
