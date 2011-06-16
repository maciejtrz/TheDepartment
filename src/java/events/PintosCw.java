package events;

import ConnectionDataBase.EventsHelper;
import Connections.UserManager;

public class PintosCw extends Event {

    @Override
    public boolean invoke(String playerName) {

        // Decreasing students satisfaction.
        decreaseSatisfaction(playerName, LotteryManager.MEGA);

        // Decreasing students population.
        UserManager.removeUndegraduatesnumber(playerName, LotteryManager.MEGA);
        UserManager.removePhdsnumber(playerName, LotteryManager.MEGA);

        return true;
    }

    @Override
    public String getInfo() {
        return "The newspapers calls it  “a disaster”, “a personal tragedy of " +
                "many” , “a catastrophe”. The pintos course work has been " +
                "released. All of your students lose whatever remained of " +
                "their life. The population of students and Ph.D. decreases. " +
                "Their satisfaction also decreases. Obviously, their alcoholism " +
                "increases though. ";
    }


    @Override
    public boolean isEqual(String name) {
        return (name.equals(LotteryManager.PINTOS_CW));
    }

    @Override
    public String getName() {
        return ("Pintos course work released!");
    }

    @Override
    public void writeToDb(String playerName) {
        EventsHelper eventHelper
                = new EventsHelper();
        eventHelper.setPintosCw(playerName, getNumOfTickets());
    }
}
