package Connections;

import ConnectionDataBase.Playerresources;
import ConnectionDataBase.PlayerresourcesHelper;
import ResearchPoints.ResearchBag;
import UserBeans.Auth;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.icefaces.application.PushRenderer;

public class SessionCleaner implements HttpSessionListener, HttpSessionAttributeListener {

    public void sessionCreated(HttpSessionEvent se) {

    }

    public void sessionDestroyed(HttpSessionEvent se) {
    }

    public void attributeAdded(HttpSessionBindingEvent event) {

        if(event.getName().equals(ConnectionSingleton.auth)) {
            Auth auth = (Auth) event.getValue();
            System.out.println("Adding auth with username: " +auth.getUsername());
            UserManager.addUser(auth);
        }
    }

    public void attributeRemoved(HttpSessionBindingEvent event) {
        if (event.getName().equals(ConnectionSingleton.idname)) {

            AuthorizationSingleton.updateUserStatus(event.getValue().toString(), false);
            PushRenderer.addCurrentSession(ConnectionSingleton.idname);

        } else if (event.getName().equals(ConnectionSingleton.researchBag)) {

            ResearchBag researchBag = (ResearchBag) event.getValue();

            UserManager.removeResearchBag(researchBag.getUserid());

        } else if (event.getName().equals(ConnectionSingleton.auth)) {

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
