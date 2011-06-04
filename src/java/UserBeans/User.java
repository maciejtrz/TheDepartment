package UserBeans;


import Connections.AuthorizationSingleton;
import Connections.ConnectionSingleton;
import java.io.IOException;
import java.sql.Statement;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class User {

    /** Creates a new instance of UserControl */
    public User() {
    }

    public String deleteMe() throws IOException {

        FacesContext facesContext = (FacesContext) FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);

        String username = (String) session.getAttribute(ConnectionSingleton.idname);

        System.out.println("Deleting: " + username);
        try {
            Statement statement = Connections.ConnectionSingleton.createConnection().getStatement();
            statement.execute("DELETE FROM Players WHERE IdName = '" + username + "'");
            statement.execute("DELETE FROM PlayerResources WHERE IdName = '" + username + "'");
            statement.execute("DELETE FROM DepartmentInfo WHERE IdName = '" + username + "'");
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        logoff();

        return "success";

    }

    private void logoff() throws IOException {

        System.out.println("Logging off...");

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

        session.removeAttribute(ConnectionSingleton.idname);
        session.removeAttribute(ConnectionSingleton.password);


        AuthorizationSingleton.removeCookies(request, response, session);

        System.out.println("Logging out!");
        System.out.println("Waiting for index...");

    }
}
