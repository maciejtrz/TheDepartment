package buildings;

import ConnectionDataBase.Buildings;
import ConnectionDataBase.BuildingsHelper;
import ConnectionDataBase.BuildingsPositionHelper;
import ConnectionDataBase.PlayerresourcesHelper;
import Connections.UserManager;
import utilities.BuildingInfo;

public class StudentUnion extends Building {

    public StudentUnion () {
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

        int su_level = building_record.getStudentunion();
        if (su_level != Building.NOT_BUILT_LEVEL) {
            return false;
        }
        

        /* Updating Buildings table. */
        buildingsHelper.updateStudentUnion(playerName, Building.BASIC_LEVEL);

        /* Updating Position table. */
        posHelper.createBuildingPosition(playerName, position,
                Building.CODE_UNION);

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
        int cur_level = building_record.getStudentunion();
        if (cur_level == Building.NOT_BUILT_LEVEL) {
            return false;
        }
        // Checking if the the input position is correct.
        if (!canPositionBeDestoryed(playerName, position, this.CODE_UNION)) {
            return false;
        }

        /* Removing from Buildings table. */
        buildingsHelper.updateStudentUnion(playerName, Building.NOT_BUILT_LEVEL);

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

        int su_level = building_record.getStudentunion();
        if (su_level != Building.NOT_BUILT_LEVEL) {
            return new BuildingInfo(false, "Union is already built");
        }

        return new BuildingInfo(true, "Build me!");
    }

    @Override
    public boolean upgrade(String playerName, int position) {
        return false;
    }

    @Override
    public String getInfo() {
        return ("Student Union");
    }

    @Override
    public String getPicture() {
        return (this.CODE_UNION);
    }

}
