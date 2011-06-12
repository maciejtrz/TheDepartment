
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
        decreaseSatisfaction(playerName, LotteryManager.HIGH);

        // Getting the population update value
        CapacityHelper capHelper = new CapacityHelper();
        Capacity cap_record = capHelper.getCapacity(playerName);
        if (cap_record == null) {
            return false;
        }
        
        // Updating the databases if everything went ok
        capHelper.updateStudentsCapacity(playerName, 100);

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
