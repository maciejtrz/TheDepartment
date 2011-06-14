/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rankings;

import ConnectionDataBase.Playerresources;
import java.util.List;

/**
 *
 * @author pk2109
 */
public class PhDsRanking extends AbstractRanking {

    public PhDsRanking() {
        super();
    }

    @Override
    public List<Playerresources> getList() {
        setPlayerPosition(1);
        setList(helper.getRanking("phdsnumber"));
        return helper.getRanking("phdsnumber");
    }

    @Override
    public List<Playerresources> produceRanking() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
