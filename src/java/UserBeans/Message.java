/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UserBeans;

import ConnectionDataBase.MessageSystemHelper;
import ConnectionDataBase.PlayerHelper;
import ConnectionDataBase.Players;
import Connections.UserManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.model.SelectItem;
import messageSystem.MessageSingleton;

/**
 *
 * @author pk2109
 */
public class Message {

    // public String sender;
    public String receiver;
    public String subject;
    public String text;

    /*Selected reicever*/
    private int selectedReceiver;

    /*List of users*/
    public List<Players> users;

    /** Creates a new instance of Message */
    public Message() {
        System.out.println("Creating new Message Bean");
        this.users = new ArrayList<Players>();
    }

    public void setSelectedReceiver(int s) {
        this.selectedReceiver = s;
    }

    public int getSelectedReceiver() {
        return this.selectedReceiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiver() {
        return this.receiver;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public void send() {

        System.out.println("Send method invoked");
        String name = utilities.BasicUtils.getUserName();

        MessageSystemHelper msghelp = new MessageSystemHelper();
        msghelp.createMessage(name, users.get(selectedReceiver).getIdname(), getSubject(),
                getText(), MessageSingleton.PLAIN_MESSAGE);

        UserManager.notifyUserAboutMessage(users.get(selectedReceiver).getIdname());

        /*
        System.out.println("Here I am 2");
        System.out.println("Receiver id is:" + users.get(selectedReceiver).getIdname());
        FacesContext receiverContext = UserManager.getFacesContext(users.get(selectedReceiver).getIdname());
        receiverContext.addMessage(null, new FacesMessage("You've got a new message!"));

        // MessageSingleton.inboxThreads.get(users.get(selectedReceiver).getIdname()).run();

        /*ActionEvent sendClicked = new ActionEvent(this.sendButton);
        System.out.println("HERE");
        sendClicked.
        System.out.println("HERE 2");
        this.listener.processAction(sendClicked);*/

    }

    public List<SelectItem> getReceivers() {
        List<SelectItem> receiverList = new ArrayList<SelectItem>();
        int i = 0;

        PlayerHelper ph = new PlayerHelper();

        Iterator<Players> iterator = ph.getPlayers().iterator();

        while (iterator.hasNext()) {
            Players player = iterator.next();

            receiverList.add(new SelectItem(new Integer(i++), player.getIdname()));
            users.add(player);
            System.out.println(player.getIdname());
        }

        return receiverList;
    }
}
