package UserBeans;

import ConnectionDataBase.CapacityHelper;
import ConnectionDataBase.LecturersAvailableHelper;
import ConnectionDataBase.LecturersOwnedHelper;
import ConnectionDataBase.Playerresources;
import Connections.ConnectionSingleton;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import utilities.BasicUtils;
import utilities.Lecturer;
import utilities.LecturerBenefits;
import utilities.LecturersManager;


public class BuyLecturers  {

    private Lecturer selected_lecturer;
    private List<Lecturer> lecturers;
    private LecturersManager mgr;
    private LecturersOwnedHelper owned;
    private String username;

    /** Creates a new instance of BuyLecturers */
    public BuyLecturers() {

        this.owned = new LecturersOwnedHelper();
        this.username = BasicUtils.getUserName();
        this.mgr = new LecturersManager(username);
        lecturers = mgr.getAvailabeLecturers();
    }

    public String getUsername() {
        return username;
    }

    public List<Lecturer> getLecturers() {
        return lecturers;
    }

    public void setLecturers(List<Lecturer> input) {
        lecturers = input;
    }

    public Lecturer getSelected_lecturer () {
        return selected_lecturer;
    }

    public void setSelected_lecturer (Lecturer lec) {
        selected_lecturer = lec;
    }

    public List<LecturerBenefits> getBenefitsList() {
        return selected_lecturer.getSpecializations();
    }

    public void buy() {

        System.out.println("buying prof");

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        Auth auth = (Auth) session.getAttribute(ConnectionSingleton.auth);

        Playerresources resources = auth.getResources();
        CapacityHelper capacityhelper = new CapacityHelper();
        
        System.out.println(" cases start");

        if( capacityhelper.getCapacity(auth.getUsername()).getProfessorscapacity() <=  mgr.getOwnedLecturers().size() ){

            System.out.println("capacity fail");

                FacesContext.getCurrentInstance().addMessage(
                BasicUtils.findComponent(facesContext.getViewRoot(),"submitProf").getClientId(facesContext),
                new FacesMessage(FacesMessage.SEVERITY_ERROR," Me ", "Not enought space for new Prof!!!"));
                return;

        } else if ( resources.getMoney() < selected_lecturer.getPrice()){

            System.out.println("money fail");

             FacesContext.getCurrentInstance().addMessage(
             BasicUtils.findComponent(facesContext.getViewRoot(),"submitProf").getClientId(facesContext),
             new FacesMessage(FacesMessage.SEVERITY_ERROR," Me ", "This guy is too expensive for your DoC!!!"));
             return;
        }

        /* if trasaction ok then:
         * 1) get the price of selected
         * 2) remove from avauilable lec
         * 3) add lec to player lec
         * 3a) update capacity
         * 4) subtract the price from players money
         * 5) DONE !
         */

        System.out.println("buying procedure");
        LecturersAvailableHelper avail = new LecturersAvailableHelper();


        int price = getSelected_lecturer().getPrice();
        avail.deleteLecturer(getSelected_lecturer().getName());
        this.owned.addLecturer(getSelected_lecturer().getName(), username);
        //capacity thing to be done
        resources.setMoney(resources.getMoney()-price);


    }




}