/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package messageSystem;

import ConnectionDataBase.MessageSystemHelper;
import ConnectionDataBase.Messagesystem;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;

/**
 *
 * @author root
 */
public class AuctionMonitor {

    private PriorityQueue<Auction> currentAuctions = new PriorityQueue<Auction>();
    private List<Auction> listAuction = new ArrayList<Auction>();
    private MessageSystemHelper messageSystemHelper = new MessageSystemHelper();

    public AuctionMonitor() {
        List<Messagesystem> auctionsDb =
                messageSystemHelper.getMessages(MessageSingleton.AUCTION_BOARD, MessageSingleton.AUCTION_OFFER);

     //   Iterator<Messagesystem>
    }

    public synchronized void addAuction(Auction auction) {
        currentAuctions.add(auction);
        listAuction.add(auction);

        messageSystemHelper.createMessage(auction.getSenderid(),MessageSingleton.AUCTION_BOARD,auction.getSubject(),
            auction.encode(),MessageSingleton.AUCTION_OFFER);

    }

    public synchronized void update() {
        Date currentDate = new Date();

        while(!currentAuctions.isEmpty()) {
            Auction auction = currentAuctions.peek();
            if(auction.getExpireDate().compareTo(currentDate) >= 0) {
                currentAuctions.poll();
                listAuction.remove(auction);
                auction.finishAuction();
            } else {
                break;
            }
        }
    }
    
    public synchronized List<Auction> getAuctionList() {
        return listAuction;
    }

    public boolean placeOffer(Auction auction, int offer) {
        auction.setOffer(offer);
        return true;
    }




}
