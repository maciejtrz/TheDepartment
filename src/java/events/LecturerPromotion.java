package events;

import ConnectionDataBase.EventsHelper;
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
        return ("Congratulations! One of your Ph.D. students  just became a " +
                "lecturer and a fully qualified researcher. Actually, " +
                "he now prefers to be called “professor”.  Poor guy.   ");
    }

    @Override
    public boolean isEqual(String name) {
        return name.equals(LotteryManager.LECTURER_PROMOTION);
    }

    @Override
    public String getName() {
        return ("Lecturer Promotion!");
    }

    @Override
    public void writeToDb(String playerName) {
        EventsHelper eventHelper
                = new EventsHelper();
        eventHelper.setLecturerPromotion(playerName, getNumOfTickets());
    }
}
