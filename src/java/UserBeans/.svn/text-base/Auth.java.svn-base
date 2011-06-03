package UserBeans;


import Connections.AuthorizationSingleton;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Auth {

    /** Creates a new instance of Auth */
    public Auth() {
        remember = false;
    }
    private String username;
    private String password;
    private Boolean remember;

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
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

    public String validate() {
        username = username.trim();
        password = password.trim();

        String result;

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

        if (username != null && username.length() != 0 && AuthorizationSingleton.test(username, password, session)) {
            System.out.println("Successful log!");
            result = "success";
        } else {
            result = "failure";
        }

        return result;
    }

}
