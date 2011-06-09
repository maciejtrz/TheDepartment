package ConnectionDataBase;
// Generated 06-Jun-2011 22:44:46 by Hibernate Tools 3.2.1.GA

import Connections.ConnectionSingleton;
import UserBeans.Auth;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import utilities.Lecturer;

public class Research implements java.io.Serializable, Runnable  {

    private ResearchId id;
    private Integer researchpoints;
    private List<Research> researchList;
    private List<Research> finishedResearch;
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


    public Research() {
        id = new ResearchId();
        researchers = new ArrayList<Lecturer>();
        state = STOPPED;
        progress = 0;
    }

    public Research(ResearchId id) {
        this.id = id;
        researchers = new ArrayList<Lecturer>();
        state = STOPPED;
    }

    public Research(ResearchId id, Integer researchpoints) {
        this.id = id;
        this.researchpoints = researchpoints;
        researchers = new ArrayList<Lecturer>();
        state = STOPPED;
    }

    public Research(Auth auth) {
        this();
        researchers = new ArrayList<Lecturer>();
        this.auth = auth;
    }

    public void addResearcher(Lecturer lec) {
        researchers.add(lec);
    }

    public ResearchId getId() {
        return this.id;
    }

    public void setId(ResearchId id) {
        this.id = id;
    }

    public Integer getResearchpoints() {
        return this.researchpoints;
    }

    public void setResearchpoints(Integer researchpoints) {
        this.researchpoints = researchpoints;
    }

    public void setResearchBoost(int boost) {
        this.researchBoost = boost;
    }

    public int getResearchBoost() {
        return this.researchBoost;
    }


    public String getName() {
        return id.getTitle();
    }

    public void setName(String name) {
        id.setTitle(name);
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

    private void addPointsToUser() throws SQLException {
        auth.setResearchPoints(getResearchpoints());
        auth.setMoney(auth.getMoney()-getMoney());
    }

    public void run() {
        state = RUNNING;

        try {
            researchTime = 100;

            while (researchTime > 0) {

                while(state == STOPPED)
                    Thread.sleep(100);

                Thread.sleep(100);
                researchTime--;
            }

        } catch (Exception ex) {

        } finally {
            researchList.remove(this);
            finishedResearch.add(this);
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
           try {
                addPointsToUser();
            } catch (SQLException ex) {
                Logger.getLogger(Research.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    public Integer getResearchTime() {
        return researchTime;
    }

    public void addResearchList(List<Research> ongoingResearch) {
        researchList = ongoingResearch;
    }

    public void addFinishedResearchList(List<Research> finishedResearch) {
        this.finishedResearch = finishedResearch;
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



}
