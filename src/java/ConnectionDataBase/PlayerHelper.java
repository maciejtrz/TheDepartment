/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectionDataBase;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author karol
 */
public class PlayerHelper {

    Session session = null;

    public PlayerHelper() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    public List<Players> getPlayers() {

        List<Players> list = null;

        org.hibernate.Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Players");
        list = (List<Players>) q.list();

        return list;

    }

    public Players getPlayer(String idname) {
        Players player = null;

        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Players where idname='"
                + idname + "'");
        player = (Players) q.uniqueResult();

        return player;
    }

    public void updateLoggedStatus(String idname, boolean logged) {
        Transaction tx = session.beginTransaction();
        tx.begin();

        Query q = session.createQuery("from Players where idname='"
                + idname + "'");
        Players player = (Players) q.uniqueResult();
        player.setLoggedin(logged);
        session.saveOrUpdate(player);

        tx.commit();
    }

    public void addPlayer(String idname, String encodedPassword, String email) {
        Players player = new Players(idname,encodedPassword,email,false);

        Transaction tx = session.beginTransaction();
        tx.begin();
        session.save(player);
        tx.commit();
    }

    public void deletePlayer(String idname) {
        Transaction tx = session.beginTransaction();
        
        tx.begin();
        Query q = session.createQuery("from Players where idname='" + idname + "'");
        Players player = (Players) q.uniqueResult();

        if(player != null)
            session.delete(player);
        tx.commit();
    }


}
