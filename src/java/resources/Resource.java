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
public interface Resource {

    public String getResourcesName();
    public boolean canRemove(Playerresources resources, int amount);
    public void remove(Playerresources resources, int amount);
    public void add(Playerresources resources, int amount);


}
