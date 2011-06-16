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
import utilities.BasicUtils;
import utilities.BuildingsUtils;


/**
 *
 * @author pk2109
 */
public class BuildingBean implements Serializable {


    private Building selectedBuilding;
    private DataModel playersBuildings;

    /** Creates a new instance of buildingBean */
    public BuildingBean() {
        BuildingsUtils utils = new BuildingsUtils();
        String playerName = utilities.BasicUtils.getUserName();
        playersBuildings
                = new ListDataModel(utils.getAvailableBuildings(playerName));

    }

    public DataModel getPlayersBuildings(){

        return playersBuildings;
    }

    public void setDoSelect(Building s) {
        System.out.println(s.getInfo());
        String playerName = utilities.BasicUtils.getUserName();
        System.out.println("For player " + playerName);
        int position = UserManager.getBuilidngPosition(playerName);
        System.out.println("At position: " + position);
        s.build(playerName, position);
    }

}
