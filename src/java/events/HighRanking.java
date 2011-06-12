/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package events;

import ConnectionDataBase.Capacity;
import ConnectionDataBase.CapacityHelper;
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isEqual(String name) {
        return name.equals(LotteryManager.HIGH_RANKING);
    }

}
