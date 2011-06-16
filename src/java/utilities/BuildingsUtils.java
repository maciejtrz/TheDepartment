/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utilities;

import ConnectionDataBase.Buildings;
import ConnectionDataBase.BuildingsHelper;
import buildings.Building;
import buildings.BuildingFactory;
import buildings.Laboratories;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kwh109
 */
public class BuildingsUtils {


    public List<Building> getAvailableBuildings(String playerName) {
        List<Building> buildings = new ArrayList<Building>();

        BuildingFactory factory = new BuildingFactory();

        Building building = factory.getLabs();
        if (building.isAllowedToBuild(playerName).getResult()) {
            buildings.add(building);
        }

        building = factory.getBrain();
        if (building.isAllowedToBuild(playerName).getResult()) {
            buildings.add(building);
        }


        building = factory.getLecturerRoom();
        if (building.isAllowedToBuild(playerName).getResult()) {
            buildings.add(building);
        }

        building = factory.getMacChicken();
        if (building.isAllowedToBuild(playerName).getResult()) {
            buildings.add(building);
        }

        building = factory.getPdhOffice();
        if (building.isAllowedToBuild(playerName).getResult()) {
            buildings.add(building);
        }

        building = factory.getProfOffice();
        if (building.isAllowedToBuild(playerName).getResult()) {
            buildings.add(building);
        }

        building = factory.getPub();
        if (building.isAllowedToBuild(playerName).getResult()) {
            buildings.add(building);
        }

        building = factory.getStudentUnion();
        if (building.isAllowedToBuild(playerName).getResult()) {
            buildings.add(building);
        }

        building = factory.getTresco();
        if (building.isAllowedToBuild(playerName).getResult()) {
            buildings.add(building);
        }

        return buildings;
    }




    public boolean isLabBuilt(String playerName) {
        BuildingsHelper helper = new BuildingsHelper();
        Buildings building_record = helper.getBuildings(playerName);
        if (building_record == null) {
            return false;
        }
        int building_level = building_record.getLabolatories();
        return (building_level != Building.NOT_BUILT_LEVEL);

    }


    public boolean isBrainBuilt(String playerName) {
        BuildingsHelper helper = new BuildingsHelper();
        Buildings building_record = helper.getBuildings(playerName);
        if (building_record == null) {
            return false;
        }
        int building_level = building_record.getBrain();
        return (building_level != Building.NOT_BUILT_LEVEL);


    }

    public boolean isDocPubBuilt(String playerName) {
        BuildingsHelper helper = new BuildingsHelper();
        Buildings building_record = helper.getBuildings(playerName);
        if (building_record == null) {
            return false;
        }
        int building_level = building_record.getDocpub();
        return (building_level != Building.NOT_BUILT_LEVEL);


    }

    public boolean isLecturerRoomBuilt(String playerName) {
        BuildingsHelper helper = new BuildingsHelper();
        Buildings building_record = helper.getBuildings(playerName);
        if (building_record == null) {
            return false;
        }
        int building_level = building_record.getLectureroom();
        return (building_level != Building.NOT_BUILT_LEVEL);

    }

    public boolean isMacChickenBuilt(String playerName) {
        BuildingsHelper helper = new BuildingsHelper();
        Buildings building_record = helper.getBuildings(playerName);
        if (building_record == null) {
            return false;
        }
        int building_level = building_record.getMacchicken();
        return (building_level != Building.NOT_BUILT_LEVEL);


    }


    public boolean isPhdOfficeBuilt(String playerName) {
        BuildingsHelper helper = new BuildingsHelper();
        Buildings building_record = helper.getBuildings(playerName);
        if (building_record == null) {
            return false;
        }
        int building_level = building_record.getPhdsoffice();
        return (building_level != Building.NOT_BUILT_LEVEL);


    }
    
    public boolean isProfessorsOfficeBuilt(String playerName) {
        BuildingsHelper helper = new BuildingsHelper();
        Buildings building_record = helper.getBuildings(playerName);
        if (building_record == null) {
            return false;
        }
        int building_level = building_record.getProfessorsoffice();
        return (building_level != Building.NOT_BUILT_LEVEL);
    }


    public boolean isStudentUnionBuilt(String playerName) {
        BuildingsHelper helper = new BuildingsHelper();
        Buildings building_record = helper.getBuildings(playerName);
        if (building_record == null) {
            return false;
        }
        int building_level = building_record.getStudentunion();
        return (building_level != Building.NOT_BUILT_LEVEL);

    }


    public boolean isTrescoBuilt(String playerName) {

        BuildingsHelper helper = new BuildingsHelper();
        Buildings building_record = helper.getBuildings(playerName);
        if (building_record == null) {
            return false;
        }
        int building_level = building_record.getTresco();
        return (building_level != Building.NOT_BUILT_LEVEL);

    }
}
