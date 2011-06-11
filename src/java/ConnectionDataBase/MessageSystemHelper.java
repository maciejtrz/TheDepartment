/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectionDataBase;

import java.util.Date;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author pk2109
 */
public class MessageSystemHelper extends AbstractHelper {

    public void createMsg(String SenderID, String ReiverID, String subject, String text) {

        Messagesystem msg = new Messagesystem();
        msg.setSenderid(SenderID);
        msg.setReceiverid(ReiverID);
        Date date = new Date();
        String time = (" " + date.getHours() + date.getMinutes() + " " + date.getDate() + " " + date.getMonth() + " " + date.getYear());
        msg.setSubcjet(subject);
        msg.setMsg(text);
        msg.setDate(time);
        msg.setRead(false);
        msg.setMsgnumber(getMsgNum());


        Session session = createNewSessionAndTransaction();
        session.save(msg);
        commitTransaction(session);

    }

    public void deleteMsg(int num) {
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Messagesystem where msgnumber='" + num + "'");
        Messagesystem msg = (Messagesystem) q.uniqueResult();

        if (msg != null) {
            session.delete(msg);
            commitTransaction(session);
        }
    }

    public void readMsg(int num) {
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Messagesystem where msgnumber='" + num + "'");
        Messagesystem msg = (Messagesystem) q.uniqueResult();
        msg.setRead(true);

        if (msg != null) {
            session.saveOrUpdate(msg);
            commitTransaction(session);
        }
    }

    public int getMsgNum(){

        /* select count(*) from Messa; */
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Messagesystem");
        return (q.list().size()+1);
    }
}
