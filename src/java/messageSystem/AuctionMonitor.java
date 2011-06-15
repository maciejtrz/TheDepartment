/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package messageSystem;

import ConnectionDataBase.MessageSystemHelper;
import ConnectionDataBase.Messagesystem;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

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

        Set<Auction> auctionsToRemove = new HashSet<Auction>();
        Date currentDate = new Date();

        Iterator<Messagesystem> iterator = auctionsDb.iterator();

        while (iterator.hasNext()) {
            Messagesystem auctionOffer = iterator.next();

            Auction auction = new Auction();
            auction.parse(auctionOffer.getMsg());
            auction.setMsgnumber(auctionOffer.getMsgnumber());

            if (auction.getExpireDate().compareTo(currentDate) <= 0) {
                auctionsToRemove.add(auction);
            } else {
                auction.setSenderid(auctionOffer.getSenderid());
                auction.setSubject(auctionOffer.getSubject());
                auction.setCreationtime(auctionOffer.getCreationtime());

                currentAuctions.add(auction);
                listAuction.add(auction);
            }
        }

        Iterator<Auction> auctionIterator = auctionsToRemove.iterator();
        while (iterator.hasNext()) {
            Auction auction = auctionIterator.next();
            messageSystemHelper.deleteMsg(auction.getMsgnumber());

            //auction.finishAuction();
        }
    }

    public synchronized void addAuction(Auction auction) {
        currentAuctions.add(auction);
        listAuction.add(auction);

        messageSystemHelper.createMessage(auction.getSenderid(), MessageSingleton.AUCTION_BOARD, auction.getSubject(),
                auction.encode(), MessageSingleton.AUCTION_OFFER);
    }

    public synchronized void update() {
        Date currentDate = new Date();

        while (!currentAuctions.isEmpty()) {
            Auction auction = currentAuctions.peek();
            if (auction.getExpireDate().compareTo(currentDate) <= 0) {
                currentAuctions.remove(auction);
                listAuction.remove(auction);

                System.out.println("Removing auction...");

                messageSystemHelper.deleteMsg(auction.getMsgnumber());

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
