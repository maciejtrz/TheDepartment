/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UserBeans;

import Connections.ConnectionSingleton;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author karol
 */
public class AddDepartment {

    /** Creates a new instance of AddDepartment */
    public AddDepartment() {
    }
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String addDepartment() throws SQLException {

        if(getName() == null || getName().trim().length() == 0)
            return "failure";

        setName(getName().trim());


        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

        ConnectionSingleton connection = ConnectionSingleton.createConnection();
        Statement statement = connection.getStatement();


        String playerName = session.getAttribute(ConnectionSingleton.idname).toString();

        /* Populating PlayerResources table. */
        String pr_query = "INSERT INTO PlayerResources"
                + " VALUES ('" + playerName + "', 100000 , 0 , 0 ,"
                + " 0)";
        statement.executeUpdate(pr_query);

        /* Populating DepartmentInfo table. */
        String dp_query = "INSERT INTO DepartmentInfo"
                + " VALUES ( '" + playerName + "' , '"
                + getName() + "')";
        statement.executeUpdate(dp_query);

        statement.close();

        return "success";
    }

    public String go() {
        return "go";
    }
}
