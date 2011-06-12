package events;

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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isEqual(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getName() {
        return ("Private investor grant received!");
    }

}
