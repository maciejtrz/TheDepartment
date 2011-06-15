package messageSystem;

import ConnectionDataBase.Auctionhistory;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class AuctionMessageReader extends MessageWriter implements Serializable  {

    private boolean checked;
    private List<Auction> auctionOffers = new ArrayList<Auction>();

    /** Creates a new instance of AuctionMessageReader */
    public AuctionMessageReader() {
        super(MessageSingleton.AUCTION_BOARD,MessageSingleton.AUCTION_OFFER);
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

        System.out.println("Auction title: " + auction.getSubject());
        System.out.println("Bid offer: " + auction.getOffer());

        OffersSuperviser.getAuctionMonitor().placeOffer(auction, auction.getOffer());

    }

}
