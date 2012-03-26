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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author Lyle
 */
public class acct_query extends HttpServlet {

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
            out.println("<title>Servlet acct_query</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet acct_query at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            * 
            * */
            
            String date1 = request.getParameter("date1");
            String date2 = request.getParameter("date2");
            String acct_query = request.getParameter("acct_query");
            ArrayList name = new ArrayList();
            
            try
            {
            //opens DB Connection
                ConnectionFactory myFactory = ConnectionFactory.getFactory();
                Connection conn = myFactory.getConnection();
                
            //SQL Query
                if(acct_query.contentEquals("salesProduct"))
                {
                    PreparedStatement pstmt = conn.prepareStatement("SELECT order_acct.prod_id, products.prod_type, products.prod_title, sum( order_acct.quantity * order_acct.sell_price ) as Total FROM order_acct, products WHERE products.prod_id = order_acct.prod_id AND order_id IN (SELECT order_id FROM cust_order WHERE paid_date BETWEEN ? AND ?) GROUP BY prod_id order by Total desc");


                    pstmt.setString(1, date1);
                    pstmt.setString(2, date2);

                ResultSet rs = pstmt.executeQuery();

                    while(rs.next())
                        {
                            name.add(rs.getString("order_acct.prod_id"));
                            name.add(rs.getString("products.prod_type"));
                            name.add(rs.getString("products.prod_title"));
                            name.add(rs.getFloat("Total"));
                        }

                        //close DB connection
                        conn.close();

                        request.setAttribute("queryResult", name);
                        request.getRequestDispatcher("accounting.jsp").forward(request,response);
                }
                else if (acct_query.contentEquals("salesProducType"))
                {
                        PreparedStatement pstmt = conn.prepareStatement("SELECT prod_type, sum( B.Total ) AS Total FROM products INNER JOIN ( SELECT prod_id, sum( quantity * sell_price ) AS Total FROM order_acct WHERE order_id IN ( SELECT order_id FROM cust_order WHERE paid_date BETWEEN ? AND ?) GROUP BY prod_id) AS B ON B.prod_id = products.prod_id GROUP BY prod_type order by Total desc");


                        pstmt.setString(1, date1);
                        pstmt.setString(2, date2);

                        ResultSet rs = pstmt.executeQuery();

                        while(rs.next())
                            {
                                name.add(rs.getString("prod_type"));
                                name.add(rs.getFloat("Total"));
                               
                            }

                    //close DB connection
                    conn.close();

                    request.setAttribute("queryResult1", name);
                    request.getRequestDispatcher("accounting.jsp").forward(request,response);
                }
                else if (acct_query.contentEquals("totalSales"))
                {
                        PreparedStatement pstmt = conn.prepareStatement("SELECT sum( quantity * sell_price ) AS Total FROM order_acct WHERE order_id IN ( SELECT order_id FROM cust_order WHERE paid_date BETWEEN ? AND ?)");


                        pstmt.setString(1, date1);
                        pstmt.setString(2, date2);

                        ResultSet rs = pstmt.executeQuery();

                        while(rs.next())
                            {
                                
                                name.add(rs.getFloat("Total"));
                               
                            }

                    //close DB connection
                    conn.close();

                    request.setAttribute("queryResult2", name);
                    request.getRequestDispatcher("accounting.jsp").forward(request,response);
                }
             
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
