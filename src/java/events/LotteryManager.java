package events;

import ConnectionDataBase.Events;
import ConnectionDataBase.EventsHelper;
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

    private EventFactory factory;

    private LotteryManager() {
        factory = new EventFactory();
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

    public void writeEventsToDB(String playerName) {
        if (event_list != null) {
            Iterator<Event> it = event_list.iterator();
            while (it.hasNext()) {
                Event next = it.next();
                next.writeToDb(playerName);
            }
        }
    }

    public void readEventsFromDB(String playerName) {
        EventsHelper events_helper = new EventsHelper();
        Events rec = events_helper.getPlayerRecord(playerName);
        Event e = null;

        // Reading each event and assigning probability to it, given
        // its probability from the database.

        // Keeps track of the event_pool index.
        int index = 0;

        int level = rec.getBarnight();
        e = factory.createBarNight();
        giveTickets(e, level, index);
        index += level;

        level = rec.getFacebookblocked();
        e = factory.createFB();
        giveTickets(e, level, index);
        index += level;

        level = rec.getGirlsarrival();
        e = factory.createGirls();
        giveTickets(e, level, index);
        index += level;

        level = rec.getGovernmentgrant();
        e = factory.createGovGrant();
        giveTickets(e, level, index);
        index += level;

        level = rec.getHaskellconference();
        e = factory.createHaskell();
        giveTickets(e, level, index);
        index += level;

        level = rec.getHighranking();
        e = factory.createHighRanking();
        giveTickets(e, level, index);
        index += level;

        level = rec.getLabshacking();
        e = factory.createLabHacking();
        giveTickets(e, level, index);
        index += level;

        level = rec.getLabsinfire();
        e = factory.createLabInFire();
        giveTickets(e, level, index);
        index += level;

        level = rec.getLecturerpromotion();
        e = factory.createLecProm();
        giveTickets(e, level, index);
        index += level;

        level = rec.getMacchickenpromotion();
        e = factory.createMacChickenPromotion();
        giveTickets(e, level, index);
        index += level;

        level = rec.getMalice();
        e = factory.createMalice();
        giveTickets(e, level, index);
        index += level;

        level = rec.getNobelprice();
        e = factory.createNobelPrice();
        giveTickets(e, level, index);
        index += level;

        level = rec.getOutofchicken();
        e = factory.createOutOfChicken();
        giveTickets(e, level, index);
        index += level;

        level = rec.getPhdpromotion();
        e = factory.createPhdPromotion();
        giveTickets(e, level, index);
        index += level;

        level = rec.getPintoscw();
        e = factory.createPintos();
        giveTickets(e, level, index);
        index += level;

        level = rec.getPaperleak();
        e = factory.createLeak();
        giveTickets(e, level, index);
        index += level;

        level = rec.getPrivatecompanygrant();
        e = factory.createPrivGrant();
        giveTickets(e, level, index);
        index += level;

        level = rec.getPubdemolished();
        e = factory.createPubDemolished();
        giveTickets(e, level, index);
        index += level;

        level = rec.getTrescomiracle();
        e = factory.createTrescoMiracle();
        giveTickets(e, level, index);
        index += level;

        level = rec.getTrescotragedy();
        e = factory.createTrescoTragedy();
        giveTickets(e, level, index);
        index += level;

        level = rec.getUnionparty();
        e = factory.createUnionParty();
        giveTickets(e, level, index);
        index += level;

        if (index == TICKETS_NUMBER) {
            System.out.println("EVERNTS READ COLLECTLY!");
        }

        else {
            System.out.println("EVENTS READ INCORRECTLY!");
        }
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

        prob_list.add(factory.createMalice());

        prob_list.add(factory.createPintos());


        prob_list.add(factory.createTrescoTragedy());


        prob_list.add(factory.createGirls());


        prob_list.add(factory.createGovGrant());


        prob_list.add(factory.createTrescoMiracle());


        prob_list.add(factory.createLeak());


        prob_list.add(factory.createPhdPromotion());


        prob_list.add(factory.createFB());


        prob_list.add(factory.createHaskell());

        index = assignProbabilities(prob_list, probability, index);


        /* Events with initial probability 5% */
        prob_list = new ArrayList<Event>();
        probability = 5;

        prob_list.add(factory.createLabHacking());


        prob_list.add(factory.createMacChickenPromotion());


        prob_list.add(factory.createOutOfChicken());


        prob_list.add(factory.createLabInFire());


        prob_list.add(factory.createLecProm());


        prob_list.add(factory.createUnionParty());

        index = assignProbabilities(prob_list, probability, index);


        /* Evetns with initial probability 3% */
        prob_list = new ArrayList<Event>();
        probability = 3;

        prob_list.add(factory.createBarNight());


        prob_list.add(factory.createPrivGrant());

        index = assignProbabilities(prob_list, probability, index);


        /* Events with initial probability 2% */
        prob_list = new ArrayList<Event>();
        probability = 2;

        prob_list.add(factory.createPubDemolished());

        index = assignProbabilities(prob_list, probability, index);


        /* Events with initial probability 1% */
        prob_list = new ArrayList<Event>();
        probability = 1;

        prob_list.add(factory.createHighRanking());


        prob_list.add(factory.createNobelPrice());

        index = assignProbabilities(prob_list, probability, index);


        if (index == TICKETS_NUMBER) {
            System.out.println("PROBABILITIES SUCCESSFULLY ASSIGNED!");
        } else {
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


    private class EventFactory {

        public BarNight createBarNight() {
            BarNight barNight = new BarNight();
            barNight.setOnChangeList(onAlcoIncrease);
            event_list.add(barNight);
            return barNight;
        }

        public FacebookBlocked createFB() {
            FacebookBlocked facebookBlocked = new FacebookBlocked();
            facebookBlocked.setOnChangeList(onSatiDecrease);
            event_list.add(facebookBlocked);
            return facebookBlocked;
        }

        public GirlsArrival createGirls() {
            GirlsArrival girlsArrival = new GirlsArrival();
            girlsArrival.setOnChangeList(onSatiIncrease);
            event_list.add(girlsArrival);
            return girlsArrival;
        }

        public GovernmentGrant createGovGrant() {
            GovernmentGrant govGrant = new GovernmentGrant();
            govGrant.setOnChangeList(onRestChanges);
            event_list.add(govGrant);
            return govGrant;
        }

        public HighRanking createHighRanking() {
            HighRanking highRanking = new HighRanking();
            highRanking.setOnChangeList(onSatiIncrease);
            event_list.add(highRanking);
            return highRanking;
        }

        public HaskellConference createHaskell() {
            HaskellConference haskellConference = new HaskellConference();
            haskellConference.setOnChangeList(onRestChanges);
            event_list.add(haskellConference);
            return haskellConference;
        }

        public LabHacking createLabHacking() {
            LabHacking labHacking = new LabHacking();
            labHacking.setOnChangeList(onSatiDecrease);
            event_list.add(labHacking);
            return labHacking;
        }

        public LabsInFire createLabInFire() {
            LabsInFire labsInFire = new LabsInFire();
            labsInFire.setOnChangeList(onAlcoIncrease);
            event_list.add(labsInFire);
            return labsInFire;
        }

        public LecturerPromotion createLecProm() {
            LecturerPromotion lecturerPromotion = new LecturerPromotion();
            lecturerPromotion.setOnChangeList(onRestChanges);
            event_list.add(lecturerPromotion);
            return lecturerPromotion;
        }

        public MacChickenPromotion createMacChickenPromotion() {
            MacChickenPromotion macPromotion = new MacChickenPromotion();
            macPromotion.setOnChangeList(onStarDecrease);
            event_list.add(macPromotion);
            return macPromotion;
        }

        public Malice createMalice() {

            Malice malice = new Malice();
            malice.setOnChangeList(onSatiDecrease);
            event_list.add(malice);
            return malice;
        }

        public NobelPrice createNobelPrice() {
            NobelPrice nobel = new NobelPrice();
            nobel.setOnChangeList(onSatiIncrease);
            event_list.add(nobel);
            return nobel;
        }

        public OutOfChicken createOutOfChicken() {
            OutOfChicken outOfChicken = new OutOfChicken();
            outOfChicken.setOnChangeList(onStarIncrease);
            event_list.add(outOfChicken);
            return outOfChicken;
        }

        public PaperLeak createLeak() {
            PaperLeak paperLeak = new PaperLeak();
            paperLeak.setOnChangeList(onSatiIncrease);
            event_list.add(paperLeak);
            return paperLeak;
        }

        public PintosCw createPintos() {
            PintosCw pintos = new PintosCw();
            pintos.setOnChangeList(onAlcoIncrease);
            event_list.add(pintos);
            return pintos;
        }

        public PhdPromotion createPhdPromotion() {
            PhdPromotion phdPromotion = new PhdPromotion();
            phdPromotion.setOnChangeList(onRestChanges);
            event_list.add(phdPromotion);
            return phdPromotion;
        }

        public PrivateCompanyGrant createPrivGrant() {
            PrivateCompanyGrant privGrant = new PrivateCompanyGrant();
            privGrant.setOnChangeList(onRestChanges);
            event_list.add(privGrant);
            return privGrant;
        }

        public PubDemolished createPubDemolished() {
            PubDemolished pubDemolished = new PubDemolished();
            pubDemolished.setOnChangeList(onAlcoDecrease);
            event_list.add(pubDemolished);
            return pubDemolished;
        }

        public TrescoMiracle createTrescoMiracle() {
            TrescoMiracle trescoMiracle = new TrescoMiracle();
            trescoMiracle.setOnChangeList(onStarDecrease);
            event_list.add(trescoMiracle);
            return trescoMiracle;
        }

        public TrescoTragedy createTrescoTragedy() {
            TrescoTragedy tresco = new TrescoTragedy();
            tresco.setOnChangeList(onStarIncrease);
            event_list.add(tresco);
            return tresco;
        }

        public UnionParty createUnionParty() {
            UnionParty union = new UnionParty();
            union.setOnChangeList(onAlcoDecrease);
            event_list.add(union);
            return union;
        }
    }


}
