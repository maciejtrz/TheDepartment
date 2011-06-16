package buildings;

import ConnectionDataBase.Buildings;
import ConnectionDataBase.BuildingsHelper;
import ConnectionDataBase.BuildingsPositionHelper;
import ConnectionDataBase.Playerresources;
import ConnectionDataBase.PlayerresourcesHelper;
import Connections.UserManager;
import utilities.BuildingInfo;

public class Laboratories  extends Building {

    public Laboratories () {
        this.cost = 500;
        max_level = MEDIUM_LEVEL;
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

        // Checkng whether labs were not already built.
        Buildings building_record
                = buildingsHelper.getBuildings(playerName);
        if (building_record == null) {
            return false;
        }

        int labs_level = building_record.getLabolatories();
        if (labs_level != Building.NOT_BUILT_LEVEL) {
            return false;
        }

        
        /* Updating Buildings table. */
        buildingsHelper.updateLabolatories(playerName, Building.BASIC_LEVEL);

        /* Updating Position table. */
        posHelper.createBuildingPosition(playerName, position,
                Building.CODE_LABS);

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
        int cur_level = building_record.getLabolatories();
        if (cur_level == Building.NOT_BUILT_LEVEL) {
            return false;
        }
        // Checking if the the input position is correct.
        if (!canPositionBeDestoryed(playerName, position, CODE_LABS)
            &&!canPositionBeDestoryed(playerName, position, CODE_SUPERLABS)) {
            return false;
        }

        /* Removing from Buildings table. */
        buildingsHelper.updateLabolatories(playerName, Building.NOT_BUILT_LEVEL);

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

        int labs_level = building_record.getLabolatories();
        if (labs_level != Building.NOT_BUILT_LEVEL) {
            return new BuildingInfo(false, "Labs are already built");
        }

        return new BuildingInfo(true, "Build me!");
    }

    @Override
    public boolean upgrade(String playerName, int position) {

        // Getting all required helpers.
        BuildingsHelper buildingHelper
                = new BuildingsHelper();
        BuildingsPositionHelper posHelper
                = new BuildingsPositionHelper();


        /* Checking whether the building is eligible for an upgrade. */
        // Getting current level
        Buildings building_record = buildingHelper.getBuildings(playerName);
        if (building_record == null) {
            // This should not happen, problem with initialization.
            return false;
        }
        int cur_level = building_record.getLabolatories();
        if (cur_level == max_level || cur_level == NOT_BUILT_LEVEL) {
            // Cannot be upgraded any more or not yet built.
            return false;
        }

        // Checking whether the player has sufficient cash.
        int upgrade_cost = cur_level * upgrade_base_cost;
        int cash = UserManager.getMoney(playerName);
        if (cash < upgrade_cost) {
            return false;
        }

        // Checking whether the input position is correct.
        String occupant = posHelper.getAtPosition(playerName, position);
        if (!occupant.equals(CODE_LABS)) {
            return false;
        }

        // Upgrading to the superlabs
        buildingHelper.updateLabolatories(playerName, MEDIUM_LEVEL);
        posHelper.updateBuildingPosition(playerName, position, CODE_SUPERLABS);
        UserManager.removeMoney(playerName, cost);

        return true;
    }


    @Override
    public int getUpgradeCost(String playerName) {
        BuildingsHelper buildingHelper = new BuildingsHelper();
        Buildings building_record = buildingHelper.getBuildings(playerName);
        if (building_record == null) {
            // This should not happen, problem with initialization.
            return 0;
        }
        int cur_level = building_record.getLabolatories();
        return cur_level * upgrade_base_cost;
    }

    @Override
    public String getInfo() {
        return ("Labs");
    }

    @Override
    public String getPicture() {
        //TODO Change code on superlabs!
        return (this.CODE_LABS);
    }

}
