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
public class Money  implements Resource {

    private static final String resourceName = "Cash";

    @Override
    public boolean canRemove(Playerresources resources, int amount) {
        return resources.getMoney() >= amount;
    }

    @Override
    public void remove(Playerresources resources, int amount) {
        resources.setMoney(resources.getMoney() - amount);
    }

    @Override
    public void add(Playerresources resources, int amount) {
        resources.setMoney(resources.getMoney()+amount);
    }

    @Override
    public String getResourcesName() {
        return resourceName;
    }

}
