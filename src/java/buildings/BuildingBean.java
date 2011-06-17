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

    /** Creates a new instance of buildingBean */
    public BuildingBean() {
        utils = new BuildingsUtils();
        playerName = utilities.BasicUtils.getUserName();
        buildingList = utils.getAvailableBuildings(playerName);
    }

    public List<Building> getPlayersBuildings(){

        return buildingList;
    }


    public void setDoSelect(Building s) {
        int position = UserManager.getBuilidngPosition(playerName);
        s.build(playerName, position);
        buildingList = utils.getAvailableBuildings(playerName);
    }

    public void initializeBuildingList() {
        buildingList = utils.getAvailableBuildings(playerName);
    }


}
