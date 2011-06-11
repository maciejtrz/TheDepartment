package buildings;

import utilities.BuildingInfo;

public class BobTheBuilder extends Building {


    public BobTheBuilder (int cost) {
        this.cost = cost;
    }

    @Override
    public boolean build(String playerName, int position) {
        // Not supported.
        return false;
    }

    @Override
    public boolean remove(String playerName, int position) {
        // Not supported.
        return false;
    }

    @Override
    public BuildingInfo isAllowedToBuild(String playerName, int position) {
        /* Not supported*/
        return new BuildingInfo(false, "Already built!");
    }

}
