/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package buildings;


/**
 *
 * @author kwh109
 */
public class UpgradeBean {

    /** Creates a new instance of UpgradeBean */
    public UpgradeBean() {
    }

    public void upgradeLabs() {
        BuildingFactory factory = new BuildingFactory();
        Laboratories labs = factory.getLabs();
    }

}
