/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package events;

import ConnectionDataBase.CapacityHelper;
import ConnectionDataBase.EventsHelper;
import utilities.LecturersManager;

/**
 *
 * @author kwh109
 */
public class HighRanking extends Event {

    @Override
    public boolean invoke(String playerName) {

        // No prerequirements.

        // Update lecturers boost.
        LecturersManager mgr = new LecturersManager(playerName);
        if (!mgr.updateLecturersBoost(2)) {
            return false;
        }

        // Updating phD satisfaction.
        if (!increaseSatisfaction(playerName, LotteryManager.LOW)) {
            return false;
        }

        // Increasing students and phds capacity.
        CapacityHelper capacityHelper = new CapacityHelper();
        capacityHelper.updateStudentsCapacity(playerName, LotteryManager.HIGH);
        capacityHelper.updatePhDsCapacity(playerName, LotteryManager.MEDIUM);
        capacityHelper.updateProfessorsCapacity(playerName, LotteryManager.LOW);
        
        return true;
    }

    @Override
    public String getInfo() {
        return "Good job. Some funny newspaper places your department in top " +
                "ten computing departments in the country! All of you lecturers " +
                "receive a +1 boost to their specializations. Unfortunately, " +
                "your students do not believe in rankings any more. After all, " +
                "they already have been tricked once...";
    }

    @Override
    public boolean isEqual(String name) {
        return name.equals(LotteryManager.HIGH_RANKING);
    }

    @Override
    public String getName() {
        return ("High university ranking.");
    }

    @Override
    public void writeToDb(String playerName) {
        EventsHelper eventHelper
                = new EventsHelper();
        eventHelper.setHighRanking(playerName, getNumOfTickets());
    }
}
