package ConnectionDataBase;

import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;



public class LecturersHelper extends AbstractHelper {


    public Lecturers getLecturer(String lecturerName) {
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Lecturers where "
                + "LecturerName = '" + lecturerName + "'" );
        Lecturers lecturer_info = (Lecturers)q.uniqueResult();
        return lecturer_info;
    }


    public void removeLecturer(String lecturerName) {

        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Lecturers where "
                + "LecturerName = '" + lecturerName + "'" );
        Lecturers lecturer_info = (Lecturers)q.uniqueResult();
        if (lecturer_info != null) {
            session.delete(lecturer_info);
        }
        commitTransaction(session);
    }

    public void addLecturer(String lecturerName , int price,
            boolean usable) {

        Session session = createNewSessionAndTransaction();
        Lecturers newLecturer
                = new Lecturers(lecturerName, price, usable);
        session.save(newLecturer);
        commitTransaction(session);
    }

    public void setUsable(String lecturerName, boolean usable) {
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Lecturers where "
                + "LecturerName = '" + lecturerName + "'" );
        Lecturers lecturer_info = (Lecturers)q.uniqueResult();
        if (lecturer_info != null) {
            lecturer_info.setUsable(usable);
        }
        commitTransaction(session);
    }

}
