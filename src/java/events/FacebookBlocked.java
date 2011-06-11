
package events;

import ConnectionDataBase.*;
import buildings.Building;

public class FacebookBlocked extends Event {

    @Override
    public boolean invoke(String playerName) {

        // Checking whether labs are present.
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

        
        // Getting the satisfaction update value
        ExtrastatsHelper statsHelper
                = new ExtrastatsHelper();
        Extrastats stats_record = statsHelper.getPlayerStatsRecrod(playerName);
        if (stats_record == null) {
            return false;
        }
        int satisfaction_level = stats_record.getSatisfaction();
        int sat_update
              = getDecreasedValue(satisfaction_level, LotteryManager.MEDIUM);

        // Getting the population update value
        CapacityHelper capHelper = new CapacityHelper();
        Capacity cap_record = capHelper.getCapacity(playerName);
        if (cap_record == null) {
            return false;
        }
        int new_value = cap_record.getStudentscapacity() + 100;


        // Updating the databases if everything went ok
        statsHelper.updateSatisfaction(playerName, sat_update);
        capHelper.updateStudentsCapacity(playerName, new_value);

        return true;
    }

    @Override
    public String getInfo() {
        return ("Surprise! Your Computer Support Group decided to block access " +
                "to facebook on all computers in your labs. As a result, " +
                "the overall population of available students and PhDs " +
                "increases (but they prefer to work at home, for some " +
                "unknown reasons). Unfortunately, their satisfaction suffers " +
                "considerably.");
    }

    @Override
    public boolean isEqual(String name) {
        return name.equals(LotteryManager.FACEBOOK_BLOCKED);
    }

}
