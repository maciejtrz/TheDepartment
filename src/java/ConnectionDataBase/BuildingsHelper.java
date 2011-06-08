/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectionDataBase;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author pk2109
 */
public class BuildingsHelper extends AbstractHelper {

    public void createBuildingsHelper(String idname) {

        Buildings buildings = new Buildings();
        buildings.setIdname(idname);
        buildings.setBrain(0);
        buildings.setDocpub(0);
        buildings.setLabolatories(0);
        buildings.setLectureroom(0);
        buildings.setMacchicken(0);
        buildings.setPhdsoffice(0);
        buildings.setProfessorsoffice(0);
        buildings.setStudentunion(0);
        buildings.setTresco(0);

        Session session = createNewSessionAndTransaction();
        session.save(buildings);
        commitTransaction(session);

    }

    public Buildings getBuildings(String idname) {
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Buildings where idname='"
                + idname + "'");
        return (Buildings) q.uniqueResult();
    }

    public void updateBrain(String idname, int newValue) {
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Buildings where idname='"
                + idname + "'");
        Buildings buildings = (Buildings) q.uniqueResult();
        if (buildings != null) {
            buildings.setBrain(newValue);
            commitTransaction(session);
        }
    }

    public void updateDocPub(String idname, int newValue) {
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Buildings where idname='"
                + idname + "'");
        Buildings buildings = (Buildings) q.uniqueResult();
        if (buildings != null) {
            buildings.setDocpub(newValue);
            commitTransaction(session);
        }
    }

    public void updateLabolatories(String idname, int newValue) {
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Buildings where idname='"
                + idname + "'");
        Buildings buildings = (Buildings) q.uniqueResult();
        if (buildings != null) {
            buildings.setLabolatories(newValue);
            commitTransaction(session);
        }
    }

    public void updateLectureRoom(String idname, int newValue) {
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Buildings where idname='"
                + idname + "'");
        Buildings buildings = (Buildings) q.uniqueResult();
        if (buildings != null) {
            buildings.setLectureroom(newValue);
            commitTransaction(session);
        }
    }

    public void updateMacChicken(String idname, int newValue) {
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Buildings where idname='"
                + idname + "'");
        Buildings buildings = (Buildings) q.uniqueResult();
        if (buildings != null) {
            buildings.setMacchicken(newValue);
            commitTransaction(session);
        }
    }

    public void updatePhDsOffice(String idname, int newValue) {
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Buildings where idname='"
                + idname + "'");
        Buildings buildings = (Buildings) q.uniqueResult();
        if (buildings != null) {
            buildings.setProfessorsoffice(newValue);
            commitTransaction(session);
        }
    }

    public void updateStudentUnion(String idname, int newValue) {
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Buildings where idname='"
                + idname + "'");
        Buildings buildings = (Buildings) q.uniqueResult();
        if (buildings != null) {
            buildings.setStudentunion(newValue);
            commitTransaction(session);
        }
    }

    public void updateTresco(String idname, int newValue) {
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Buildings where idname='"
                + idname + "'");
        Buildings buildings = (Buildings) q.uniqueResult();
        if (buildings != null) {
            buildings.setTresco(newValue);
            commitTransaction(session);
        }
    }
}
