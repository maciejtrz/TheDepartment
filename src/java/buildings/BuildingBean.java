/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package buildings;

import Connections.UserManager;
import java.io.Serializable;
import java.util.List;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import utilities.BuildingsUtils;


/**
 *
 * @author pk2109
 */
public class BuildingBean implements Serializable {

    private BuildingsUtils utils;
    private String playerName;
    private DataModel<Building> modelList;

    /** Creates a new instance of buildingBean */
    public BuildingBean() {
        System.out.println(" Building Bean created. ");
        utils = new BuildingsUtils();
        playerName = utilities.BasicUtils.getUserName();
        modelList = new ListDataModel(utils.getAvailableBuildings(playerName));
    }

    public DataModel<Building> getPlayersBuildings(){

        modelList.setWrappedData(utils.getAvailableBuildings(playerName));
        return modelList;
    }

    public void buy() {
        Building building = (Building)modelList.getRowData();
        System.out.println("I WAS CALLED!");
        System.out.println(building.getInfo());
        int position = UserManager.getBuilidngPosition(playerName);
        building.build(playerName, position);
        modelList.setWrappedData(utils.getAvailableBuildings(playerName));
    }

    public void setDoSelect(Building s) {
        System.out.println("I WAS CALLED KURWA!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        int position = UserManager.getBuilidngPosition(playerName);
        s.build(playerName, position);
    }


}
