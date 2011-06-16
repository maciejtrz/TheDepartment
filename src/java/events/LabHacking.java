package events;

import ConnectionDataBase.Buildings;
import ConnectionDataBase.BuildingsHelper;
import ConnectionDataBase.EventsHelper;
import buildings.Building;

public class LabHacking extends Event {

    @Override
    public boolean invoke(String playerName) {

        // Has to have at least SuperLabs.
        BuildingsHelper buildingsHelper
                = new BuildingsHelper();
        Buildings building_record = buildingsHelper.getBuildings(playerName);
        if (building_record == null) {
            return false;
        }
        int labs_level = building_record.getLabolatories();
        if (labs_level < Building.MEDIUM_LEVEL) {
            return false;
        }

        // Decreasing student satisfaction.
        return (decreaseSatisfaction(playerName, LotteryManager.LOW));
    }

    @Override
    public String getInfo() {
        return ("Bad news! Your labs have been hacked by some anonymous group of" +
                " students. All of your student's personal information MAY have" +
                " been leaked. Consequently, the satisfaction of your students " +
                "considerably decreases.");
    }

    @Override
    public boolean isEqual(String name) {
        return (name.equals(LotteryManager.LAB_HACKING));
    }

    @Override
    public String getName() {
        return ("Labs Hacked!");
    }

    @Override
    public void writeToDb(String playerName) {
        EventsHelper eventHelper
                = new EventsHelper();
        eventHelper.setLabsHacking(playerName, getNumOfTickets());
    }
}
