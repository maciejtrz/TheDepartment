
package events;

import ConnectionDataBase.Extrastats;
import ConnectionDataBase.ExtrastatsHelper;
import java.util.List;
import java.util.ArrayList;

public abstract class Event {

    private List<Ticket> tickets;

    public Event() {
        tickets = new ArrayList<Ticket>();
    }

    // Returns and removes one ticket from this Event object.
    // Should never be called when the object has only one or zero tickets
    // (it is the responsibility of a LotteryManager to ensure that.
    public Ticket getOneTicket() {
        Ticket ticket = null;
        int size = getNumOfTickets();
        if (size > 2) {
            // Get the first ticket
            ticket = tickets.remove(size - 1);
        }
        return ticket;
    }

    // Adds one ticket to the tickets lists of this object. Used when
    // donating tickets. Note, it is the responsibility of a ticket
    // object to donate itself properly.
    public void addOneTicket(Ticket t) {
        tickets.add(t);
    }

    public int getNumOfTickets() {
        return tickets.size();
    }

    protected int getIncreasedValue(int level, int update_value) {
        return (level < (LotteryManager.UPPER_BOUND - update_value)) ?
               (level + update_value) : LotteryManager.UPPER_BOUND;
    }

    protected int getDecreasedValue(int level, int update_value) {
        return (level > (LotteryManager.LOWER_BOUND + update_value)) ?
               (level - update_value) : LotteryManager.LOWER_BOUND;
    }

    protected boolean increaseSatisfaction (String playerName , int value) {

        ExtrastatsHelper statsHelper
                = new ExtrastatsHelper();

        Extrastats stats_record
                = statsHelper.getPlayerStatsRecrod(playerName);
        if(stats_record == null) {
            System.err.println("Player record does not exist!");
            return false;
        }

        int satisfaction = stats_record.getSatisfaction();
        int sat_update
              = this.getIncreasedValue(satisfaction, value);

        statsHelper.updateSatisfaction(playerName, sat_update);
        return true;
    }

    protected boolean decreaseSatisfaction (String playerName, int value) {

        ExtrastatsHelper statsHelper
                = new ExtrastatsHelper();

        Extrastats stats_record
                = statsHelper.getPlayerStatsRecrod(playerName);
        if(stats_record == null) {
            System.err.println("Player record does not exist!");
            return false;
        }

        int satisfaction = stats_record.getSatisfaction();
        int sat_update
              = this.getDecreasedValue(satisfaction, value);

        statsHelper.updateSatisfaction(playerName, sat_update);
        return true;
    }

    protected boolean increaseAlcoholizm (String playerName, int value) {

        ExtrastatsHelper statsHelper
                = new ExtrastatsHelper();

        Extrastats stats_record
                = statsHelper.getPlayerStatsRecrod(playerName);
        if(stats_record == null) {
            System.err.println("Player record does not exist!");
            return false;
        }

        int alcoholizm = stats_record.getAlcoholizm();
        int alco_update
              = this.getIncreasedValue(alcoholizm, value);
        
        statsHelper.updateAlcoholizm(playerName, alco_update);
        return true;
    }

    protected boolean decreaseAlcoholizm (String playerName, int value) {

        ExtrastatsHelper statsHelper
                = new ExtrastatsHelper();

        Extrastats stats_record
                = statsHelper.getPlayerStatsRecrod(playerName);
        if(stats_record == null) {
            System.err.println("Player record does not exist!");
            return false;
        }

        int alcoholizm = stats_record.getAlcoholizm();
        int alco_update
              = this.getDecreasedValue(alcoholizm, value);

        statsHelper.updateAlcoholizm(playerName, alco_update);
        return true;
    }

    protected boolean increaseStarvation (String playerName, int value) {

        ExtrastatsHelper statsHelper
                = new ExtrastatsHelper();

        Extrastats stats_record
                = statsHelper.getPlayerStatsRecrod(playerName);
        if(stats_record == null) {
            System.err.println("Player record does not exist!");
            return false;
        }

        int starvation = stats_record.getStarvation();
        int starv_update
              = this.getIncreasedValue(starvation, value);

        statsHelper.updateStarvation(playerName, starv_update);
        return true;
    }

    protected boolean decreaseStarvation (String playerName, int value) {

        ExtrastatsHelper statsHelper
                = new ExtrastatsHelper();

        Extrastats stats_record
                = statsHelper.getPlayerStatsRecrod(playerName);
        if(stats_record == null) {
            System.err.println("Player record does not exist!");  
            return false;
        }

        int starvation = stats_record.getStarvation();
        int starv_update
              = this.getDecreasedValue(starvation, value);

        statsHelper.updateStarvation(playerName, starv_update);
        return true;
    }


    // Invokes a given event.
    public abstract boolean invoke(String playerName);

    // Returns the information about a given event.
    public abstract String getInfo();

    // Returns true if input name corresponds to the given event.
    public abstract boolean isEqual(String name);


}
