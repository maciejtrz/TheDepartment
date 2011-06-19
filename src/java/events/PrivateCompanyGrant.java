package events;

import ConnectionDataBase.EventsHelper;
import Connections.UserManager;
import java.util.Random;

public class PrivateCompanyGrant extends Event {

    @Override
    public boolean invoke(String playerName) {

        // NO prerequirements.

        // Updating money
        Random rand = new Random();
        int value = rand.nextInt(10);
        UserManager.addMoney(playerName, value * 1000);

        return true;
    }

    @Override
    public String getInfo() {
        return ("A private investor decided to give you a research grant. " +
                "The gullible fellow thinks that you can produce some useful " +
                "research for him! Anyway, your wealth increases.");
    }

    @Override
    public boolean isEqual(String name) {
        return (name.equals(LotteryManager.PRIVATE_INVESTOR_GRANT));
    }

    @Override
    public String getName() {
        return ("Private investor grant received!");
    }

    @Override
    public void writeToDb(String playerName) {
        EventsHelper eventHelper
                = new EventsHelper();
        eventHelper.setPrivateCompanyGrant(playerName, getNumOfTickets());
    }
}
