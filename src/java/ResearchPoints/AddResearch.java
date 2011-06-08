/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ResearchPoints;

import ConnectionDataBase.Research;
import Connections.ConnectionSingleton;
import java.util.List;
import java.util.Vector;
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

    public AddResearch() {
        for (int i = 0; i < subjectList.length; i++) {
            subjects.add(new SelectItem(new Integer(i), subjectList[i]));
        }

        for (int i = 0; i < lecturerList.length; i++) {
            lecturers.add(new SelectItem(new Integer(i), lecturerList[i]));
        }
    }


    private String[] subjectList = {"Artifical Intelligence", "Machine Learning",
        "Compilers", "Operating System Design", "Networks and Communication",
        "Models of Computation"
    };
    private String[] lecturerList = {"Tony Field", "Karol Pysniak", "Krzysztof Huszcza",
        "Paul Kelly", "Piotr Kraus", "Mark Zuckerberg", "Murzynek Bambo"
    };

    private Integer subject;  // The index of the selected item
    // can be given as String/Integer/int ...
    private Vector<SelectItem> subjects = new Vector<SelectItem>();
    private Vector<SelectItem> lecturers = new Vector<SelectItem>();


    public Vector getSubjects() {
        return subjects;
    }

    public Integer getSubject() {
        return subject;
    }

    public void setSubject(Integer nextSWVersion) {
        subject = nextSWVersion;
    }

    public void setLecturers(Vector chosenLecturers) {
        for(int i = 0; i < chosenLecturers.size();i++)
            System.out.println((i+1) + ": " + chosenLecturers.get(i).toString());
    }

    public Vector getLecturers() {
        return new Vector();
    }

    public Vector getLecturerList() {
        return lecturers;
    }

    public void setName(String name) { this.name = name; }
    public void setResearchPoints(int rp) { ResearchPoints = rp; }

    public String getName() { return name; }
    public int getResearchPoints() { return ResearchPoints; }

    public void removeLecturer(String lecturer) { lecturers.remove(lecturer); }


    public String startResearch() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);


        /* Adding research thread to the list of researches of the given user */
        ResearchBag researchBag = (ResearchBag)
                session.getAttribute(Connections.ConnectionSingleton.researchBag);

        /* Creating new object research */
        Research research = new Research();
        research.setName(getName());
        research.setUserId(session.getAttribute(ConnectionSingleton.idname).toString());
        research.setResearchpoints(100);

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
