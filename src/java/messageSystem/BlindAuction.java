package messageSystem;

import java.util.HashSet;
import java.util.Set;
import utilities.BasicUtils;

public class BlindAuction extends AuctionMessageWriter {

    private Set<String> bidders = new HashSet<String>();


    @Override
    public void setHighestOfferedPrice(String price) {
        bidders.add(BasicUtils.getUserName());
        super.setHighestOfferedPrice(price);
    }
}
