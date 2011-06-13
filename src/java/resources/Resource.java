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
public abstract class Resource {

    abstract public String getResourcesName();
    abstract public boolean canRemove(Playerresources resources, int amount);
    abstract public void remove(Playerresources resources, int amount);
    abstract public void add(Playerresources resources, int amount);

}
