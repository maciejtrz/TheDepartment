/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package events;

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
        return (increaseSatisfaction(playerName, LotteryManager.LOW));
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
