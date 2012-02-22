/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dbconnection.ConnectionFactory;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

/**
 *
 * @author JM
 */
public class product_controller extends HttpServlet {

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
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        HttpSession session = request.getSession(false); 
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            String dropdowned = request.getParameter("type");
            String stringed = request.getParameter("searchBox");
            
             
          
       // String[] name = new String[100];
        
        ArrayList name = new ArrayList();
        
              try
                {
                    //opens DB Connection
                    ConnectionFactory myFactory = ConnectionFactory.getFactory();
                    Connection conn = myFactory.getConnection();

                    if(dropdowned.contentEquals("all"))
                    {
                        
                        //SQL Query
                        PreparedStatement pstmt = conn.prepareStatement("SELECT  prod_id, prod_type, prod_title, prod_syn, prod_price FROM products where prod_title like ?");
                        pstmt.setString(1, "%"+stringed+"%");
                        
                        ResultSet rs = pstmt.executeQuery();
                    
                        while(rs.next())
                            {
                                name.add(rs.getString("prod_id"));
                                name.add(rs.getString("prod_type"));
                                name.add(rs.getString("prod_title"));
                                name.add(rs.getString("prod_syn"));
                                name.add(rs.getFloat("prod_price"));
                            }

                    //close DB connection
                    conn.close();
                    }
                    
                    
                    if(!dropdowned.contentEquals("all"))
                    {
                    PreparedStatement pstmt = conn.prepareStatement("SELECT  prod_id, prod_type, prod_title, prod_syn, prod_price FROM products where prod_title like ? and prod_type = ?");
                    
                    int i = 1;
                    
                    pstmt.setString(i++, "%"+stringed+"%");
                    pstmt.setString(i++, dropdowned);
                    
                    
                    ResultSet rs = pstmt.executeQuery();
                    
                        while(rs.next())
                            {
                                name.add(rs.getString("prod_id"));
                                name.add(rs.getString("prod_type"));
                                name.add(rs.getString("prod_title"));
                                name.add(rs.getString("prod_syn"));
                                name.add(rs.getFloat("prod_price"));
                            }

                    //close DB connection
                    conn.close();
                                         
        
                     }
                }
                catch(SQLException ex)
                    {
                    ex.printStackTrace();
                    }
                  
              
             
              request.setAttribute("productsResult", name);
          //    request.getRequestDispatcher("index.jsp").forward(request,response);
              
               if(session.getAttribute("loggedIn") == null)
               {
                   request.getRequestDispatcher("index.jsp").forward(request,response);
               }
               else if(request.getParameter("searchButton1") != null)
               {
                    request.setAttribute("hasBuy", "hasBuy");
                    request.getRequestDispatcher("home.jsp").forward(request,response);
               }
               else
               {
                    request.getRequestDispatcher("home.jsp").forward(request,response);
               }
            //   else request.getRequestDispatcher("index.jsp").forward(request,response);
        
        }
         finally {            
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(product_controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(product_controller.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(product_controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(product_controller.class.getName()).log(Level.SEVERE, null, ex);
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
