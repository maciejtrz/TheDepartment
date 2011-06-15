/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package buildings;

import java.util.List;
import utilities.BasicUtils;
import utilities.BuildingsUtils;


/**
 *
 * @author pk2109
 */
public class buildingBean {


    public int selectedNumber;
    public List<Building> playersBuildings;
    private BuildingsUtils bs;


    /** Creates a new instance of buildingBean */
    public buildingBean() {
        this.bs = new BuildingsUtils();

    }

    public List<Building> getPlayersBuildings(){

        setPlayersBuildings(bs.getAvailableBuildings(BasicUtils.getUserName()));
        return this.playersBuildings;
    }

    public void setPlayersBuildings(List<Building> l){
        this.playersBuildings=l;
    }

    public int getSelectedNumber(){
        return this.selectedNumber;
    }

    public void setSelectedNumber(int num){
        this.selectedNumber=num;
    }

    public void submit(){
        System.out.println("Submiting building query");
    }


}
