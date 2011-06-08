/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ResearchPoints;

import ConnectionDataBase.LecturersHelper;
import ConnectionDataBase.Research;
import Connections.ConnectionSingleton;
import utilities.LecturerBenefits;
import utilities.LecturersManager;
import utilities.Lecturer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

/**
 *
 * @author karol
 */
public class AddResearch {

    /* The title of the research */
    private String name;

    /* Research points awarded to the player for successful completion */
    private int ResearchPoints;

    /* A vector of choosen lecturers. */
    private List<String> chosenLecturers;
    private List<SelectItem> lecturers;
    private List<SelectItem> subjects;

    private String[] subjectList = {"AI", "Machine Learning",
        "Compilers", "Operating System Design", "Networks and Communication",
        "Models of Computation"
    };


    private Integer subject;  // The index of the selected item
    // can be given as String/Integer/int ...

    public AddResearch() {
        chosenLecturers = new ArrayList<String>();
        subjects = new ArrayList<SelectItem>();
        lecturers = new ArrayList<SelectItem>();

        String playerName = utilities.BasicUtils.getUserName();
        LecturersManager mgr
                = new LecturersManager(playerName);

        ArrayList<Lecturer> owned_lecturers = mgr.getOwnedLecturers();

        for (int i = 0; i < owned_lecturers.size(); i++) {
            Lecturer lec = owned_lecturers.get(i);
            if (lec.getUsable()) {
                // Add lecturer only if he is not occupied with another
                // research.
                lecturers.add(new SelectItem(new Integer(i),
                        owned_lecturers.get(i).getName()));
            }
        }

        // Adding subjects names.
        for (int i = 0; i < subjectList.length; i++) {
            subjects.add(new SelectItem(new Integer(i), subjectList[i]));
        }
    }

    public List getSubjects() {
        return subjects;
    }

    public Integer getSubject() {
        return subject;
    }

    public void setSubject(Integer nextSWVersion) {
        subject = nextSWVersion;
    }

    public void setLecturers(List chosenLects) {
        for (int i = 0; i < chosenLects.size(); i++) {
            System.out.println("ADDING: " + chosenLects.get(i));
            String selected_item = chosenLects.get(i).toString();
            SelectItem item = lecturers.get(Integer.parseInt(selected_item));
            System.out.println("Adding " + item.getLabel());
            chosenLecturers.add(item.getLabel());
        }
    }

    public List getLecturers() {
        return new ArrayList();
    }

    public List getLecturerList() {
        return lecturers;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setResearchPoints(int rp) {
        ResearchPoints = rp;
    }

    public String getName() {
        return name;
    }

    public int getResearchPoints() {
        return ResearchPoints;
    }

    public String startResearch() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session
            = (HttpSession) facesContext.getExternalContext().getSession(false);
        Research research = new Research();

        /* Adding research thread to the list of researches of the given user */
        ResearchBag researchBag 
          = (ResearchBag) session.getAttribute
                         (Connections.ConnectionSingleton.researchBag);

        /* Reading lecturers from the database. */
        LecturersManager mgr 
              = new LecturersManager(utilities.BasicUtils.getUserName());
        ArrayList<Lecturer> owned_lecturers = mgr.getOwnedLecturers();

        /* Getting neccessary hibernate helpers. */
        LecturersHelper helper
                = new LecturersHelper();

        // Calculacting the boost a given research would give.
        int boost_value = 0;
        for (int i = 0; i < chosenLecturers.size(); i++) {
            String lecturerName = chosenLecturers.get(i);
            System.out.println(lecturerName);
            Lecturer lecturer = mgr.lookUpLecturer(lecturerName, owned_lecturers);
            System.out.println("-----------------------------------------");
            System.out.println("LECTURERS OBJECT: " + lecturer.getName());

            /* Making the researcher unusable. */
            helper.setUsable(lecturerName, false);

            /* Setting the research object. */
            research.addResearcher(lecturer);

            // Checking whether a given lecturer is has a given research as
            // its attribtues
            ArrayList<LecturerBenefits> benefits
                    = lecturer.getSpecializations();
            String research_area = subjectList[subject];
            Iterator<LecturerBenefits> it = benefits.iterator();
            while (it.hasNext()) {
                LecturerBenefits benefit = it.next();
                if (benefit.getField().equals(research_area)) {
                    boost_value += benefit.getBoost();
                    break;
                }
            }
        }

        
        System.out.println("MY BOOST VALUE IS:" + boost_value);
        /* Setting the research object research */
        research.setName(getName());
        research.setUserId(session.getAttribute(ConnectionSingleton.idname).toString());
        research.setResearchpoints(100);
        research.setResearchBoost(boost_value);

        List<Research> ongoingResearch = researchBag.getResearches();
        List<Research> finishedResearch = researchBag.getFinishedResearches();

        ongoingResearch.add(research);
        research.addResearchList(ongoingResearch);
        research.addFinishedResearchList(finishedResearch);

        Thread thread = new Thread(research);

        thread.start();

        return "success";
    }
}
