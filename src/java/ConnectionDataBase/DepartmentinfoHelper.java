/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectionDataBase;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DepartmentinfoHelper {

    Session session = null;

    public DepartmentinfoHelper() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    public void createDepartment(String idName, String departmentName) {
        Departmentinfo departmentInfo = new Departmentinfo();
        departmentInfo.setIdname(idName);
        departmentInfo.setName(departmentName);

        Transaction tx = session.beginTransaction();
        tx.begin();

        session.save(departmentInfo);
       
        tx.commit();

    }

    public void deleteDepartment(String idname) {
        Transaction tx = session.beginTransaction();

        tx.begin();
        Query q = session.createQuery("from Departmentinfo where idname='"
                + idname + "'");
        Departmentinfo departmentInfo = (Departmentinfo) q.uniqueResult();

        if(departmentInfo != null)
            session.delete(departmentInfo);
        tx.commit();
        
    }

}
