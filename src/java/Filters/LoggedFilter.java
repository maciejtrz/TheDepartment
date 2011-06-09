/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Filters;

import Connections.AuthorizationSingleton;
import Connections.ConnectionSingleton;
import ResearchPoints.ResearchBag;
import UserBeans.Auth;
import java.io.IOException;
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
public class LoggedFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();


        if (!AuthorizationSingleton.isSessionValid(session)) {

            AuthorizationSingleton.goToIndexPage(res);
            return;

        } else {

            Auth auth = (Auth) session.getAttribute(ConnectionSingleton.Auth);
            ResearchBag researchBag = (ResearchBag)
                    session.getAttribute(ConnectionSingleton.researchBag);

            if(auth == null) {
     
                 res.sendRedirect(ConnectionSingleton.addAuth);
                 return;
            }

            if(researchBag == null) {
               
                res.sendRedirect(ConnectionSingleton.addResearchBag);
                return;
            }

            if(auth.logging){

                if(auth.getRememberBool()) {
                    AuthorizationSingleton.addCookies(res, session);
                } else {
                    AuthorizationSingleton.removeCookies(req, res, session);
                }

                auth.logging = false;
            }
        }
        
        chain.doFilter(request, response);
    }

    public void destroy() {
    }
}