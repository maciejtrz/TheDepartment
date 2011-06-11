package Connections;

import ConnectionDataBase.Playerresources;
import ConnectionDataBase.PlayerresourcesHelper;
import ConnectionDataBase.ResearchHelper;
import ResearchPoints.ResearchBag;
import UserBeans.Auth;
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

        if(event.getName().equals(ConnectionSingleton.Auth)) {
            Auth auth = (Auth) event.getValue();
            UserManager.addUser(auth);
        }
    }

    public void attributeRemoved(HttpSessionBindingEvent event) {
        if (event.getName().equals(ConnectionSingleton.idname)) {
            AuthorizationSingleton.updateUserStatus(event.getValue().toString(), false);
        } else if (event.getName().equals(ConnectionSingleton.researchBag)) {

            ResearchHelper researchHelper = new ResearchHelper();
            ResearchBag researchBag = (ResearchBag) event.getValue();

           if (researchBag != null && !researchBag.getAvailableResearch().isEmpty()) {

                researchHelper.addResearches(researchBag.getUserid(),
                        researchBag.getAvailableResearch());
           }
        } else if (event.getName().equals(ConnectionSingleton.Auth)) {
            Auth auth = (Auth) event.getValue();
            UserManager.removeUser(auth.getUsername());
        }

    }

    public void attributeReplaced(HttpSessionBindingEvent event) {
    }
}
