package UserBeans;

import ConnectionDataBase.Playerresources;
import ConnectionDataBase.PlayerresourcesHelper;
import Connections.AuthorizationSingleton;
import Connections.ConnectionSingleton;
import java.io.Serializable;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import utilities.BasicUtils;

public class Auth implements Serializable {

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
    private boolean sentNewMessage;
    private Playerresources resources;
    private int buildingPosition;

    public boolean logging;

    public void setBuildingPosition(int buildingPosition) {
         this.buildingPosition = buildingPosition;
    }

    public int getBuildingPosition() {
        return buildingPosition;
    }

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

        String result = null;

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

        boolean error = false;

        if(username == null || username.length() == 0) {

            FacesContext.getCurrentInstance().addMessage(
                BasicUtils.findComponent(facesContext.getViewRoot(),"username").getClientId(facesContext),
                new FacesMessage(FacesMessage.SEVERITY_ERROR,"Login error", "Login is required!"));

            error = true;
        }

        if(password == null || password.length() == 0) {

            FacesContext.getCurrentInstance().addMessage(
                BasicUtils.findComponent(facesContext.getViewRoot(),"password").getClientId(facesContext),
                new FacesMessage(FacesMessage.SEVERITY_ERROR,"Password error", "Password is required!"));

            error = true;

        }

        if (username != null && username.length() != 0 && AuthorizationSingleton.test(username, password, session)) {

            updateResearchPoints();
            result = "success";
            logging = true;

        } else if(!error) {

            FacesContext.getCurrentInstance().addMessage(
                    BasicUtils.findComponent(facesContext.getViewRoot(),"submit").getClientId(facesContext),
                new FacesMessage(FacesMessage.SEVERITY_ERROR,"Login error", "Login or password incorrent!"));


        }

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

    public void hasAnyNewMessage() {
        if(getHasNewMessage()) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Messaging System","You've got a new message"));
            setHasNewMessage(false);
        }
    }

    public boolean sentNewMessage() {
        boolean result = sentNewMessage;
        sentNewMessage = false;
        return result;
    }

    public void notifyAboutSendingMessage() {
        sentNewMessage = true;
    }

}