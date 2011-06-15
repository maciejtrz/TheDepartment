package buildings;

import ConnectionDataBase.BuildingsPositionHelper;
import ConnectionDataBase.PlayerresourcesHelper;
import utilities.BuildingInfo;


public abstract class Building {


    public static final int NOT_BUILT_LEVEL = 0;
    public static final int BASIC_LEVEL = 1;
    public static final int MEDIUM_LEVEL = 2;
    public static final int ADVANCED_LEVEL = 3;

    /* Buildings codes. */
    public static final String CODE_TRESCO = "tr1";
    public static final String CODE_MAC_CHICKEN_1 = "mc1";
    public static final String CODE_DOCPUB = "dp1";
    public static final String CODE_BLACK_MARKET = "bm1";
    public static final String CODE_LECTURER_ROOM_1 = "lr1";
    public static final String CODE_LECTURER_ROOM_2 = "lr2";
    public static final String CODE_LECTURER_ROOM_3 = "lr3";
    public static final String CODE_PHD_OFFICE_1 = "po1";
    public static final String CODE_PHD_OFFICE_2 = "po2";
    public static final String CODE_PHD_OFFICE_3 = "po3";
    public static final String CODE_PROF_OFFICE_1 = "ro1";
    public static final String CODE_PROF_OFFICE_2 = "ro2";
    public static final String CODE_PROF_OFFICE_3 = "ro3";
    public static final String CODE_LABS = "lb1";
    public static final String CODE_SUPERLABS = "sb1";
    public static final String CODE_BRAIN = "br1";
    public static final String CODE_BOB = "qt1";
    public static final String CODE_UNION = "su1";

    /* Initial values. */
    protected int cost = 1000;
    protected int max_level = BASIC_LEVEL;

    // The cost of each upgrade is calculated the below value by building's
    // current level.
    protected int upgrade_base_cost = 0;

    public int getCost() {
        return cost;
    }

    public int getMaxLevel() {
        return max_level;
    }

    public int getUpgradeCost(String userName) {
        // Just a stub, userName is ignored.
        return upgrade_base_cost;
    }

    public abstract boolean build (String playerName, int position);
    /* Create the building for a given player at a given position. */

    public abstract boolean remove(String playerName , int position);
    /* Removes the building for a given player. */

    public abstract boolean upgrade(String playerName , int position);
    /* Upgrades the building by one level. */

    public abstract BuildingInfo isAllowedToBuild(String playerName, int position);
    /* Informs whether a given player is allowed to build anything on
       a free position. */


    protected boolean checkMoneyAndPosition (String playerName, int position) {
        BuildingsPositionHelper posHelper
                = new BuildingsPositionHelper();

        PlayerresourcesHelper resourcesHelper
                = new PlayerresourcesHelper();

        // Checking if allowed to build.
        int cash = resourcesHelper.getMoney(playerName);
        if (cash < cost) {
            // Cannot build that building.
            return false;
        }

        boolean isUnoccupied = posHelper.isAllowedToBuild(playerName, position);
        if (!isUnoccupied) {
            return false;
        }

        return true;
    }


    protected BuildingInfo checkMoneyAndPositionInfo(String playerName,
            int position) {

        BuildingsPositionHelper posHelper
                = new BuildingsPositionHelper();

        PlayerresourcesHelper resourcesHelper
                = new PlayerresourcesHelper();

        // Checking money.
        int cash = resourcesHelper.getMoney(playerName);
        if (cash < cost) {
            // Cannot build that building.
            return new BuildingInfo(false, "Not enough money!");
        }

        // Checking occupations.
        boolean isUnoccupied = posHelper.isAllowedToBuild(playerName, position);
        if (!isUnoccupied) {
            return new BuildingInfo(false, "The possition is already occupied");
        }

        return new BuildingInfo(true, "Build me!");
    }


    // Checks whether a given building occupies a given position.
    protected boolean canPositionBeDestoryed(String playerName, int position,
            String buildingAcronym) {

        BuildingsPositionHelper helper
                = new BuildingsPositionHelper();
        String occupant = helper.getAtPosition(playerName, position);
        return (occupant != null && occupant.equals(buildingAcronym));

    }


}
