/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package messageSystem;

import ConnectionDataBase.MessageSystemHelper;
import ConnectionDataBase.Messagesystem;
import Connections.UserManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.icefaces.application.PushRenderer;
import utilities.BasicUtils;

public class TradeMessageReader extends MessageWriter implements Serializable {

    private boolean checked;
    private boolean yourMessagesChecked;

    private List<TradeOffer> offeredTrades = new ArrayList<TradeOffer>();
    private PriorityQueue<TradeOffer> expirationTimeList = new PriorityQueue<TradeOffer>();


    private List<TradeOffer> yourOfferedTrades = new ArrayList<TradeOffer>();
    private PriorityQueue<TradeOffer> yourExpirationTimeList = new PriorityQueue<TradeOffer>();

    MessageSystemHelper messageSystemHelper = new MessageSystemHelper();
    private TradeOffer selectedTradeOffer;
    private TradeOffer yourSelectedTradeOffer;

    public TradeMessageReader() {
        super(BasicUtils.getUserName(),MessageSingleton.TRADE_OFFER);
        checked = false;
        yourMessagesChecked = false;

        setUsername(BasicUtils.getUserName());
        PushRenderer.addCurrentSession(BasicUtils.getUserName() + "trading");
    }

    public void initialize(String username) {
        
    }

    public List<TradeOffer> getOfferedTrades() {

        if (checked) {
            Date currentDate = new Date();

            while (!expirationTimeList.isEmpty()) {
                TradeOffer tradeOffer = expirationTimeList.peek();
                if (tradeOffer.getExpireDate().compareTo(currentDate) <= 0) {
                    expirationTimeList.poll();
                    offeredTrades.remove(tradeOffer);

                    messageSystemHelper.deleteMsg(tradeOffer.getMsgnumber());
                } else {
                    break;
                }

            }
        }

        if (!checked || UserManager.hasNewMessage(getUsername())) {
            checked = true;
            offeredTrades = new ArrayList<TradeOffer>();

            List<Messagesystem> encodedTrades = getMessages();
            Iterator<Messagesystem> iterator = encodedTrades.iterator();

            Date currentDate = new Date();

            while (iterator.hasNext()) {
                Messagesystem message = iterator.next();

                TradeOffer tradeOffer = new TradeOffer();

                tradeOffer.parse(message.getMsg());

                if (tradeOffer.getExpireDate().compareTo(currentDate) > 0) {

                    tradeOffer.setSenderid(message.getSenderid());
                    tradeOffer.setReceiverid(message.getReceiverid());
                    tradeOffer.setCreationtime(message.getCreationtime());
                    tradeOffer.setMsgnumber(message.getMsgnumber());
                    tradeOffer.setSubject(message.getSubject());
                    
                    offeredTrades.add(tradeOffer);
                    expirationTimeList.offer(tradeOffer);
                } else {
                    messageSystemHelper.deleteMsg(tradeOffer.getMsgnumber());
                }
            }
        }
        return offeredTrades;
    }

    public List<TradeOffer> getYourOfferedTrades() {

        if (yourMessagesChecked) {
            Date currentDate = new Date();

            while (!yourExpirationTimeList.isEmpty()) {
                TradeOffer tradeOffer = yourExpirationTimeList.peek();
                if (tradeOffer.getExpireDate().compareTo(currentDate) <= 0) {
                    yourExpirationTimeList.poll();
                    yourOfferedTrades.remove(tradeOffer);

                    messageSystemHelper.deleteMsg(tradeOffer.getMsgnumber());
                } else {
                    break;
                }

            }
        }

        if (!yourMessagesChecked || UserManager.sentNewMessage(getUsername())) {
            yourMessagesChecked = true;
            yourOfferedTrades = new ArrayList<TradeOffer>();

            List<Messagesystem> encodedTrades = getSentMessages();

            System.out.println("Number of sent messages " + encodedTrades.size());

            Iterator<Messagesystem> iterator = encodedTrades.iterator();

            Date currentDate = new Date();

            while (iterator.hasNext()) {
                Messagesystem message = iterator.next();

                TradeOffer tradeOffer = new TradeOffer();

                tradeOffer.parse(message.getMsg());
                

                tradeOffer.setSenderid(message.getSenderid());
                tradeOffer.setReceiverid(message.getReceiverid());
                tradeOffer.setCreationtime(message.getCreationtime());
                tradeOffer.setMsgnumber(message.getMsgnumber());
                tradeOffer.setSubject(message.getSubject());

                yourOfferedTrades.add(tradeOffer);
                yourExpirationTimeList.offer(tradeOffer);

            }
        }
        
        return yourOfferedTrades;
    }

    public void setAcceptTradeOffer(TradeOffer acceptedTradeOffer) {

        FacesContext facesContext = FacesContext.getCurrentInstance();

        System.out.println("Accepting the offer...");
       
        if(!acceptedTradeOffer.accept()) {
            
            FacesContext.getCurrentInstance().addMessage(
                    BasicUtils.findComponent(facesContext.getViewRoot(),
                    "acceptButton").getClientId(facesContext),
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Accept conditions",
                    "You cannot accept this offer: you must own more than " +
                    acceptedTradeOffer.getAmountWanted() + " of " + 
                    acceptedTradeOffer.getResourcesWantedName()));

        } else {
            offeredTrades.remove(acceptedTradeOffer);
        }
        
    }

    public void setDeclineTradeOffer(TradeOffer declinedTradeOffer) {
        declinedTradeOffer.decline();
        offeredTrades.remove(declinedTradeOffer);
        expirationTimeList.remove(declinedTradeOffer);

        UserManager.notifyAboutSendingMessage(declinedTradeOffer.getSenderid());
    }
    
    public void setDeleteTradeOffer(TradeOffer declinedTradeOffer) {
        declinedTradeOffer.decline();
        yourOfferedTrades.remove(declinedTradeOffer);
        yourExpirationTimeList.remove(declinedTradeOffer);

        UserManager.notifyUserAboutMessage(declinedTradeOffer.getReceiverid());
    }

    public void setSelectedShowTrade(TradeOffer selectedTradeOffer) {    
        this.selectedTradeOffer = selectedTradeOffer;
    }

    public TradeOffer getSelectedShowTrade() {
        return selectedTradeOffer;
    }

    public void setYourSelectedShowTrade(TradeOffer yourSelectedTradeOffer) {
        this.yourSelectedTradeOffer = yourSelectedTradeOffer;
    }

    public TradeOffer getYourSelectedShowTrade() {
        return yourSelectedTradeOffer;
    }
}
