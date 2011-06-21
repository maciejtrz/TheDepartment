package events;

import ConnectionDataBase.CapacityHelper;
import ConnectionDataBase.EventsHelper;

public class GirlsArrival extends Event {

    @Override
    public boolean invoke(String playerName) {

        // No prerequirements.

        // Updating capacities.
        CapacityHelper capHelper = new CapacityHelper();
        capHelper.updateStudentsCapacity(playerName, 200);
        capHelper.updatePhDsCapacity(playerName, 50);
        capHelper.updateProfessorsCapacity(playerName, LotteryManager.LOW);

        return true;
    }

    @Override
    public String getInfo() {
        return "Wooooha! Some unknown college from a place you never heard about" +
                " asked to exchange students with your department. As you value " +
                "the “quality” of your undergrads, you were about to decline the " +
                "offer. However, then you noticed that the offered students are all " +
                "females! Your remaining students were very excited about their " +
                "arrival. As a result their satisfaction drastically increases.";
    }

    @Override
    public boolean isEqual(String name) {
        return (name.equals(LotteryManager.GIRLS_ARRIVAL));
    }

    @Override
    public String getName() {
        return ("100 young girls arrival!");
    }

    @Override
    public void writeToDb(String playerName) {
        EventsHelper eventHelper
                = new EventsHelper();
        eventHelper.setGirlsArrival(playerName, getNumOfTickets());
    }

}
