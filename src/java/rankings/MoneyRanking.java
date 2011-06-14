/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rankings;

import ConnectionDataBase.Playerresources;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author pk2109
 */
public class MoneyRanking extends AbstractRanking implements Serializable  {

    public MoneyRanking(){
        super();
    }

    @Override
    public List<Playerresources> produceRanking() {
        setPlayerPosition(1);
        setList(helper.getRanking("money"));
        return helper.getRanking("money");
    }

    @Override
    public List<Playerresources> getList() {
        setPlayerPosition(1);
        setList(helper.getRanking("money"));
        return helper.getRanking("money");
    }
    

    

}
