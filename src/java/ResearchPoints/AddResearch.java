/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ResearchPoints;

import ConnectionDataBase.Research;
import Connections.ConnectionSingleton;
import game.lecturerSystem.Lecturer;
import game.lecturerSystem.LecturerBenefits;
import game.lecturerSystem.LecturersManager;
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

    public AddResearch() {
        chosenLecturers = new ArrayList<String>();
        subjects = new ArrayList<SelectItem>();

        // Adding lecturers names.
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

        LecturersManager mgr = (LecturersManager) session.getAttribute(ConnectionSingleton.LECTURERSMANAGER);

        List<Lecturer> av_lecturers = mgr.getAvailableLecturers();

        for (int i = 0; i < av_lecturers.size(); i++) {
            Lecturer lec = av_lecturers.get(i);
            if (lec.getUsable()) {
                // Add lecturer only if he is not occupied with another
                // research.
                lecturers.add(new SelectItem(new Integer(i),
                        av_lecturers.get(i).getName()));
            }
        }

        // Adding subjects names.
        for (int i = 0; i < subjectList.length; i++) {
            subjects.add(new SelectItem(new Integer(i), subjectList[i]));
        }
    }
    private String[] subjectList = {"Artifical Intelligence", "Machine Learning",
        "Compilers", "Operating System Design", "Networks and Communication",
        "Models of Computation"
    };
    private Integer subject;  // The index of the selected item
    // can be given as String/Integer/int ...


    public List getSubjects() {
        return subjects;
    }

    public Integer getSubject() {
        return subject;
    }

    public void setSubject(Integer nextSWVersion) {
        subject = nextSWVersion;
    }

    public void setLecturers(List chosenLecturers) {
        for (int i = 0; i < chosenLecturers.size(); i++) {
            System.out.println((i + 1) + ": " + chosenLecturers.get(i).toString());
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
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);


        /* Adding research thread to the list of researches of the given user */
        ResearchBag researchBag = (ResearchBag) session.getAttribute(Connections.ConnectionSingleton.researchBag);
        LecturersManager mgr = (LecturersManager) session.getAttribute(ConnectionSingleton.LECTURERSMANAGER);
        ArrayList<Lecturer> av_list = mgr.getAvailableLecturers();

        // Calculacting the boost a given research would give.
        int boost_value = 0;
        for (int i = 0; i < chosenLecturers.size(); i++) {
            String lecturerName = chosenLecturers.get(i);
            Lecturer lecturer = mgr.lookUpLecturer(lecturerName, av_list);
            ArrayList<LecturerBenefits> benefits = lecturer.getSpecializations();
            // Checking whether a given lecturer is has a given research as
            // its attribtues
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

        /* Creating new object research */
        Research research = new Research();
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
