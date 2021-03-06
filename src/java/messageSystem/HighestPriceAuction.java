package messageSystem;

import java.io.Serializable;


public class HighestPriceAuction extends Auction implements Serializable {

    private static final String typeName = "Highest Price Auction";

    public HighestPriceAuction() {
        super(AuctionFactory.HIGHEST_PRICE_AUCTION,true, true);
    }

    @Override
    public String getTypeName() {
       return typeName;
    }

}
