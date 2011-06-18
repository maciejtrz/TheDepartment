/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package UserBeans;

import ConnectionDataBase.ExtrastatsHelper;

/**
 *
 * @author kwh109
 */
public class EventBean {

    /** Creates a new instance of EventBean */
    private ExtrastatsHelper helper;
    private String playerName;



    public EventBean() {
        helper = new ExtrastatsHelper();
        playerName = utilities.BasicUtils.getUserName();

    }

    public int getStarvation() {
        int starvation = helper.getPlayerStatsRecrod(playerName).getStarvation();
        return starvation;
    }

    public int getAlcoholism() {
        return helper.getPlayerStatsRecrod(playerName).getAlcoholizm();
    }

    public int getSatisfaction() {
        return helper.getPlayerStatsRecrod(playerName).getSatisfaction();
    }

}
