package events;

import ConnectionDataBase.EventsHelper;
import utilities.LecturersManager;

public class NobelPrice extends Event {

    @Override
    public boolean invoke(String playerName) {

        // No prerequirements.

        // Increase the boost of all researchers.
        LecturersManager mgr = new LecturersManager(playerName);
        mgr.updateLecturersBoost(LotteryManager.MEDIUM);

        // Increase players satisfaction.
        return (increaseSatisfaction(playerName, LotteryManager.MEGA));
    }

    @Override
    public String getInfo() {
        return ("Impossible! You were awarded a noble price! The satisfaction of " +
                "all your students increases dramatically Moreover, you get a "
                + "1000 free research points. Finally, the performance" +
                " of your lecturers increases!");
    }

    @Override
    public boolean isEqual(String name) {
        return (name.equals(LotteryManager.NOBEL_PRICE));
    }

    @Override
    public String getName() {
        return ("Nobel price award won!");
    }

    @Override
    public void writeToDb(String playerName) {
        EventsHelper eventHelper
                = new EventsHelper();
        eventHelper.setNobelPrice(playerName, getNumOfTickets());
    }
}
