package ResearchPoints;

import ConnectionDataBase.Research;
import ConnectionDataBase.ResearchHelper;
import Connections.ConnectionSingleton;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


public class ResearchBag {

    private List<Research> researchBag = new ArrayList<Research>();
    private String username;
    private ResearchHelper researchHelper;

    public ResearchBag() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

        researchBag = (List<Research>) session.getAttribute(ConnectionSingleton.researchBag);
        username = (String) session.getAttribute(ConnectionSingleton.idname);
        researchHelper = new ResearchHelper();
    }

    public List<Research> getResearches() {

        return researchBag;

    }

    public void setResearches(List<Research> researchBag) {
        this.researchBag = researchBag;
    }
    
    public List<Research> getFinishedResearches() {
        
        return researchHelper.getFinishedResearches(username);
    }

    public void update() {

    }

}
