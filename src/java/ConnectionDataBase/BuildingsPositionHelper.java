/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectionDataBase;

import buildings.Building;
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
        Random rand = new Random();
        int bm_position = rand.nextInt(16) + 1;
        int qt_position = rand.nextInt(16) + 1;
        while (bm_position == qt_position) {
            qt_position = rand.nextInt(16) + 1;
        }
        buildingspos.setPos(bm_position, Building.CODE_BLACK_MARKET);
        buildingspos.setPos(qt_position, Building.CODE_BOB);
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
            
        }
        commitTransaction(session);
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
            
        }
        commitTransaction(session);
    }

    // Returns the acronym of building that occupies given position.
    public String getAtPosition(String idname, int position) {
        String output = null;
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Buildingsposition where idname='"
                + idname + "'");
        Buildingsposition bp = (Buildingsposition) q.uniqueResult();
        if (bp != null) {
            output = bp.getPos(position);
        }
        commitTransaction(session);
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
        commitTransaction(session);
        return false;

    }

    // Returns the position of a given building specified by its
    // acronym. Returns 0 if the acronym is not built, which should not happen.
    public int getPosition (String idname, String acronym) {
        int output = 0;
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Buildingsposition where idname='"
                + idname + "'");
        Buildingsposition bp = (Buildingsposition) q.uniqueResult();
        if (bp != null) {
            // Magic numbers
            for (int i = 1 ; i <= 16 ; i++) {
                String cur = bp.getPos(i);
                if (cur != null) {
                    if (cur.equals(acronym)) {
                        output = i;
                        break;
                    }
                }
            }
        }
        commitTransaction(session);
        return output;
    }

}