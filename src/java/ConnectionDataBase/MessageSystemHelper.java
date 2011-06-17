/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectionDataBase;


import Connections.UserManager;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;



/**
 *
 * @author pk2109
 */
public class MessageSystemHelper extends AbstractHelper {

    public int createMessage(String SenderID, String ReceiverID, String subject, String text, int messageType) {

        Messagesystem msg = new Messagesystem();

        msg.setSenderid(SenderID);
        msg.setReceiverid(ReceiverID);

        Date date = new Date();
        msg.setCreationtime(date.getTime());

        msg.setSubject(subject);
        msg.setMsg(text);
        msg.setRead(false);

        msg.setType(messageType);
        // commiting transaction
        Session session = createNewSessionAndTransaction();
        session.save(msg);
        commitTransaction(session);

        UserManager.notifyUserAboutMessage(ReceiverID);

        System.out.println("Setting new message...");

        UserManager.notifyAboutSendingMessage(SenderID);

        return msg.getMsgnumber();

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
            
        }
        commitTransaction(session);
    }


    public List<Messagesystem> getMessages(String receiver, int messageType) {
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Messagesystem where ReceiverId = '" + receiver + "'"
                + "and type=" + messageType);

        List<Messagesystem> ret = q.list();
        session.close();
        return ret;
    }

    public List<Messagesystem> getSentMessages(String sender, int messageType) {
        Session session = createNewSessionAndTransaction();

        String query = "from Messagesystem where senderid = '" + sender + "'"
                + " and type = " + messageType;

        System.out.println("Our query is: " + query);

        Query q = session.createQuery(query);

        List<Messagesystem> ret = q.list();
        session.close();
        return ret;
    }

}
