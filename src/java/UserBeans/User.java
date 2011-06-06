package UserBeans;


import ConnectionDataBase.DepartmentinfoHelper;
import ConnectionDataBase.PlayerHelper;
import ConnectionDataBase.PlayerresourcesHelper;
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

        PlayerresourcesHelper playerresources = new PlayerresourcesHelper();
        playerresources.deleteResources(username);

        DepartmentinfoHelper departmentInfo = new DepartmentinfoHelper();
        departmentInfo.deleteDepartment(username);

        PlayerHelper playerHelper = new PlayerHelper();
        playerHelper.deletePlayer(username);

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
