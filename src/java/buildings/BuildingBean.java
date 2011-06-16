/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package buildings;

import Connections.UserManager;
import java.io.Serializable;
import java.util.List;
import utilities.BuildingsUtils;


/**
 *
 * @author pk2109
 */
public class BuildingBean implements Serializable {


    private List<Building> playersBuildings;
    private BuildingsUtils utils;

    /** Creates a new instance of buildingBean */
    public BuildingBean() {
        utils = new BuildingsUtils();
        String playerName = utilities.BasicUtils.getUserName();
        playersBuildings= utils.getAvailableBuildings(playerName);

    }

    public List<Building> getPlayersBuildings(){

        return playersBuildings;
    }

    public void setDoSelect(Building s) {
        System.out.println("Selected building: " + s.getInfo());
        String playerName = utilities.BasicUtils.getUserName();
        System.out.println("For player " + playerName);
        int position = UserManager.getBuilidngPosition(playerName);
        System.out.println("At position: " + position);
        s.build(playerName, position);
        playersBuildings = utils.getAvailableBuildings(playerName);
    }

}
