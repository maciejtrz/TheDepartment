package messageSystem;

public class BlindAuction extends Auction {

    private static final String typeName = "Blind Auction";

    public BlindAuction() {
        super(AuctionFactory.BLIND_AUCTION,true, false);
    }

    @Override
    public String getTypeName() {
       return typeName;
    }

}
