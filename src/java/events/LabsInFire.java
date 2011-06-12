package events;

import ConnectionDataBase.*;
import buildings.Building;
import buildings.BuildingFactory;
import buildings.Laboratories;

public class LabsInFire extends Event {

    @Override
    public boolean invoke(String playerName) {

        // Checking whether labs are present
        BuildingsHelper buildingsHelper
                = new BuildingsHelper();
        Buildings building_record = buildingsHelper.getBuildings(playerName);
        if (building_record == null) {
            return false;
        }
        int labs_level = building_record.getLabolatories();
        if (labs_level == Building.NOT_BUILT_LEVEL) {
            return false;
        }

        // Destorying labs.
        BuildingsPositionHelper posHelper
                = new BuildingsPositionHelper();
        int labs_position = 
                posHelper.getPosition(playerName, Building.CODE_LABS);

        if (labs_position == 0) {
            // Checking for superlabs
            labs_position
               = posHelper.getPosition(playerName, Building.CODE_SUPERLABS);
        }

        if (labs_position == 0) {
            // Inconsistency in the database, given that labs are built this
            // should not happen.
            System.err.println("Incosistency in the databse, could not find" +
                    " labs posotion given that labs exists.");
            return false;
        }

        Laboratories labs = BuildingFactory.getLabs();
        labs.remove(playerName, labs_position);

        return true;
    }

    @Override
    public String getInfo() {
        return "LabsInFire";
    }

    @Override
    public boolean isEqual(String name) {
        return (name.equals(LotteryManager.LABS_FIRE));
    }

    @Override
    public String getName() {
        return ("Fire in the labs!");
    }

}
