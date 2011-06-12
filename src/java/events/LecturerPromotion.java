package events;

import Connections.UserManager;
import utilities.LecturersManager;

public class LecturerPromotion extends Event {

    @Override
    public boolean invoke(String playerName) {

        // No prerequirements.

        // Removes one PhD provided he exists
        if (!UserManager.removePhdsnumber(playerName, 1)) {
            System.out.println("Not enough phd");
            return false;
        }

        // Generates one lecturers for the lucky player.
        LecturersManager mgr = new LecturersManager(playerName);
        mgr.generateOneOwnedLecturer();

        return true;
    }

    @Override
    public String getInfo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isEqual(String name) {
        return name.equals(LotteryManager.LECTURER_PROMOTION);
    }

}
