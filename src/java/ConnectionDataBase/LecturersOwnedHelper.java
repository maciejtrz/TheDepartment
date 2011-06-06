
package ConnectionDataBase;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class LecturersOwnedHelper {

    Session session = null;

    public LecturersOwnedHelper() {
       this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    public List<Lecturersowned> getLecturersList(String idname) {
        List<Lecturersowned> output_list = null;
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Lecturersowned where"
                 + " idname = '" + idname + "'" );
        output_list = (List<Lecturersowned>)q.list();

        return output_list;
    }

    public void addLecturer (String lecturerName , String idname) {
        Lecturersowned  lecturer_record
                = new Lecturersowned (lecturerName , idname);
        Transaction tx = session.beginTransaction();
        //tx.begin();
        session.save(lecturer_record);
        //tx.commit();
    }

    public void deleteLecturer(String lecturerName) {
        Transaction tx = session.beginTransaction();
        //tx.begin();

        Query q = session.createQuery("from Lecturersowned  where "
                + "LecturerName = '" + lecturerName + "'" );
        Lecturersowned  lecturer_record = (Lecturersowned ) q.uniqueResult();
        if (lecturer_record != null) {
            session.delete(lecturer_record);
        }

        //tx.commit();
    }
}
