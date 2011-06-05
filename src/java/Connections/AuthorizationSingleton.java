package Connections;

import ResearchPoints.Research;
import UserBeans.Auth;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
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

    public static boolean isLoggedOut(HttpSession session) {
        return session.getAttribute("loggedout") != null &&
                    session.getAttribute("loggedout").equals("true");
    }

    public static void goToWelcomePage(HttpServletResponse response) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect(ConnectionSingleton.welcomePage);
    }

    public static void checkCookies(HttpServletRequest request, HttpServletResponse response,
            HttpSession session, Auth auth) throws IOException{
        Cookie[] cookies = request.getCookies();
        String user = null;
        String password = null;

        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {

                response.getWriter().print(cookies[i].getName() + ": ");
                response.getWriter().println(cookies[i].getValue());

                if (cookies[i].getName().equals(ConnectionSingleton.idname)) {
                    user = cookies[i].getValue();
                } else if (cookies[i].getName().equals(ConnectionSingleton.password)) {
                    password = cookies[i].getValue();
                }
            }
        

        if (user != null && password != null) {
            System.out.println("Looking up cookies in db...");
            if(test(user,password,session)) {
                session.setAttribute(ConnectionSingleton.researchBag, new HashSet<Research>());
                session.setAttribute(ConnectionSingleton.Auth, auth);
                auth.setUsername(user);
                auth.setPassword(password);
                auth.updateResearchPoints();
                
                response.sendRedirect(ConnectionSingleton.welcomePage);
            }

            }

        }
    }

    public static void removeCookies(HttpServletRequest request, HttpServletResponse response,
            HttpSession session)
            throws IOException {

        Cookie idname = new Cookie(ConnectionSingleton.idname,"");
        Cookie pass = new Cookie(ConnectionSingleton.password,"");

        idname.setMaxAge(0);
        pass.setMaxAge(0);

        idname.setPath("/");
        pass.setPath("/");

        response.addCookie(idname);
        response.addCookie(pass);
        
    }

    public static boolean test(String username, String password, HttpSession session) {
        boolean result = false;

        try {
            Statement statement = Connections.ConnectionSingleton.createConnection().getStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM Players");
            String encodedPassword = EncodingSingleton.encodePassword(password);

            while (resultSet.next()) {
                String name = resultSet.getString(ConnectionSingleton.idname);
                String pass = resultSet.getString(ConnectionSingleton.password);

                if (name.equals(username) && pass.equals(encodedPassword)) {

                    session.setAttribute(ConnectionSingleton.idname, username);
                    session.setAttribute(ConnectionSingleton.password, password);

                    AuthorizationSingleton.updateUserStatus(username,true);


                    result = true;
                    break;
                    
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
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

        try {
            Statement statement = Connections.ConnectionSingleton.createConnection().getStatement();
            statement.execute("UPDATE Players SET "+ Connections.ConnectionSingleton.loggedIn
                    + "=" + (logged ? "true" : "false")+ " WHERE "
                    + Connections.ConnectionSingleton.idname+"='" + idname + "'");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void addAuth(HttpServletResponse response) throws IOException {
        response.sendRedirect(ConnectionSingleton.addAuth);
    }

    public void logoff() throws IOException {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

        updateUserStatus(session.getAttribute(ConnectionSingleton.idname).toString(),false);

        session.removeAttribute(ConnectionSingleton.idname);
        session.removeAttribute(ConnectionSingleton.password);
        session.removeAttribute(ConnectionSingleton.Auth);

        removeCookies(request, response, session);

    }
}
