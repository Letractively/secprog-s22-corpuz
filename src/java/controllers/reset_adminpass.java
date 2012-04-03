/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import classes.log_admin;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import security.hasher;
import security.login_checkUserifFailed;
import security.login_temp;

/**
 *
 * @author unseen
 */
public class reset_adminpass extends HttpServlet {

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
            throws ServletException, IOException, NoSuchAlgorithmException {
        HttpSession session = request.getSession(false);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            login_temp newPass = new login_temp();
            login_checkUserifFailed temp = new login_checkUserifFailed();
            hasher hs = new hasher();
            
            newPass.setUsername((String) session.getAttribute("UserName"));
            newPass.setPassword(hs.setHash((String) session.getAttribute("Password")));
            
            System.out.println( newPass.getPassword() );
             System.out.println( newPass.getUsername() );
            
            temp.changeAdminPassword(newPass);
            if(session.getAttribute("sessionName") == "accounting")
            {
                session.setAttribute("loggedIn_acct", "true");
                session.setAttribute("user", session.getAttribute("user"));
                session.setAttribute("mgr_pos", session.getAttribute("mgr_pos"));
                 boolean insertLog1 = new log_admin().addLogsFinancial(session.getAttribute("user").toString().concat(", ").concat(session.getAttribute("mgr_pos").toString()).concat(", ").concat("changed password."));
                boolean insertLog = new log_admin().addLogsFinancial(session.getAttribute("user").toString().concat(", ").concat(session.getAttribute("mgr_pos").toString()).concat(", ").concat("has Logged-In."));
                request.getRequestDispatcher("accounting.jsp").forward(request,response);
            }
            else
            {
                session.setAttribute("loggedIn_prod", true);
                 session.setAttribute("user", session.getAttribute("user"));
                 session.setAttribute("mgr_pos", session.getAttribute("mgr_pos"));
                 boolean insertLog1 = new log_admin().addLogsProduct(session.getAttribute("user").toString().concat(", ").concat(session.getAttribute("mgr_pos").toString()).concat(", ").concat("changed password."));
                 boolean insertLog = new log_admin().addLogsProduct(session.getAttribute("user").toString().concat(", ").concat(session.getAttribute("mgr_pos").toString()).concat(", ").concat("has Logged-In."));
                 request.getRequestDispatcher("product_mgt_controller").forward(request,response);
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
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(reset_adminpass.class.getName()).log(Level.SEVERE, null, ex);
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
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(reset_adminpass.class.getName()).log(Level.SEVERE, null, ex);
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
