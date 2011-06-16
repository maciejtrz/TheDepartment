

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
        return ("Hey cheater! Welcome to Black Market. Here you can trade " +
                "whatever you want with whoever you want. There is no police, " +
                "there are not regulations! For legal trade please refer to the " +
                "official “auctions” system. Here, you are free to choose any " +
                "amount of any resource you would like and trade them.");
    }

    @Override
    public String getPicture() {
        return (this.CODE_BLACK_MARKET);
    }




}
