package messageSystem;

/**
 *
 * IMPORTANT:
 * It is a stud class - CLASS FACTORY would be implemented
 *
 */
public class AuctionFactory {

    private static int auctionNumber = 2;

    public final static int HIGHEST_PRICE_AUCTION = 0;
    public final static int BLIND_AUCTION = 1;

    public static Auction getInstance(int auctionType) {
        switch(auctionType) {
            case HIGHEST_PRICE_AUCTION:
                return new HighestPriceAuction();
            case BLIND_AUCTION:
                return new BlindAuction();
            default:
                return null;
        }
    }

    public static int getSize() {
        return auctionNumber;
    }

    public static String getAuctionName(int auctionType) {
        return getInstance(auctionType).getTypeName();
    }

}
