package ConnectionDataBase;

import org.hibernate.Query;
import org.hibernate.Session;

public class ExtrastatsHelper extends AbstractHelper {

    public void createInitialStats(String idname, int satisfaction,
            int starvation, int alcoholizm) {

        Session session = createNewSessionAndTransaction();
        Extrastats stats
            = new Extrastats(idname, satisfaction, starvation, alcoholizm);
        session.save(stats);
        commitTransaction(session);

    }

    public void deleteRecord(String idname) {

        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Extrastats where idname='"
                + idname + "'");
        Extrastats stats_record = (Extrastats)q.uniqueResult();

        if (stats_record != null) {
            session.delete(stats_record);  
        }
        commitTransaction(session);
    }

    public void updateSatisfaction (String idname, int satisfacation) {
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Extrastats where idname='"
                + idname + "'");
        Extrastats stats_record = (Extrastats)q.uniqueResult();

        if (stats_record != null) {
            stats_record.setSatisfaction(satisfacation);
        }

        commitTransaction(session);
    }

    public void updateStarvation (String idname, int starvation) {
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Extrastats where idname='"
                + idname + "'");
        Extrastats stats_record = (Extrastats)q.uniqueResult();

        if (stats_record != null) {
            stats_record.setStarvation(starvation);
        }

        commitTransaction(session);
    }

    public void updateAlcoholizm (String idname, int alcoholizm) {
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Extrastats where idname='"
                + idname + "'");
        Extrastats stats_record = (Extrastats)q.uniqueResult();

        if (stats_record != null) {
            stats_record.setAlcoholizm(alcoholizm);
        }

        commitTransaction(session);
    }

    public Extrastats getPlayerStatsRecrod(String idname) {
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Extrastats where idname='"
                + idname + "'");
        Extrastats stats_record = (Extrastats)q.uniqueResult();

        commitTransaction(session);
        return stats_record;
    }


}
