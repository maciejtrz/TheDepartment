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

            ResearchBag researchBag = (ResearchBag) event.getValue();

            System.out.println("Removing research bag of: " + researchBag.getUserid());
            UserManager.removeResearchBag(researchBag.getUserid());

        } else if (event.getName().equals(ConnectionSingleton.Auth)) {

            System.out.println("Removing auth in session listener");
            Auth auth = (Auth) event.getValue();

            UserManager.removeUser(auth.getUsername());

            Playerresources resources = auth.getResources();
            PlayerresourcesHelper resourcesHelper = new PlayerresourcesHelper();
            resourcesHelper.updateResources(resources);
        }

    }

    public void attributeReplaced(HttpSessionBindingEvent event) {
    }
}
