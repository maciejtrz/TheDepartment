package ConnectionDataBase;

import org.hibernate.Query;
import org.hibernate.Session;

public class EventsHelper extends AbstractHelper {


    public void createRecord(String idname) {
        Events event = new Events(idname, 0 ,0 ,0, 0 ,0 ,0, 0 ,0 ,0, 0 ,0 ,0 ,
                0 ,0 ,0, 0 ,0 ,0, 0 ,0 ,0);

        Session session = createNewSessionAndTransaction();
        session.save(event);
        commitTransaction(session);
    }

    public void setBarNight(String idname, int new_value) {
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Events where idname='"
                + idname + "'");
        Events event = (Events) q.uniqueResult();
        if (event != null) {
            event.setBarnight(new_value);
            session.update(event);
            commitTransaction(session);
        }
    }

    public void setFacebookBooked(String idname, int new_value) {
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Events where idname='"
                + idname + "'");
        Events event = (Events) q.uniqueResult();
        if (event != null) {
            event.setFacebookblocked(new_value);
            session.update(event);
            commitTransaction(session);
        }
    }

    public void setGirlsArrival(String idname, int new_value) {
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Events where idname='"
                + idname + "'");
        Events event = (Events) q.uniqueResult();
        if (event != null) {
            event.setGirlsarrival(new_value);
            session.update(event);
            commitTransaction(session);
        }
    }
    
    public void setGovernmentGrant(String idname, int new_value) {
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Events where idname='"
                + idname + "'");
        Events event = (Events) q.uniqueResult();
        if (event != null) {
            event.setGovernmentgrant(new_value);
            session.update(event);
            commitTransaction(session);
        }
    }

    public void setHaskellConference(String idname, int new_value) {
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Events where idname='"
                + idname + "'");
        Events event = (Events) q.uniqueResult();
        if (event != null) {
            event.setHaskellconference(new_value);
            session.update(event);
            commitTransaction(session);
        }
    }

    public void setHighRanking(String idname, int new_value) {
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Events where idname='"
                + idname + "'");
        Events event = (Events) q.uniqueResult();
        if (event != null) {
            event.setHighranking(new_value);
            session.update(event);
            commitTransaction(session);
        }
    }

    public void setLabsHacking(String idname, int new_value) {
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Events where idname='"
                + idname + "'");
        Events event = (Events) q.uniqueResult();
        if (event != null) {
            event.setLabshacking(new_value);
            session.update(event);
            commitTransaction(session);
        }
    }

    public void setLabsInFire(String idname, int new_value) {
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Events where idname='"
                + idname + "'");
        Events event = (Events) q.uniqueResult();
        if (event != null) {
            event.setLabsinfire(new_value);
            session.update(event);
            commitTransaction(session);
        }
    }

    public void setLecturerPromotion(String idname, int new_value) {
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Events where idname='"
                + idname + "'");
        Events event = (Events) q.uniqueResult();
        if (event != null) {
            event.setLecturerpromotion(new_value);
            session.update(event);
            commitTransaction(session);
        }
    }

    public void setMacChickenPromotion(String idname, int new_value) {
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Events where idname='"
                + idname + "'");
        Events event = (Events) q.uniqueResult();
        if (event != null) {
            event.setMacchickenpromotion(new_value);
            session.update(event);
            commitTransaction(session);
        }
    }

    public void setMalice(String idname, int new_value) {
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Events where idname='"
                + idname + "'");
        Events event = (Events) q.uniqueResult();
        if (event != null) {
            event.setMalice(new_value);
            session.update(event);
            commitTransaction(session);
        }
    }

    public void setNobelPrice(String idname, int new_value) {
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Events where idname='"
                + idname + "'");
        Events event = (Events) q.uniqueResult();
        if (event != null) {
            event.setNobelprice(new_value);
            session.update(event);
            commitTransaction(session);
        }
    }

    public void setOutOfChicken(String idname, int new_value) {
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Events where idname='"
                + idname + "'");
        Events event = (Events) q.uniqueResult();
        if (event != null) {
            event.setOutofchicken(new_value);
            session.update(event);
            commitTransaction(session);
        }
    }

    public void setPaperLeak(String idname, int new_value) {
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Events where idname='"
                + idname + "'");
        Events event = (Events) q.uniqueResult();
        if (event != null) {
            event.setPaperleak(new_value);
            session.update(event);
            commitTransaction(session);
        }
    }

    public void setPhdPromotion(String idname, int new_value) {
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Events where idname='"
                + idname + "'");
        Events event = (Events) q.uniqueResult();
        if (event != null) {
            event.setPhdpromotion(new_value);
            session.update(event);
            commitTransaction(session);
        }
    }

    public void setPintosCw(String idname, int new_value) {
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Events where idname='"
                + idname + "'");
        Events event = (Events) q.uniqueResult();
        if (event != null) {
            event.setPintoscw(new_value);
            session.update(event);
            commitTransaction(session);
        }
    }

    public void setPrivateCompanyGrant(String idname, int new_value) {
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Events where idname='"
                + idname + "'");
        Events event = (Events) q.uniqueResult();
        if (event != null) {
            event.setPrivatecompanygrant(new_value);
            session.update(event);
            commitTransaction(session);
        }
    }

    public void setPubDemolished(String idname, int new_value) {
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Events where idname='"
                + idname + "'");
        Events event = (Events) q.uniqueResult();
        if (event != null) {
            event.setPubdemolished(new_value);
            session.update(event);
            commitTransaction(session);
        }
    }

    public void setTrescoMiracle(String idname, int new_value) {
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Events where idname='"
                + idname + "'");
        Events event = (Events) q.uniqueResult();
        if (event != null) {
            event.setTrescomiracle(new_value);
            session.update(event);
            commitTransaction(session);
        }
    }

    public void setTrescoTragedy(String idname, int new_value) {
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Events where idname='"
                + idname + "'");
        Events event = (Events) q.uniqueResult();
        if (event != null) {
            event.setTrescotragedy(new_value);
            session.update(event);
            commitTransaction(session);
        }
    }

    public void setUnionParty(String idname, int new_value) {
        Session session = createNewSessionAndTransaction();
        Query q = session.createQuery("from Events where idname='"
                + idname + "'");
        Events event = (Events) q.uniqueResult();
        if (event != null) {
            event.setUnionparty(new_value);
            session.update(event);
            commitTransaction(session);
        }
    }
    



}
