package ResearchPoints;

import ConnectionDataBase.Research;
import ConnectionDataBase.ResearchHelper;
import java.util.ArrayList;
import java.util.List;



public class ResearchBag {

    private List<Research> researchBag = new ArrayList<Research>();
    private List<Research> finishedResearch;

    private String username;
    private ResearchHelper researchHelper;

    public ResearchBag() {
        researchHelper = new ResearchHelper();

    }

    public void initialize(String username) {
        this.username = username;
        finishedResearch = researchHelper.getFinishedResearches(username);
        researchHelper = new ResearchHelper();
    }

    public List<Research> getResearches() {
        return researchBag;
    }

    public void setResearches(List<Research> researchBag) {
        this.researchBag = researchBag;
    }
    
    public List<Research> getFinishedResearches() {        
        return finishedResearch;
    }

    public void update() {

    }

}
