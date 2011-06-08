package utilities;

import Connections.ConnectionSingleton;
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
}
