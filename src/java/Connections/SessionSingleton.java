
package Connections;

import game.lecturerSystem.LecturersManager;
import javax.servlet.http.HttpSession;

public class SessionSingleton {


    public static void initializeLecturers(HttpSession session) {
        /* Responsible for reading user's lecturers from the database. */
        String userName
                = (String) session.getAttribute(ConnectionSingleton.idname);
        LecturersManager mgr = new LecturersManager(userName);
        mgr.readLecturers();
        session.setAttribute(ConnectionSingleton.LECTURERSMANAGER, mgr);


    }
}
