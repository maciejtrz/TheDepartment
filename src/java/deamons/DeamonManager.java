
package deamons;



/* Singleton responsible for creation and destruction of
   any background threads. */

public class DeamonManager {

    private static DeamonManager mgr =  null;

    /* Lecturers populating thread's control variable. */
    private boolean lec_pop_started = false;
    private Thread lecturers_populator = null;


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
            System.out.println(" MANAGER is starting new thread. !!!!");
            lecturers_populator = new LecturersPopulator();
            lec_pop_started = true;
        }
    }

}
