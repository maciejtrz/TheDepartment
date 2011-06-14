/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package messageSystem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import utilities.BasicUtils;

/**
 *
 * @author kp1209
 */
public class Auction extends TradeOffer {

    private int highestOfferedPrice;
    private String winner;

    private List<AuctionOffer> auctionOffersHistory;
    private boolean keepHistory;

    private Set<String> bidders;
    boolean canBidMoreThanOnce;

    private int auctionType;

    /** Creates a new instance of AuctionMessageWriter */
    public Auction(int auctionType, boolean keepHistory, boolean canBidMoreThanOnce) {

        this.auctionType = auctionType;
        highestOfferedPrice = 0;
        this.keepHistory = keepHistory;
        if(keepHistory) {
            auctionOffersHistory = new ArrayList<AuctionOffer>();
        }

        this.canBidMoreThanOnce = canBidMoreThanOnce;

        if(!canBidMoreThanOnce) {
            bidders = new HashSet<String>();
        }

    }

    public Auction() {
    }

    public String getTypeName() {
        return "null";
    }

    public int getAuctionType() {
        return auctionType;
    }

    public void setAuctionType(int auctionType) {
        this.auctionType = auctionType;
    }

    private boolean keepHistory() {
        return keepHistory;
    }

    private boolean canBidMoreThanOnce() {
        return canBidMoreThanOnce;
    }

    private boolean hasAlreadyBidded(String username) {
        return bidders.contains(username);
    }

    public boolean setHighestOfferedPrice(String price) {
        return setHighestOfferedPrice(Integer.parseInt(price));
    }

    public boolean setHighestOfferedPrice(int price) {

        if(!canBidMoreThanOnce() && hasAlreadyBidded(BasicUtils.getUserName())) {
            return false;
        }

        if(getHighestOfferedPrice() < price) {
            highestOfferedPrice = price;
            winner = BasicUtils.getUserName();

            if(keepHistory()) {
                getAuctionOffersHistory().add(new AuctionOffer(getWinner(), getHighestOfferedPrice()));
            }

            return true;
        }

        return false;
    }

    public int getHighestOfferedPrice() {
        return highestOfferedPrice;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public List<AuctionOffer> getAuctionOffersHistory() {
        return auctionOffersHistory;
    }


    @Override
    public String encode() {

        return getAuctionType() + " " +
               getResourcesOfferedType() + "=" + getAmountOffered() + " " +
               getResourcesWantedType() + " " +
               getExpireDate().getTime() + "\n" +
              (getTradeDescription() == null || getTradeDescription().isEmpty()
                        ? "" : getTradeDescription());
        
    }

    @Override
    public void parse(String encodedMessage) {
        ParsingPosition parsingPosition = new ParsingPosition(encodedMessage);

        setAuctionType(getNumber(parsingPosition));
        setResourcesOfferedType(getNumber(parsingPosition));
        setAmountOffered(getNumber(parsingPosition));
        setResourcesWantedType(getNumber(parsingPosition));
        setExpireDate(getLongNumber(parsingPosition));
        setTradeDescription(getTradeDesrciptionText(parsingPosition));

    }

    void setTradeOffer(TradeOffer tradeOffer) {

        setSubject(tradeOffer.getSubject());
        setReceiverid(tradeOffer.getReceiverid());
        setSenderid(tradeOffer.getSenderid());
        setAmountOffered(tradeOffer.getAmountOffered());
        setAmountWanted(tradeOffer.getAmountWanted());
        setResourcesOfferedType(tradeOffer.getResourcesOfferedType());
        setResourcesWantedType(tradeOffer.getResourcesWantedType());
        
    }


}
