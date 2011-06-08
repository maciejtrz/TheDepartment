package ConnectionDataBase;
// Generated 06-Jun-2011 22:44:46 by Hibernate Tools 3.2.1.GA

import Connections.ConnectionSingleton;
import game.lecturerSystem.Lecturer;
import game.lecturerSystem.LecturersManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


public class Research implements java.io.Serializable, Runnable {


    /* ------------------------------  GUI --------------------- */
     private ResearchId id;
     private Integer researchpoints;
     private List<Research> researchList;
     private List<Research> finishedResearch;

     private int researchTime;
     private int researchBoost;

     /* ------------------------- Backend --------------------- */
     ArrayList<Lecturer> researchers;



    public Research() {
        id = new ResearchId();
        researchers = new ArrayList<Lecturer>();
    }


    public Research(ResearchId id) {
        researchBoost = 1;
        researchTime = 10;
        this.id = id;
        researchers = new ArrayList<Lecturer>();
    }
    public Research(ResearchId id, Integer researchpoints) {
       this.id = id;
       this.researchpoints = researchpoints;
       researchers = new ArrayList<Lecturer>();
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

    public void setResearchBoost (int boost) {
        this.researchBoost = boost;
    }

    public int getResearchBoost () {
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

    private void addPointsToUser() throws SQLException {

        PlayerresourcesHelper playerResourcesHelper = new PlayerresourcesHelper();
        playerResourcesHelper.addResearchPoints(getUserId(), getResearchpoints());

    }

    public void run() {
          try {

            researchTime = 10;

            int sleep_time = 20000;

            if (this.researchBoost != 0) {
                sleep_time = sleep_time/this.researchBoost;
            }

            while(researchTime > 0) {
                Thread.sleep(sleep_time);
                researchTime--;
            }

            addPointsToUser();

        } catch (Exception ex) {

        } finally {
            researchList.remove(this);
            finishedResearch.add(this);

            /* Making all participating lecturer usable again.
               TO FIX WHEN THERE IS NO SESSION! */
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
            LecturersManager mgr =
                (LecturersManager) session.getAttribute(ConnectionSingleton.LECTURERSMANAGER);
            Iterator<Lecturer> it = researchers.iterator();
            while (it.hasNext()) {
                Lecturer lec = it.next();
                mgr.setUsable(lec, true);
            }


        }
    }


    public String getResearchTime() {
        return researchTime+"";
    }

    public void addResearchList(List<Research> ongoingResearch) {
        researchList = ongoingResearch;
    }

    public void addFinishedResearchList(List<Research> finishedResearch) {
        this.finishedResearch = finishedResearch;
    }

}


