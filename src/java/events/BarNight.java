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
        ExtrastatsHelper statsHelper
                = new ExtrastatsHelper();
        Extrastats stats_record
                = statsHelper.getPlayerStatsRecrod(playerName);
        if(stats_record == null) {
            return false;
        }
        int alcoholizm = stats_record.getAlcoholizm();
        int satisfaction = stats_record.getSatisfaction();

        int alco_update 
              = this.getIncreasedValue(alcoholizm, LotteryManager.HIGH);
        int sat_update
              = this.getDecreasedValue(satisfaction, LotteryManager.HIGH);

        statsHelper.updateAlcoholizm(playerName, alco_update);
        statsHelper.updateSatisfaction(playerName, sat_update);

        return true;
    }

    @Override
    public String getInfo() {
        return "Poor manager. Your undergraduate studetns decided to" +
                " organazie the DOCSOC Bar Night event. As a result, everyone" +
                " got drunk and went crazy. Fortunately for you," +
                " the union pub is still standing, but the level of" +
                " alcoholizm of the students has drastically increased. On the" +
                " other hand, student satisfaction also increases. Oddly enough," +
                " although PhD students do not generally have life, a few of" +
                " them managed to join as well." ;
    }

    @Override
    public boolean isEqual(String name) {
        return (name.equals(LotteryManager.BAR_NIGHT));
    }

}
