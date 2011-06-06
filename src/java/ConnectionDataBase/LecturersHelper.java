package ConnectionDataBase;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;



public class LecturersHelper {

    Session session = null;
    
    public LecturersHelper () {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    public Lecturers getLecturer(String lecturerName) {
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Lecturers where "
                + "LecturerName = '" + lecturerName + "'" );
        Lecturers lecturer_info = (Lecturers)q.uniqueResult();
        return lecturer_info;
    }

}
