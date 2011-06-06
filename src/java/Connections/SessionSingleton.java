
package Connections;

import game.lecturerSystem.Lecturer;
import game.lecturerSystem.LecturersManager;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.http.HttpSession;

public class SessionSingleton {


    public static void initializeLecturers(HttpSession session) {
        /* Responsible for reading user's lecturers from the database. */
        String userName
                = (String) session.getAttribute(ConnectionSingleton.idname);
        LecturersManager mgr = new LecturersManager(userName);

        System.out.println("READING LECTURERS");
        mgr.readLecturers();
        session.setAttribute(ConnectionSingleton.LECTURERSMANAGER, mgr);

        /* Testing 
        mgr.purchaseLecturer("Dziadzia");

        ArrayList<Lecturer> lecturers_av = mgr.getAvailableLecturers();
        ArrayList<Lecturer> lecturers_owned = mgr.getOwnedLecturers();

        Iterator<Lecturer> it = lecturers_av.iterator();
        while (it.hasNext()) {
            System.out.println("AVAILABLE LECTURER");
            Lecturer l = it.next();
            System.out.println(l.getName() + " " + l.getPrice() + " " +
                    l.getRpContribution());
        }
        it = lecturers_owned.iterator();
        while (it.hasNext()) {
            System.out.println("OWNED LECTURER");
            Lecturer l = it.next();
            System.out.println(l.getName() + " " + l.getPrice() + " " +
                    l.getRpContribution());
        }


        */
    }
}
