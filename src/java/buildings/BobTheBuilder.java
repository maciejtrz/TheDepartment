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
        return ("This is the Queen's Tower building, the only remnant of the " +
                "once great and beautiful Imperial Institute. Sadly, the " +
                "institute had to be destroyed to “make place” for modern " +
                "Imperial College London. Don't jump!. ");
    }

    @Override
    public String getPicture() {
        return (this.CODE_BOB);
    }

}
