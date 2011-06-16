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
        return ("A disaster. Malice course work was release yesterday. You now " +
                "see you undergraduates students outside the labs only on very " +
                "special occasions. Even then, they spend most of their time " +
                "crying quietly in some corner. The population of your students " +
                "decreases. Also the population of Ph.D. decreases, as they are " +
                "busy marking the damned thing (or rather pretending that they " +
                "are marking).");
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
