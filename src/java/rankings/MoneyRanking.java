/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rankings;

/**
 *
 * @author pk2109
 */
public class MoneyRanking extends AbstractRanking {

    public MoneyRanking(){
        super();
    }

    @Override
    public void produceRanking() {
        setList(helper.getRanking("money"));
    }

}
