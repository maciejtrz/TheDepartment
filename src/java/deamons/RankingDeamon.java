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

    private static final int SLEEP_TIME = 2;

    public RankingDeamon() {
        //    setDaemon(true);
    }

    public void run() {
        while (true) {

            MoneyRankingSingleton.produceRanking();
            ResearchPointsRankingSingleton.produceRanking();
            PhDsRankingSingleton.produceRanking();
            StudentsRankingSingleton.produceRanking();

            try {
                Thread.sleep(SLEEP_TIME * 60 * 1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(RankingDeamon.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
