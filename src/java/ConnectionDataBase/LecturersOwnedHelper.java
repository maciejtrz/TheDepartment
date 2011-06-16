
package ConnectionDataBase;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class LecturersOwnedHelper extends AbstractHelper {

    public List<Lecturersowned> getLecturersList(String idname) {
        List<Lecturersowned> output_list = null;
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Lecturersowned where"
                 + " idname = '" + idname + "'" );
        output_list = (List<Lecturersowned>)q.list();
        commitTransaction(session);

        return output_list;
    }

    public void addLecturer (String lecturerName , String idname) {
        Session session = createNewSessionAndTransaction();
        Lecturersowned  lecturer_record
                = new Lecturersowned (lecturerName , idname);
        session.save(lecturer_record);
        commitTransaction(session);
    }

    public void deleteLecturer(String lecturerName) {
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Lecturersowned  where "
                + "LecturerName = '" + lecturerName + "'" );
        Lecturersowned  lecturer_record = (Lecturersowned ) q.uniqueResult();
        if (lecturer_record != null) {
            session.delete(lecturer_record);
            
        }
        super.commitTransaction(session);
    }
}
