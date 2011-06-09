package buildings;

import ConnectionDataBase.BuildingsHelper;
import ConnectionDataBase.BuildingsPositionHelper;

public class Laboratories  extends Building {


    public Laboratories (int cost) {
        this.cost = cost;
    }

    @Override
    public boolean build(String playerName, int position) {
        /* Updating Buildings table. */
        BuildingsHelper buildingsHelper
                = new BuildingsHelper();
        buildingsHelper.updateBrain(playerName, Building.BASIC_LEVEL);

        /* Updating Position table. */
        BuildingsPositionHelper posHelper
                = new BuildingsPositionHelper();
        posHelper.createBuildingPosition(playerName, position,
                Building.CODE_LABS);

        return true;
    }

    @Override
    public boolean remove(String playerName, int position) {

        /* Removing from Buildings table. */
        BuildingsHelper buildingsHelper
                = new BuildingsHelper();
        buildingsHelper.updateBrain(playerName, Building.NOT_BUILT_LEVEL);

        /* Removing from Position table. */
        BuildingsPositionHelper posHelper
                = new BuildingsPositionHelper();
        posHelper.updateBuildingPosition(playerName, position, null);


        return true;
    }

    

}
