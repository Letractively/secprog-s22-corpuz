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
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import security.hasher;

/**
 *
 * @author Lyle
 */
@WebServlet(name = "admin_reg_controller", urlPatterns = {"/admin_reg_controller"})
public class admin_reg_controller extends HttpServlet {

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
            throws ServletException, IOException, ParseException, NoSuchAlgorithmException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            
                staff newStaff = new staff();
                hasher hs = new hasher();
                
                newStaff.setStaff_id(request.getParameter("username"));
                newStaff.setPassword(request.getParameter("password"));
                String rePass = request.getParameter("repass");
                newStaff.setState("unlock");
                newStaff.setFname(request.getParameter("firstName"));
                newStaff.setLname(request.getParameter("lastName"));
                newStaff.setMname(request.getParameter("middleName"));
                newStaff.setEmail(request.getParameter("email"));
                newStaff.setAddress(request.getParameter("homeadd"));
                newStaff.setPosition(request.getParameter("position"));
                
                 ArrayList result2 = new admin_controller().checkAccountParameters(newStaff, rePass);
                 
                 request.setAttribute("resulta", result2);
          
          
                 if(!(result2.isEmpty()))
                 { 
                  /*  for(int i=0; i<result2.size(); i++)
                     {
                        out.println(result2.get(i)+ "<br/>");
                      //  System.out.println(result2.get(i));
                     }
                  */
                  request.getRequestDispatcher("administrator.jsp").forward(request,response);   
                 }
                 else
                 {
                    newStaff.setPassword(hs.setHash(request.getParameter("password")));
                    boolean result5 = new admin_controller().addAccount(newStaff);
                    if(result5 == true)
                    {
                       String resultChecker1 = "tama";
                       request.setAttribute("flagKo", resultChecker1);
                       boolean insertLog = new log_admin().addLogsAdmin("Username " + newStaff.getStaff_id().concat(", ").concat(newStaff.getPosition()).concat(", ").concat("has Created."));
                       request.getRequestDispatcher("administrator.jsp").forward(request,response);

                      
                    }
                    else
                    {
                        String resultChecker = "mali";
                       request.setAttribute("flagKo", resultChecker);
                       request.getRequestDispatcher("administrator.jsp").forward(request,response);
                    }
                   
                     
                 }
              
            
           //request.getRequestDispatcher("administrator.jsp").forward(request,response);
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
        try {
            try {
                processRequest(request, response);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(admin_reg_controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ParseException ex) {
            Logger.getLogger(admin_reg_controller.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            try {
                processRequest(request, response);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(admin_reg_controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ParseException ex) {
            Logger.getLogger(admin_reg_controller.class.getName()).log(Level.SEVERE, null, ex);
        }
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
