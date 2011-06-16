package events;

import ConnectionDataBase.CapacityHelper;
import ConnectionDataBase.EventsHelper;
import Connections.UserManager;

public class PhdPromotion extends Event {

    @Override
    public boolean invoke(String playerName) {

        // Removing students, if not enough available return false.
        if (!UserManager.removeUndegraduatesnumber(playerName,
                LotteryManager.MEGA)) {
            return false;
        }

        // Increasing the PhD population.
        UserManager.addPhdsnumber(playerName, LotteryManager.MEGA);

        return true;

    }

    @Override
    public String getInfo() {
        return("Some of your undergrads decided to do a Ph.D. in your " +
                "department. Obviously, the number of your undergrads " +
                "decreases and the number of Ph.D. increases.");
    }

    @Override
    public boolean isEqual(String name) {
        return (name.equals(LotteryManager.PHD_PROMOTION));
    }

    @Override
    public String getName() {
        return ("PhD promotion!");
    }

    @Override
    public void writeToDb(String playerName) {
        EventsHelper eventHelper
                = new EventsHelper();
        eventHelper.setPhdPromotion(playerName, getNumOfTickets());
    }
}
