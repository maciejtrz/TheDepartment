package buildings;

import ConnectionDataBase.Buildings;
import ConnectionDataBase.BuildingsHelper;
import ConnectionDataBase.BuildingsPositionHelper;
import ConnectionDataBase.PlayerresourcesHelper;
import Connections.UserManager;
import utilities.BuildingInfo;

public class Brain extends Building {


    public Brain () {
        this.cost = 5000;
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
        if (building_record == null) {
            return false;
        }
        int labs_level = building_record.getLabolatories();
        if (labs_level != Building.MEDIUM_LEVEL) {
            // The player has not met a sufficient prerequisite.
            return false;
        }

        //Checking whether not already built.
        int cur_level = building_record.getBrain();
        if (cur_level != Building.NOT_BUILT_LEVEL) {
            return false;
        }
        
        /* Updating Buildings table. */
        buildingsHelper.updateBrain(playerName, Building.BASIC_LEVEL);

        /* Updating Position table. */
        posHelper.createBuildingPosition(playerName, position,
                Building.CODE_BRAIN);

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
        int cur_level = building_record.getBrain();
        if (cur_level == Building.NOT_BUILT_LEVEL) {
            return false;
        }
        // Checking if the the input position is correct.
        if (!canPositionBeDestoryed(playerName, position, CODE_BRAIN)) {
            return false;
        }

        /* Removing from Buildings table. */
        buildingsHelper.updateBrain(playerName, Building.NOT_BUILT_LEVEL);

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

        Buildings building_record = buildingsHelper.getBuildings(playerName);
        if (building_record == null) {
            return new BuildingInfo(false, "Player does not exists");
        }

        int labs_level = building_record.getLabolatories();
        if (labs_level != Building.MEDIUM_LEVEL) {
            // The player has not met a sufficient prerequisite.
            return new BuildingInfo(false, "You should build SuperLabs first!");
        }

        //Checking whether not already built.
        int cur_level = building_record.getBrain();
        if (cur_level != Building.NOT_BUILT_LEVEL) {
            return new BuildingInfo(false, "Brain already built!");
        }

        return new BuildingInfo(true, "Build me!");
    }

    @Override
    public boolean upgrade(String playerName, int position) {
        return false;
    }

    @Override
    public String getInfo() {
        return ("BrAiN");
    }

    @Override
    public String getPicture() {
        return (this.CODE_BRAIN);
    }
}
