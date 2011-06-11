package ConnectionDataBase;

import Connections.ConnectionSingleton;
import Connections.UserManager;
import ResearchPoints.ResearchDevelopment;
import ResearchPoints.ResearchTreeNode;
import UserBeans.Auth;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import utilities.Lecturer;

public class Research  implements java.io.Serializable, Runnable {

    private ResearchId id;
    private Integer researchpoints;
    private List<Research> researchList;
    private List<Integer> availableResearch;
    private int researchTime;
    private int researchBoost;
    private final static String RUNNING = "Running";
    private final static String FINISHED = "Finished";
    private final static String STOPPED = "Stopped";
    private String state;
    private Auth auth;
    private Integer progress;
    private Integer money;

    private List<Lecturer> researchers;
    private Integer researchNumber = new Integer(0);

    public Research() {
        id = new ResearchId();
        researchers = new ArrayList<Lecturer>();
        state = STOPPED;
        progress = 0;
    }

    public Research(String name, int researchId) {
        id = new ResearchId(name, researchId);
    }

    public Research(ResearchId id) {
        this();
        this.id = id;
        researchers = new ArrayList<Lecturer>();
        state = STOPPED;
    }

    public Research(Auth auth, Integer researchNumber) {
        this();
        researchers = new ArrayList<Lecturer>();
        this.auth = auth;
        this.researchNumber = researchNumber;

        ResearchTreeNode treeNode = ResearchDevelopment.getResearchTreeNode(researchNumber);
        id = new ResearchId(auth.getUsername(),researchNumber);
        
    }

    public void addResearcher(Lecturer lec) {
        researchers.add(lec);
    }

    /* ID - composite key of the table */
    public ResearchId getId() {
        return this.id;
    }

    public void setId(ResearchId id) {
        this.id = id;
    }

    /* Amlunt of reseach points rewarded for successful finishing of research */
    public Integer getResearchpoints() {
        return ResearchDevelopment.getResearchTreeNode(getId().getResearchid()).
                getResearchInstance().getResearchpoints();
    }

    /* Boost of the reseach - how long it takes to finish a research */
    public void setResearchBoost(int boost) {
        this.researchBoost = boost;
    }

    public int getResearchBoost() {
        return this.researchBoost;
    }


    /* Name of the research */
    public String getName() {
        return ResearchDevelopment.getResearchTreeNode(getId().getResearchid()).
                getResearchInstance().getResearchname();
    }

    public String getUserId() {
        return id.getIdname();
    }

    public void setUserId(String userid) {
        id.setIdname(userid);
    }

    public String getState() {
        return state;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public void run() {
        state = RUNNING;

        try {
            researchTime = 30;

            while (researchTime > 0) {

                while(state == STOPPED)
                    Thread.sleep(2000);

                Thread.sleep(1000);
                researchTime--;
            }

        } catch (Exception ex) {

        } finally {
            System.out.println("Finishing: " + getName());

            /* updating research table and adding research points */
            UserManager.addResearch(this);

            state = FINISHED;
            /* Making all participating lecturer usable again.*/
            LecturersHelper helper
                    = new LecturersHelper();

            Iterator<Lecturer> it = researchers.iterator();
            while (it.hasNext()) {
                Lecturer lec = it.next();
                helper.setUsable(lec.getName(), true);
            }

        }
    }

    public Integer getResearchTime() {
        return researchTime;
    }

    public void addResearchList(List<Research> ongoingResearch) {
        researchList = ongoingResearch;
    }

    public void addAvailableResearchList(List<Integer> availableResearch) {
        this.availableResearch = availableResearch;
    }

    public String manageResearch() throws IOException {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

        System.out.println("Setting up research...");

        session.setAttribute(ConnectionSingleton.researchName, getName());

        return "go";

    }

    public void changeState() {
        if(state == RUNNING) {
            state = STOPPED;
        }
        else  {
            state = RUNNING;
        }
    }

    public boolean equals(Research research) {
        return research.getId().getResearchid() == getId().getResearchid();
    }

    public List<Integer> getAvailableResearch() {
        return availableResearch;
    }


}


