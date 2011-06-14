/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Filters;

import ConnectionDataBase.DepartmentinfoHelper;
import Connections.AuthorizationSingleton;
import Connections.ConnectionSingleton;
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


public class PlayingFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        if (!AuthorizationSingleton.isSessionValid(session)) {

            AuthorizationSingleton.goToIndexPage(res);
            return;

        } else {
            DepartmentinfoHelper departmentInfo = new DepartmentinfoHelper();

            boolean isItWelcomePage = req.getPathTranslated().endsWith(ConnectionSingleton.AddDepartment);
            boolean hasDepartment = departmentInfo.hasDepartment(session.
                    getAttribute(ConnectionSingleton.idname).toString());

           if (!hasDepartment && !isItWelcomePage) {
                AuthorizationSingleton.goToWelcomePage(res);
                return;
           } else if(hasDepartment && isItWelcomePage) {
                res.sendRedirect("/TheDepartment/faces/Logged/Play.xhtml");
                return;
           }

        }

        chain.doFilter(request, response);
    }

    public void destroy() {

    }



}
