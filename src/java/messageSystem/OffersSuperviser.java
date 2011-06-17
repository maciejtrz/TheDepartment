/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package messageSystem;
/**
 *
 * @author root
 */
public class OffersSuperviser implements Runnable{

    private static final long SLEEPING_INTERVAL = 10000;
    private static AuctionMonitor auctionMonitor = new AuctionMonitor();
    private static NoticeMonitor noticeMonitor;

    public static AuctionMonitor getAuctionMonitor() {
        return auctionMonitor;
    }

    public static NoticeMonitor getNoticeMonitor() {
        return noticeMonitor;
    }

    public static void initializeOffersSupervise() {       
        Thread thread = new Thread(new OffersSuperviser());
        thread.start();
    }

    public void run() {

        noticeMonitor = new NoticeMonitor();
        auctionMonitor = new AuctionMonitor();

        while(true) {

            try {
                Thread.sleep(SLEEPING_INTERVAL);
                if(auctionMonitor != null)
                    auctionMonitor.update();
                
                if(noticeMonitor != null)
                    noticeMonitor.update();
            } catch (InterruptedException ex) {
               
            } finally {
                
            }
            
        }
    }

}
