/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package messageSystem;

import utilities.BasicUtils;

/**
 *
 * @author root
 */
public abstract class AuctionMessageWriter extends TradeWriter {

    private int highestOfferedPrice;
    private String winner;

    /** Creates a new instance of AuctionMessageWriter */
    public AuctionMessageWriter() {
        super(MessageSingleton.AUCTION_OFFER);
        highestOfferedPrice = 0;
    }

        public void setHighestOfferedPrice(String price) {
        setHighestOfferedPrice(Integer.parseInt(price));
    }

    public void setHighestOfferedPrice(int price) {
        if(getHighestOfferedPrice() < price) {
            highestOfferedPrice = price;
            winner = BasicUtils.getUserName();
        }
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

}
