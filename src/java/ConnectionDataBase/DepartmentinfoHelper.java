/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectionDataBase;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class DepartmentinfoHelper extends AbstractHelper {


    public void createDepartment(String idName, String departmentName) {


        Session session = createNewSession();

        Departmentinfo departmentInfo = new Departmentinfo();
        departmentInfo.setIdname(idName);
        departmentInfo.setName(departmentName);

        startNewTransaction(session);
        session.save(departmentInfo);
        commitTransaction(session);

    }

    public void deleteDepartment(String idname) {
        Session session = createNewSessionAndTransaction();

        Query q = session.createQuery("from Departmentinfo where idname='"
                + idname + "'");
        Departmentinfo departmentInfo = (Departmentinfo) q.uniqueResult();

        if(departmentInfo != null) {
            session.delete(departmentInfo);
        }

        commitTransaction(session);
        
    }

    public boolean hasDepartment(String idname) {

        Session session = createNewSessionAndTransaction();

        Query q = session.createQuery("from Departmentinfo where idname='"
                + idname + "'");
        boolean hasDepartment = !q.list().isEmpty();

        commitTransaction(session);

        return hasDepartment;

    }

    public boolean departmentExists(String name) {
        Session session = createNewSessionAndTransaction();

        Query q = session.createQuery("from Departmentinfo where name='"
                + name + "'");
        boolean existsDepartment = !q.list().isEmpty();

        commitTransaction(session);

        return existsDepartment;
    }

}
