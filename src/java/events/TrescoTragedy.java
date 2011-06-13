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
        throw new UnsupportedOperationException("Not supported yet.");
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

