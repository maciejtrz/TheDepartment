/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ResearchPoints;

import Connections.ConnectionSingleton;
import java.util.List;
import javax.faces.context.FacesContext;
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

    /* List of participating lectures */
    private List lecturers;

    public AddResearch() {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        
        Thread thread = new Thread(new Research("Some title",
                100,null,session.getAttribute(ConnectionSingleton.idname).toString(),1000));
        
        thread.start();
    }

    public void setName(String name) { this.name = name; }
    public void setResearchPoints(int rp) { ResearchPoints = rp; }

    public String getName() { return name; }
    public int getResearchPoints() { return ResearchPoints; }

    public void addLecturer(String lecturer) { lecturers.add(lecturer); }
    public void removeLecturer(String lecturer) { lecturers.remove(lecturer); }
    public List getLecturers() { return lecturers; }


    public String startResearch() {

        return "success";
    }

}
