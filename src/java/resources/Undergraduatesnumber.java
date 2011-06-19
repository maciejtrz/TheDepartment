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
public class Undergraduatesnumber implements Resource {

    private static String resourceName = "Undegraduates";

    @Override
    public String getResourcesName() {
        return resourceName;
    }

    @Override
    public boolean canRemove(Playerresources resources, int amount) {
        return resources.getUndergraduatesnumber() >= amount;
    }

    @Override
    public void remove(Playerresources resources, int amount) {
        resources.setUndergraduatesnumber(resources.getUndergraduatesnumber() - amount);
    }

    @Override
    public void add(Playerresources resources, int amount) {
        resources.setUndergraduatesnumber(resources.getUndergraduatesnumber()+amount);
    }


}
