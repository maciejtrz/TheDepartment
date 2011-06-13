/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package messageSystem;

import resources.ResourcesType;
import ConnectionDataBase.MessageSystemHelper;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;


public class TradeMessageWriter extends MessageManager{

    private static List<SelectItem> resources;
    private TradeOffer tradeOffer;

    static {
            resources = new ArrayList<SelectItem>();
            
            for(int i = 0;i < ResourcesType.getResourcesList().length;i++) {
                resources.add(new SelectItem(new Integer(i),ResourcesType.getResourcesList()[i].getResourcesName()));
            }
    }

    /** Creates a new instance of TradeMessageWriter */
    public TradeMessageWriter() {
        super(MessageSingleton.TRADE_OFFER);
        tradeOffer = new TradeOffer();
    }

    public void setSubject(String subject) {
        tradeOffer.setSubject(subject);
    }

    public String getSubject() {
        return tradeOffer.getSubject();
    }

    public String getTradeDescription() {
        return tradeOffer.getTradeDescription();
    }

    public void setTradeDescription(String tradeDescription) {
        tradeOffer.setTradeDescription(tradeDescription);
    }

    public int getResourcesOfferedType() {
        return tradeOffer.getResourcesOfferedType();
    }

    public void setResourcesOfferedType(int resourcesOfferedType) {
        tradeOffer.setResourcesOfferedType(resourcesOfferedType);
    }

    public int getAmountOffered() {
        return tradeOffer.getAmountOffered();
    }

    public void setAmountOffered(int amountOffered) {
        tradeOffer.setAmountOffered(amountOffered);
    }

    public int getAmountWanted() {
        return tradeOffer.getAmountWanted();
    }

    public void setAmountWanted(int amountWanted) {
        tradeOffer.setAmountWanted(amountWanted);
    }

    public int getResourcesWantedType(){
        return tradeOffer.getResourcesWantedType();
    }

    public void setResourcesWantedType(int resourcesWantedType) {
        tradeOffer.setResourcesWantedType(resourcesWantedType);
    }

    public List<SelectItem> getAvailableResources() {
        return resources;
    }

    /* reading offers and accepting/decling... */

    public void sendTrade() {

        MessageSystemHelper messageSystemHelper = new MessageSystemHelper();
        messageSystemHelper.createMessage(getUsername(),getReceiverid(),tradeOffer.getSubject(),
                tradeOffer.encode(),getMessageType());
        
    }



}
