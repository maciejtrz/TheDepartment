/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Filters;

import Connections.AuthorizationSingleton;
import Connections.ConnectionSingleton;
import Connections.UserManager;
import ResearchPoints.ResearchBag;
import UserBeans.Auth;
import UserBeans.BuyLecturers;
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
import messageSystem.TradeMessageReader;

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

            Auth auth = (Auth) session.getAttribute(ConnectionSingleton.auth);
            ResearchBag researchBag = (ResearchBag)session.getAttribute(ConnectionSingleton.researchBag);
            TradeMessageReader tradeMessageReader = (TradeMessageReader)
                    session.getAttribute(ConnectionSingleton.tradeMessageReader);

            BuyLecturers buyLecturers = (BuyLecturers) session.getAttribute(ConnectionSingleton.buyLecturers);

            if(auth == null || auth.getUsername() == null) {
     
                 res.sendRedirect(ConnectionSingleton.addAuth);
                 return;
            }

            if(researchBag == null) {
               
                res.sendRedirect(ConnectionSingleton.addResearchBag);
                return;
            }

            if(tradeMessageReader == null) {

                res.sendRedirect(ConnectionSingleton.addTradeMessageReader);
                return;
                
            }

            if(!UserManager.isUserMonitored(auth.getUsername())) {
                UserManager.addUser(auth);
            }

            if(buyLecturers == null) {
                res.sendRedirect(ConnectionSingleton.addBuyLecturers);
                return;
            }
            
            if(buyLecturers.getUsername() == null) {
                buyLecturers.initialize(auth.getUsername());
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