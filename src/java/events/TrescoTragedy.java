package events;

import ConnectionDataBase.Buildings;
import ConnectionDataBase.BuildingsHelper;
import ConnectionDataBase.EventsHelper;
import buildings.Building;

public class TrescoTragedy extends Event {

    @Override
    public boolean invoke(String playerName) {

        // Checking if tresco is built.
        BuildingsHelper buildingsHelper
                = new BuildingsHelper();
        Buildings building_record = buildingsHelper.getBuildings(playerName);
        if (building_record == null) {
            // This should not happen.
            System.err.println
               ("Player's " + playerName + " buildings record does not exists.");
            return false;
        }
        int tresco_level = building_record.getTresco();
        if (tresco_level == Building.NOT_BUILT_LEVEL) {
            return false;
        }

        // Increasing player's starvation.
        return increaseStarvation(playerName, LotteryManager.MEDIUM);
    }

    @Override
    public String getInfo() {
        return ("Real tragedy! The amount of cheap food was extremely reduced " +
                "in your local Tresco, due to the increase in the number of " +
                "millionaires visiting to buy their pseudo organic food. " +
                "The starvation of students increases. Oh well, it is high " +
                "time for your students to destroy some Ferrari.  ");
    }


    @Override
    public boolean isEqual(String name) {
        return (name.equals(LotteryManager.TRESCO_TRAGEDY));
    }

    @Override
    public String getName() {
        return ("Tresco tragedy!");
    }

    @Override
    public void writeToDb(String playerName) {
        EventsHelper eventHelper
                = new EventsHelper();
        eventHelper.setTrescoTragedy(playerName, getNumOfTickets());
    }
}

