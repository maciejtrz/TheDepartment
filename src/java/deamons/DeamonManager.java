
package deamons;



/* Singleton responsible for creation and destruction of
   any background threads. */

public class DeamonManager {

    private static DeamonManager mgr =  null;

    /* Lecturers populating thread's control variable. */
    private static volatile boolean lec_pop_started = false;
    private static Thread lecturers_populator = null;

    /* Event manager thread's control variable. */
    private static volatile boolean event_deamon_started = false;
    private static Thread event_deamon = null;

    private DeamonManager() {

    }

    public static DeamonManager getManager() {
        if (mgr == null) {
            mgr = new DeamonManager();
        }
        return mgr;
    }

    public synchronized void createLecturersPopulator() {
        if (!lec_pop_started && lecturers_populator == null) {
            lec_pop_started = true;
            System.out.println
                    ("MANAGER is starting new Lecturer populator thread.");
            lecturers_populator = new LecturersPopulator();
            lecturers_populator.start();
        }
    }

    public synchronized void createEventDeamon() {
        if (!event_deamon_started && event_deamon == null) {
            event_deamon_started = true;
            System.out.println
                    ("MANAGER is starting new Event deamon thread.");
            event_deamon = new EventDeamon();
            event_deamon.start();
        }
    }

}
