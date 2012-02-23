/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import classes.customer;
import classes.info_tracker;
import dbconnection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author arvin
 */
public class addcustomer_controller extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            customer newCustomer = (customer) request.getAttribute("customer");
            
            
            int i = 1;
            int lol = 1;
            
            
            ConnectionFactory myFactory = ConnectionFactory.getFactory();
            Connection conn = myFactory.getConnection();
            try 
            {
                
                
                PreparedStatement pstmt1 = conn.prepareStatement("insert into cust_acct(cust_id, first_name, last_name, middle_name, email_add, billing_add) values (?,?,?,?,?,?)");
                pstmt1.setString(i++, newCustomer.getCust_id());
                pstmt1.setString(i++, newCustomer.getFname());
                pstmt1.setString(i++, newCustomer.getLname());
                pstmt1.setString(i++, newCustomer.getMname());
                pstmt1.setString(i++, newCustomer.getEmail());
                pstmt1.setString(i++, newCustomer.getBilling());
                pstmt1.executeUpdate();
                i = 1;
                PreparedStatement pstmt3 = conn.prepareStatement("insert into oldpass(cust_id, password) values (?,?)");
                pstmt3.setString(i++, newCustomer.getCust_id());
                pstmt3.setString(i++, newCustomer.getPassword());
                
                pstmt3.executeUpdate();
                i = 1;
                
                PreparedStatement pstmt = conn.prepareStatement("insert into customer(cust_id, password, state) values (?,?,?)");
                pstmt.setString(i++, newCustomer.getCust_id());
                pstmt.setString(i++, newCustomer.getPassword());
                pstmt.setInt(i++, lol);
                pstmt.executeUpdate();
                
                
               
                conn.close();
                
                
            } catch (SQLException ex) {
                Logger.getLogger(addcustomer_controller.class.getName()).log(Level.SEVERE, null, ex);
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
