/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dbconnection.ConnectionFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Lyle
 */
@WebServlet(name = "purchase_controller", urlPatterns = {"/purchase_controller"})
public class purchase_controller extends HttpServlet {

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
            /*
             * TODO output your page here. You may use following sample code.
             
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet purchase_controller</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet purchase_controller at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            * 
            */
             
             
            String quantity_text = request.getParameter("quantity");
            int quantity = Integer.parseInt(quantity_text);
            
            String choice_prod = request.getParameter("choice");
            
            Date today1 = Calendar.getInstance().getTime();
            String today = new SimpleDateFormat("yyyy-MM-dd").format(today1);
            
            String user = request.getParameter("userId");
            
            int orderId = 0;
            float price = 0;
            
            
             /*
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet purchase_controller</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet purchase_controller at " + today + "</h1> <br>");
            out.println("<h1>Servlet purchase_controller at " + choice_prod + "</h1> <br>");
            out.println("<h1>Servlet purchase_controller at " + user + "</h1 <br>");
          
            out.println("</body>");
            out.println("</html>");
            
            */
            
             try
        {
            //opens DB Connection
            ConnectionFactory myFactory = ConnectionFactory.getFactory();
            Connection conn = myFactory.getConnection();
            
            
            PreparedStatement pstmt0 = conn.prepareStatement("SELECT max(order_id) AS E FROM cust_order");
            
          
            
            ResultSet rs = pstmt0.executeQuery();

                while(rs.next())
                    {
                        orderId = rs.getInt("E");
                       
                    }
                
                 orderId = orderId + 1;
                 
                 out.print(orderId);
                 
           PreparedStatement pstmt4 = conn.prepareStatement("SELECT prod_price FROM products where prod_id = ?");
           pstmt4.setString(1, choice_prod);
          
            
            ResultSet rs1 = pstmt4.executeQuery();

                while(rs1.next())
                    {
                        price = rs1.getFloat("prod_price");
                        
                    }
                
                out.print(price);
                
             //include parameters
          
            
            
            
          
            //SQL Query
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO cust_order(null, cust_id, order_date, null, null, null, null, null, null) VALUES(?,?)");
            
            pstmt.setString(1, user);
            pstmt.setString(2, today);
            
          
            //execute query
            pstmt.executeUpdate();
            
            
             //SQL Query
            PreparedStatement pstmt2 = conn.prepareStatement("INSERT INTO order_acct(order_id, prod_id, quantity, sell_price) VALUES(?,?,?,?)");
            
            
            pstmt2.setInt(1, orderId);
            pstmt2.setString(2, choice_prod);
            pstmt2.setInt(3, quantity);
            pstmt2.setFloat(4, price);
          
            //execute query
            pstmt2.executeUpdate();
            
             //close DB connection
            conn.close();

       
            
        }
        catch(SQLException ex)
            {
              ex.printStackTrace();
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
