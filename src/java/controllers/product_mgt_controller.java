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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JM
 */
public class product_mgt_controller extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
      
        ArrayList ResultCatcher = new ArrayList();
          
        try {
            
            ConnectionFactory myFactory = ConnectionFactory.getFactory();
            Connection conn = myFactory.getConnection();


            
            String ManageType = request.getParameter("ProdType");
            out.println(ManageType);
            
   
              PreparedStatement CentralCatcher = conn.prepareStatement("SELECT * FROM PRODUCTS WHERE PROD_TYPE = ?");
              CentralCatcher.setString(1,""+ManageType+"");
              
              ResultSet GenerateRS = CentralCatcher.executeQuery();
             
              int i=0;
              
              while(GenerateRS.next())
              {
                  ResultCatcher.add(GenerateRS.getString("prod_id"));
                  ResultCatcher.add(GenerateRS.getString("prod_type"));
                  ResultCatcher.add(GenerateRS.getString("prod_title"));
                  ResultCatcher.add(GenerateRS.getString("prod_syn"));
                  ResultCatcher.add(GenerateRS.getString("prod_price"));
                //  i++;
              }
              //System.out.println("Rows:"+i);
              
            /*
             * TODO output your page here. You may use following sample code.
             
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet product_mgt_controller</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet product_mgt_controller at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");*/
//              conn.close();
            request.setAttribute("ArrayPacks",ResultCatcher);
            request.getRequestDispatcher("CentralProdMgr.jsp").forward(request,response);
                
        } 
        catch(SQLException ex)
        {            
         System.out.println(ex.getMessage());   
        }
        finally
        {
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
        } catch (SQLException ex) {
            Logger.getLogger(product_mgt_controller.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException ex) {
            Logger.getLogger(product_mgt_controller.class.getName()).log(Level.SEVERE, null, ex);
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
