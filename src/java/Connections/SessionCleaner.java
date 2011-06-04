package Connections;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


public class SessionCleaner implements  HttpSessionListener, HttpSessionAttributeListener {

    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("Session created");
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("Session destroyed");
    }

    public void attributeAdded(HttpSessionBindingEvent event) {

    }

    public void attributeRemoved(HttpSessionBindingEvent event) {
        System.out.println("Removing: " + event.getName());

        if(event.getName().equals(ConnectionSingleton.idname))
            AuthorizationSingleton.updateUserStatus(event.getValue().toString(), false);
    }

    public void attributeReplaced(HttpSessionBindingEvent event) {

    }

}
