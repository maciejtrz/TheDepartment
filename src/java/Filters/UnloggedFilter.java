package Filters;

import Connections.AuthorizationSingleton;
import Connections.ConnectionSingleton;
import UserBeans.Auth;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author karol
 */
public class UnloggedFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession();


         if (AuthorizationSingleton.isFacesContext()) {
                System.out.println("No faces context!");
                AuthorizationSingleton.goToIndexPage(res);
                return;

         } else if(AuthorizationSingleton.isSessionValid(session)) {

               AuthorizationSingleton.goToWelcomePage(res);

         } else {

             Auth auth = (Auth) session.getAttribute(ConnectionSingleton.Auth);

            if(auth == null) {
                AuthorizationSingleton.addAuth(res);
                return;
            }

           AuthorizationSingleton.checkCookies(req, res, session, auth);
             
        }

        chain.doFilter(request, response);
    }

    public void destroy() {

    }

}