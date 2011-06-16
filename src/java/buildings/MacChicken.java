package buildings;

import ConnectionDataBase.Buildings;
import ConnectionDataBase.BuildingsHelper;
import ConnectionDataBase.BuildingsPositionHelper;
import ConnectionDataBase.PlayerresourcesHelper;
import Connections.UserManager;
import utilities.BuildingInfo;

public class MacChicken extends Building {


    public MacChicken () {
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

        /* Updating players money. */
        UserManager.removeMoney(playerName, cost);

        return true;
    }

    @Override
    public boolean remove(String playerName, int position) {

        BuildingsPositionHelper posHelper
                = new BuildingsPositionHelper();

        BuildingsHelper buildingsHelper
                = new BuildingsHelper();

        /* Checking prerequirements. */
        Buildings building_record = buildingsHelper.getBuildings(playerName);
        if (building_record == null) {
            return false;
        }
        //Checking whether is already built.
        int cur_level = building_record.getMacchicken();
        if (cur_level == Building.NOT_BUILT_LEVEL) {
            return false;
        }
        // Checking if the the input position is correct.
        if (!canPositionBeDestoryed(playerName, position, CODE_MAC_CHICKEN_1)) {
            return false;
        }

        /* Removing from Buildings table. */
        buildingsHelper.updateMacChicken(playerName, Building.NOT_BUILT_LEVEL);

        /* Removing from Position table. */
        posHelper.updateBuildingPosition(playerName, position, null);


        return true;
    }

    @Override
    public BuildingInfo isAllowedToBuild(String playerName) {

        BuildingsHelper buildingsHelper
                = new BuildingsHelper();

        // Checking money and position.
        BuildingInfo result = checkMoney(playerName);
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

    @Override
    public boolean upgrade(String playerName, int position) {
        return false;
    }

    @Override
    public String getInfo() {
        return ("MacChicken is the most famous fast food restaurant " +
                "in the world. Despite its continuous attempt to produce " +
                "chickens with more than two legs, MacChicken still remains " +
                "one of the largest chicken supplier in the world. Possessing " +
                "MacChicken has a considerable effect on the starvation of " +
                "your students.");
    }

    @Override
    public String getPicture() {
        // TODO CHANGE CODE ON LVLS
        return (this.CODE_MAC_CHICKEN_1);
    }

}
