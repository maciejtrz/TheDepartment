package UserBeans;

import ConnectionDataBase.PlayerHelper;
import ConnectionDataBase.PlayerresourcesHelper;
import ConnectionDataBase.ResearchHelper;
import Connections.AuthorizationSingleton;
import java.io.IOException;
import java.io.Serializable;


public class User implements Serializable {

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
    
    public String logoff() throws IOException {

        AuthorizationSingleton.logoff();
        return "success";
    }

}
