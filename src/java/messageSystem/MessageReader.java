/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package messageSystem;

import ConnectionDataBase.MessageSystemHelper;
import ConnectionDataBase.Messagesystem;
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
public abstract class MessageReader {

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

    public MessageReader(int messageType) {
        this.messageType = messageType;
        username = BasicUtils.getUserName();
        selectedMessage = new Integer(0);
        MessageSystemHelper messageHelper = new MessageSystemHelper();
        messages = messageHelper.getMessages(username);
    }

    public int getMessageType() {
        return messageType;
    }

    public List<Messagesystem> getMessages() {
        return messages;
    }

    public void setMessages(List<Messagesystem> messages) {
        this.messages = messages;
    }

    public String getReceiver() {
        return username;
    }

    public void sendMessage(String receiver, String subject, String text) {

        MessageSystemHelper messageHelper = new MessageSystemHelper();
        messageHelper.createMsg(getReceiver(), receiver, subject, text);

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
            messagesSubjects.add(new SelectItem(new Integer(i++), message.getSubcjet()));

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
        int i = 0;

        Iterator<Messagesystem> iterator = getMessages().iterator();
        while (iterator.hasNext()) {
            Messagesystem message = iterator.next();

            receiverList.add(new SelectItem(new Integer(i), message.getReceiverid()));
            System.out.println(message.getReceiverid() + " This is what i get in getRecievers");
        }

        return receiverList;
    }
}
