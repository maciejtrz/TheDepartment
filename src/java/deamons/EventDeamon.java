package deamons;

import events.Event;
import events.LotteryManager;

public class EventDeamon extends Thread {

    public EventDeamon() {
        setDaemon(true);
    }

    @Override
    public void run() {
        System.out.println("Event deamon is running.");
        LotteryManager mgr = LotteryManager.getManager();
        while (true) {
            Event e = mgr.getWinner();
            System.out.println("By " + getName() +" THE WINNER IS: " + e.getName());
            System.out.println("AND THE WINNER'S PROB: " + e.getNumOfTickets());
            try {
                System.out.println("Event thread goind to sleep");
                sleep(1000);
            }
            catch (Exception ex) {
                System.out.println("Event thread got interrupted. ");
            }
        }
        
    }
}
