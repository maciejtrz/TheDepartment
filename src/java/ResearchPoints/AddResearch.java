/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ResearchPoints;

import ConnectionDataBase.LecturersHelper;
import ConnectionDataBase.Research;
import Connections.ConnectionSingleton;
import UserBeans.Auth;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import org.primefaces.event.FlowEvent;
import specializationsGenerator.SpecializationsGenerator;
import utilities.Lecturer;
import utilities.LecturerBenefits;
import utilities.LecturersManager;

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
    private List<SelectItem> researchesList;
    private Integer moneyAmount;
    private Integer chosenResearch;

    public AddResearch() {
        chosenLecturers = new ArrayList<String>();
        subjects = new ArrayList<SelectItem>();
        lecturers = new ArrayList<SelectItem>();

        moneyAmount = 50;

        String playerName = utilities.BasicUtils.getUserName();
        LecturersManager mgr = new LecturersManager(playerName);
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
        for (int i = 0; i < getSubjectList().length; i++) {
            subjects.add(new SelectItem(new Integer(i), getSubjectList()[i]));
        }
    }



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

    public void setLecturers(List chosenLects) {
        for (int i = 0; i < chosenLects.size(); i++) {
            String selected_item = chosenLects.get(i).toString();
            SelectItem item = lecturers.get(Integer.parseInt(selected_item));
            chosenLecturers.add(item.getLabel());
        }
    }
    
    public String getResearchName() {
        return  researchesList.get(getChosenResearch()).getValue().toString();
    }

    public Integer getChosenResearch() {
        return chosenResearch;
    }

    public void setChosenResearch(Integer chosenResearch) {
        this.chosenResearch = chosenResearch;
    }

    public List getAvailableResearches() {
        researchesList = new ArrayList<SelectItem>();

        List<ResearchTreeNode> researches = ResearchDevelopment.getFirstResearch(getSubject());
        Iterator<ResearchTreeNode> iterator = researches.iterator();

        int i = 0;
        while(iterator.hasNext()) {
            ResearchTreeNode researchNode = iterator.next();
            researchesList.add(new SelectItem(new Integer(i),researchNode.getResearchInstance().getResearchname()));
        }

        return researchesList;
    }

    public List getLecturers() {
        return new ArrayList();
    }

    public List getLecturerList() {
        return lecturers;
    }

    public void setResearchPoints(int rp) {
        ResearchPoints = rp;
    }


    public int getResearchPoints() {
        return ResearchPoints;
    }

    public Integer getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(Integer moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public Integer getMinimumMoney() {
        return new Integer(0);
    }

    public Integer getMaximumMoney() {
        return new Integer(100);
    }

    public String getSubjectName() {
        return getSubjectList()[getSubject()];
    }

    public String startResearch() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        Research research = new Research((Auth) session.getAttribute(ConnectionSingleton.Auth));

        /* Adding research thread to the list of researches of the given user */
        ResearchBag researchBag = (ResearchBag) session.getAttribute(Connections.ConnectionSingleton.researchBag);

        /* Reading lecturers from the database. */
        LecturersManager mgr = new LecturersManager(utilities.BasicUtils.getUserName());
        ArrayList<Lecturer> owned_lecturers = mgr.getOwnedLecturers();

        /* Getting neccessary hibernate helpers. */
        LecturersHelper helper = new LecturersHelper();

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
            ArrayList<LecturerBenefits> benefits = lecturer.getSpecializations();
            String research_area = getSubjectList()[subject];
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
        research.setName(getResearchName());
        research.setMoney(getMoneyAmount());
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

    public String onFlowProcess(FlowEvent event) {
        System.out.println("Next event: " + event.getNewStep());
        System.out.println("Current event: " + event.getOldStep());
        return event.getNewStep();
    }

    public void clean() {
    }

    private String[] getSubjectList() {
        return SpecializationsGenerator.subjectList;
    }
}
