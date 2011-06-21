package ResearchPoints;

import ConnectionDataBase.Research;
import Connections.ConnectionSingleton;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.model.TreeNode;
import utilities.BasicUtils;


public class ResearchReader {

    private ResearchBag researchBag = null;

    public ResearchReader() {
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);

        researchBag = (ResearchBag) session.getAttribute(ConnectionSingleton.researchBag);

    }

    public List<Research> getResearches() {
        return researchBag.getResearches();
    }

    public void setResearches(List<Research> ongoingResearches) {
        researchBag.setResearches(ongoingResearches);
    }

    public Research getSelectedResearch() {
        return researchBag.getSelectedResearch();
    }

    public void setSelectedResearch(Research selectedResearch) {

        selectedResearch.changeState();
    }

    public TreeNode getFinishedReserchTree() {
        return researchBag.getFinishedReserchTree();
    }

    

    

}
