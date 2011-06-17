
package buildings;

import ConnectionDataBase.Buildings;
import ConnectionDataBase.BuildingsHelper;
import ConnectionDataBase.BuildingsPositionHelper;
import ConnectionDataBase.PlayerresourcesHelper;
import Connections.UserManager;
import utilities.BuildingInfo;

public class Tresco extends Building {

    public Tresco () {
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

        // Checkng whether labs were not already built.
        Buildings building_record
                = buildingsHelper.getBuildings(playerName);
        if (building_record == null) {
            return false;
        }

        int tresco_level = building_record.getTresco();
        if (tresco_level != Building.NOT_BUILT_LEVEL) {
            return false;
        }


        /* Updating Buildings table. */
        buildingsHelper.updateTresco(playerName, Building.BASIC_LEVEL);

        /* Updating Position table. */
        posHelper.createBuildingPosition(playerName, position,
                Building.CODE_TRESCO);

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
        int cur_level = building_record.getTresco();
        if (cur_level == Building.NOT_BUILT_LEVEL) {
            return false;
        }
        // Checking if the the input position is correct.
        // Checking if the the input position is correct.
        if (!canPositionBeDestoryed(playerName, position, CODE_TRESCO)) {
            return false;
        }

        /* Removing from Buildings table. */
        buildingsHelper.updateTresco(playerName, Building.NOT_BUILT_LEVEL);

        /* Removing from Position table. */
        posHelper.updateBuildingPosition(playerName, position, null);


        return true;
    }

    @Override
    public BuildingInfo isAllowedToBuild(String playerName) {
        BuildingsHelper buildingsHelper
                = new BuildingsHelper();

        // Checking money and position.
        BuildingInfo info = checkMoney(playerName);
        if (!info.getResult()) {
            return info;
        }

        // Checkng whether labs were not already built.
        Buildings building_record
                = buildingsHelper.getBuildings(playerName);
        if (building_record == null) {
            return new BuildingInfo(false, "Player does not exist");
        }

        int tresco_level = building_record.getTresco();
        if (tresco_level != Building.NOT_BUILT_LEVEL) {
            return new BuildingInfo(false, "Tresco is already built");
        }

        return new BuildingInfo(true, "Build me!");
    }

    @Override
    public boolean upgrade(String playerName, int position) {
        return false;
    }

    @Override
    public String getInfo() {
        return ("Tresco is a famous, world-class reduced food supplier. " +
                "You can buy there anything, from ten-days, freshly reduced " +
                "bread to a Tresco Value “blinding“ vodka. " +
                "Unfortunately, this fabulous brand is recently plagued by " +
                "so called “organic food”. Possessing Tresco allows you to " +
                "build MacChicken. It decreases " +
                "starvation level of your students as well.");
    }

    @Override
    public String getPicture() {
        // TODO CHANGE CODE ON LVLS
        return (this.CODE_TRESCO);
    }
}
