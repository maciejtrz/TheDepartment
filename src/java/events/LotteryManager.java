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
    public static final String GIRLS_ARRIVAL = "GIRLS_ARRIVAL";
    public static final String TRESCO_MIRACLE = "TRESCO_MIRACLE";
    public static final String MAC_PROMOTION = "MAC_CHICKEN_PROMOTION";
    public static final String GOVERNMENT_GRANT = "GOVERNMENT_GRANT";
    public static final String PRIVATE_INVESTOR_GRANT = "PRIVATE_GRANT";

    // Event special statistics update levels
    public static final int LOW = 5;
    public static final int MEDIUM = 10;
    public static final int HIGH = 15;
    public static final int MEGA = 20;

    // Statistics upper and lower bound
    public static final int LOWER_BOUND = 0;
    public static final int UPPER_BOUND = 100;


    private static LotteryManager mgr = null;
    private static final int TICKETS_NUMBER = 100;

    private Ticket[] lottery_pool;
    private List<Event> event_list;

    /* Extra stats dependent lists. */
    private List<Event> onAlcoIncrease;
    private List<Event> onAlcoDecrease;
    private List<Event> onSatiIncrease;
    private List<Event> onSatiDecrease;
    private List<Event> onStarIncrease;
    private List<Event> onStarDecrease;
    private List<Event> onRestChanges;


    private LotteryManager() {
        lottery_pool = new Ticket[TICKETS_NUMBER];
        event_list = new ArrayList<Event>();
        onAlcoIncrease = new ArrayList<Event>();
        onAlcoDecrease = new ArrayList<Event>();
        onSatiIncrease = new ArrayList<Event>();
        onSatiDecrease = new ArrayList<Event>();
        onStarIncrease = new ArrayList<Event>();
        onStarDecrease = new ArrayList<Event>();
        onRestChanges = new ArrayList<Event>();
        initializeLottery();
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

            // Also increasing the probability of one of the onList group member.
            Event on_list_event
                    = getRandomEventFromList(output.getOnChangeList());
            if (!on_list_event.equals(output)) {
                // Donating its probability to some unlucky random event.
                Event unlucky_event = getRandEvent();
                while (unlucky_event.equals(on_list_event)) {
                       unlucky_event = getRandEvent();
                }
                if (unlucky_event.getNumOfTickets() > 1) {
                    t = unlucky_event.getOneTicket();
                    t.donate(on_list_event);
                }
            }
        }
        
        return output;
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

    // Used in event dynamic probability changing.
    private Event getRandEvent() {
        Random rand = new Random();
        int index = rand.nextInt(TICKETS_NUMBER);
        Ticket t = lottery_pool[index];
        return (t.getOwner());
    }

    // Initializes the lottery, assigning inital probabilities
    // to all events. Everything is hardcoded. Note, number of tickets
    // assigned has to sum to TICKETS_NUMBER
    private void initializeLottery() {

        /* Keep trace of the current index number. */
        int index = 0;
        int probability = 6;

        /*  Events with initial probability 6% */
        List<Event> prob_list = new ArrayList<Event>();
        Event malice = new Malice();
        malice.setOnChangeList(onSatiDecrease);
        event_list.add(malice);
        prob_list.add(malice);

        Event pintosCw = new PintosCw();
        pintosCw.setOnChangeList(onSatiDecrease);
        event_list.add(pintosCw);
        prob_list.add(pintosCw);

        Event tresco = new TrescoTragedy();
        tresco.setOnChangeList(onStarIncrease);
        event_list.add(tresco);
        prob_list.add(tresco);

        Event girlsArrival = new GirlsArrival();
        girlsArrival.setOnChangeList(onSatiIncrease);
        event_list.add(girlsArrival);
        prob_list.add(girlsArrival);

        Event govGrant = new GovernmentGrant();
        govGrant.setOnChangeList(onRestChanges);
        event_list.add(govGrant);
        prob_list.add(govGrant);

        Event trescoMiracle = new TrescoMiracle();
        trescoMiracle.setOnChangeList(onStarDecrease);
        event_list.add(trescoMiracle);
        prob_list.add(trescoMiracle);

        Event paperLeak = new PaperLeak();
        paperLeak.setOnChangeList(onSatiIncrease);
        event_list.add(paperLeak);
        prob_list.add(paperLeak);

        Event phdPromotion = new PhdPromotion();
        phdPromotion.setOnChangeList(onRestChanges);
        event_list.add(phdPromotion);
        prob_list.add(phdPromotion);

        Event facebookBlocked = new FacebookBlocked();
        facebookBlocked.setOnChangeList(onSatiDecrease);
        event_list.add(facebookBlocked);
        prob_list.add(facebookBlocked);

        Event haskellConference = new HaskellConference();
        haskellConference.setOnChangeList(onRestChanges);
        event_list.add(haskellConference);
        prob_list.add(haskellConference);

        index = assignProbabilities(prob_list, probability , index);


        /* Events with initial probability 5% */
        prob_list = new ArrayList<Event>();
        probability = 5;

        Event labHacking = new LabHacking();
        labHacking.setOnChangeList(onSatiDecrease);
        event_list.add(labHacking);
        prob_list.add(labHacking);

        Event macPromotion = new MacChickenPromotion();
        macPromotion.setOnChangeList(onStarDecrease);
        event_list.add(macPromotion);
        prob_list.add(macPromotion);

        Event outOfChicken = new OutOfChicken();
        outOfChicken.setOnChangeList(onStarIncrease);
        event_list.add(outOfChicken);
        prob_list.add(outOfChicken);

        Event labsInFire = new LabsInFire();
        labsInFire.setOnChangeList(onAlcoIncrease);
        event_list.add(labsInFire);
        prob_list.add(labsInFire);

        Event lecturerPromotion = new LecturerPromotion();
        lecturerPromotion.setOnChangeList(onRestChanges);
        event_list.add(lecturerPromotion);
        prob_list.add(lecturerPromotion);

        Event union = new UnionParty();
        union.setOnChangeList(onAlcoDecrease);
        event_list.add(union);
        prob_list.add(union);

        index = assignProbabilities(prob_list, probability , index);


        /* Evetns with initial probability 3% */
        prob_list = new ArrayList<Event>();
        probability = 3;

        Event barNight = new BarNight();
        barNight.setOnChangeList(onAlcoIncrease);
        event_list.add(barNight);
        prob_list.add(barNight);

        Event privGrant = new PrivateCompanyGrant();
        privGrant.setOnChangeList(onRestChanges);
        event_list.add(privGrant);
        prob_list.add(privGrant);

        index = assignProbabilities(prob_list, probability , index);


        /* Events with initial probability 2% */
        prob_list = new ArrayList<Event>();
        probability = 2;

        Event pubDemolished = new PubDemolished();
        pubDemolished.setOnChangeList(onAlcoDecrease);
        event_list.add(pubDemolished);
        prob_list.add(pubDemolished);

        index = assignProbabilities(prob_list, probability , index);


        /* Events with initial probability 1% */
        prob_list = new ArrayList<Event>();
        probability = 1;

        Event highRanking = new HighRanking();
        highRanking.setOnChangeList(onSatiIncrease);
        event_list.add(highRanking);
        prob_list.add(highRanking);

        Event nobel = new NobelPrice();
        nobel.setOnChangeList(onSatiIncrease);
        event_list.add(nobel);
        prob_list.add(nobel);

        index = assignProbabilities(prob_list, probability , index);

        
        if (index == TICKETS_NUMBER) {
            System.out.println("PROBABILITIES SUCCESSFULLY ASSIGNED!");
        }
        else {
            System.out.println("INCORECT INDEX: " + index);
        }
    }

    // Returns a random event from the given list.
    private Event getRandomEventFromList(List<Event> input_list) {
        Random rand = new Random();
        int index = rand.nextInt(input_list.size());
        return input_list.get(index);
    }

    // Assigns objects from the input lists with a probability value specified
    // by prob_value, also populating lottery_pool starting from input index.
    // Returns the index value after the operation.
    private int assignProbabilities(List<Event> events , int probability, int index) {
        Iterator<Event> it = events.iterator();
        while (it.hasNext()) {
            if (index >= TICKETS_NUMBER) {
                break;
            }
            Event e = it.next();
            giveTickets(e, probability, index);
            index += probability;
        }
        return index;
        
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
