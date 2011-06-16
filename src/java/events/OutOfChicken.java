package events;

import ConnectionDataBase.Buildings;
import ConnectionDataBase.BuildingsHelper;
import ConnectionDataBase.EventsHelper;
import buildings.Building;

public class OutOfChicken extends Event {

    @Override
    public boolean invoke(String playerName) {

        // Prerequisite MacChicken built.
        BuildingsHelper helper
                = new BuildingsHelper();
        Buildings building_record = helper.getBuildings(playerName);
        if (building_record == null) {
            return false;
        }
        int chicken_level = building_record.getMacchicken();
        if (chicken_level == Building.NOT_BUILT_LEVEL) {
            return false;
        }

        // Increase students starvation.
        increaseStarvation(playerName, LotteryManager.MEDIUM);

        return true;
    }

    @Override
    public String getInfo() {
        return ("Even in spite of a huge effort to increases the number of legs " +
                "a single chicken can have, your MacChicken has managed to run " +
                "out of legs. As a result, the starvation of your students " +
                "increases.");
    }

    @Override
    public boolean isEqual(String name) {
        return (name.equals(LotteryManager.OUT_OF_CHICKEN));
    }

    @Override
    public String getName() {
        return ("MacChicken has run out of chicken!");
    }

    @Override
    public void writeToDb(String playerName) {
        EventsHelper eventHelper
                = new EventsHelper();
        eventHelper.setOutOfChicken(playerName, getNumOfTickets());
    }
}
