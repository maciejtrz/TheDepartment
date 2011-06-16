

package buildings;

import utilities.BuildingInfo;

public class BlackMarket extends Building {


    public BlackMarket () {
    }

    @Override
    public boolean build(String playerName, int position) {
        /* Not supported. */
        return false;

    }

    @Override
    public boolean remove(String playerName , int position) {
        /* Not supported. */
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
        return ("Black Market");
    }

    @Override
    public String getPicture() {
        return (this.CODE_BLACK_MARKET);
    }




}
