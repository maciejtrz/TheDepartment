package messageSystem;

import java.io.Serializable;

public class BlindAuction extends Auction implements Serializable {

    private static final String typeName = "Blind Auction";

    public BlindAuction() {
        super(AuctionFactory.BLIND_AUCTION,true, false);
    }

    @Override
    public String getTypeName() {
       return typeName;
    }

}
