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

        System.out.println("Sending a notice offfer...");

        getTradeOffer().setSenderid(BasicUtils.getUserName());

        String result = null;
        if(validate()) {
            OffersSuperviser.getNoticeMonitor().addNoticeOffer(getTradeOffer());

            FacesContext facesContext = FacesContext.getCurrentInstance();

            FacesContext.getCurrentInstance().addMessage(
                    BasicUtils.findComponent(facesContext.getViewRoot(),
                    "sendNoticeOfferTrade").getClientId(facesContext),
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Sent trade",
                    "Noticeboard offer sent successfully"));

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
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Expiry date error",
                    "Expiry date earlier than the current time"));

            error = false;
        }

        if(getAmountWanted() <= 0) {
            FacesContext.getCurrentInstance().addMessage(
                    BasicUtils.findComponent(facesContext.getViewRoot(),
                    "wantedNoticeOfferAmount").getClientId(facesContext),
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wanted Amount Limi",
                    "Amount has be greater than 0"));

            error = false;
        }

        if(getAmountOffered() <= 0) {
            FacesContext.getCurrentInstance().addMessage(
                    BasicUtils.findComponent(facesContext.getViewRoot(),
                    "offeredNoticeOfferAmount").getClientId(facesContext),
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Offered Amount Limi",
                    "Amount has be greater than 0"));

            error = false;
        }

        if(getResourcesOfferedType() == getResourcesWantedType()) {
            FacesContext.getCurrentInstance().addMessage(
                    BasicUtils.findComponent(facesContext.getViewRoot(),
                    "sendNoticeOfferTrade").getClientId(facesContext),
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Resources type",
                    "Wanted and offered have the same type"));

            error = false;
        }

        return error;
    }

}
