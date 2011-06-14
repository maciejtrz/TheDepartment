/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package messageSystem;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class AuctionSuperviser implements Runnable{

    private static final long SLEEPING_INTERVAL = 10000;
    private static AuctionMonitor auctionMonitor;

    static {
        auctionMonitor = new AuctionMonitor();
    }

    public static AuctionMonitor getAuctionMonitor() {
        return auctionMonitor;
    }

    public void run() {
        while(true) {
            try {
                Thread.sleep(SLEEPING_INTERVAL);
            } catch (InterruptedException ex) {
                Logger.getLogger(AuctionSuperviser.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                getAuctionMonitor().update();
            }
        }

    }

}
