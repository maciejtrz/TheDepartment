/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rankings;

import ConnectionDataBase.Playerresources;
import ConnectionDataBase.PlayerresourcesHelper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pk2109
 */
public class StudentsRankingSingleton  implements Serializable {


    private static List<Playerresources> list = new ArrayList<Playerresources>();
    private static int playerPosition;
    private static PlayerresourcesHelper helper = new PlayerresourcesHelper();

   /*
    private static StudentsRankingSingleton singleton;


    private StudentsRankingSingleton() {
        //
    }
    
    public StudentsRankingSingleton createSingleton() {
        if (singleton == null) {
            singleton = new StudentsRankingSingleton();
        }
        return singleton;
    } */

    public static void setPlayerPosition(int p){
        playerPosition = p;
    }

    public static int getPlayerPosition(){
        int pos = playerPosition;
        playerPosition++;
        return pos ;
    }

    public static void setList(List<Playerresources> l){
        list=l;
    }

    public static List<Playerresources> getList(){
        setPlayerPosition(1);

        return list;
    }

    public static void produceRanking(){
    setList(helper.getRanking("undergraduatesnumber"));
    }


}
