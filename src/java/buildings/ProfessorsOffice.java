
package buildings;

import ConnectionDataBase.Buildings;
import ConnectionDataBase.BuildingsHelper;
import ConnectionDataBase.BuildingsPositionHelper;
import ConnectionDataBase.PlayerresourcesHelper;
import utilities.BuildingInfo;

public class ProfessorsOffice extends Building {

    public ProfessorsOffice (int cost) {
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

}
