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
import utilities.BasicUtils;

public class TradeMessageReader extends MessageWriter implements Serializable {

    private boolean checked;
    private List<TradeOffer> offeredTrades = new ArrayList<TradeOffer>();
    private PriorityQueue<TradeOffer> expirationTimeList = new PriorityQueue<TradeOffer>();
    MessageSystemHelper messageSystemHelper = new MessageSystemHelper();

    public TradeMessageReader() {
        super(MessageSingleton.TRADE_OFFER);
        checked = false;
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

    public void setAcceptTradeOffer(TradeOffer acceptedTradeOffer) {

        FacesContext facesContext = FacesContext.getCurrentInstance();
       
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
    }
}
