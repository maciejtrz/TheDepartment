/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package messageSystem;

import ConnectionDataBase.MessageSystemHelper;
import ConnectionDataBase.Messagesystem;
import ConnectionDataBase.PlayerHelper;
import ConnectionDataBase.Players;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import utilities.BasicUtils;
import javax.faces.model.SelectItem;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author kp1209
 */
public abstract class MessageManager {

    /* Type of the message, for example, regular message or a trade offer */
    private final int messageType;

    /* List of messages of the given type to the user */
    private List<Messagesystem> messages;

    /* UserId of the receiver */
    private final String username;

    /* Selected message */
    private Integer selectedMessage;

    /*Selected reicever*/
    private int selectedReceiver;

    /*List of users*/
    public List<Players> users;

    /*Message sender */
    private String sender;
    private String receiver;

    private String date;
    private int id;

    public MessageManager(int messageType) {
        this.messageType = messageType;
        username = BasicUtils.getUserName();
        sender = username;
        selectedMessage = new Integer(0);
        users = new ArrayList<Players>();
        selectedReceiver = 0;
    }

    public void setUniqueid(int id){
        this.id = id;
    }

    public int getUniqueid() {
        return id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSender() {
        return sender;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiver() {
        return receiver;
    }

    public int getMessageType() {
        return messageType;
    }

    public List<Messagesystem> getMessages() {
        MessageSystemHelper messageSystemHelper = new MessageSystemHelper();
        return messageSystemHelper.getMessages(getSender(), getMessageType());

    }

    public void setMessages(List<Messagesystem> messages) {
        this.messages = messages;
    }

    public String getUsername() {
        return username;
    }

    public String getSelectedReceiverId() {
        return users.get(getSelectedReceiver()).getIdname();
    }

    public void sendMessage(String receiver, String subject, String text) {

        MessageSystemHelper messageHelper = new MessageSystemHelper();
        messageHelper.createMessage(getSender(),receiver,subject,text, getMessageType());

    }

    public void updateMessages() {

        /* Reading messages from database and updating list */
    }

    public List<SelectItem> getMessagesSubjects() {
        List<SelectItem> messagesSubjects = new ArrayList<SelectItem>();
        int i = 0;

        Iterator<Messagesystem> iterator = getMessages().iterator();
        while (iterator.hasNext()) {

            Messagesystem message = iterator.next();
            messagesSubjects.add(new SelectItem(new Integer(i++), message.getSubject()));

        }

        return messagesSubjects;
    }

    public void setSelectedMessage(Integer selectedMessage) {
        this.selectedMessage = selectedMessage;
    }

    public Integer getSelectedMessage() {
        return selectedMessage;
    }

    public String getChosenMessage() {
        String result = null;
        if (getMessages().size() > getSelectedMessage()) {
            result = getMessages().get(getSelectedMessage()).getDate();
        }

        return result;
    }

    public String getText() {
        String result = null;

        System.out.println("Get text invoked: selected item is: " + getSelectedMessage());

        if (getMessages().size() > getSelectedMessage()) {
            result = getMessages().get(getSelectedMessage()).getMsg();
        }
        return result;
    }

    public String onFlowProcess(FlowEvent event) {
        System.out.println("Old step: " + event.getOldStep());
        System.out.println("New step: " + event.getNewStep());
        return event.getNewStep();
    }

        public List<SelectItem> getReceivers() {
        List<SelectItem> receiverList = new ArrayList<SelectItem>();
        users = new ArrayList<Players>();
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

    public void setSelectedReceiver(int selectedReceiver) {
        this.selectedReceiver = selectedReceiver;
    }

    public int getSelectedReceiver() {
        return this.selectedReceiver;
    }


}