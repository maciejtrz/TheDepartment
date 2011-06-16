package messageSystem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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


    public void sendAuction() {
        getAuction().setTradeOffer(getTradeOffer());
        getAuction().setSenderid(BasicUtils.getUserName());
        
        OffersSuperviser.getAuctionMonitor().addAuction(getAuction());
    }

}
