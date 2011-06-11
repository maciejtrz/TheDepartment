/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Connections;

import ConnectionDataBase.Playerresources;
import ConnectionDataBase.PlayerresourcesHelper;
import UserBeans.Auth;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kp1209
 */
public class UserManager {

    /* Set of all user currently having sessions */
    private static Map<String,Auth> sessionMap = new HashMap<String,Auth>();

    /* Singleton */
    private UserManager() {
    }

    public Auth getUser(String username) {
        return sessionMap.get(username);
    }

    public void setUser(Auth auth) {
        sessionMap.put(auth.getUsername(), auth);
    }

    public void removeUser(String username) {
        sessionMap.remove(username);
    }

    public Playerresources getPlayerResources(String username) {
        return getUser(username).getResources();
    }

    public void saveResources(Playerresources resources) {

        if(sessionMap.containsKey(resources.getIdname())) {

            Auth auth = getUser(resources.getIdname());
            auth.setResources(resources);

        } else {

            PlayerresourcesHelper resourcesHelper = new PlayerresourcesHelper();
            resourcesHelper.updateResources(resources);

        }

    }

}
