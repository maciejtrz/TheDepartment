

package ConnectionDataBase;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class LecturersAvailableHelper {

    Session session = null;

    public LecturersAvailableHelper() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    public List<Lecturersavailable> getLecturersList(String idname) {
        List<Lecturersavailable> output_list = null;
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Lecturersavailable where"
                 + " idname = '" + idname + "'" );
        output_list = (List<Lecturersavailable>)q.list();

        return output_list;
    }

    public void addLecturer (String lecturerName , String idname) {
        Lecturersavailable lecturer_record
                = new Lecturersavailable(lecturerName , idname);
        Transaction tx = session.beginTransaction();
        //tx.begin();
        session.save(lecturer_record);
        //tx.commit();
    }

    public void deleteLecturer(String lecturerName) {
        Transaction tx = session.beginTransaction();
        //tx.begin();

        Query q = session.createQuery("from Lecturersavailable where "
                + "LecturerName = '" + lecturerName + "'" );
        Lecturersavailable lecturer_record = (Lecturersavailable) q.uniqueResult();
        if (lecturer_record != null) {
            session.delete(lecturer_record);
        }

        //tx.commit();
    }

}
