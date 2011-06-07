package ConnectionDataBase;

import ConnectionDataBase.HibernateUtil;
import ConnectionDataBase.Research;
import ConnectionDataBase.ResearchId;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

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

}
