package events;

import ConnectionDataBase.*;
import buildings.Building;

public class BarNight extends Event {

    @Override
    public boolean invoke(String playerName) {
        
        /* Checking whether DocPub is standing. */
        BuildingsHelper buildingsHelper
                = new BuildingsHelper();
        Buildings building_record
                = buildingsHelper.getBuildings(playerName);
        int pub_level = building_record.getDocpub();
        if (pub_level == Building.NOT_BUILT_LEVEL) {
            return false;
        }

        /* Updating database accordingly. */
        boolean b1 = increaseAlcoholizm(playerName, LotteryManager.HIGH);
        boolean b2 = increaseSatisfaction(playerName, LotteryManager.HIGH);

        return (b1 && b2);
    }

    @Override
    public String getInfo() {
        return  "Poor manager. Your undergraduate students decided to organize " +
                "the DOCSOC Bar Night event. As a result, " +
                "everyone got drunk and went crazy. Fortunately for you, " +
                "the union pub is still standing, but the level of alcoholism " +
                "of the students has drastically increases. On the other hand, " +
                "student satisfaction also increases. Oddly enough, although " +
                "Ph.D. students do not generally have life, a few of them " +
                "managed to join as well." ;
    }

    @Override
    public boolean isEqual(String name) {
        return (name.equals(LotteryManager.BAR_NIGHT));
    }

    @Override
    public String getName() {
        return ("Bar Night!");
    }

    @Override
    public void writeToDb(String playerName) {
        EventsHelper eventHelper
                = new EventsHelper();
        eventHelper.setBarNight(playerName, getNumOfTickets());
    }

}
