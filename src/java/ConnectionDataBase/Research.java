package ConnectionDataBase;
// Generated 06-Jun-2011 22:44:46 by Hibernate Tools 3.2.1.GA

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Research  implements java.io.Serializable, Runnable {


     private ResearchId id;
     private Integer researchpoints;
     private List<Research> researchList;
     private List lecturers;

     private int researchTime;

    public Research() {
        id = new ResearchId();
        lecturers = new ArrayList();
    }

	
    public Research(ResearchId id) {
        this.id = id;
        lecturers = new ArrayList();
    }
    public Research(ResearchId id, Integer researchpoints) {
       this.id = id;
       this.researchpoints = researchpoints;
       lecturers = new ArrayList();
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


    public void addLecturer(String lecturer) {
        lecturers.add(lecturer);
    }

    public void removeLecturer(String lecturer) {
        lecturers.remove(lecturer);
    }

    public List getLecturers() {
        return lecturers;
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

    public void addResearch() {
        ResearchHelper researchHelper = new ResearchHelper();
        researchHelper.createResearch(this);
    }

    public void run() {
          try {

            researchTime = 10;

            while(researchTime > 0) {
                Thread.sleep(1000);
                researchTime--;
            }

            addPointsToUser();
            addResearch();

        } catch (Exception ex) {

        } finally {
            researchList.remove(this);
        }
    }

    public void addList(List<Research> list) {
        researchList = list;
    }

    public String getResearchTime() {
        return researchTime+"";
    }

}


