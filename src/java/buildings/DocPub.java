

package buildings;

import ConnectionDataBase.Buildings;
import ConnectionDataBase.BuildingsHelper;
import ConnectionDataBase.BuildingsPositionHelper;
import ConnectionDataBase.PlayerresourcesHelper;
import utilities.BuildingInfo;

public class DocPub extends Building {


    public DocPub (int cost) {
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

        int union_level = building_record.getStudentunion();
        if (union_level != Building.BASIC_LEVEL) {
            return false;
        }
        
        int mac_chicken_level = building_record.getMacchicken();
        if (mac_chicken_level != Building.BASIC_LEVEL) {
            return false;
        }

        // Checking whether not already built.
        int pub_level = building_record.getDocpub();
        if (pub_level != Building.NOT_BUILT_LEVEL) {
            return false;
        }
        
        /* Updating Buildings table. */
        buildingsHelper.updateDocPub(playerName, Building.BASIC_LEVEL);

        /* Updating Position table. */
        posHelper.createBuildingPosition(playerName, position,
                Building.CODE_DOCPUB);

        return true;
    }


    @Override
    public boolean remove(String playerName, int position) {

        /* Removing from Buildings table. */
        BuildingsHelper buildingsHelper
                = new BuildingsHelper();
        buildingsHelper.updateDocPub(playerName, Building.NOT_BUILT_LEVEL);

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

        // Checking other dependencies.
        Buildings building_record = buildingsHelper.getBuildings(playerName);
        if (building_record == null) {
            return new BuildingInfo(false, "Player does not exists");
        }

        int union_level = building_record.getStudentunion();
        if (union_level != Building.BASIC_LEVEL) {
            return new BuildingInfo(false, "Although it is useless, you should " +
                    "build Union Building first");
        }

        int mac_chicken_level = building_record.getMacchicken();
        if (mac_chicken_level != Building.BASIC_LEVEL) {
            return new BuildingInfo(false, "You should build Mac Chicken first");
        }

        // Checking whether not already built.
        int pub_level = building_record.getDocpub();
        if (pub_level != Building.NOT_BUILT_LEVEL) {
            return new BuildingInfo(false, "Pub already built");
        }


        return new BuildingInfo(true, "Build me!");
    }
}
