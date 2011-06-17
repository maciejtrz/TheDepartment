/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package UserBeans;

import java.io.IOException;
import ConnectionDataBase.BuildingsHelper;
import ConnectionDataBase.BuildingsPositionHelper;
import ConnectionDataBase.CapacityHelper;
import ConnectionDataBase.DepartmentinfoHelper;
import ConnectionDataBase.EventsHelper;
import ConnectionDataBase.ExtrastatsHelper;
import ConnectionDataBase.LecturersOwnedHelper;
import Connections.ConnectionSingleton;
import buildings.BuildingBean;
import events.LotteryManager;
import java.io.Serializable;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import utilities.BasicUtils;



/**
 *
 * @author karol
 */
public class Department implements Serializable {

    private String username;
    private String name;

    public Department() {
        username = utilities.BasicUtils.getUserName();
    }
    /** Creates a new instance of Department */
    public Department(String username) {
        this.username = username;
    }

    public String deleteDepartment() throws IOException {
        
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


        // Deleting lecturers onwed info.
        LecturersOwnedHelper ownedHelper
                = new LecturersOwnedHelper();
        ownedHelper.deleteLecturer(username);

        // Deleting research
        // TODO

        // Deleting events.
        EventsHelper eventHelper = new EventsHelper();
        eventHelper.deleteEvents(username);

        // Deleting extra stats
        ExtrastatsHelper extrasHelper
                = new ExtrastatsHelper();
        extrasHelper.deleteRecord(username);


        return "success";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String addDepartment() throws SQLException {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

        if(getName() == null || getName().trim().length() == 0) {
            FacesContext.getCurrentInstance().addMessage(
                BasicUtils.findComponent(facesContext.getViewRoot(),"departmentName").getClientId(facesContext),
                new FacesMessage(FacesMessage.SEVERITY_ERROR,"Department Error", "Department Name is required!"));

            return null;
        }
            
        setName(getName().trim());

        
        String playerName = session.getAttribute(ConnectionSingleton.idname).toString();

        /* Populating DepartmentInfo table. */
        DepartmentinfoHelper departmentInfo = new DepartmentinfoHelper();

        if(departmentInfo.departmentExists(getName())) {
            FacesContext.getCurrentInstance().addMessage(
                BasicUtils.findComponent(facesContext.getViewRoot(),"departmentName").getClientId(facesContext),
                new FacesMessage(FacesMessage.SEVERITY_ERROR,"departmentInfo",
                "Departmanet named " + getName() +" already exists!"));

            return null;
        }

        departmentInfo.createDepartment(playerName,getName());

        BuildingsHelper buildinghelper = new BuildingsHelper();
        buildinghelper.createBuildings(playerName);

        CapacityHelper capacityhelper = new CapacityHelper();
        capacityhelper.createCapacity(playerName);

        BuildingsPositionHelper buildingsPosition =
                new BuildingsPositionHelper();
        buildingsPosition.initiateBuildingsPosition(playerName);

        /* Populating speical events table. */
        ExtrastatsHelper extraStatsHelper
                = new ExtrastatsHelper();
        extraStatsHelper
                .createInitialStats(playerName, 50, 50, 50);

        /* Populating events table. */
        EventsHelper eventsHelper = new EventsHelper();
        eventsHelper.createRecord(playerName);

        /* Initializing events. */
        LotteryManager mgr = new LotteryManager(playerName);
        mgr.initializeLottery();
        mgr.writeEventsToDB();

        /* Populating building Bean*/
        BuildingBean buildingBean = (BuildingBean) session
                .getAttribute(Connections.ConnectionSingleton.buildingBean);
        if (buildingBean != null) {
            buildingBean.initializeBuildingList();
        }


        return "success";
    }

}
