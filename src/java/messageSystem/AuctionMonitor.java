/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package messageSystem;

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

    public synchronized void addAuction(Auction auction) {
        currentAuctions.add(auction);
        listAuction.add(auction);
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

    private synchronized List<Auction> getAuctionList() {
        return listAuction;
    }



}
