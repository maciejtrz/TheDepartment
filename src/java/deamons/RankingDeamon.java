/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package deamons;

import java.util.logging.Level;
import java.util.logging.Logger;
import rankings.MoneyRankingSingleton;
import rankings.PhDsRankingSingleton;
import rankings.ResearchPointsRankingSingleton;
import rankings.StudentsRankingSingleton;

/**
 *
 * @author pk2109
 */
public class RankingDeamon implements Runnable {

    public RankingDeamon() {
    //    setDaemon(true);

    }

    public void run(){
        while(true){
    System.out.println("I'm producing important stuff");
    MoneyRankingSingleton.produceRanking();
    ResearchPointsRankingSingleton.produceRanking();
    PhDsRankingSingleton.produceRanking();
    StudentsRankingSingleton.produceRanking();
    System.out.println("Still alive...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(RankingDeamon.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
}
   


}
