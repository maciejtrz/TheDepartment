package Connections;

import ConnectionDataBase.PlayerHelper;
import ConnectionDataBase.Players;
import utilities.LecturersManager;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author karol
 */
public class AuthorizationSingleton {

    public static boolean isFacesContext() {
        return FacesContext.getCurrentInstance() == null;
    }

    public static void goToIndexPage(HttpServletResponse response) {
        try {
            response.sendRedirect(ConnectionSingleton.gameName);
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public static boolean isSessionValid(HttpSession session) {
        return session.getAttribute(ConnectionSingleton.idname) != null;
    }

    public static void goToWelcomePage(HttpServletResponse response) throws IOException {
        response.sendRedirect(ConnectionSingleton.welcomePage);
    }


    public static void removeCookies(HttpServletRequest request, HttpServletResponse response,
            HttpSession session)
            throws IOException {

        Cookie idname = new Cookie(ConnectionSingleton.idname, "");
        Cookie pass = new Cookie(ConnectionSingleton.password, "");

        idname.setMaxAge(0);
        pass.setMaxAge(0);

        idname.setPath("/");
        pass.setPath("/");

        response.addCookie(idname);
        response.addCookie(pass);

    }

    public static boolean test(String username, String password, HttpSession session) {
        boolean result = false;

        String encodedPassword = EncodingSingleton.encodePassword(password);

        PlayerHelper playerHelper = new PlayerHelper();
        List<Players> players = playerHelper.getPlayers();
        Iterator<Players> iterator = players.iterator();

        while (iterator.hasNext()) {
            Players player = iterator.next();

            if (username.equals(player.getIdname())
                    && encodedPassword.equals(player.getPassword())) {

                LecturersManager mgr = new LecturersManager(username);
                session.setAttribute(ConnectionSingleton.idname, username);
                session.setAttribute(ConnectionSingleton.password, password);

                AuthorizationSingleton.updateUserStatus(username, true);

                result = true;
                break;

            }
        }

        return result;
    }

    public static void addCookies(HttpServletResponse response, HttpSession session) {
        Cookie idname =
                new Cookie(ConnectionSingleton.idname,
                session.getAttribute(ConnectionSingleton.idname).toString());

        Cookie pass = new Cookie(ConnectionSingleton.password,
                session.getAttribute(ConnectionSingleton.password).toString());

        idname.setMaxAge(24 * 60 * 60);
        pass.setMaxAge(24 * 60 * 60);

        idname.setPath("localhost");
        pass.setPath("localhost");

        idname.setPath("/");
        pass.setPath("/");

        response.addCookie(idname);
        response.addCookie(pass);

    }

    public static void updateUserStatus(String idname, boolean logged) {

        PlayerHelper playerHelper = new PlayerHelper();
        playerHelper.updateLoggedStatus(idname, logged);

    }

    public static void logoff() throws IOException {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

        updateUserStatus(session.getAttribute(ConnectionSingleton.idname).toString(), false);

        session.removeAttribute(ConnectionSingleton.idname);
        session.removeAttribute(ConnectionSingleton.password);
        session.removeAttribute(ConnectionSingleton.Auth);

        removeCookies(request, response, session);
    }
}