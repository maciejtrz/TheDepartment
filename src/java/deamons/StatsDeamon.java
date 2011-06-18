package deamons;

import ConnectionDataBase.PlayerHelper;
import ConnectionDataBase.Players;
import Connections.UserManager;
import events.Event;
import events.LabsInFire;
import java.util.Iterator;
import java.util.List;
import utilities.BuildingsUtils;
import utilities.LecturersManager;
import utilities.BuildingsUtils ;


/* A daemon thread responsible for increasing and decreasing player's statistics
   basing on their current game states. */

public class StatsDeamon extends Thread {


    // Value specifying how much a given statistic increases/decreases
    // in a given unit of time (SLEEP_TIME in minutes).
    private static final int UPDATE_VALUE = 5;

    // Value specifying how much the state of the game affects UPDATE_VALUE
    private static final int BOOST = 3;

    // The time a given thread sleeps in minutes.
    private static final int SLEEP_TIME = 1;

    private BuildingsUtils b_utils;

    // Updating the value. Dirty code, apologies for that. The increase/
    // decrease functions should be in an externs EventUtils class.
    // There is not time for a change.
    private Event event;

    public StatsDeamon() {
        setDaemon(true);
        b_utils = new BuildingsUtils();
        event = new LabsInFire();
    }


    @Override
    public void run() {

        PlayerHelper playerHelper = new PlayerHelper();

        while(true) {

            try {
            // Initially sleep for one unit of time.
            sleep(SLEEP_TIME * 1000 * 60);

            // Iterating through a player list, updating all statistics.
            List<Players> allPlayers = playerHelper.getPlayers();
            if (allPlayers == null) {
                continue;
            }
            Iterator<Players> it = allPlayers.iterator();
            while (it.hasNext()) {
                Players player_record = it.next();
                String playerName = player_record.getIdname();
                if(playerName != null) {
                    // Updating all details.
                    updateAlcoholism(playerName);
                    updateStarvation(playerName);
                    updateSatisfaction(playerName);
                    updateMoney(playerName);
                }
            }

            } catch (InterruptedException ex) {
                System.out.println("Thread " + this.getName() + " operating as a"
                        + " stats deamon got interrupted.");
            }
        }
    }


    private void updateAlcoholism(String playerName) {

        /* Increasing alcoholism value by UPDATE_VALUE. If DocPub is
           built UPDATE_VALUE is decreased by BOOST. If Superlabs are built
           UPDATE_VALUE is further decreased by BOOST. */


        int update_value = UPDATE_VALUE;
        if (b_utils.isDocPubBuilt(playerName)) {
            update_value -= BOOST;
        }
        if (b_utils.isLabBuilt(playerName)) {
            update_value -= BOOST;
        }

        if (update_value > 0) {
            event.increaseAlcoholizm(playerName, update_value);
        }
        else {
            update_value = 0 - update_value;
            event.decreaseAlcoholizm(playerName, update_value);
        }

    }

    private void updateStarvation(String playerName) {

        /* Increasing starvation value by UPDATE_VALUE. If Tresco is
           built UPDATE_VALUE is decreased by BOOST. If MacChicken is built
           UPDATE_VALUE is further decreased by BOOST. */

        int update_value = UPDATE_VALUE;
        if (b_utils.isTrescoBuilt(playerName)) {
            update_value -= BOOST;
        }

        if (b_utils.isMacChickenBuilt(playerName)) {
            update_value -= BOOST;
        }

        if (update_value > 0) {
            event.increaseStarvation(playerName, update_value);
        }
        else {
            update_value = 0 - update_value;
            event.decreaseStarvation(playerName, update_value);
        }


    }

    private void updateSatisfaction(String playerName) {

        /* Decreasing satisfaction value by UPDATE_VALUE. If DocPub is
           built UPDATE_VALUE is decreased by BOOST. If Brain is built
           UPDATE_VALUE is further decreased by BOOST. */

        int update_value = UPDATE_VALUE;
        if (b_utils.isDocPubBuilt(playerName)) {
            update_value -= BOOST;
        }

        if (b_utils.isBrainBuilt(playerName)) {
            update_value -= BOOST;
        }

        if (update_value < 0) {
            update_value  = 0 - update_value;
            event.increaseSatisfaction(playerName, update_value);
        }
        else {
            event.decreaseSatisfaction(playerName, update_value);
        }

    }

    private void updateMoney(String playerName) {

        int update_money = 0;
        int tresco_update = 200;
        int mac_chicken_update = 300;
        int doc_pub_update = 500;

        /* For each lecturers owned substract a small amount of cash. */
        LecturersManager mgr = new LecturersManager(playerName);
        int decrease_value = mgr.getOwnedLecturers().size() * UPDATE_VALUE;
        update_money =- decrease_value;

        BuildingsUtils utils = new BuildingsUtils();
        if (utils.isTrescoBuilt(playerName)) {
            update_money += tresco_update;
        }
        if (utils.isMacChickenBuilt(playerName)) {
            update_money += mac_chicken_update;
        }
        if (utils.isDocPubBuilt(playerName)) {
            update_money += doc_pub_update;
        }

        if (update_money >= 0) {
            UserManager.addMoney(playerName, update_money);
        }
        else {
            UserManager.removeMoney(playerName, decrease_value);
        }
    }
}
