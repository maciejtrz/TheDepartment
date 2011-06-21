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
        capacity.setPhdscapacity(100);
        capacity.setStudentscapacity(1000);
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
        }

        commitTransaction(session);
    }

    public Capacity getCapacity(String idname) {
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Capacity where idname='"
                + idname + "'");

        Capacity capacity = (Capacity) q.uniqueResult();
        session.close();
        return capacity;
    }

    /* The following methods increase capacities by update values. */
    public void updateStudentsCapacity(String idname, int updateValue) {
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Capacity where idname='"
                + idname + "'");
        Capacity capacity = (Capacity) q.uniqueResult();
        if (capacity != null) {
            int student_cap = capacity.getStudentscapacity();
            capacity.setStudentscapacity(student_cap + updateValue);
            session.update(capacity);  
        }

        commitTransaction(session);
    }

    public void updatePhDsCapacity(String idname, int updateValue) {
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Capacity where idname='"
                + idname + "'");
        Capacity capacity = (Capacity) q.uniqueResult();
        if (capacity != null) {
            int phd_cap = capacity.getPhdscapacity();
            capacity.setPhdscapacity(phd_cap + updateValue);
            session.update(capacity);
        }

        commitTransaction(session);
    }

    public void updateProfessorsCapacity(String idname, int updateValue) {
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Capacity where idname='"
                + idname + "'");
        Capacity capacity = (Capacity) q.uniqueResult();
        if (capacity != null) {
            int prof_cap = capacity.getProfessorscapacity();
            capacity.setProfessorscapacity(prof_cap + updateValue);
            session.update(capacity);
        }
        commitTransaction(session);
    }
}
