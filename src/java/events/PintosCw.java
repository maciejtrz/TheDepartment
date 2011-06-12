package events;

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
        return "PintosCw";
    }


    @Override
    public boolean isEqual(String name) {
        return (name.equals(LotteryManager.PINTOS_CW));
    }
}
