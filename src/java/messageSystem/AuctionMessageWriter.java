package messageSystem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import utilities.BasicUtils;

public class AuctionMessageWriter extends TradeWriter implements Serializable {

    private Auction auction;
    private Integer selectedAuctionType;
    private final static List<SelectItem> availableAuctionTypes;

    static {
        availableAuctionTypes = new ArrayList<SelectItem>();
        for(int i = 0; i < AuctionFactory.getSize(); i++) {
            availableAuctionTypes.add(new SelectItem(new Integer(i),AuctionFactory.getAuctionName(i)));
        }
    }

    public AuctionMessageWriter() {
        super(MessageSingleton.AUCTION_OFFER);

        auction = new Auction();
        auction.setSenderid(BasicUtils.getUserName());
        selectedAuctionType = 0;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public Auction getAuction() {
        return auction;
    }

    public List<SelectItem> getAvailableAuctionTypes() {
        return availableAuctionTypes;
    }

    public void setSelectedAuctionType(int selectedType) {
        this.selectedAuctionType = selectedType;
        setAuction(AuctionFactory.getInstance(selectedType));
    }

    public int getSelectedAuctionType() {

        return selectedAuctionType;
    }


    public String sendAuction() {
        getAuction().setTradeOffer(getTradeOffer());
        getAuction().setSenderid(BasicUtils.getUserName());

        String result = null;

        if(validate()) {
            OffersSuperviser.getAuctionMonitor().addAuction(getAuction());
            
            FacesContext facesContext = FacesContext.getCurrentInstance();

            FacesContext.getCurrentInstance().addMessage(
                    BasicUtils.findComponent(facesContext.getViewRoot(),
                    "sendAuctionOfferTrade").getClientId(facesContext),
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Sent trade",
                    "Auction sent sucessfully"));

             getTradeOffer().cleanTradeOffer();

            result = "success";
        }

        return result;
        
    }

    public boolean validate() {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        boolean error = true;

        if (getExpireDate().before(new Date())) {
            System.out.println("Adding new message for expire date...");
            FacesContext.getCurrentInstance().addMessage(
                    BasicUtils.findComponent(facesContext.getViewRoot(),
                    "expireAuctionOfferDate").getClientId(facesContext),
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Expire date error",
                    "Expiry date earlier than the current time"));

            error = false;
        }

        if(getAmountOffered() <= 0) {
            FacesContext.getCurrentInstance().addMessage(
                    BasicUtils.findComponent(facesContext.getViewRoot(),
                    "offeredAuctionAmount").getClientId(facesContext),
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Offered Amount Limi",
                    "Amount has to be greater than 0"));

            error = false;
        }

        if(getResourcesOfferedType() == getResourcesWantedType()) {
            FacesContext.getCurrentInstance().addMessage(
                    BasicUtils.findComponent(facesContext.getViewRoot(),
                    "sendAuctionOfferTrade").getClientId(facesContext),
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Resources type",
                    "Wanted and offered have the same type"));

            error = false;
        }




        return error;
    }

}
