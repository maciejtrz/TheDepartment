package buildings;

import utilities.BuildingInfo;

public class BobTheBuilder extends Building {


    public BobTheBuilder () {
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
    public BuildingInfo isAllowedToBuild(String playerName) {
        /* Not supported*/
        return new BuildingInfo(false, "Already built!");
    }

    @Override
    public boolean upgrade(String playerName, int position) {
        return false;
    }

    @Override
    public String getInfo() {
        return ("Queens tower");
    }

    @Override
    public String getPicture() {
        return (this.CODE_BOB);
    }

}
