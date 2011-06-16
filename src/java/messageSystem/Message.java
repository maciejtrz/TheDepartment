package messageSystem;

import ConnectionDataBase.MessageSystemHelper;
import ConnectionDataBase.Messagesystem;
import ConnectionDataBase.PlayerHelper;
import ConnectionDataBase.Players;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.model.SelectItem;


public class Message extends Messagesystem implements Serializable  {
    
        /*Selected reicever*/
    private int selectedReceiver;

    /*List of users*/
    public List<Players> users = new ArrayList<Players>();

    public Message() {
        setSenderid(utilities.BasicUtils.getUserName());
    }

    public void setSelectedReceiver(int s) {
        this.selectedReceiver = s;
    }

    public int getSelectedReceiver() {
        return this.selectedReceiver;
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
        }

        return receiverList;
    }


        public void send() {

        MessageSystemHelper msghelp = new MessageSystemHelper();
        msghelp.createMessage(getSenderid(), users.get(selectedReceiver).getIdname(), getSubject(),
                getMsg(), MessageSingleton.PLAIN_MESSAGE);

        }
}
