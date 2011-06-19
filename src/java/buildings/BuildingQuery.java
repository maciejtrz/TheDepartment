/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package buildings;

import ConnectionDataBase.BuildingsPositionHelper;
import Connections.ConnectionSingleton;
import Connections.UserManager;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kp1209
 */
public class BuildingQuery extends HttpServlet {

    private static final String BUILD_QUERY = "t";
    private static final String NOT_BUILD_QUERY = "n";
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        try {

            String input_position = request.getParameter("position");
            String input_buy = request.getParameter("buy");
            Integer position = Integer.parseInt(input_position);
            BuildingsPositionHelper helper
                    = new BuildingsPositionHelper();
            
            String userName 
               = request.getSession().getAttribute(ConnectionSingleton.idname).toString();

            String output = helper.getAtPosition(userName, position);

            if ( output == null) {
                output = "nop";
                out.print("nop");
            }

            else {
                out.print(output);
            }
            /*System.out.println("Printing all info of object!: " + this.toString() +
                               " PlayerName: " + userName +
                               " input_position: " + input_position +
                               " input_buy: " + input_buy +
                               " position: " + position +
                               " output: " + output +
                               " end of print: " + this.toString()); */
            if (input_buy.equals(BUILD_QUERY)) {
                UserManager.setBuildingPosition(userName, position);
            }

        } finally { 
            out.close();
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
