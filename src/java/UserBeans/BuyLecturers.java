

package UserBeans;

import Connections.ConnectionSingleton;
import game.lecturerSystem.LecturersManager;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


public class BuyLecturers {

    public String lecturer;

    /** Creates a new instance of BuyLecturers */
    public BuyLecturers() {

    }


    public String getLecturer () {
        return lecturer;
    }

    public void setLecturer(String lec) {
        lecturer = lec;
    }

    public void buy() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        LecturersManager mgr = (LecturersManager) session.getAttribute(ConnectionSingleton.LECTURERSMANAGER);
        mgr.purchaseLecturer(lecturer);
    }

}