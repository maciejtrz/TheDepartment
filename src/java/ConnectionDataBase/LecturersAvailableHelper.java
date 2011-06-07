

package ConnectionDataBase;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class LecturersAvailableHelper extends AbstractHelper {


    public List<Lecturersavailable> getLecturersList(String idname) {
        List<Lecturersavailable> output_list = null;

        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Lecturersavailable where"
                 + " idname = '" + idname + "'" );
        output_list = (List<Lecturersavailable>)q.list();

        return output_list;
    }

    public void addLecturer (String lecturerName , String idname) {
        Lecturersavailable lecturer_record
                = new Lecturersavailable(lecturerName , idname);
        Session session = createNewSessionAndTransaction();
        session.save(lecturer_record);
        commitTransaction(session);
    }

    public void deleteLecturer(String lecturerName) {
        Session session = createNewSessionAndTransaction();

        Query q = session.createQuery("from Lecturersavailable where "
                + "LecturerName = '" + lecturerName + "'" );
        Lecturersavailable lecturer_record = (Lecturersavailable) q.uniqueResult();
        if (lecturer_record != null) {
            session.delete(lecturer_record);
            commitTransaction(session);
        }
    }

}
