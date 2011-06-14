package messageSystem;


public class HighestPriceAuction extends Auction {

    private static final String typeName = "Highest Price Auction";

    public HighestPriceAuction() {
        super(AuctionFactory.HIGHEST_PRICE_AUCTION,true, true);
    }

    @Override
    public String getTypeName() {
       return typeName;
    }
}
