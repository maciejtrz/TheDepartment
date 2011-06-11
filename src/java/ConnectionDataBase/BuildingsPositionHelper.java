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

    
    public void initiateBuildingsPosition(String idname) {


        Session session = createNewSessionAndTransaction();
        Buildingsposition buildingspos = new Buildingsposition(idname);
        //Random rand = new Random();
        buildingspos.setPos(1, "bm1");
        buildingspos.setPos(2, "bs1");
        session.save(buildingspos);
        commitTransaction(session);
    }

    public void createBuildingPosition( String idname , int position ,
            String acronym) {


            updateBuildingPosition(idname, position, acronym);
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

    // Returns the acronym of building that occupies given position.
    public String getPosition(String idname, int position) {
        String output = null;
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Buildingsposition where idname='"
                + idname + "'");
        Buildingsposition bp = (Buildingsposition) q.uniqueResult();
        if (bp != null) {
            output = bp.getPos(position);
        }
        return output;
    }

    // Denetrmines whether anything is built on that position.
    public boolean isAllowedToBuild(String idname, int position) {

        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Buildingsposition where idname='"
                + idname + "'");
        Buildingsposition bp = (Buildingsposition) q.uniqueResult();
        if (bp != null) {
            String result = bp.getPos(position);
            return (result == null);
        }
        return false;

    }

}