

package ConnectionDataBase;

import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class LecturersSpecializationsHelper extends AbstractHelper {

    public List<Lecturersspecializations> getSpecializationsRecord(String lecturerName) {
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Lecturersspecializations  where "
                + "LecturerName = '" + lecturerName + "'" );
        return (List<Lecturersspecializations>)q.list();
    }

    public void removeSpecializationsRecords (String lecturerName) {
        List<Lecturersspecializations> specializations
                = getSpecializationsRecord(lecturerName);
        Session session = createNewSessionAndTransaction();
        Iterator<Lecturersspecializations> it = specializations.iterator();
        while (it.hasNext()) {
            Lecturersspecializations spec = it.next();
            session.delete(spec);
        }
        commitTransaction(session);
    }

    public void setSpecialization (String name , String specialization , int boost) {
        Session session = createNewSessionAndTransaction();
        Lecturersspecializations specialization_record
                = new Lecturersspecializations(name, specialization,boost);
        session.save(specialization_record);
        commitTransaction(session);
    }

    public void setBoost(Lecturersspecializations spec, int newValue) {
        Session session = createNewSessionAndTransaction();
        spec.setBoost(newValue);
        session.update(spec);
        commitTransaction(session);
    }

}
