package ConnectionDataBase;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PlayerresourcesHelper {

    Session session = null;

    public PlayerresourcesHelper() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    public void createPlayerResources(String idname) {
        Playerresources playerResources = new Playerresources();
        playerResources.setIdname(idname);
        playerResources.setMoney(100000);
        playerResources.setPhdsnumber(0);
        playerResources.setUndegraduatesnumber(0);
        playerResources.setResearchpoints(0);

        Transaction tx = session.beginTransaction();
        tx.begin();

        session.save(playerResources);

        tx.commit();

    }
    
    public int getResearchpoints(String idname) {

        int points = 0;

        Transaction tx = session.beginTransaction();

        tx.begin();
        Query q = session.createQuery("from Playerresources where idname='"
                + idname + "'");
        Playerresources resources = (Playerresources) q.uniqueResult();

        if(resources != null)
            points = resources.getResearchpoints();
        tx.commit();

        return points;
    }

    public void deleteResources(String idname) {

        Transaction tx = session.beginTransaction();

        tx.begin();
        Query q = session.createQuery("from Playerresources where idname='"
                + idname + "'");
        Playerresources resources = (Playerresources) q.uniqueResult();
        if(resources != null)
            session.delete(resources);
        tx.commit();

    }


}
