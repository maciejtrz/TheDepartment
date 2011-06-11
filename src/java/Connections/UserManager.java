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

    static private boolean isUserMonitored(String username) {
        return sessionMap.containsKey(username);
    }

    private Playerresources getPlayerResources(String username) {
        return getUser(username).getResources();
    }

    static private void saveResources(String username, Playerresources resources) {

        if(!isUserMonitored(username)) {
             saveToDatabase(resources);
        }

    }

    static private Playerresources getResources(String username) {

        Playerresources resources = null;
        if(isUserMonitored(username)) {

            resources = getUser(username).getResources();

        } else {

            PlayerresourcesHelper resourcesHelper = new PlayerresourcesHelper();
            resources = resourcesHelper.getResources(username);

        }

        return resources;
    }

    static private Auth getUser(String username) {
        return sessionMap.get(username);
    }

    static private void saveToDatabase(Playerresources resources) {
            PlayerresourcesHelper resourcesHelper = new PlayerresourcesHelper();
            resourcesHelper.updateResources(resources);
    }

    static synchronized public void addUser(Auth auth) {
        sessionMap.put(auth.getUsername(), auth);
    }

    static synchronized public void removeUser(String username) {
        saveToDatabase(getUser(username).getResources());
    }

    static synchronized public void addMoney(String username, int money) {

            Playerresources resources = getResources(username);
            resources.setMoney(resources.getMoney() + money);
            saveResources(username,resources);

    }

    static synchronized public boolean removeMoney(String username, int money) {

            boolean result = false;

            Playerresources resources = getResources(username);

            if(resources.getMoney() - money >= 0) {
                resources.setMoney(resources.getMoney() - money);
                saveResources(username,resources);
                result = true;
            }

            return result;

    }

    static synchronized public void addResourcesPoints(String username, int researchPoints) {
            Playerresources resources = getResources(username);
            resources.setResearchpoints(resources.getResearchpoints()+researchPoints);
            saveResources(username,resources);
    }


    static synchronized public boolean removePhdsnumber(String username, int phdsnumber) {

            boolean result = false;

            Playerresources resources = getResources(username);

            if(resources.getPhdsnumber() - phdsnumber >= 0) {
                resources.setMoney(resources.getPhdsnumber() - phdsnumber);
                saveResources(username,resources);
                result = true;
            }

            return result;

    }

    static synchronized public void addPhdsnumber(String username, int phdsnumber) {
            Playerresources resources = getResources(username);
            resources.setPhdsnumber(resources.getPhdsnumber()+ phdsnumber);
            saveResources(username,resources);
    }

    static synchronized public boolean removeUndegraduatesnumber(String username, int undergraduatesnumber) {

            boolean result = false;

            Playerresources resources = getResources(username);

            if(resources.getUndergraduatesnumber() - undergraduatesnumber >= 0) {
                resources.setMoney(resources.getUndergraduatesnumber() - undergraduatesnumber);
                saveResources(username,resources);
                result = true;
            }

            return result;

    }

    static synchronized public void addUndegraduatesnumber(String username, int undergraduatesnumber) {
            Playerresources resources = getResources(username);
            resources.setUndergraduatesnumber(resources.getUndergraduatesnumber()+undergraduatesnumber);
            saveResources(username,resources);
    }


}
