package ConnectionDataBase;

import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class ResearchHelper extends AbstractHelper {
    
    public void createResearch(String IdName, String Title, int researchPoints) {
        
        ResearchId researchId = new ResearchId(IdName,Title);
        Research research = new Research(researchId, researchPoints);
        
        createResearch(research);
    }

    void createResearch(Research research) {

        Session session = createNewSessionAndTransaction();
        session.save(research);
        commitTransaction(session);
    }

    public List<Research> getFinishedResearches(String idname) {

        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Research where idname='"
                + idname + "'");
        return (List<Research>) q.list();
        
    }

    public void addResearches(List<Research> finishedResearches) {

        Iterator<Research> iterator = finishedResearches.iterator();
        while(iterator.hasNext()) {

            Session session = createNewSessionAndTransaction();
            session.save(iterator.next());
            commitTransaction(session);

        }


    }

}
