/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package messageSystem;

import java.io.Serializable;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import utilities.BasicUtils;


/**
 *
 * @author kp1209
 */
public class NoticeMessageWriter extends TradeWriter implements Serializable  {
    
    public NoticeMessageWriter() {
        super(MessageSingleton.NOTICE_BOARD_OFFER);
    }

    public String sendNoticeOffer() {
        getTradeOffer().setSenderid(BasicUtils.getUserName());

        String result = null;
        if(validate()) {
            OffersSuperviser.getNoticeMonitor().addNoticeOffer(getTradeOffer());

            FacesContext facesContext = FacesContext.getCurrentInstance();

            FacesContext.getCurrentInstance().addMessage(
                    BasicUtils.findComponent(facesContext.getViewRoot(),
                    "sendNoticeOfferTrade").getClientId(facesContext),
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Sent trade",
                    "You sent a notice offer " + getAmountOffered() + ""
                    + " of " + getResourceName(getResourcesOfferedType()) +
                    getAmountWanted() + " of " +
                    getResourceName(getResourcesWantedType()) +
                    "\n" + "Trade Subject: " + getSubject() + "\n" +
                    "Tade Description: \n" + getTradeDescription()));

             getTradeOffer().cleanTradeOffer();

            result = "success";
        } 
        return result;
    }

    private boolean validate() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        
        boolean error = true;

        if (getExpireDate().before(new Date())) {
            System.out.println("Adding new message for expire date...");
            FacesContext.getCurrentInstance().addMessage(
                    BasicUtils.findComponent(facesContext.getViewRoot(),
                    "expireNoticeOfferDate").getClientId(facesContext),
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Expire date error",
                    "Expire date of trade offer cannot be earlier than the current time!"));

            error = false;
        }

        if(getAmountWanted() <= 0) {
            FacesContext.getCurrentInstance().addMessage(
                    BasicUtils.findComponent(facesContext.getViewRoot(),
                    "wantedNoticeOfferAmount").getClientId(facesContext),
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wanted Amount Limi",
                    "Wanted amount must be greater than 0"));

            error = false;
        }

        if(getAmountOffered() <= 0) {
            FacesContext.getCurrentInstance().addMessage(
                    BasicUtils.findComponent(facesContext.getViewRoot(),
                    "offeredNoticeOfferAmount").getClientId(facesContext),
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Offered Amount Limi",
                    "Offered amount must be greater than 0"));

            error = false;
        }

        if(getResourcesOfferedType() == getResourcesWantedType()) {
            FacesContext.getCurrentInstance().addMessage(
                    BasicUtils.findComponent(facesContext.getViewRoot(),
                    "sendNoticeOfferTrade").getClientId(facesContext),
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Resources type",
                    "Reasources wanted and offered cannot be of the same type"));

            System.out.println("Resources are the same...");

            error = false;
        }

        return error;
    }

}
