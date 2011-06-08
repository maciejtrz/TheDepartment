package Filters;

import Connections.AuthorizationSingleton;
import Connections.ConnectionSingleton;
import UserBeans.Auth;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
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

        System.out.println("Unlogged Fitler");
        if (AuthorizationSingleton.isSessionValid(session)) {

            System.out.println("Going to welcome page");
            AuthorizationSingleton.goToWelcomePage(res);
            return;

        } else {
            System.out.println("Checking cookies");

            Cookie[] cookies = req.getCookies();
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
                    if (AuthorizationSingleton.test(user, password, session)) {
                        res.sendRedirect(ConnectionSingleton.welcomePage);
                        return;
                    }

                }

            }
        }

        chain.doFilter(request, response);
    }

    public void destroy() {
    }

}
