/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import classes.*;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Lyle
 */
@WebServlet(name = "admin_lock_controller", urlPatterns = {"/admin_lock_controller"})
public class admin_lock_controller extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false); 
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /*
             * TODO output your page here. You may use following sample code.
             
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet admin_lock_controller</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet admin_lock_controller at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            * */
            
            staff newStaff = new staff();
                
                newStaff.setStaff_id(request.getParameter("staffID"));
                newStaff.setState(request.getParameter("choose"));
                String positionName = session.getAttribute("Position").toString();
                
           //     out.println(request.getParameter("staffID"));
            //    out.println(request.getParameter("choose"));
                
                ArrayList result1 = new admin_controller().checkLockParameters(newStaff, positionName);
                
                request.setAttribute("resulta1", result1);
                
                if(!(result1.isEmpty()))
                {
                    request.getRequestDispatcher("administrator.jsp").forward(request,response); 
                }
                else if ((result1.isEmpty()))
                {
                boolean result = new admin_controller().updateState(newStaff, positionName);
                if(result == true)
                    {
                       String resultChecker1 = "tama";
                       request.setAttribute("flagKo2", resultChecker1);
                       request.getRequestDispatcher("administrator.jsp").forward(request,response);

                      
                    }
                 if(result == false)
                    {
                        String resultChecker = "mali";
                       request.setAttribute("flagKo2", resultChecker);
                       request.getRequestDispatcher("administrator.jsp").forward(request,response);
                    }
               }
            
            
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
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
     * Handles the HTTP
     * <code>POST</code> method.
     *
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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
