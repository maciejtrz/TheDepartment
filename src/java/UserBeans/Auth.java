package UserBeans;

import Connections.AuthorizationSingleton;
import Connections.ConnectionSingleton;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Auth {

    /** Creates a new instance of Auth */
    public Auth() {
        remember = false;
        logging = false;
    }
    private String username;
    private String password;
    private Boolean remember;
    private int researchPoints;

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
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

        if (username != null && username.length() != 0 && AuthorizationSingleton.test(username, password, session)) {
            updateResearchPoints();
            result = "success";
        } else {
            result = "failure";
        }

        logging = true;

        return result;
    }

    public String getResearchPoints() {
        return researchPoints+"";
    }

    public void setResearchPoints(int researchPoints) {
        this.researchPoints = researchPoints;
    }

    public void updateResearchPoints() {

        Statement statement = ConnectionSingleton.createConnection().getStatement();

        String query = "SELECT " + ConnectionSingleton.researchPoints
                + " FROM " + ConnectionSingleton.playerResources + " WHERE "
                + ConnectionSingleton.idname + "='" + getUsername() + "'";
        String result = null;

        try {
        ResultSet resultSet = statement.executeQuery(query);

        if (resultSet.next())
            result = resultSet.getString(ConnectionSingleton.researchPoints);
        } catch(Exception e) { }

        if (result != null)
            setResearchPoints(Integer.parseInt(result));
        else
            setResearchPoints(0);
      
    }

    public String go() {
        return "success";
    }
}
