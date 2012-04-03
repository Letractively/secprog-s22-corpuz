/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import classes.log_admin;
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
public class acct_query2 extends HttpServlet {

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
            ArrayList name1 = new ArrayList();
          
            System.out.println("OUT"+ acct_query);
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
                    
                     PreparedStatement pstmt1 = conn.prepareStatement("select a.paid_date, a.order_id, c.prod_type, b.prod_id, c.prod_title, b.quantity, b.sell_price from cust_order  as a inner join order_acct as b on a.order_id = b.order_id inner join products as c on b.prod_id = c.prod_id where a.paid_date between ? and ?  order by c.prod_type");


                    pstmt1.setString(1, date1);
                    pstmt1.setString(2, date2);

                ResultSet rs1 = pstmt1.executeQuery();

                    while(rs1.next())
                        {
                            name1.add(rs1.getString("a.paid_date"));
                            name1.add(rs1.getString("a.order_id"));
                            name1.add(rs1.getString("c.prod_type"));
                            name1.add(rs1.getString("b.prod_id"));
                            name1.add(rs1.getString("c.prod_title"));
                            name1.add(rs1.getInt("b.quantity"));
                            name1.add(rs1.getFloat("b.sell_price"));
                        }

                        //close DB connection
                        conn.close();

                        request.setAttribute("queryResult", name);
                        request.setAttribute("queryResultA", name1);
                        boolean insertLog = new log_admin().addLogsFinancial(session.getAttribute("user").toString().concat(" viewed Sales-per-Product report from the day of ").concat(date1).concat(" to ").concat(date2).concat("."));
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
                        
                         PreparedStatement pstmt1 = conn.prepareStatement("select a.paid_date, a.order_id, c.prod_type, b.prod_id, c.prod_title, b.quantity, b.sell_price from cust_order  as a inner join order_acct as b on a.order_id = b.order_id inner join products as c on b.prod_id = c.prod_id where a.paid_date between ? and ?  order by c.prod_type");


                    pstmt1.setString(1, date1);
                    pstmt1.setString(2, date2);

                ResultSet rs1 = pstmt1.executeQuery();

                    while(rs1.next())
                        {
                            name1.add(rs1.getString("a.paid_date"));
                            name1.add(rs1.getString("a.order_id"));
                            name1.add(rs1.getString("c.prod_type"));
                            name1.add(rs1.getString("b.prod_id"));
                            name1.add(rs1.getString("c.prod_title"));
                            name1.add(rs1.getInt("b.quantity"));
                            name1.add(rs1.getFloat("b.sell_price"));
                        }

                    //close DB connection
                    conn.close();

                    request.setAttribute("queryResult1", name);
                    request.setAttribute("queryResultA", name1);
                     boolean insertLog = new log_admin().addLogsFinancial(session.getAttribute("user").toString().concat(" viewed Sales-per-Product Type report from the day of ").concat(date1).concat(" to ").concat(date2).concat("."));
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
                     
                        PreparedStatement pstmt1 = conn.prepareStatement("select a.paid_date, a.order_id, c.prod_type, b.prod_id, c.prod_title, b.quantity, b.sell_price from cust_order  as a inner join order_acct as b on a.order_id = b.order_id inner join products as c on b.prod_id = c.prod_id where a.paid_date between ? and ?  order by a.paid_date");


                    pstmt1.setString(1, date1);
                    pstmt1.setString(2, date2);

                ResultSet rs1 = pstmt1.executeQuery();

                    while(rs1.next())
                        {
                            name1.add(rs1.getString("a.paid_date"));
                            name1.add(rs1.getString("a.order_id"));
                            name1.add(rs1.getString("c.prod_type"));
                            name1.add(rs1.getString("b.prod_id"));
                            name1.add(rs1.getString("c.prod_title"));
                            name1.add(rs1.getInt("b.quantity"));
                            name1.add(rs1.getFloat("b.sell_price"));
                        }
                    
                    //close DB connection
                    conn.close();

                    request.setAttribute("queryResult2", name);
                    request.setAttribute("queryResultA", name1);
                     boolean insertLog = new log_admin().addLogsFinancial(session.getAttribute("user").toString().concat(" viewed Total Sales report from the day of ").concat(date1).concat(" to ").concat(date2).concat("."));
                    request.getRequestDispatcher("accounting.jsp").forward(request,response);
                }
                else if(!(acct_query.isEmpty()))
                {
                    //SQL Query
                
                    PreparedStatement pstmt = conn.prepareStatement("select a.paid_date, a.order_id, c.prod_type, b.prod_id, c.prod_title, b.quantity, b.sell_price, (b.quantity * b.sell_price) as E from cust_order  as a inner join order_acct as b on a.order_id = b.order_id inner join products as c on b.prod_id = c.prod_id where cust_id = ? and a.paid_date IS NOT NULL");


                    pstmt.setString(1, acct_query);
                  

                ResultSet rs = pstmt.executeQuery();

                    while(rs.next())
                        {
                             name.add(rs.getString("a.paid_date"));
                            name.add(rs.getString("a.order_id"));
                            name.add(rs.getString("c.prod_type"));
                            name.add(rs.getString("b.prod_id"));
                            name.add(rs.getString("c.prod_title"));
                            name.add(rs.getInt("b.quantity"));
                            name.add(rs.getFloat("b.sell_price"));
                            name.add(rs.getFloat("E"));
                        }
                    
                      conn.close();

                        request.setAttribute("queryResultB", name);
                         boolean insertLog = new log_admin().addLogsFinancial(session.getAttribute("user").toString().concat(" viewed Transactions List report of Username ").concat(acct_query).concat("."));
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
