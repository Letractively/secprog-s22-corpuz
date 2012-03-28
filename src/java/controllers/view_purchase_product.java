/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dbconnection.ConnectionFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Lyle
 */
public class view_purchase_product extends HttpServlet {

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
            out.println("<title>Servlet view_purchase_product</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet view_purchase_product at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            */
            ArrayList name = new ArrayList();
              String user1 = session.getAttribute("UserName").toString();
            try
            {
            //opens DB Connection
                ConnectionFactory myFactory = ConnectionFactory.getFactory();
                Connection conn = myFactory.getConnection();
                
            //SQL Query
                
                    PreparedStatement pstmt = conn.prepareStatement("select  b.prod_id ,a.paid_date, a.order_id, c.prod_type, c.prod_title, b.quantity, b.sell_price, (b.quantity * b.sell_price) as E from cust_order  as a inner join order_acct as b on a.order_id = b.order_id inner join products as c on b.prod_id = c.prod_id where cust_id = ? and a.paid_date IS NOT NULL");


                    pstmt.setString(1, user1);
                   

                ResultSet rs = pstmt.executeQuery();

                    while(rs.next())
                        {
                            name.add(rs.getString("b.prod_id"));
                            name.add(rs.getString("a.paid_date"));
                            name.add(rs.getString("c.prod_type"));
                            
                            name.add(rs.getString("c.prod_title"));
                            name.add(rs.getInt("b.quantity"));
                            name.add(rs.getFloat("b.sell_price"));
                            name.add(rs.getFloat("E"));
                        }
                    
                  

                

                        //close DB connection
                        conn.close();

                        request.setAttribute("queryResultA", name);
                        request.getRequestDispatcher("viewpurchase.jsp").forward(request,response);
                        
                        
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
