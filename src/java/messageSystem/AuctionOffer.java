package messageSystem;

import java.util.Date;


public class AuctionOffer implements Comparable {

    private String bidder;
    private Date offerDate;
    private int bidValue;

    public AuctionOffer(String bidder, int bidValue) {
        this.bidder = bidder;
        offerDate = new Date();
        this.bidValue = bidValue;
    }

    public int getBidValue() {
        return bidValue;
    }

    public String getBidder() {
        return bidder;
    }

    public long getOfferTime() {
        return offerDate.getTime();
    }

    public Date getOfferDate() {
        return offerDate;
    }

    public int compareTo(Object o) {
        AuctionOffer auctionOffer = (AuctionOffer) o;
        return getBidValue() - auctionOffer.getBidValue();
    }

    @Override
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    public boolean equals(Object o) {
        AuctionOffer auctionOffer = (AuctionOffer) o;
        return auctionOffer.getBidValue() == getBidValue();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (this.bidder != null ? this.bidder.hashCode() : 0);
        hash = 29 * hash + (this.offerDate != null ? this.offerDate.hashCode() : 0);
        hash = 29 * hash + this.bidValue;
        return hash;
    }

}
