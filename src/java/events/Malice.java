package events;

import ConnectionDataBase.EventsHelper;
import Connections.UserManager;

public class Malice extends Event {

    @Override
    public boolean invoke(String playerName) {

        // No prerequirements.

        // Decreasing students satisfaction.
        decreaseSatisfaction(playerName, LotteryManager.MEGA);

        // Decreasing students population.
        UserManager.removeUndegraduatesnumber(playerName, LotteryManager.MEGA);
        UserManager.removePhdsnumber(playerName, LotteryManager.MEDIUM);

        return true;
    }

    @Override
    public String getInfo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isEqual(String name) {
        return name.equals(LotteryManager.MALICE);
    }

    @Override
    public String getName() {
        return ("Malice Course work released!");
    }

    @Override
    public void writeToDb(String playerName) {
        EventsHelper eventHelper
                = new EventsHelper();
        eventHelper.setMalice(playerName, getNumOfTickets());
    }

}
