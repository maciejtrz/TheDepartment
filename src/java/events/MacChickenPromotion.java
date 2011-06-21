package events;

import ConnectionDataBase.Buildings;
import ConnectionDataBase.BuildingsHelper;
import ConnectionDataBase.EventsHelper;
import buildings.Building;

public class MacChickenPromotion extends Event {

    @Override
    public boolean invoke(String playerName) {

        // Checking whether macChicken is built
        BuildingsHelper builidingHelper = new BuildingsHelper();
        Buildings building_record = builidingHelper.getBuildings(playerName);
        if (building_record == null) {
            // This should not happen.
            System.err.println("Inconsistency in a database when trying to" +
                    " access buildings record of player " + playerName);
            return false;
        }
        int mac_chicken_lvl = building_record.getMacchicken();
        if (mac_chicken_lvl == Building.NOT_BUILT_LEVEL) {
            return false;
        }

        // Decreasing starvation
        return decreaseStarvation(playerName, LotteryManager.HIGH);
    }

    @Override
    public String getInfo() {
        return "Lucky you. Your local MacChicken managed to produce a chicken " +
                "with more than two legs. The food there became even cheaper, " +
                "so the starvation level of you students decreases.";
    }

    @Override
    public boolean isEqual(String name) {
        return name.equals(LotteryManager.MAC_PROMOTION);
    }

    @Override
    public String getName() {
        return ("MacChicken Promotion!");
    }

    @Override
    public void writeToDb(String playerName) {
        EventsHelper eventHelper
                = new EventsHelper();
        eventHelper.setMacChickenPromotion(playerName, getNumOfTickets());
    }
}
