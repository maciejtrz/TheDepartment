/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package messageSystem;

/**
 *
 * @author kp1209
 */
class ResourcesType {

    private static String[] resourcesList = {"Money","Undegraduates","Phds"};

    public static String[] getResourcesList() {
        return resourcesList;
    }

    public int getResourcesListSize() {
        return resourcesList.length;
    }

    public String getResourcesElement(int pos) {
        return resourcesList[pos];
    }

    


}
