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
import java.util.ArrayList;
import classes.*;

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
             
            /*
            if(request.getParameter("purchase2")!=null)
            {
                orders order = new orders();
                int orderId = order.getOrder_id();
                out.println("orderID " + orderId);
            }
            */ 
            
            if(request.getParameter("purchase")!=null)
            {
             
            String quantity_text = request.getParameter("quantity");
            int quantity = Integer.parseInt(quantity_text);
            
            String choice_prod = request.getParameter("choice");
            
            Date today1 = Calendar.getInstance().getTime();
            String today = new SimpleDateFormat("yyyy-MM-dd").format(today1);
            
            String user = request.getParameter("userId");
            
            int orderId = 0;
            float price = 0;
            
            
            
           
            ArrayList name = new ArrayList();
            
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
                 
            
           PreparedStatement pstmt4 = conn.prepareStatement("SELECT prod_price FROM products where prod_id = ?");
           pstmt4.setString(1, choice_prod);
          
            
            ResultSet rs1 = pstmt4.executeQuery();

                while(rs1.next())
                    {
                        price = rs1.getFloat("prod_price");
                        
                    }
                
              //  out.print(price);
                
             //include parameters
          
            
            
            
          
            //SQL Query
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO cust_order (cust_id, order_date) VALUES(?,?)");
            
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
            
          
            
             PreparedStatement pstmt5 = conn.prepareStatement("select order_acct.prod_id, products.prod_type, products.prod_title, order_acct.quantity, order_acct.sell_price from order_acct inner join products on order_acct.prod_id = products.prod_id where order_id = ?");
             pstmt5.setInt(1, orderId);
          
            
            ResultSet rs2 = pstmt5.executeQuery();

                while(rs2.next())
                    {
                        name.add(rs2.getString("order_acct.prod_id"));
                        name.add(rs2.getString("products.prod_type"));
                        name.add(rs2.getString("products.prod_title"));
                        name.add(rs2.getInt("order_acct.quantity"));
                        name.add(rs2.getFloat("order_acct.sell_price"));
                        
                    }
            
            
                    
             //close DB connection
            conn.close();
                
       
            
        }
        catch(SQLException ex)
            {
              ex.printStackTrace();
            }
            
              
              request.setAttribute("purchaseResult", name);
              request.getRequestDispatcher("purchase.jsp").forward(request,response); 
         }
            else if(request.getParameter("buyMore")!= null)
            {
                request.setAttribute("hasBuy", "hasBuy");
                request.getRequestDispatcher("home.jsp").forward(request,response); 
               
            }
            else if(request.getParameter("hasPurchase")!= null)
            {
                 String quantity_text = request.getParameter("quantity");
            int quantity = Integer.parseInt(quantity_text);
            
            String choice_prod = request.getParameter("choice");
            
        //    Date today1 = Calendar.getInstance().getTime();
         //   String today = new SimpleDateFormat("yyyy-MM-dd").format(today1);
            
         //   String user = request.getParameter("userId");
            
            int orderId = 0;
            float price = 0;
            
            
            
           
            ArrayList name = new ArrayList();
            
            
            
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
                
                
               orderId = orderId + 0;
                 
            
           PreparedStatement pstmt4 = conn.prepareStatement("SELECT prod_price FROM products where prod_id = ?");
           pstmt4.setString(1, choice_prod);
          
            
            ResultSet rs1 = pstmt4.executeQuery();

                while(rs1.next())
                    {
                        price = rs1.getFloat("prod_price");
                        
                    }
                
              //  out.print(price);
                
           
           
            
            
             //SQL Query
            PreparedStatement pstmt2 = conn.prepareStatement("INSERT INTO order_acct(order_id, prod_id, quantity, sell_price) VALUES(?,?,?,?)");
            
            
            pstmt2.setInt(1, orderId);
            pstmt2.setString(2, choice_prod);
            pstmt2.setInt(3, quantity);
            pstmt2.setFloat(4, price);
          
            //execute query
            pstmt2.executeUpdate();
            
          
            
             PreparedStatement pstmt5 = conn.prepareStatement("select order_acct.prod_id, products.prod_type, products.prod_title, order_acct.quantity, order_acct.sell_price from order_acct inner join products on order_acct.prod_id = products.prod_id where order_id = ?");
             pstmt5.setInt(1, orderId);
          
            
            ResultSet rs2 = pstmt5.executeQuery();

                while(rs2.next())
                    {
                        name.add(rs2.getString("order_acct.prod_id"));
                        name.add(rs2.getString("products.prod_type"));
                        name.add(rs2.getString("products.prod_title"));
                        name.add(rs2.getInt("order_acct.quantity"));
                        name.add(rs2.getFloat("order_acct.sell_price"));
                        
                    }
            
            
                    
             //close DB connection
            conn.close();
                
       
            
        }
        catch(SQLException ex)
            {
              ex.printStackTrace();
            }
            
              
              request.setAttribute("purchaseResult", name);
              request.getRequestDispatcher("purchase.jsp").forward(request,response); 
            }
           
            else if(request.getParameter("removeProd")!=null)
            {
                 int orderId = 0;
                 
                  
                  String remove_prod = request.getParameter("choice");
  
            ArrayList name = new ArrayList();
            
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
                
                
               orderId = orderId + 0;
                 
            
            
             //SQL Query
            PreparedStatement pstmt2 = conn.prepareStatement("DELETE FROM order_acct  WHERE order_id = ? AND prod_id = ?");
            
            
            pstmt2.setInt(1, orderId);
            pstmt2.setString(2, remove_prod);
           
          
            //execute query
            pstmt2.executeUpdate();
            
          
            
             PreparedStatement pstmt5 = conn.prepareStatement("select order_acct.prod_id, products.prod_type, products.prod_title, order_acct.quantity, order_acct.sell_price from order_acct inner join products on order_acct.prod_id = products.prod_id where order_id = ?");
             pstmt5.setInt(1, orderId);
          
            
            ResultSet rs2 = pstmt5.executeQuery();

                while(rs2.next())
                    {
                        name.add(rs2.getString("order_acct.prod_id"));
                        name.add(rs2.getString("products.prod_type"));
                        name.add(rs2.getString("products.prod_title"));
                        name.add(rs2.getInt("order_acct.quantity"));
                        name.add(rs2.getFloat("order_acct.sell_price"));
                        
                    }
            
            
                    
             //close DB connection
            conn.close();
                
       
            
        }
        catch(SQLException ex)
            {
              ex.printStackTrace();
            }
            
              
              request.setAttribute("purchaseResult", name);
              request.getRequestDispatcher("purchase.jsp").forward(request,response); 
            }
      
            else if (request.getParameter("checkout")!=null)
            {
                try
                {
                    ConnectionFactory myFactory = ConnectionFactory.getFactory();
                    Connection conn = myFactory.getConnection();
                
                    String user = request.getParameter("userId");
                     String billingAdd = "";
                     String card_name = "" ;
                        int card_num = 0;
                     String exp_date = "";
                      String card_type = "";                  
                    Date today1 = Calendar.getInstance().getTime();
                    String today = new SimpleDateFormat("yyyy-MM-dd").format(today1);
                    int orderId = 0;
                    
                     PreparedStatement pstmt = conn.prepareStatement("SELECT max(order_id) AS E FROM cust_order");
 
                    ResultSet rs = pstmt.executeQuery();

                        while(rs.next())
                            {
                                orderId = rs.getInt("E");

                            }
                
                     PreparedStatement pstmt0 = conn.prepareStatement("select billing_add from cust_acct where cust_id = ?");
                     pstmt0.setString(1, user);
                     
                      ResultSet rs2 = pstmt0.executeQuery();
                     while(rs2.next())
                    {
                        billingAdd = rs2.getString("billing_add");
                    }
                     
                     PreparedStatement pstmt1 = conn.prepareStatement("select card_name, card_num, card_type, exp_date from info_tracker where cust_id = ?");
                     pstmt1.setString(1, user);
                     
                      ResultSet rs3 = pstmt1.executeQuery();
                      
                     while(rs3.next())
                    {
                         card_name = rs3.getString("card_name");
                         card_num = rs3.getInt("card_num");
                        card_type = rs3.getString("card_type");
                         exp_date = rs3.getString("exp_date");

                    }
                     
                      //SQL Query
                    PreparedStatement pstmt2 = conn.prepareStatement("UPDATE cust_order SET billing_add = ?, paid_date = ? , card_name = ?, card_num = ?, card_type = ?, exp_date =? where order_id = ?");

                    pstmt2.setString(1, billingAdd);
                    pstmt2.setString(2, today);
                    pstmt2.setString(3,  card_name);
                    pstmt2.setInt(4, card_num);
                    pstmt2.setString(5, card_type);
                    pstmt2.setString(6, exp_date);
                    pstmt2.setInt(7, orderId);

                    //execute query
                    pstmt2.executeUpdate();
                    
                     conn.close();
                     
                }
                catch(SQLException ex)
                 {
                     ex.printStackTrace();
                 }
                
           
                 request.setAttribute("buySuccessful", "true");
                 request.setAttribute("hasBuy", null);
                 
                 request.getRequestDispatcher("home.jsp").forward(request,response);
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
