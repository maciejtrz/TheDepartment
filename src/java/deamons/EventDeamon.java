package deamons;

import ConnectionDataBase.DepartmentinfoHelper;
import ConnectionDataBase.MessageSystemHelper;
import ConnectionDataBase.PlayerHelper;
import ConnectionDataBase.Players;
import events.Event;
import events.LotteryManager;
import java.util.Iterator;
import java.util.List;

public class EventDeamon extends Thread {

    private static final int SLEEP_TIME = 1;

    public EventDeamon() {
        setDaemon(true);
    }

    @Override
    public void run() {

        PlayerHelper playersHelper = new PlayerHelper();
        DepartmentinfoHelper deptInfoHelper = new DepartmentinfoHelper();
       
        System.out.println("Event deamon started operating!");

        boolean stop = false;

        while (!stop) {

            try {
           /* Sleep for four minutes. */
           System.out.println("Event daemon " + this.getName() +
                   " is going to sleep for two minutes.");
           sleep(1000 * 34 * SLEEP_TIME);

           List<Players> allPlayers = playersHelper.getPlayers();
                if (allPlayers == null) {
                    System.out.println("Inconsistency in a database, player" +
                            " record does no exist.");
                    continue;
                }

                Iterator<Players> it = allPlayers.iterator();
                while (it.hasNext()) {
                    Players player = it.next();
                    String idname = player.getIdname();

                    if (idname != null) {
                        // Checking whether a given player has a department
                        boolean hasDept = deptInfoHelper.hasDepartment(idname);
                        if (!hasDept) {
                            // If not continue to another player.
                            continue;
                        }
                    }

                    // Initializing a random event.
                    LotteryManager mgr = new LotteryManager(idname);
                    mgr.readEventsFromDB();
                    Event e = mgr.getWinner();
                    e.invoke(idname);

                    MessageSystemHelper helper = new MessageSystemHelper();
                    helper.createMessage("Main advisor",idname , e.getName(), e.getInfo(), 0);
                    mgr.writeEventsToDB();
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("Event thread " + getName() + " got interrupted. Stopping. ");
                stop = true;
            }
        }
        
    }
}
