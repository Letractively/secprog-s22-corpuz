/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import classes.info_tracker;
import dbconnection.ConnectionFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import security.hasher;

/**
 *
 * @author unseen
 */
public class addcustomer_controller2 extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
       try {
            
            info_tracker newTracker = (info_tracker) request.getAttribute("newTracker");
            int i = 1;
            ConnectionFactory myFactory = ConnectionFactory.getFactory();
            Connection conn = myFactory.getConnection();
            try {
                
                
                    hasher hs = new hasher();
                    String credit_card = null;
                    try {
                        credit_card = hs.setHash(newTracker.getCard_num());
                        System.out.println(credit_card);
                    } catch (NoSuchAlgorithmException ex) {
                        Logger.getLogger(addcustomer_controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
            
                
                PreparedStatement pstmt = null;
                try {
                    pstmt = conn.prepareStatement("insert into info_tracker(cust_id, card_name,card_num,card_type, exp_date) values(?,?,?,?,?)");
                } catch (SQLException ex) {
                    Logger.getLogger(addcustomer_controller2.class.getName()).log(Level.SEVERE, null, ex);
                }
                pstmt.setString(i++, newTracker.getCustomerID());
                pstmt.setString(i++, newTracker.getCard_name());
                pstmt.setString(i++, credit_card);
                pstmt.setString(i++, newTracker.getCard_type());
                pstmt.setDate(i++, newTracker.getExp_date());
                
                pstmt.executeUpdate();
                i=1;
                pstmt = conn.prepareStatement("insert into info_billing(cust_id, billing_add) values(?,?)");
                pstmt.setString(i++, newTracker.getCustomerID());
                pstmt.setString(i++, newTracker.getShipping());
                
                pstmt.executeUpdate();
                
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(addcustomer_controller2.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            response.sendRedirect("index.jsp");
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
            Logger.getLogger(addcustomer_controller2.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(addcustomer_controller2.class.getName()).log(Level.SEVERE, null, ex);
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
