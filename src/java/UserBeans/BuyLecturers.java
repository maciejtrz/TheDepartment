package UserBeans;

import java.io.Serializable;
import java.util.List;
import utilities.Lecturer;
import utilities.LecturerBenefits;
import utilities.LecturersManager;


public class BuyLecturers implements Serializable  {

    private Lecturer selected_lecturer;
    private List<Lecturer> lecturers;

    /** Creates a new instance of BuyLecturers */
    public BuyLecturers() {
        //System.out.println("The lecturers constructor is called");
        LecturersManager mgr
                = new LecturersManager(utilities.BasicUtils.getUserName());
        lecturers = mgr.getAvailabeLecturers();
    }

    public List<Lecturer> getLecturers() {
        //System.out.println("The lecturers getter is called!");
        return lecturers;
    }

    public void setLecturers(List<Lecturer> input) {
        lecturers = input;
    }

    public Lecturer getSelected () {
        return selected_lecturer;
    }

    public void setSelected(Lecturer lec) {
        selected_lecturer = lec;
    }

    public List<LecturerBenefits> getBenefitsList() {
        return selected_lecturer.getSpecializations();
    }

    public void buy() {
        LecturersManager mgr 
                = new LecturersManager(utilities.BasicUtils.getUserName());
        mgr.purchaseLecturer(selected_lecturer.getName());
    }


}