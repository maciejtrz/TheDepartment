/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectionDataBase;

import java.util.Random;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author pk2109
 */
public class BuildingsPositionHelper extends AbstractHelper {

    /*
    public void createBuildingsPosition(String idname) {


        Buildingsposition buildingspos = new Buildingsposition();
        Random rand = new Random();
        int num = rand.nextInt(15) + 1;
        buildingspos.setPos(num, "bm1");
        num = rand.nextInt(15)+1;
        while(buildingspos.getPos(num) != null){
           num = rand.nextInt(15)+1;
        }
        buildingspos.setPos(num, "bs1");


    }
    */

    public void createBuildingPosition( String idname , int position ,
            String acronym) {

        Session session = createNewSessionAndTransaction();
        Buildingsposition buildingspos = new Buildingsposition();
        buildingspos.setIdname(idname);
        buildingspos.setPos(position, acronym);
        commitTransaction(session);


    }

    public void deleteBuildingsPosition(String idname) {
        Session session = createNewSessionAndTransaction();

        Query q = session.createQuery("from Buildingsposition where idname='"
                + idname + "'");
        Buildingsposition bp = (Buildingsposition) q.uniqueResult();
        if (bp !=null) {
            session.delete(bp);
            commitTransaction(session);
        }
    }

    // Used to overide the given position with give building code.
    public void updateBuildingPosition(String idname, int newValue,
                                    String building) {
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Buildingsposition where idname='"
                + idname + "'");
        Buildingsposition bp = (Buildingsposition) q.uniqueResult();
        if (bp != null) {
            bp.setPos(newValue, building);
            commitTransaction(session);
        }
    }

}