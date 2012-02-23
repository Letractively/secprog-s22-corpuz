/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import security.*;

/**
 *
 * @author arvin
 */
public class login_controller extends HttpServlet {

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
            boolean check_username, check_password, check_state;
            login_temp login_user = new login_temp();
            login_user.setUsername((String) request.getAttribute("UserName"));
            login_user.setPassword((String) request.getAttribute("Password"));
            login_checkUserifFailed temp_check = new login_checkUserifFailed();
            check_username = temp_check.checkUsername(login_user);
            check_password = temp_check.checkPassword(login_user);
            
            if(check_username == true && check_password == true)
            {
                session.setAttribute("loggedIn", "true");
                session.setAttribute("user", login_user.getUsername());
                response.sendRedirect("home.jsp");
            }
            else if(check_username == true && check_password == false)
            {
                session.setAttribute("loggedIn", "false");
                session.getLastAccessedTime();
                
                response.sendRedirect("index.jsp");
                
            }
            else
            {
                session.setAttribute("loggedIn", null);
                session.setAttribute("brute", "set");
                response.sendRedirect("index.jsp");
                
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
