/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import classes.login;
import dbconnection.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;

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
            int i = 1;
            boolean result = false;
            String username, password;
            login newlogin = new login();
            
            username = request.getParameter("username");
            password = request.getParameter("password");
           
            newlogin.setCust_id(username);
            newlogin.setPassword(password);
            
            System.out.print(newlogin.getCust_id());
            System.out.print(newlogin.getPassword());
            //newlogin.setState("looged-in");
            
            ConnectionFactory myFactory = ConnectionFactory.getFactory();
            Connection conn = myFactory.getConnection();
            try {
                PreparedStatement pstmt = conn.prepareStatement("select * from customer where cust_id = ? and password = ?");
                pstmt.setString(i++, newlogin.getCust_id());
                pstmt.setString(i++, newlogin.getPassword());
                
                ResultSet rs = pstmt.executeQuery();

                while(rs.next())
                { result=true;}
                conn.close();
                
                if(result == true)
                {
                    
                session.setAttribute("loggedIn", "true");
                session.setAttribute("user", username);
                response.sendRedirect("home.jsp");
                }
                else
                {
                    session.setAttribute("loggedIn", null);
                    response.sendRedirect("index.jsp");
                }
                System.out.print("PASOK");
                
            } catch (SQLException ex) {
                Logger.getLogger(login_controller.class.getName()).log(Level.SEVERE, null, ex);
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
