package UserBeans;


import Connections.AuthorizationSingleton;
import Connections.ConnectionSingleton;
import java.io.IOException;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Unlog {

    /** Creates a new instance of Unlog */
    public Unlog() {
    }

    public void logoff() throws IOException {

        System.out.println("Logging off...");

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

        AuthorizationSingleton.updateUserStatus(session.getAttribute(ConnectionSingleton.idname).toString(),false);

        session.removeAttribute(ConnectionSingleton.idname);
        session.removeAttribute(ConnectionSingleton.password);


        AuthorizationSingleton.removeCookies(request, response, session);

        System.out.println("Logging out!");
        System.out.println("Waiting for index...");



    }

}

