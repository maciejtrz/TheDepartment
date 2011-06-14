package messageSystem;

/**
 *
 * IMPORTANT:
 * It is a stud class - CLASS FACTORY would be implemented
 *
 */
public class AuctionFactory {

    private int auctionNumber = 3;

    public final static int HIGHEST_PRICE_AUCTION = 0;
    public final static int BLIND_AUCTION = 1;
    public final static int VIKREY_AUCTION = 2;

    public Auction getInstance(int auctionType) {
        switch(auctionType) {
            case HIGHEST_PRICE_AUCTION:
                return new HighestPriceAuction();
            case BLIND_AUCTION:
                return new BlindAuction();
            case VIKREY_AUCTION:
                return new VikreyAuction();
            default:
                return null;
        }
    }

    public int getSize() {
        return auctionNumber;
    }

    public String getAuctionName(int auctionType) {
        return getInstance(auctionType).getTypeName();
    }

}
