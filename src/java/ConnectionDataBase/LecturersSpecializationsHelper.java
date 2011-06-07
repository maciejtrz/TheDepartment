

package ConnectionDataBase;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class LecturersSpecializationsHelper extends AbstractHelper {

    public List<Lecturersspecializations> getSpecializationsRecord(String lecturerName) {
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Lecturersspecializations  where "
                + "LecturerName = '" + lecturerName + "'" );
        return (List<Lecturersspecializations>)q.list();
    }

}
