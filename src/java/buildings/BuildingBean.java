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

        String playerName = utilities.BasicUtils.getUserName();

        int position = UserManager.getBuilidngPosition(playerName);

        s.build(playerName, position);
        playersBuildings = utils.getAvailableBuildings(playerName);
    }

    public boolean getAfford() {
        return false;
    }

}
