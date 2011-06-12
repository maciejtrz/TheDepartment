package events;

import ConnectionDataBase.Buildings;
import ConnectionDataBase.BuildingsHelper;
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isEqual(String name) {
        return (name.equals(LotteryManager.OUT_OF_CHICKEN));
    }

}
