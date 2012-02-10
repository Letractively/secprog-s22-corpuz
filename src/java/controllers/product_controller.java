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
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/foobar";
            Connection conn = DriverManager.getConnection(url,"root","password");
            Statement select = conn.createStatement();
            
            String dropdowned = request.getParameter("type");
            String stringed = request.getParameter("searchBox");
            
            out.println(dropdowned);
            
            if(dropdowned.matches("book"))
            {
            //  ResultSet result = select.executeQuery("SELECT * from products");
             ResultSet result = select.executeQuery("SELECT prod_title, prod_syn, prod_price from PRODUCTS where prod_type = 'BOOK' AND prod_title LIKE "+"'%"+""+stringed+""+"%'");
             request.setAttribute("Result",result);
             request.getRequestDispatcher("index.jsp").forward(request,response);
            }
            if(dropdowned.matches("magazine"))
            {
             ResultSet result = select.executeQuery("SELECT prod_title, prod_syn, prod_price from PRODUCTS where prod_type = 'MAGAZINE' AND prod_title LIKE "+"'%"+""+stringed+""+"%'");
             request.setAttribute("Result",result);
             request.getRequestDispatcher("index.jsp").forward(request,response);
            }
            if(dropdowned.matches("cd"))
            {
            ResultSet result = select.executeQuery("SELECT prod_title, prod_syn, prod_price from PRODUCTS where prod_type = 'AUDIO CD' AND prod_title LIKE "+"'%"+""+stringed+""+"%'");
             request.setAttribute("Result",result);
             request.getRequestDispatcher("index.jsp").forward(request,response);
            }
            if(dropdowned.matches("dvd"))
            {
             ResultSet result = select.executeQuery("SELECT prod_title, prod_syn, prod_proce from PRODUCTS where prod_type = 'DVD' AND prod_title LIKE "+"'%"+""+stringed+""+"%'");
             request.setAttribute("Result",result);
             request.getRequestDispatcher("index.jsp").forward(request,response);
            }
            
            
            
            /*
             * TODO output your page here. You may use following sample code.
             
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet product_controller</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet product_controller at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");*/
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
