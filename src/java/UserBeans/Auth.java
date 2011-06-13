package UserBeans;

import ConnectionDataBase.Playerresources;
import ConnectionDataBase.PlayerresourcesHelper;
import Connections.AuthorizationSingleton;
import Connections.ConnectionSingleton;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class Auth {

    /** Creates a new instance of Auth */
    public Auth() {
        remember = false;
        logging = false;
        hasNewMessage = false;
    }
    private String username;
    private String password;
    private Boolean remember;
    private boolean hasNewMessage;
    private Playerresources resources;

    public boolean logging;

    public String getPassword() {
        return password;
    }

    public String getUsername() {

        return username;
    }

    public void addUsername(HttpSession session) {

            setUsername(session.getAttribute(ConnectionSingleton.idname).toString());
            setPassword(session.getAttribute(ConnectionSingleton.password).toString());
            setRemember("true");

    }

    public Playerresources getResources() {
        return resources;
    }

    public void setResources(Playerresources resources) {
        this.resources = resources;
    }

    public String getRemember() {
        return remember.toString();
    }

    public boolean getRememberBool() {
        return remember;
    }

    public void setPassword(String pass) {
        password = pass;
    }

    public void setUsername(String ID) {
        username = ID;
    }

    public void setRemember(String value) {
        remember = Boolean.parseBoolean(value);
    }

    public String newUser() {
        return "go";
    }

    public String validate() throws SQLException {
        username = username.trim();
        password = password.trim();

        String result;

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

        if (username != null && username.length() != 0 && AuthorizationSingleton.test(username, password, session)) {
            updateResearchPoints();
            result = "success";
        } else {
            result = "failure";
        }

        logging = true;

        return result;
    }

    public Integer getResearchPoints() {
        return resources.getResearchpoints();
    }

    public void setResearchPoints(int researchPoints) {
        resources.setResearchpoints(resources.getResearchpoints()+researchPoints);
    }

    public Integer getUndergraduatesnumber() {
        return resources.getUndergraduatesnumber();
    }

    public void setUndergraduatesnumber(Integer number) {
        resources.setUndergraduatesnumber(number);
    }

    public void setPhdsnumber(int number){
        resources.setPhdsnumber(number);
    }

    public Integer getPhdsnumber() {
        return resources.getPhdsnumber();
    }

    public Integer getMoney() {
        return resources.getMoney();
    }

    public void setMoney(Integer money) {
        resources.setMoney(money);
    }

    public synchronized void updateResearchPoints() {

        PlayerresourcesHelper playerResources = new PlayerresourcesHelper();
        resources = playerResources.getResources(username);
        System.out.println("The name is: " + resources);

    }

    public String go() {
        return "success";
    }

    public void notifyUserAboutMessage() {
        setHasNewMessage(true);
    }

    public synchronized void setHasNewMessage(boolean value) {
        hasNewMessage = value;
    }

    public boolean getHasNewMessage() {
        return hasNewMessage;
    }

    public void hasNewMessage() {
        if(getHasNewMessage()) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Message System","You've got a new message"));
            setHasNewMessage(false);
        }
    }

}