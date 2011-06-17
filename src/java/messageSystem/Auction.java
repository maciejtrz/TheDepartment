/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package messageSystem;

import ConnectionDataBase.Auctionhistory;
import ConnectionDataBase.AuctionhistoryHelper;
import ConnectionDataBase.AuctionhistoryId;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import utilities.BasicUtils;

/**
 *
 * @author kp1209
 */
public class Auction extends TradeOffer implements Serializable {

    private int highestOfferedPrice;
    private String winner;
    private List<Auctionhistory> auctionOffersHistory;
    private boolean keepHistory;
    private Set<String> bidders;
    private boolean canBidMoreThanOnce;
    private int auctionType;
    private int offer;
    private AuctionhistoryHelper auctionHistoryHelper;

    /** Creates a new instance of AuctionMessageWriter */
    public Auction(int auctionType, boolean keepHistory, boolean canBidMoreThanOnce) {

        this.auctionType = auctionType;
        highestOfferedPrice = 0;
        this.keepHistory = keepHistory;

        auctionHistoryHelper = new AuctionhistoryHelper();
        auctionOffersHistory = new ArrayList<Auctionhistory>();

        this.canBidMoreThanOnce = canBidMoreThanOnce;

        bidders = new HashSet<String>();
    }

    public Auction() {
        auctionType = 0;
        highestOfferedPrice = 0;
        keepHistory = true;
        canBidMoreThanOnce = true;

        auctionHistoryHelper = new AuctionhistoryHelper();
        auctionOffersHistory = new ArrayList<Auctionhistory>();

        bidders = new HashSet<String>();
    }

    public int getWinningOffer() {
        return 0;
    }

    public int getOffer() {
        return offer;
    }

    public void setOffer(int offer) {
        this.offer = offer;
    }

    public void setOffer(String offer) {
        System.out.println("The offer is: " + offer);
        this.offer = Integer.parseInt(offer);
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
        return setHighestOffer(Integer.parseInt(price));
    }

    public boolean setHighestOffer(int price) {

        String bidder = BasicUtils.getUserName();

        price = getOffer();

        boolean result = false;

        System.out.println("Winner: " + getSenderid());
        System.out.println("Sender: " + bidder);

        if (winner != null && winner.equals(bidder)) {

            String errorMessage = null;

            if (!getTypeName().equals(AuctionFactory.getAuctionName(AuctionFactory.BLIND_AUCTION))) {

                errorMessage = "Your offer is the highest one so far!";

            } else {

                errorMessage = "Unsuccessful bid";

            }
            
                FacesContext facesContext = FacesContext.getCurrentInstance();

                FacesContext.getCurrentInstance().addMessage(
                        BasicUtils.findComponent(facesContext.getViewRoot(),
                        "bidOfferGrid").getClientId(facesContext),
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sent trade",
                        errorMessage));
            


            return false;
        }

        System.out.println("Offered price is " + price);

        if (bidder.equals(getSenderid())) {

            FacesContext facesContext = FacesContext.getCurrentInstance();

            FacesContext.getCurrentInstance().addMessage(
                    BasicUtils.findComponent(facesContext.getViewRoot(),
                    "bidOfferGrid").getClientId(facesContext),
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sent trade",
                    "You cannot bid your own auction!"));

            return false;
        }

        if (highestOfferedPrice < price) {

            highestOfferedPrice = price;
            winner = BasicUtils.getUserName();

            Auctionhistory auctionHistory = new Auctionhistory(
                    new AuctionhistoryId(getMsgnumber(), winner, price), (new Date()).getTime());

            auctionHistoryHelper.addAuctionOffer(auctionHistory);

            result = true;
        } else {
            
            String errorMessage;

            if (!getTypeName().equals(AuctionFactory.getAuctionName(AuctionFactory.BLIND_AUCTION))) {

                errorMessage = "Your offer is too low!";

            } else {

                errorMessage = "Unsuccessful bid";

            }

                FacesContext facesContext = FacesContext.getCurrentInstance();

                FacesContext.getCurrentInstance().addMessage(
                        BasicUtils.findComponent(facesContext.getViewRoot(),
                        "bidOfferGrid").getClientId(facesContext),
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sent trade",
                        errorMessage));

                result = false;
        }

        return result;
    }

    public int highestOffer() {
        return highestOfferedPrice;
    }

    public String getHighestOfferedPrice() {
        return highestOfferedPrice + "";
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public List<Auctionhistory> getAuctionOffersHistory() {
        return auctionOffersHistory;
    }

    @Override
    public String encode() {

        String query = getAuctionType() + " "
                + getResourcesOfferedType() + "=" + getAmountOffered() + " "
                + getResourcesWantedType() + " "
                + getExpireDate().getTime() + "\n"
                + (getTradeDescription() == null || getTradeDescription().isEmpty()
                ? "" : getTradeDescription());

        return query;

    }

    public Auction parseAuction(String encodedMessage) {
        ParsingPosition parsingPosition = new ParsingPosition(encodedMessage);
        int type = getNumber(parsingPosition);

        Auction auction = AuctionFactory.getInstance(type);

        auction.setAuctionType(type);
        auction.setResourcesOfferedType(getNumber(parsingPosition));
        auction.setAmountOffered(getNumber(parsingPosition));
        auction.setResourcesWantedType(getNumber(parsingPosition));
        auction.setExpireDate(getLongNumber(parsingPosition));
        auction.setTradeDescription(getTradeDesrciptionText(parsingPosition));

        return auction;
    }

    public void setTradeOffer(TradeOffer tradeOffer) {

        setSubject(tradeOffer.getSubject());
        setReceiverid(tradeOffer.getReceiverid());
        setSenderid(tradeOffer.getSenderid());
        setAmountOffered(tradeOffer.getAmountOffered());
        setAmountWanted(tradeOffer.getAmountWanted());
        setResourcesOfferedType(tradeOffer.getResourcesOfferedType());
        setResourcesWantedType(tradeOffer.getResourcesWantedType());
        setExpireDate(tradeOffer.getExpireDate());
        setTradeDescription(tradeOffer.getTradeDescription());

    }

    void finishAuction() {

        Iterator<Auctionhistory> iterator =
                auctionHistoryHelper.getAuctionOffers(getMsgnumber()).iterator();

        boolean auctionExecuted = false;

        while (iterator.hasNext() && !auctionExecuted) {
            Auctionhistory auctionOffer = iterator.next();

            setReceiverid(auctionOffer.getId().getBidder());
            setAmountWanted(auctionOffer.getId().getOffer());

            auctionExecuted = accept();
        }


        auctionHistoryHelper.deleteOffers(getMsgnumber());


    }

    public void setHighestPrice(int highestAuctionOffer) {
        this.highestOfferedPrice = highestAuctionOffer;
    }
}
