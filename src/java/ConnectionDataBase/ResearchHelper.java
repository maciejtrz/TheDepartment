package ConnectionDataBase;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ResearchHelper {
    
    Session session = null;

    public ResearchHelper() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
    public void createResearch(String IdName, String Title, int researchPoints) {
        
        ResearchId researchId = new ResearchId(IdName,Title);
        Research research = new Research(researchId, researchPoints);
        
        createResearch(research);
    }

    void createResearch(Research research) {

        Transaction tx = session.beginTransaction();
        tx.begin();
        session.save(research);
        tx.commit();
    }

    public List<Research> getFinishedResearches(String idname) {

        while(!session.isOpen())
            session = HibernateUtil.getSessionFactory().getCurrentSession();

        Transaction tx = session.beginTransaction();

        Query q = session.createQuery("from Research where idname='"
                + idname + "'");
        
        return (List<Research>) q.list();
        
    }

}
