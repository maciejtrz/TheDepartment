

package ConnectionDataBase;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class LecturersSpecializationsHelper {

    Session session = null;

    public LecturersSpecializationsHelper () {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    public List<Lecturersspecializations> getSpecializationsRecord(String lecturerName) {
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Lecturersspecializations  where "
                + "LecturerName = '" + lecturerName + "'" );
        return (List<Lecturersspecializations>)q.list();
    }

}
