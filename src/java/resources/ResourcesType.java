/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package resources;

/**
 *
 * @author kp1209
 */
public class ResourcesType {

    private static Resource[] resources;

    static {
        resources = new Resource[3];
        resources[0] = new Money();
        resources[1] = new Phdsnumber();
        resources[2] = new Undergraduatesnumber();
    }

    public static Resource[] getResourcesList() {
        return resources;
    }

    public static int getResourcesListSize() {
        return resources.length;
    }

    public static Resource getResourcesElement(int pos) {
        return resources[pos];
    }

    


}
