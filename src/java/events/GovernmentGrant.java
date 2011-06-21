package events;

import ConnectionDataBase.EventsHelper;
import Connections.UserManager;
import java.util.Random;

public class GovernmentGrant extends Event {

    @Override
    public boolean invoke(String playerName) {

        // Checking whether player has more than 1000 research points.
        // TODO

        // Adding money
        Random rand = new Random();
        int grant = rand.nextInt(5) + 1;
        UserManager.addMoney(playerName, grant * 500);

        return true;
    }

    @Override
    public String getInfo() {
        return "Congrats! Thanks to your excellence in research (and probably " +
                "the fact that your distant cousin works in the Ministry of " +
                "Education) you received a government grant. ";
    }

    @Override
    public boolean isEqual(String name) {
        return (name.equals(LotteryManager.GOVERNMENT_GRANT));
    }

    @Override
    public String getName() {
        return ("Government Research Grant Received!");
    }

    @Override
    public void writeToDb(String playerName) {
        EventsHelper eventHelper
                = new EventsHelper();
        eventHelper.setGovernmentGrant(playerName, getNumOfTickets());
    }
}
