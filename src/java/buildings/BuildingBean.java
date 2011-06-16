/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package buildings;

import Connections.UserManager;
import java.util.List;
import utilities.BasicUtils;
import utilities.BuildingsUtils;


/**
 *
 * @author pk2109
 */
public class BuildingBean {


    private Building selectedBuilding;
    private List<Building> playersBuildings;
    private BuildingsUtils bs;


    /** Creates a new instance of buildingBean */
    public BuildingBean() {
        this.bs = new BuildingsUtils();

    }

    public List<Building> getPlayersBuildings(){

        setPlayersBuildings(bs.getAvailableBuildings(BasicUtils.getUserName()));
        return this.playersBuildings;
    }

    public void setPlayersBuildings(List<Building> l){
        this.playersBuildings=l;
    }

    public Building getSelectedBuilding(){
        return this.selectedBuilding;
    }

    public void setSelectedBuilding(Building building){
        this.selectedBuilding = building;
    }

    public void submit(){
        String playerName = utilities.BasicUtils.getUserName();
        int position = UserManager.getBuilidngPosition(playerName);
        selectedBuilding.build(playerName, position);
        
    }


}
