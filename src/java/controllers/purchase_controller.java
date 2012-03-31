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
import javax.servlet.http.HttpSession;
import classes.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import security.inputValidator;
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
        HttpSession session = request.getSession(false);
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
            
                ArrayList errors = new ArrayList();
                
                String quantitytext = request.getParameter("quantity");
                if(!(quantitytext.matches("^\\d+$")))
                {
                    errors.add("Not an Integer Value");
                    request.setAttribute("errorResult", errors);
                    request.getRequestDispatcher("home.jsp").forward(request,response);
                }
            
               int quantity = Integer.parseInt(quantitytext);
               
               if(quantity < 1)
               {
                   errors.add("Quantity should be greater than 0");
                   request.setAttribute("errorResult", errors);
                   request.getRequestDispatcher("home.jsp").forward(request,response);
               }
            
            String choice_prod = request.getParameter("choice");
            
            if(choice_prod == null)
            {
                   errors.add("Please select a product");
                   request.setAttribute("errorResult", errors);
                   request.getRequestDispatcher("home.jsp").forward(request,response);
            }
            
            Date today1 = Calendar.getInstance().getTime();
            String today = new SimpleDateFormat("yyyy-MM-dd").format(today1);
      
            boolean  hasNull = false;
            
            int orderId = 0;
            float price = 0;

            ArrayList name = new ArrayList();
            ArrayList name1 = new ArrayList();
         
            String user1 = session.getAttribute("UserName").toString();
            
            
            
             try
            {
           
            //opens DB Connection
            ConnectionFactory myFactory = ConnectionFactory.getFactory();
            Connection conn = myFactory.getConnection();
            
                        
            PreparedStatement pstmt0 = conn.prepareStatement("SELECT order_id FROM cust_order WHERE billing_id IS NULL AND cust_id = ?");
            pstmt0.setString(1, user1);
          
            
            ResultSet rs = pstmt0.executeQuery();
            
                while(rs.next())
                    {
                        orderId = rs.getInt("order_id");
                       hasNull = true;
                    }
                System.out.println(orderId);

           PreparedStatement pstmt4 = conn.prepareStatement("SELECT prod_price FROM products where prod_id = ? and isDeleted = 0");
           pstmt4.setString(1, choice_prod);
          
            
            ResultSet rs1 = pstmt4.executeQuery();

                while(rs1.next())
                    {
                        price = rs1.getFloat("prod_price");
                        
                    }
                
              //  out.print(price);
                
             //include parameters
          
            
            
            
           if (hasNull == false)
          {
              //SQL Query
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO cust_order (cust_id, order_date) VALUES(?,?)");
            
            pstmt.setString(1, user1);
            pstmt.setString(2, today);
            
          
            //execute query
            pstmt.executeUpdate();
          }
            
          //SQL Query
           PreparedStatement pstmt7 = conn.prepareStatement("SELECT max(order_id) AS E FROM cust_order WHERE cust_id = ?");
            pstmt7.setString(1, user1);
          
            
            ResultSet rs7 = pstmt7.executeQuery();
            
                while(rs7.next())
                    {
                        orderId = rs7.getInt("E");
                      
                    }
                
                session.setAttribute("orderIDNum", orderId);
                
             //SQL Query
            PreparedStatement pstmt2 = conn.prepareStatement("INSERT INTO order_acct(order_id, prod_id, quantity, sell_price) VALUES(?,?,?,?) where prod_id = (select prod_id from products where isDeleted = 0)");
            
            
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
            
             PreparedStatement pstmt8 = conn.prepareStatement("select sum(quantity*sell_price) AS F from order_acct where order_id = ?");
             pstmt8.setInt(1, orderId);
          
            
            ResultSet rs8 = pstmt8.executeQuery();

                while(rs8.next())
                    {
                        name1.add(rs8.getFloat("F"));
                    }
            
                    
             //close DB connection
            conn.close();
                
       
            
        }
        catch(SQLException ex)
            {
              ex.printStackTrace();
            }
            
              
              request.setAttribute("purchaseResult", name);
              request.setAttribute("purchaseResult1", name1);
              request.getRequestDispatcher("purchase.jsp").forward(request,response); 
         }
            
            else if(request.getParameter("viewCart")!= null)
            {
                  try
                    {
                    
                    boolean  hasNull = false;
            
                    int orderId = 0;
                      String user1 = session.getAttribute("UserName").toString();

                        ArrayList name = new ArrayList();
                        ArrayList name1 = new ArrayList();
           
            //opens DB Connection
            ConnectionFactory myFactory = ConnectionFactory.getFactory();
            Connection conn = myFactory.getConnection();
            
                        
            PreparedStatement pstmt0 = conn.prepareStatement("SELECT order_id FROM cust_order WHERE billing_id IS NULL AND cust_id = ?");
            pstmt0.setString(1, user1);
          
            
            ResultSet rs = pstmt0.executeQuery();
            
                while(rs.next())
                    {
                        orderId = rs.getInt("order_id");
                       hasNull = true;
                    }
                
                
                if(hasNull == false)
                {
                     request.setAttribute("clear", true);
                     request.getRequestDispatcher("purchase.jsp").forward(request,response);
                }
                
               session.setAttribute("orderIDNum", orderId);
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
            
             PreparedStatement pstmt8 = conn.prepareStatement("select sum(quantity*sell_price) AS F from order_acct where order_id = ?");
             pstmt8.setInt(1, orderId);
          
            
            ResultSet rs8 = pstmt8.executeQuery();

                while(rs8.next())
                    {
                        name1.add(rs8.getFloat("F"));
                    }
            
                    
             //close DB connection
            conn.close();
                
              request.setAttribute("purchaseResult", name);
              request.setAttribute("purchaseResult1", name1);
              request.getRequestDispatcher("purchase.jsp").forward(request,response); 
            
        }
        catch(SQLException ex)
            {
              ex.printStackTrace();
            }
         
 
              
            }
            
            
            else if(request.getParameter("buyMore")!= null)
            {
                request.setAttribute("hasBuy", "hasBuy");
                request.getRequestDispatcher("home.jsp").forward(request,response); 
               
            }
            else if(request.getParameter("hasPurchase")!= null)
            {
                
                ArrayList errors = new ArrayList();
                  String quantitytext = request.getParameter("quantity");
                    if(!(quantitytext.matches("^\\d+$")))
                    {
                        errors.add("Not an Integer Value");
                        request.setAttribute("errorResult", errors);
                        request.getRequestDispatcher("home.jsp").forward(request,response);
                    }

                int quantity = Integer.parseInt(quantitytext);

                if(quantity < 1)
                {
                    errors.add("Quantity should be greater than 0");
                    request.setAttribute("errorResult", errors);
                    request.getRequestDispatcher("home.jsp").forward(request,response);
                }
            
            String choice_prod = request.getParameter("choice");
            
            if(choice_prod == null)
            {
                   errors.add("Please select a product");
                   request.setAttribute("errorResult", errors);
                   request.getRequestDispatcher("home.jsp").forward(request,response);
            }
            
        //    Date today1 = Calendar.getInstance().getTime();
         //   String today = new SimpleDateFormat("yyyy-MM-dd").format(today1);
            
         //   String user = request.getParameter("userId");
            
            String orderId_text = session.getAttribute("orderIDNum").toString();
            int orderId = Integer.parseInt(orderId_text);
            float price = 0;
            
            
            
           
            ArrayList name = new ArrayList();
            ArrayList name1 = new ArrayList();
            
            
             try
        {
            //opens DB Connection
            ConnectionFactory myFactory = ConnectionFactory.getFactory();
            Connection conn = myFactory.getConnection();
            
            
         
                 
            
           PreparedStatement pstmt4 = conn.prepareStatement("SELECT prod_price FROM products where prod_id = ? and isDeleted = 0");
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
            
                 PreparedStatement pstmt8 = conn.prepareStatement("select sum(quantity*sell_price) AS F from order_acct where order_id = ?");
             pstmt8.setInt(1, orderId);
          
            
            ResultSet rs8 = pstmt8.executeQuery();

                while(rs8.next())
                    {
                        name1.add(rs8.getFloat("F"));
                    }
            
                    
             //close DB connection
            conn.close();
                
       
            
        }
        catch(SQLException ex)
            {
              ex.printStackTrace();
            }
            
              
              request.setAttribute("purchaseResult", name);
              request.setAttribute("purchaseResult1", name1);
              request.getRequestDispatcher("purchase.jsp").forward(request,response); 
            }
           
            else if(request.getParameter("removeProd")!=null)
            {
                 String orderId_text = session.getAttribute("orderIDNum").toString();
                 int orderId = Integer.parseInt(orderId_text);
                  
                  String remove_prod = request.getParameter("choice");
  
            ArrayList name = new ArrayList();
            ArrayList name1 = new ArrayList();
            
             try
              {
            //opens DB Connection
            ConnectionFactory myFactory = ConnectionFactory.getFactory();
            Connection conn = myFactory.getConnection();
            
            
            
                 
            
            
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
            
                   PreparedStatement pstmt8 = conn.prepareStatement("select sum(quantity*sell_price) AS F from order_acct where order_id = ?");
             pstmt8.setInt(1, orderId);
          
            
            ResultSet rs8 = pstmt8.executeQuery();

                while(rs8.next())
                    {
                        name1.add(rs8.getFloat("F"));
                    }
                
            
                    
             //close DB connection
            conn.close();
                
       
            
        }
        catch(SQLException ex)
            {
              ex.printStackTrace();
            }
            
              
              request.setAttribute("purchaseResult", name);
              request.setAttribute("purchaseResult1", name1);
              request.getRequestDispatcher("purchase.jsp").forward(request,response); 
            }
      
            else if (request.getParameter("checkout")!=null)
            {
                
                String user = session.getAttribute("UserName").toString();
                    String card = request.getParameter("cCard");
                    ArrayList errors = new ArrayList();
                    
                    inputValidator iv = new inputValidator();
                    
                    String orderId_text = session.getAttribute("orderIDNum").toString();
                    int orderId = Integer.parseInt(orderId_text);
                    
                    if(!(iv.checkCard(card, user)))
                    {
                        out.println("Wrong Credit Card Number");
                        
                        out.println(" <form method ='post' action ='purchase_controller'>");
                         out.println("<input type='submit'  class='registerButton' name ='viewCart' value='View Cart'><br>");
                         out.println("</form>");
                        
                       // out.println("<a href = 'purchase.jsp'>BACK</a>");
                    }
                    else if(!(iv.checkCart(orderId)))
                    {
                        out.println("Cart is empty");
                        
                        out.println(" <form method ='post' action ='purchase_controller'>");
                         out.println("<input type='submit'  class='registerButton' name ='viewCart' value='View Cart'><br>");
                         out.println("</form>");
                        
                       // out.println("<a href = 'purchase.jsp'>BACK</a>");
                    }
                    
                    else
                    {
                try
                {
                    
                   
                    ConnectionFactory myFactory = ConnectionFactory.getFactory();
                    Connection conn = myFactory.getConnection();
                
                    
                    String billingAdd_txt = session.getAttribute("addressID").toString();
                    int billingAdd = Integer.parseInt(billingAdd_txt);
                    
                    
                    
                     String card_name = "" ;
                        String card_num = "";
                     String exp_date = "";
                      String card_type = "";                  
                    Date today1 = Calendar.getInstance().getTime();
                    String today = new SimpleDateFormat("yyyy-MM-dd").format(today1);
                     
                    
                     
                /*
                     PreparedStatement pstmt0 = conn.prepareStatement("select billing_add from cust_acct where cust_id = ?");
                     pstmt0.setString(1, user);
                     
                      ResultSet rs2 = pstmt0.executeQuery();
                     while(rs2.next())
                    {
                        billingAdd = rs2.getString("billing_add");
                    }
                     */
                   
                     
                     PreparedStatement pstmt1 = conn.prepareStatement("select card_name, card_num, card_type, exp_date from info_tracker where cust_id = ?");
                     pstmt1.setString(1, user);
                     
                      ResultSet rs3 = pstmt1.executeQuery();
                      
                     while(rs3.next())
                    {
                         card_name = rs3.getString("card_name");
                         card_num = rs3.getString("card_num");
                        card_type = rs3.getString("card_type");
                         exp_date = rs3.getString("exp_date");

                    }
                     
                      //SQL Query
                    PreparedStatement pstmt2 = conn.prepareStatement("UPDATE cust_order SET billing_id = ?, paid_date = ? , card_name = ?, card_num = ?, card_type = ?, exp_date =? where order_id = ?");

                    pstmt2.setInt(1, billingAdd);
                    pstmt2.setString(2, today);
                    pstmt2.setString(3,  card_name);
                    pstmt2.setString(4, card_num);
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
                 boolean result = new log_admin().addLogsCustomer("Username: " + session.getAttribute("user") + " made a transaction. (OrderID:" + orderId + " ) ");
                 request.getRequestDispatcher("home.jsp").forward(request,response);
                    }
            }
            else if(request.getParameter("comment") != null)
            {
                
                
                session.setAttribute("isView", session.getAttribute("isView"));
                System.out.print(request.getParameter("choice").toString());
                ArrayList name = new ArrayList();
                ConnectionFactory myFactory = ConnectionFactory.getFactory();
                Connection conn = myFactory.getConnection();
                try {
                    int i = 1;
                    String prod_title = null;
                    PreparedStatement view = conn.prepareStatement("select * from cust_act where prod_id = ?");
                    view.setString(i++, request.getParameter("choice").toString());
                    
                    ResultSet rs = view.executeQuery();
                    
                    while(rs.next())
                    {
                        name.add(rs.getString("cust_id"));
                        name.add(rs.getDate("prod_com_date"));
                        name.add(rs.getString("prod_com"));
                    }
                    i=1;
                   view = conn.prepareStatement("select prod_title from products where prod_id = ? ");
                    view.setString(i++, request.getParameter("choice"));
                    
                    rs = view.executeQuery();
                    while(rs.next())
                    {
                        
                        prod_title = rs.getString("prod_title");
                    }
                    conn.close();
                    session.setAttribute("user", session.getAttribute("user"));
                    session.setAttribute("choice", request.getParameter("choice"));
                    session.setAttribute("product", prod_title);
                    request.setAttribute("a", name);
                    request.getRequestDispatcher("review.jsp").forward(request,response);
                } catch (SQLException ex) {
                    Logger.getLogger(purchase_controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
           
        } 
        finally 
        {            
            out.close();
        }
        
        
        
    } //outer branch
 
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
