/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import classes.*;
import java.util.ArrayList;

/**
 *
 * @author Lyle
 */
@WebServlet(name = "admin_accountList_controller", urlPatterns = {"/admin_accountList_controller"})
public class admin_accountList_controller extends HttpServlet {

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
            out.println("<title>Servlet admin_accountList_controller</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet admin_accountList_controller at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            */
            
            String position = request.getParameter("position");
            String positionName = "N/A";
            
            ArrayList result = new admin_controller().getAccount( position );
            
             request.setAttribute("resultSet", result);
             
         
             if(position.contentEquals("A-AM"))
                 positionName = "Accounting Manager";
             if(position.contentEquals("B-ACM"))
                 positionName = "Audio CD Manager";
             if(position.contentEquals("B-BM"))
                 positionName = "Book Manager";
             if(position.contentEquals("B-DM"))
                 positionName = "DVD Manager";
             if(position.contentEquals("B-MM"))
                 positionName = "Magazine Manager";
             if(position.contentEquals("Customer"))
                 positionName = "Customer";
             
             request.setAttribute("positionName", positionName);
             
             request.getRequestDispatcher("administrator.jsp").forward(request,response);
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
