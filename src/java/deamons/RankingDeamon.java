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

    private static final int SLEEP_TIME = 1;

    public RankingDeamon() {
    }

    public void run() {
        boolean stopped = false;
        System.out.println("Starting ranking deamon!");
        while (!stopped) {

            try {
                Thread.sleep(SLEEP_TIME * 41 * 1000);

            System.out.println("Ranking daemon woke up!");
            MoneyRankingSingleton.produceRanking();
            ResearchPointsRankingSingleton.produceRanking();
            PhDsRankingSingleton.produceRanking();
            StudentsRankingSingleton.produceRanking();
            }

            catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("Ranking daemon caught exception!");
                stopped = true;
            }
        }
    }
}
