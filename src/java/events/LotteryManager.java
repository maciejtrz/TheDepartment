package events;

public class LotteryManager {

    private LotteryManager mgr = null;
    private static final int TICKETS_NUMBER = 100;

    private Ticket[] lottery_pool;



    private LotteryManager() {
        lottery_pool = new Ticket[TICKETS_NUMBER];
    }

    public LotteryManager getManager() {
        if (mgr == null) {
            mgr = new LotteryManager();
        }
        return mgr;
    }

    // Initializes the lottery, assigning inital probabilities
    // to all events. Everything is hardcoded. Note, number of tickers
    // assigned has to sum to TICKETS_NUMBER
    public void initializeLottery() {

        Event LabsInFire = new LabsInFire();
        Event PintosCw = new PintosCw();
        Event BarNight = new BarNight();
    }

    // Generates a winner of the lottery.
    public Event getWinner() {
        Event e = null;
        return e;
    }

    // Creates n ticket for a given event. Also, asigns n places for that
    // tickets in a lottery_pool, starting from start_position and up to
    // (start_position + n - 1)
    private void giveTickets(Event event , int n) {
        for (int i = 0 ; i < n ; i ++) {
            
        }
    }
}
