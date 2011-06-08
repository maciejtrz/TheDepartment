package Connections;

import ConnectionDataBase.ResearchHelper;
import ResearchPoints.ResearchBag;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionCleaner implements HttpSessionListener, HttpSessionAttributeListener {

    public void sessionCreated(HttpSessionEvent se) {

        System.out.println("Session creation...");

    }

    public void sessionDestroyed(HttpSessionEvent se) {
    }

    public void attributeAdded(HttpSessionBindingEvent event) {
    }

    public void attributeRemoved(HttpSessionBindingEvent event) {
        if (event.getName().equals(ConnectionSingleton.idname)) {
            AuthorizationSingleton.updateUserStatus(event.getValue().toString(), false);
        } else if (event.getName().equals(ConnectionSingleton.researchBag)) {
            ResearchHelper researchHelper = new ResearchHelper();
            ResearchBag researchBag = (ResearchBag) event.getValue();
            if (researchBag != null && !researchBag.getFinishedResearches().isEmpty()) {
                researchHelper.addResearches(researchBag.getFinishedResearches());
            }
        }

    }

    public void attributeReplaced(HttpSessionBindingEvent event) {
    }
}
