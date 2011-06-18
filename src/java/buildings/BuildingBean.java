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
    private List<Building> buildingList;
    private Building selected_building;

    /** Creates a new instance of buildingBean */
    public BuildingBean() {
        System.out.println("Inside the creator");
        utils = new BuildingsUtils();
        playerName = utilities.BasicUtils.getUserName();
        buildingList = utils.getAvailableBuildings(playerName);
    }

    public List<Building> getPlayersBuildings(){
        if (buildingList == null) {
            System.out.println("Returning null list ");
        }
        return buildingList;
    }

    public Building getSelectedBuilding() {
        return selected_building;
    }

    public void setSelectedBuilding(Building b) {
        selected_building = b;
    }

    public void buy () {
        Building s = selected_building;
        int position = UserManager.getBuilidngPosition(playerName);
        System.out.println("Position: " + position);
        System.out.println("BUilding: " + s.getInfo());
        System.out.println("Player name: " + playerName);
        s.build(playerName, position);
        buildingList = utils.getAvailableBuildings(playerName);
    }

    public void setDoSelect(Building s) {
        int position = UserManager.getBuilidngPosition(playerName);
        System.out.println("Position: " + position);
        System.out.println("BUilding: " + s.getInfo());
        System.out.println("Player name: " + playerName);
        s.build(playerName, position);
        buildingList = utils.getAvailableBuildings(playerName);
    }

    public void initializeBuildingList() {
        buildingList = utils.getAvailableBuildings(playerName);
    }


}
