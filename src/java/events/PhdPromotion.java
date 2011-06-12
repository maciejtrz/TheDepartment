package events;

import ConnectionDataBase.CapacityHelper;
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
        return("Phd promotion");
    }

    @Override
    public boolean isEqual(String name) {
        return (name.equals(LotteryManager.PHD_PROMOTION));
    }

    @Override
    public String getName() {
        return ("PhD promotion!");
    }
}
