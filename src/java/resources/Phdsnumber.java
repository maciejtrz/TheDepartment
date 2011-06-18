/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package resources;

import ConnectionDataBase.Playerresources;

/**
 *
 * @author kp1209
 */
public class Phdsnumber implements Resource {

     private static final String resourceName = "Phds students";

    @Override
    public String getResourcesName() {
        return resourceName;
    }

    @Override
    public boolean canRemove(Playerresources resources, int amount) {
        return resources.getPhdsnumber() >= amount;
    }

    @Override
    public void remove(Playerresources resources, int amount) {
        resources.setPhdsnumber(resources.getPhdsnumber() - amount);
    }

    @Override
    public void add(Playerresources resources, int amount) {
        resources.setPhdsnumber(resources.getPhdsnumber()+amount);
    }

}
