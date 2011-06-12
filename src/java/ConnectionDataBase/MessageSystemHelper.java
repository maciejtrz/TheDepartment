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

    public void createMsg(String SenderID, String ReceiverID, String subject, String text) {

        Messagesystem msg = new Messagesystem();
        System.out.println("Creating Message");
        int num = getMsgNum();
        msg.setMsgnumber(num);
        System.out.println("Msg number is: " + num);
        msg.setSenderid(SenderID);
        System.out.println("Sender ID is: " + SenderID );
        msg.setReceiverid(ReceiverID);
        System.out.println("Receiver ID is: " + ReceiverID );

        Date date = new Date();
        String time = (date.getHours() + date.getMinutes() + "|" + date.getDate() + "|" + date.getMonth() + "|" + date.getYear());

        msg.setDate(time);
        System.out.println("Date is: " + time);
        msg.setSubcjet(subject);
        System.out.println("Msg Number is: " + subject);
        msg.setMsg(text);
        System.out.println("Text of msg is:" + text);
        msg.setRead(false);

        // commiting transaction
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
        System.out.print("NEXT MSG NUMBER = "  + q.list().size() + " +1 " );
        return (q.list().size());
    }

    /* HUJ */
}
