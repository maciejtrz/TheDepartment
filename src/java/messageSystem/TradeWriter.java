/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package messageSystem;

import ConnectionDataBase.MessageSystemHelper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.model.SelectItem;
import resources.ResourcesType;

/**
 *
 * @author root
 */
public class TradeWriter extends MessageWriter implements Serializable {

    private static final List<SelectItem> resources;
    private TradeOffer tradeOffer;

    static {
            resources = new ArrayList<SelectItem>();

            for(int i = 0;i < ResourcesType.getResourcesList().length;i++) {
                resources.add(new SelectItem(new Integer(i),ResourcesType.getResourcesList()[i].getResourcesName()));
            }
    }

    TradeWriter(int messageType) {
        super(messageType);
        tradeOffer = new TradeOffer();
    }

    public Date getExpireDate() {
        return getTradeOffer().getExpireDate();
    }

    public void setExpireDate(Date date) {
        getTradeOffer().setExpireDate(date);
    }

    public void setExpireDate(long date) {
        getTradeOffer().setExpireDate(date);
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

    public TradeOffer getTradeOffer() {
        return tradeOffer;
    }

    public void sendTrade() {

        System.out.println("Sending trade...");

        MessageSystemHelper messageSystemHelper = new MessageSystemHelper();
        messageSystemHelper.createMessage(getUsername(),getReceiverid(),getSubject(),
                getTradeOffer().encode(),getMessageType());

    }

}
