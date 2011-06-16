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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import resources.ResourcesType;
import utilities.BasicUtils;

/**
 *
 * @author root
 */
public class TradeWriter extends MessageWriter implements Serializable {

    private static final List<SelectItem> resources;
    private TradeOffer tradeOffer;

    static {
        resources = new ArrayList<SelectItem>();

        for (int i = 0; i < ResourcesType.getResourcesList().length; i++) {
            resources.add(new SelectItem(new Integer(i), ResourcesType.getResourcesList()[i].getResourcesName()));
        }
    }

    TradeWriter(int messageType) {
        super(messageType);
        tradeOffer = new TradeOffer();
    }

    public String getResourceName(int i) {
        return ResourcesType.getResourcesList()[i].getResourcesName();
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

    public int getResourcesWantedType() {
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

    public String sendTrade() {

        FacesContext facesContext = FacesContext.getCurrentInstance();

        boolean error = false;

        if (getUsername().equals(getReceiverid())) {
            FacesContext.getCurrentInstance().addMessage(
                    BasicUtils.findComponent(facesContext.getViewRoot(),
                    "PrivateTradeWriter").getClientId(facesContext),
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Trade error",
                    "You cannot make trades with yourself!"));

            error = true;
        }

        if (getExpireDate().before(new Date())) {
            FacesContext.getCurrentInstance().addMessage(
                    BasicUtils.findComponent(facesContext.getViewRoot(),
                    "expireTradeOfferDate").getClientId(facesContext),
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Expire date error",
                    "Expire date of trade offer cannot be earlier than the current time!"));

            error = true;
        }

        if(getAmountOffered() <= 0) {
            FacesContext.getCurrentInstance().addMessage(
                    BasicUtils.findComponent(facesContext.getViewRoot(),
                    "offeredAmount").getClientId(facesContext),
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Offered Amount Limi",
                    "Offered amount must be greater than 0"));

            error = true;
        }

        if(getAmountOffered() <= 0) {
            FacesContext.getCurrentInstance().addMessage(
                    BasicUtils.findComponent(facesContext.getViewRoot(),
                    "offeredAmount").getClientId(facesContext),
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Offered Amount Limi",
                    "Offered amount must be greater than 0"));

            error = true;
        }

        if(getAmountWanted() <= 0) {
            FacesContext.getCurrentInstance().addMessage(
                    BasicUtils.findComponent(facesContext.getViewRoot(),
                    "wantedAmount").getClientId(facesContext),
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wanted Amount Limi",
                    "Wanted amount must be greater than 0"));

            error = true;
        }

        String result = null;

        if (!error) {
            MessageSystemHelper messageSystemHelper = new MessageSystemHelper();
            messageSystemHelper.createMessage(getUsername(), getReceiverid(), getSubject(),
                    getTradeOffer().encode(), getMessageType());

            FacesContext.getCurrentInstance().addMessage(
                    BasicUtils.findComponent(facesContext.getViewRoot(),
                    "sendTradeOffer").getClientId(facesContext),
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Sent trade",
                    "You offered " + getAmountOffered() + ""
                    + " of " + getResourceName(getResourcesOfferedType()) +
                    " to " + getReceiverid() + " in exchange for " +
                    getAmountWanted() + " of " +
                    getResourceName(getResourcesWantedType()) + 
                    "\n" + "Trade Subject: " + getSubject() + "\n" +
                    "Tade Description: \n" + getTradeDescription()));

            setAmountOffered(0);
            setAmountWanted(0);
            setExpireDate(new Date());
            setResourcesOfferedType(0);
            setResourcesWantedType(0);
            setSubject("");
            setTradeDescription("");
            setSelectedReceiver(0);

            result = "success";
        }

        return result;
    }
}
