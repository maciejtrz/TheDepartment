package events;

import ConnectionDataBase.Buildings;
import ConnectionDataBase.BuildingsHelper;
import ConnectionDataBase.EventsHelper;
import buildings.Building;

public class UnionParty extends Event {

    @Override
    public boolean invoke(String playerName) {

        // Union buildings exists as prerequirement.
        BuildingsHelper buildingHelper = new BuildingsHelper();
        Buildings building_record = buildingHelper.getBuildings(playerName);
        if (building_record == null) {
            // This should not happen.
            System.err.println
               ("Player's " + playerName + " buildings record does not exists.");
        }
        int union_level = building_record.getStudentunion();
        if (union_level == Building.NOT_BUILT_LEVEL) {
            return false;
        }

        // Decreasing studented's satisfaction.
        return (decreaseSatisfaction(playerName, LotteryManager.LOW));
    }

    @Override
    public String getInfo() {
        return ("Union Party?!? Nothing happened... Only a slight decrease " +
                "in your students' satisfaction level.");
    }

    @Override
    public boolean isEqual(String name) {
        return name.equals(LotteryManager.UNION_PARTY);
    }

    @Override
    public String getName() {
        return ("Union Party!");
    }

    @Override
    public void writeToDb(String playerName) {
        EventsHelper eventHelper
                = new EventsHelper();
        eventHelper.setUnionParty(playerName, getNumOfTickets());
    }

}
