
package buildings;

import ConnectionDataBase.Buildings;
import ConnectionDataBase.BuildingsHelper;
import ConnectionDataBase.BuildingsPositionHelper;
import ConnectionDataBase.Playerresources;
import ConnectionDataBase.PlayerresourcesHelper;
import Connections.UserManager;
import utilities.BuildingInfo;

public class ProfessorsOffice extends Building {

    public ProfessorsOffice () {
        cost = 3000;
        max_level = ADVANCED_LEVEL;
        upgrade_base_cost = 2000;
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

        // Checkng whether professor ofice was not already built.
        Buildings building_record
                = buildingsHelper.getBuildings(playerName);
        if (building_record == null) {
            return false;
        }
        int professor_office_level = building_record.getProfessorsoffice();
        if (professor_office_level != Building.NOT_BUILT_LEVEL) {
            return false;
        }

        /* Updating Buildings table. */
        buildingsHelper.updatePorfessorsOffice(playerName, Building.BASIC_LEVEL);

        /* Updating Position table. */
        posHelper.createBuildingPosition(playerName, position,
                Building.CODE_PROF_OFFICE_1);

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
        int cur_level = building_record.getProfessorsoffice();
        if (cur_level == Building.NOT_BUILT_LEVEL) {
            return false;
        }
        // Checking if the the input position is correct.
        if (!canPositionBeDestoryed(playerName, position, CODE_PROF_OFFICE_1)
            &&!canPositionBeDestoryed(playerName, position,CODE_PROF_OFFICE_2)
            &&!canPositionBeDestoryed(playerName, position,CODE_PROF_OFFICE_3)) {
            return false;
        }

        /* Removing from Buildings table. */
        buildingsHelper
                .updatePorfessorsOffice(playerName, Building.NOT_BUILT_LEVEL);

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

        // Checkng whether labs were not already built.
        Buildings building_record
                = buildingsHelper.getBuildings(playerName);
        if (building_record == null) {
            return new BuildingInfo(false, "Player does not exist");
        }

        int prof_level = building_record.getProfessorsoffice();
        if (prof_level != Building.NOT_BUILT_LEVEL) {
            return new BuildingInfo(false, "Professor offices are already built");
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
        int cur_level = building_record.getProfessorsoffice();
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

        // Upgrading with respect to the current level
        String occupant = posHelper.getAtPosition(playerName, position);
        if (cur_level == BASIC_LEVEL) {
            if (!occupant.equals(CODE_PROF_OFFICE_1)) {
                return false;
            }
            // Upgrading to the room lvl_1
            buildingHelper.updatePorfessorsOffice(playerName, MEDIUM_LEVEL);
            posHelper.updateBuildingPosition(playerName, position, CODE_PROF_OFFICE_2);
            UserManager.removeMoney(playerName, cost);
        }
        else {
            if (!occupant.equals(CODE_PROF_OFFICE_2)) {
                return false;
            }
            // Upgrading to the room lvl_2
            buildingHelper.updatePorfessorsOffice(playerName, ADVANCED_LEVEL);
            posHelper.updateBuildingPosition(playerName, position, CODE_PROF_OFFICE_3);
            UserManager.removeMoney(playerName, cost);
        }

        return true;
    }

    @Override
    public String getInfo() {
        return ("ProfessorsOffice");
    }

    @Override
    public String getPicture() {
        // TODO CHANGE CODE ON LVLS
        return (this.CODE_PROF_OFFICE_1);
    }
}
