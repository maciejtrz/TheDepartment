package buildings;


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
    public static final String CODE_BOB = "bs1";
    public static final String CODE_UNION = "su1";

    protected int cost;


    public int getCost() {
        return cost;
    }

    public abstract boolean build (String playerName, int position);
    /* Create the building for a given player at a given position. */

    public abstract boolean remove(String playerName , int position);
    /* Removes the building for a given player. */




}
