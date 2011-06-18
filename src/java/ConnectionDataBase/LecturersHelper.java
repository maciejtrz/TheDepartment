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

        session.close();
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
        Lecturers newLecturer = new Lecturers();

        newLecturer.setLecturername(lecturerName);
        newLecturer.setPrice(price);
        newLecturer.setUsable(usable);

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

    public boolean isUsable(String lecturerName) {
        boolean output = false;
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Lecturers where "
                + "LecturerName = '" + lecturerName + "'" );
        Lecturers lecturer_info = (Lecturers)q.uniqueResult();
        if (lecturer_info != null) {
            output = lecturer_info.getUsable();
        }
        session.close();
        return output;
    }

    public String getLecturer(int idnumber) {
        String result = null;
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Lecturers where " +
                "LecturerId = " + idnumber);

        Lecturers lecturer = (Lecturers) q.uniqueResult();
        result = lecturer.getLecturername();

        session.close();

        return result;
    }

}
