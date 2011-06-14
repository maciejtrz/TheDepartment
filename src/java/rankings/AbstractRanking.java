/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rankings;

import ConnectionDataBase.Playerresources;
import ConnectionDataBase.PlayerresourcesHelper;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pk2109
 */
public abstract class AbstractRanking {

    private List<Playerresources> list;
    private int playerPosition;
    protected PlayerresourcesHelper helper;

    public AbstractRanking(){
        this.list = new ArrayList<Playerresources>();
        this.helper = new PlayerresourcesHelper();
    }

    public void setPlayerPosition(int p){
        this.playerPosition = p;
    }

    public int getPlayerPosition(){
        return this.playerPosition;
    }

    public List<Playerresources> getList(){
        return this.list;
    };

    public void setList(List<Playerresources> l){
        this.list=l;
    }

    public abstract void produceRanking();


}
