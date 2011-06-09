package buildings;

import ConnectionDataBase.Buildings;
import ConnectionDataBase.BuildingsHelper;
import ConnectionDataBase.BuildingsPositionHelper;
import ConnectionDataBase.PlayerresourcesHelper;
import utilities.BuildingInfo;

public class Brain extends Building {


    public Brain (int cost) {
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

        return true;
    }

    @Override
    public boolean remove(String playerName, int position) {
        
        /* Removing from Buildings table. */
        BuildingsHelper buildingsHelper
                = new BuildingsHelper();
        buildingsHelper.updateBrain(playerName, Building.NOT_BUILT_LEVEL);

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
        BuildingInfo info = checkMoneyAndPositionInfo(playerName, position);
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

}
