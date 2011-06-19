package messageSystem;

import ConnectionDataBase.Auctionhistory;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import utilities.BasicUtils;

public class AuctionMessageReader extends MessageWriter implements Serializable {

    private boolean checked;
    private List<Auction> auctionOffers = new ArrayList<Auction>();
    private Auction selectedAuction;

    /** Creates a new instance of AuctionMessageReader */
    public AuctionMessageReader() {
        super(MessageSingleton.AUCTION_BOARD, MessageSingleton.AUCTION_OFFER);
        checked = false;
    }

    public List<Auction> getAuctionOffers() {
        return OffersSuperviser.getAuctionMonitor().getAuctionList();
    }

    public List<Auctionhistory> getOffersHistory(Auction auction) {
        List<Auctionhistory> offersHistory = new ArrayList<Auctionhistory>();

        return offersHistory;
    }

    public void setSendOffer(Auction auction) {

        FacesContext facesContext = FacesContext.getCurrentInstance();

        if (OffersSuperviser.getAuctionMonitor().placeOffer(auction,auction.getOffer())) {

            FacesContext.getCurrentInstance().addMessage(
                    BasicUtils.findComponent(facesContext.getViewRoot(),
                    "bidOfferGrid").getClientId(facesContext),
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Sent trade",
                    "Successful bid, amount: " + auction.getOffer()));

        } else {

            if (!facesContext.getMessageList(BasicUtils.findComponent(facesContext.getViewRoot(),
                    "bidOfferGrid").getClientId(facesContext)).isEmpty()) {

                FacesContext.getCurrentInstance().addMessage(
                        BasicUtils.findComponent(facesContext.getViewRoot(),
                        "bidOfferGrid").getClientId(facesContext),
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sent trade",
                        "Unsuccessful bid, amount: " + auction.getOffer()));

            }
        }

    }

    public void setSelectedAuction(Auction auction){

        this.selectedAuction = auction;
    }

    public Auction getSelectedAuction() {
        if(selectedAuction == null)
            return new Auction();
        return selectedAuction;
    }
}
