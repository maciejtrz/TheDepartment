package events;

import ConnectionDataBase.Buildings;
import ConnectionDataBase.BuildingsHelper;
import buildings.Building;

public class TrescoMiracle extends Event {

    @Override
    public boolean invoke(String playerName) {

        // Checking whether tresco is built.
        BuildingsHelper buildingHelper = new BuildingsHelper();
        Buildings building_record = buildingHelper.getBuildings(playerName);
        if (building_record == null) {
            // This should not happned.
            System.err.println("Inconsistency in a database when trying to" +
                    " access buildings record of player " + playerName);
            return false;
        }
        int tresco_level = building_record.getTresco();
        if (tresco_level == Building.NOT_BUILT_LEVEL) {
            return false;
        }

        // Decreasing students starvation.
        decreaseStarvation(playerName, LotteryManager.HIGH);

        return true;
    }

    @Override
    public String getInfo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isEqual(String name) {
        return (name.equals(LotteryManager.TRESCO_MIRACLE));
    }

    @Override
    public String getName() {
        return "Tresco Miracle!";
    }

}
