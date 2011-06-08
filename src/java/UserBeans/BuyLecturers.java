

package UserBeans;

import utilities.LecturersManager;


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
        LecturersManager mgr 
                = new LecturersManager(utilities.BasicUtils.getUserName());
        mgr.purchaseLecturer(lecturer);
    }

}