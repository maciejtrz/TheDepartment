package buildings;

import ConnectionDataBase.Buildings;
import ConnectionDataBase.BuildingsHelper;
import ConnectionDataBase.BuildingsPositionHelper;
import ConnectionDataBase.Playerresources;
import ConnectionDataBase.PlayerresourcesHelper;
import utilities.BuildingInfo;

public class PhdOffice extends Building {

    public PhdOffice () {
        cost = 2000;
        max_level = ADVANCED_LEVEL;
        upgrade_base_cost = 1000;
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

        // Checking whether PhdOffice was not already built
        int phd_level = building_record.getPhdsoffice();
        if (phd_level != Building.NOT_BUILT_LEVEL) {
            return false;
        }


        /* Updating Buildings table. */
        buildingsHelper.updatePhDsOffice(playerName, Building.BASIC_LEVEL);

        /* Updating Position table. */
        posHelper.createBuildingPosition(playerName, position,
                Building.CODE_PHD_OFFICE_1);


        /* Updating players money. */
        PlayerresourcesHelper player_record
                = new PlayerresourcesHelper();
        int money = player_record.getMoney(playerName);
        player_record.updateMoney(playerName, money - cost);

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
        int cur_level = building_record.getPhdsoffice();
        if (cur_level == Building.NOT_BUILT_LEVEL) {
            return false;
        }
        // Checking if the the input position is correct.
        if (!canPositionBeDestoryed(playerName, position, CODE_PHD_OFFICE_1)
            &&!canPositionBeDestoryed(playerName, position, CODE_PHD_OFFICE_2)
            &&!canPositionBeDestoryed(playerName, position, CODE_PHD_OFFICE_3)) {
            return false;
        }


        /* Removing from Buildings table. */
        buildingsHelper.updatePhDsOffice(playerName, Building.NOT_BUILT_LEVEL);

        /* Removing from Position table. */
        posHelper.updateBuildingPosition(playerName, position, null);


        return true;
    }

    @Override
    public BuildingInfo isAllowedToBuild(String playerName, int position) {

        BuildingsHelper buildingsHelper
                = new BuildingsHelper();

        // Checking money and position.
        BuildingInfo info = checkMoneyAndPositionInfo(playerName, position);
        if (!info.getResult()) {
            return info;
        }

        // Checkng whether Phd office was not already built.
        Buildings building_record
                = buildingsHelper.getBuildings(playerName);
        if (building_record == null) {
            return new BuildingInfo(false, "Player does not exist");
        }

        // Checking whether PhdOffice was not already built
        int phd_level = building_record.getPhdsoffice();
        if (phd_level != Building.NOT_BUILT_LEVEL) {
            return new BuildingInfo(false, "Phd office already built");
        }

        return new BuildingInfo(true, "Build me");

    }

    @Override
    public boolean upgrade(String playerName, int position) {

        // Getting all required helpers.
        BuildingsHelper buildingHelper
                = new BuildingsHelper();
        BuildingsPositionHelper posHelper
                = new BuildingsPositionHelper();
        PlayerresourcesHelper resourcesHelper
                = new PlayerresourcesHelper();


        /* Checking whether the building is eligible for an upgrade. */
        // Getting current level
        Buildings building_record = buildingHelper.getBuildings(playerName);
        if (building_record == null) {
            // This should not happen, problem with initialization.
            return false;
        }
        int cur_level = building_record.getPhdsoffice();
        if (cur_level == max_level || cur_level == NOT_BUILT_LEVEL) {
            // Cannot be upgraded any more or not yet built.
            return false;
        }

        // Checking whether the player has sufficient cash.
        int upgrade_cost = cur_level * upgrade_base_cost;
        int cash = resourcesHelper.getMoney(playerName);
        if (cash < upgrade_cost) {
            return false;
        }

        // Upgrading with respect to the current level
        String occupant = posHelper.getPosition(playerName, position);
        if (cur_level == BASIC_LEVEL) {
            if (!occupant.equals(CODE_PHD_OFFICE_1)) {
                return false;
            }
            // Upgrading to the room lvl_2
            buildingHelper.updatePhDsOffice(playerName, MEDIUM_LEVEL);
            posHelper.updateBuildingPosition(playerName, position, CODE_PHD_OFFICE_2);
            resourcesHelper.updateMoney(playerName, cash - cost);
        }
        else {
            if (!occupant.equals(CODE_PHD_OFFICE_2)) {
                return false;
            }
            // Upgrading to the room lvl_3
            buildingHelper.updatePhDsOffice(playerName, ADVANCED_LEVEL);
            posHelper.updateBuildingPosition(playerName, position, CODE_PHD_OFFICE_3);
            resourcesHelper.updateMoney(playerName, cash - cost);
        }

        return true;
    }



}
