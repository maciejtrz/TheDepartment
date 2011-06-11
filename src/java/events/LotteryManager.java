package events;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


/* Responsible for managing lottery system. In order for it to
   work properly we must have at least two events in the pool. */

public class LotteryManager {

    // Defines String names of each even.
    public static final String BAR_NIGHT = "BAR_NIGHT";
    public static final String LAB_HACKING = "LAB_HACKING";
    public static final String LABS_FIRE = "LABS_FIRE";
    public static final String NOBEL_PRICE = "NOBEL_PRICE";
    public static final String OUT_OF_CHICKEN = "OUT_OF_CHICKEN";
    public static final String PAPER_LEAK = "PAPER_LEAK";
    public static final String PHD_PROMOTION = "PHD_PROMOTION";
    public static final String PINTOS_CW = "PINOTS_CW";
    public static final String TRESCO_TRAGEDY = "TRESCO_TRAGEDY";
    public static final String PUB_DEMOLISHED = "PUB_DEMOLISHED";
    public static final String HIGH_RANKING = "HIGH_RANKING";
    public static final String MALICE = "MALICE";
    public static final String UNION_PARTY = "UNION_PARTY";
    public static final String FACEBOOK_BLOCKED = "FACEBOOK_BLOCKED";
    public static final String HASKELL_CONFERENCE = "HASKELL_CONFERENCE";
    public static final String LECTURER_PROMOTION = "LECTURER_PROMOTION";


    private static LotteryManager mgr = null;
    private static final int TICKETS_NUMBER = 100;

    private Ticket[] lottery_pool;
    private List<Event> event_list;



    private LotteryManager() {
        lottery_pool = new Ticket[TICKETS_NUMBER];
        initializeLottery();
        event_list = new ArrayList<Event>();
    }

    public static LotteryManager getManager() {
        if (mgr == null) {
            mgr = new LotteryManager();
        }
        return mgr;
    }

    // Generates a winner of the lottery.
    public Event getWinner() {
        // Getting winner.
        Event output = getRandEvent();


        /* Decreasing probability of the event happening again. */

        // Getting a random event different than the one already choosen.
        // The probability of choosing the second even is also dependent on the
        // lottery
        Event lucky_event = getRandEvent();
        while (lucky_event.equals(output)) {
            lucky_event = getRandEvent();
        }
        // Dontating the ticket to the lucky event only if the choosen event has
        // more than one ticket left.
        if (output.getNumOfTickets() > 1) {
            Ticket t = output.getOneTicket();
            t.donate(lucky_event);
        }
        
        return output;
    }

    // Used in event dynamic probability changing.
    public Event getRandEvent() {
        Random rand = new Random();
        int index = rand.nextInt(TICKETS_NUMBER);
        Ticket t = lottery_pool[index];
        return (t.getOwner());
    }

    // Obtains an event given its name in String. Used in order to get
    // rid of many getters etc.
    public Event getEvent(String event_name) {
        Event event = null;
        Iterator<Event> it = event_list.iterator();
        while (it.hasNext()) {
            event = it.next();
            if (event.isEqual(event_name)) {
                return event;
            }

        }
        return null;
    }

    // Initializes the lottery, assigning inital probabilities
    // to all events. Everything is hardcoded. Note, number of tickers
    // assigned has to sum to TICKETS_NUMBER
    private void initializeLottery() {

        int index = 0;

        Event labsInFire = new LabsInFire();
        // Giving labs 20% chances.
        int chances = 20;
        giveTickets(labsInFire , chances , index);
        index += chances;

        Event pintosCw = new PintosCw();
        // Giving pintos 20% chances.
        chances = 20;
        giveTickets(pintosCw, chances, index);
        index += chances;

        Event barNight = new BarNight();
        // Giving barNight 20% chances.
        chances = 20;
        giveTickets(barNight, chances, index);
        index += chances;

        Event labHacking = new LabHacking();
        // Giving labHacking 10% chances.
        chances = 10;
        giveTickets(labHacking, chances, index);
        index += chances;

        Event phdPromotion = new PhdPromotion();
        // Giving phdPromotion 10% chances
        chances = 10;
        giveTickets(phdPromotion, chances, index);
        index += chances;


        Event paperLeak = new PaperLeak();
        // Giving paperLeak 19% chances
        chances = 19;
        giveTickets(paperLeak, chances, index);
        index += 19;

        Event nobel = new NobelPrice();
        // Giving nobel price 1% chances
        chances = 1;
        giveTickets(nobel, chances, index);
        index += 1;


    }

    // Returns a random event from the pool.
    private Event getRandomEvent() {
        Random rand = new Random();
        int index = rand.nextInt(event_list.size());
        return event_list.get(index);
    }

    // Creates n ticket for a given event. Also, asigns n places for that
    // tickets in a lottery_pool, starting from start_position and up to
    // (start_position + n - 1)
    private void giveTickets(Event event , int n , int start_position) {
        for (int i = 0 ; i < n ; i ++) {
            Ticket t = new Ticket (event);
            event.addOneTicket(t);
            int index = start_position + i;
            lottery_pool[index] = t;
        }
    }





}
