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
public class StudentsRanking extends AbstractRanking implements Serializable {


    public StudentsRanking() {
        super();
    }

    @Override
    public List<Playerresources> getList() {
        setPlayerPosition(1);
        setList(helper.getRanking("undergraduatesnumber"));
        return helper.getRanking("undergraduatesnumber");
    }

    @Override
    public List<Playerresources> produceRanking() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
