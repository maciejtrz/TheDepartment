/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package UserBeans;

import ConnectionDataBase.BuildingsHelper;
import ConnectionDataBase.BuildingsPositionHelper;
import ConnectionDataBase.CapacityHelper;
import ConnectionDataBase.DepartmentinfoHelper;
import ConnectionDataBase.PlayerresourcesHelper;
import java.io.IOException;


/**
 *
 * @author karol
 */
public class Department {

    private String username;

    /** Creates a new instance of Department */
    public Department(String username) {
        this.username = username;
    }

    public void deleteDepartment() throws IOException {
        
        // Deleting player resources.
        //PlayerresourcesHelper playerresources = new PlayerresourcesHelper();
        //playerresources.deleteResources(username);

        // Deleting department info.
        DepartmentinfoHelper departmentInfo = new DepartmentinfoHelper();
        departmentInfo.deleteDepartment(username);

        // Deleting capacities.
        CapacityHelper capacityHelper = new CapacityHelper();
        capacityHelper.deleteCapacity(username);

        // Deleting buildings.
        BuildingsHelper buildingsHelper = new BuildingsHelper();
        buildingsHelper.destroyBuildings(username);

        // Deleting buildings positions.
        BuildingsPositionHelper positionHelper
                = new BuildingsPositionHelper();
        positionHelper.deleteBuildingsPosition(username);

        // 

        // Deleting lecturers info.
        // TODO


        // Deleting students info.
        // TODO


    }

    public String startPlaying() {

        return "success";
    }

}
