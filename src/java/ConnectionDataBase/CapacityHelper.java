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
public class CapacityHelper extends AbstractHelper {

    public void createCapacity(String idname) {

        Capacity capacity = new Capacity();
        capacity.setIdname(idname);
        capacity.setPhdscapacity(0);
        capacity.setStudentscapacity(0);
        capacity.setProfessorscapacity(0);

        Session session = createNewSessionAndTransaction();
        session.save(capacity);
        commitTransaction(session);

    }

    public void deleteCapacity(String idname) {
        Session session = createNewSessionAndTransaction();

        Query q = session.createQuery("from Capacity where idname='"
                + idname + "'");
        Capacity capacity = (Capacity) q.uniqueResult();
        if (capacity !=null) {
            session.delete(capacity);
            commitTransaction(session);
        }
    }

    public Capacity getCapacity(String idname) {
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Capacity where idname='"
                + idname + "'");
        return (Capacity) q.uniqueResult();
    }

    public void updateStudentsCapacity(String idname, int newValue) {
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Capacity where idname='"
                + idname + "'");
        Capacity capacity = (Capacity) q.uniqueResult();
        if (capacity != null) {
            capacity.setStudentscapacity(newValue);
            commitTransaction(session);
        }
    }

    public void updatePhDsCapacity(String idname, int newValue) {
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Capacity where idname='"
                + idname + "'");
        Capacity capacity = (Capacity) q.uniqueResult();
        if (capacity != null) {
            capacity.setStudentscapacity(newValue);
            commitTransaction(session);
        }
    }

    public void updateProfessorsCapacity(String idname, int newValue) {
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Capacity where idname='"
                + idname + "'");
        Capacity capacity = (Capacity) q.uniqueResult();
        if (capacity != null) {
            capacity.setProfessorscapacity(newValue);
            commitTransaction(session);
        }
    }
}
