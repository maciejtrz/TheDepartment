/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package messageSystem;

import ConnectionDataBase.Messagesystem;
import Connections.UserManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class AuctionMessageReader extends MessageWriter implements Serializable  {

    private boolean checked;
    private List<Auction> auctionOffers = new ArrayList<Auction>();

    /** Creates a new instance of AuctionMessageReader */
    public AuctionMessageReader() {
        super(MessageSingleton.AUCTION_BOARD,MessageSingleton.AUCTION_OFFER);
        checked = false;
    }

    public List<Auction> getAuctionOffers() {
      
        if (!checked || UserManager.hasNewMessage(getUsername())) {
            checked = true;
            auctionOffers = new ArrayList<Auction>();

            List<Messagesystem> encodedTrades = getMessages();
            Iterator<Messagesystem> iterator = encodedTrades.iterator();

            while (iterator.hasNext()) {
                Messagesystem message = iterator.next();

                Auction auction = new Auction();

                auction.parse(message.getMsg());
                auction.setSenderid(message.getSenderid());
                auction.setCreationtime(message.getCreationtime());
                auction.setMsgnumber(message.getMsgnumber());
                auction.setSubject(message.getSubject());

                auctionOffers.add(auction);
            }

        }

        return auctionOffers;
    }

    public List<AuctionOffer> getOffersHistory(Auction auction) {
        List<AuctionOffer> offersHistory = new ArrayList<AuctionOffer>();

        return offersHistory;
    }

    public void setSendOffer(Auction offer) {

        System.out.println("Auction title: " + offer.getSubject());
        System.out.println("Bid offer: " + offer.getOffer());

    }

}
