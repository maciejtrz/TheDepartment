/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package UserBeans;

import Connections.ConnectionSingleton;
import java.io.IOException;
import java.sql.Statement;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


/**
 *
 * @author karol
 */
public class Department {

    /** Creates a new instance of Department */
    public Department() {
    }

    public void deleteDepartment() throws IOException {

        FacesContext facesContext
                = (FacesContext) FacesContext.getCurrentInstance();
        HttpSession session
                = (HttpSession) facesContext.getExternalContext()
                    .getSession(false);

        String username
                = (String) session.getAttribute(ConnectionSingleton.idname);
        try {
            Statement statement
              = Connections.ConnectionSingleton.createConnection().getStatement();
            statement.execute
              ("DELETE FROM PlayerResources WHERE IdName = '" + username + "'");
            statement.execute
              ("DELETE FROM DepartmentInfo WHERE IdName = '" + username + "'");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }


    public String startPlaying() {

        return "success";
    }

}
