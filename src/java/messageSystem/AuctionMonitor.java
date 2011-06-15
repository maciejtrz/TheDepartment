/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package messageSystem;

import ConnectionDataBase.MessageSystemHelper;
import ConnectionDataBase.Messagesystem;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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

       Iterator<Messagesystem> iterator = auctionsDb.iterator();

       while(iterator.hasNext()) {
           Messagesystem auctionOffer = iterator.next();

           Auction auction = new Auction();
           auction.parse(auctionOffer.getMsg());
           auction.setSenderid(auctionOffer.getSenderid());
           auction.setSubject(auctionOffer.getSubject());
           auction.setCreationtime(auctionOffer.getCreationtime());

           currentAuctions.add(auction);
           listAuction.add(auction);

       }
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

                //auction.finishAuction();
            } else {
                return;
            }
        }
    }
    
    public synchronized List<Auction> getAuctionList() {
        return listAuction;
    }

    public boolean placeOffer(Auction auction, int offer) {
        auction.setHighestOfferedPrice(offer);
        return true;
    }

}