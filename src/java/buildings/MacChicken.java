package buildings;

import ConnectionDataBase.Buildings;
import ConnectionDataBase.BuildingsHelper;
import ConnectionDataBase.BuildingsPositionHelper;
import utilities.BuildingInfo;

public class MacChicken extends Building {


    public MacChicken (int cost) {
        this.cost = cost;
    }

    @Override
    public boolean build(String playerName, int position) {

        BuildingsHelper buildingsHelper
                = new BuildingsHelper();

        BuildingsPositionHelper posHelper
                = new BuildingsPositionHelper();

        // Checking money and position.
        boolean result = checkMoneyAndPosition(playerName, position);
        if (!result) {
            return false;
        }


        // Checking other requirements.
        Buildings building_record = buildingsHelper.getBuildings(playerName);
        if (building_record == null ) {
            return false;
        }
        int tresco_level = building_record.getTresco();
        if (tresco_level == Building.NOT_BUILT_LEVEL) {
            return false;
        }

        // Checking whether the building was not already built.
        int mac_chicken_level = building_record.getMacchicken();
        if (mac_chicken_level != Building.NOT_BUILT_LEVEL) {
            return false;
        }

        /* Updating Buildings table. */
        buildingsHelper.updateMacChicken(playerName, Building.BASIC_LEVEL);

        /* Updating Position table. */
        posHelper.createBuildingPosition(playerName, position,
                Building.CODE_MAC_CHICKEN_1);

        return true;
    }

    @Override
    public boolean remove(String playerName, int position) {

        /* Removing from Buildings table. */
        BuildingsHelper buildingsHelper
                = new BuildingsHelper();
        buildingsHelper.updateMacChicken(playerName, Building.NOT_BUILT_LEVEL);

        /* Removing from Position table. */
        BuildingsPositionHelper posHelper
                = new BuildingsPositionHelper();
        posHelper.updateBuildingPosition(playerName, position, null);


        return true;
    }

    @Override
    public BuildingInfo isAllowedToBuild(String playerName, int position) {

        BuildingsHelper buildingsHelper
                = new BuildingsHelper();

        // Checking money and position.
        BuildingInfo result = checkMoneyAndPositionInfo(playerName, position);
        if (!result.getResult()) {
            return result;
        }


        // Checking other requirements.
        Buildings building_record = buildingsHelper.getBuildings(playerName);
        if (building_record == null ) {
            return new BuildingInfo(false, "Player does not exist.");
        }
        int tresco_level = building_record.getTresco();
        if (tresco_level == Building.NOT_BUILT_LEVEL) {
            return new BuildingInfo(false, "Please build Tresco first");
        }

        // Checking whether the building was not already built.
        int mac_chicken_level = building_record.getMacchicken();
        if (mac_chicken_level != Building.NOT_BUILT_LEVEL) {
            return new BuildingInfo(false, "MacChicken already built");
        }

        return new BuildingInfo(true, "Build me!");
    }

}
