package UserBeans;


import ConnectionDataBase.DepartmentinfoHelper;
import ConnectionDataBase.PlayerHelper;
import ConnectionDataBase.PlayerresourcesHelper;
import ConnectionDataBase.ResearchHelper;
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

        String username = utilities.BasicUtils.getUserName();
        logoff();

        /* Deleting department. */
        Department department = new Department(username);
        department.deleteDepartment();

        /* Deleteing player id and password. */
        PlayerHelper playerHelper = new PlayerHelper();
        playerHelper.deletePlayer(username);

        /* Deleting researches history */
        ResearchHelper researchHelper = new ResearchHelper();
        researchHelper.deleteAllResearches(username);

        /* Removing player's resources */
        PlayerresourcesHelper resourcesHelper = new PlayerresourcesHelper();
        resourcesHelper.deleteResources(username);

        return "success";
    }

    private void logoff() throws IOException {

        AuthorizationSingleton.logoff();

    }
}
