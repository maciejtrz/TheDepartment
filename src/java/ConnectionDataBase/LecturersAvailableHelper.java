

package ConnectionDataBase;

import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;


public class LecturersAvailableHelper extends AbstractHelper {


    public List<Lecturersavailable> getLecturersList(String idname) {
        List<Lecturersavailable> output_list = null;

        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Lecturersavailable where"
                 + " idname = '" + idname + "'" );
        output_list = (List<Lecturersavailable>)q.list();

        return output_list;
    }

    public void removeAvailableLecturers(String idname) {
        List<Lecturersavailable> av_list
                = getLecturersList(idname);
        Session session = createNewSessionAndTransaction();
        Iterator <Lecturersavailable> it = av_list.iterator();
        while (it.hasNext()) {
            Lecturersavailable cur = it.next();
            session.delete(cur);
        }
        commitTransaction(session);

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
