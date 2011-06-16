package utilities;

import Connections.ConnectionSingleton;
import java.util.Iterator;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class BasicUtils {

    public static String getUserName() {
        /* NOTE! May only be used when there is an open session.
           Otherwise, return "default" .*/
        String userName = "default";

        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext != null) {
            HttpSession session = (HttpSession)facesContext.
                    getExternalContext().getSession(false);

            if (session != null) {
                userName
                  = session.getAttribute(ConnectionSingleton.idname).toString();
            }
        }

        return userName;

    }

    public static UIComponent findComponent(UIComponent parent, String id) {
        if(id.equals(parent.getId())) {
                return parent;
        }
        Iterator<UIComponent> kids = parent.getFacetsAndChildren();
        while(kids.hasNext()) {
                UIComponent kid = kids.next();
                UIComponent found = findComponent(kid, id);
                if(found != null) {
                        return found;
                }
        }
        return null;
    }
}
