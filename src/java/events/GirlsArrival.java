package events;

import ConnectionDataBase.CapacityHelper;
import ConnectionDataBase.EventsHelper;

public class GirlsArrival extends Event {

    @Override
    public boolean invoke(String playerName) {

        // No prerequirements.

        // Updating capacities.
        CapacityHelper capHelper = new CapacityHelper();
        capHelper.updateStudentsCapacity(playerName, 100);
        capHelper.updatePhDsCapacity(playerName, LotteryManager.MEGA);
        capHelper.updateProfessorsCapacity(playerName, LotteryManager.HIGH);

        return true;
    }

    @Override
    public String getInfo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isEqual(String name) {
        return (name.equals(LotteryManager.GIRLS_ARRIVAL));
    }

    @Override
    public String getName() {
        return ("100 young virgins arrival!");
    }

    @Override
    public void writeToDb(String playerName) {
        EventsHelper eventHelper
                = new EventsHelper();
        eventHelper.setGirlsArrival(playerName, getNumOfTickets());
    }

}
