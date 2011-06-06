/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package UserBeans;

import ConnectionDataBase.DepartmentinfoHelper;
import ConnectionDataBase.PlayerresourcesHelper;
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

        PlayerresourcesHelper playerresources = new PlayerresourcesHelper();
        playerresources.deleteResources(username);

        DepartmentinfoHelper departmentInfo = new DepartmentinfoHelper();
        departmentInfo.deleteDepartment(username);

    }


    public String startPlaying() {

        return "success";
    }

}
