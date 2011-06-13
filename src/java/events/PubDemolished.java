package events;

import ConnectionDataBase.Buildings;
import ConnectionDataBase.BuildingsHelper;
import ConnectionDataBase.BuildingsPositionHelper;
import ConnectionDataBase.EventsHelper;
import buildings.Building;
import buildings.BuildingFactory;
import buildings.DocPub;

public class PubDemolished extends Event {

    @Override
    public boolean invoke(String playerName) {

        // Pub has to be built.
        BuildingsHelper buildingsHelper
                = new BuildingsHelper();
        Buildings building_record
                = buildingsHelper.getBuildings(playerName);
        int pub_level = building_record.getDocpub();
        if (pub_level == Building.NOT_BUILT_LEVEL) {
            return false;
        }

        // Destorying pub :(
        BuildingsPositionHelper posHelper
                = new BuildingsPositionHelper();
        int pub_position =
                posHelper.getPosition(playerName, Building.CODE_DOCPUB);

        if (pub_position == 0) {
            // Inconsistency in the database, given that labs are built this
            // should not happen.
            System.err.println("Incosistency in the databse, could not find" +
                    " pub posotion given that pub exists.");
            return false;
        }

        DocPub pub = BuildingFactory.getPub();
        pub.remove(playerName, pub_position);

        // Decreasing student satisfaction.
        if (!decreaseSatisfaction(playerName, LotteryManager.HIGH)) {
            // This should not happen.
            return false;
        }

        // Decreasing student alcoholizm level
        if (!decreaseAlcoholizm(playerName, LotteryManager.HIGH)) {
            // This should never happen.
            return false;
        }


        return true;
    }

    @Override
    public String getInfo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isEqual(String name) {
        return (name.equals(LotteryManager.PUB_DEMOLISHED));
    }

    @Override
    public String getName() {
        return ("Pub Demolished!");
    }

    @Override
    public void writeToDb(String playerName) {
        EventsHelper eventHelper
                = new EventsHelper();
        eventHelper.setPubDemolished(playerName, getNumOfTickets());
    }
}
