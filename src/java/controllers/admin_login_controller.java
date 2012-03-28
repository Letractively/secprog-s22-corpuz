/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import security.hasher;
import security.login_checkUserifFailed;
import security.login_temp;
import security.retriever;
import classes.log_admin;

/**
 *
 * @author arvin
 */
public class admin_login_controller extends HttpServlet {

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
            throws ServletException, IOException, NoSuchAlgorithmException {
        HttpSession session = request.getSession(false); 
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /*
             * TODO output your page here. You may use following sample code.
             */
            String check_staff;
            hasher hs = new hasher();
            int forJs = 0;
            retriever ret = new retriever();
            login_temp login_user = new login_temp();
            admin_controller checker = new admin_controller();
            login_user.setUsername(request.getParameter("username"));
            login_user.setPassword(hs.setHash(request.getParameter("password")));
                       
            login_checkUserifFailed temp_check = new login_checkUserifFailed();
            ret = temp_check.checkStaff(login_user);
            if(ret.getUsername() != null)
            {
               if((ret.getPosition()).contentEquals("admin"))
               {
                   session.setAttribute("loggedIn_admin", "true");
                   session.setAttribute("user", login_user.getUsername());
                   boolean insertLog = new log_admin().addLogsAdmin(session.getAttribute("user").toString().concat(", ").concat(ret.getPosition()).concat(", ").concat("has Logged-In."));
                   request.getRequestDispatcher("administrator.jsp").forward(request,response);
               }
               else if((ret.getPosition()).contentEquals("A-AM"))
               {
                   if(!ret.isPass_changed())
                   {
                        boolean isExpired = checker.checkExpiration(ret.getTs());
                        if(isExpired)
                        {
                            request.getRequestDispatcher("exit_controller").forward(request,response);
                        }
                        else
                        {
                            session.setAttribute("passChange", "true");
                            session.setAttribute("sessionName", "accounting");
                            response.sendRedirect("changeadmin_pass.jsp");
                            //request.getRequestDispatcher("changeadmin_pass.jsp").forward(request,response);
                        }
                   }
                   else
                   {
                        session.setAttribute("loggedIn_acct", "true");
                        session.setAttribute("user", login_user.getUsername());
                        boolean insertLog = new log_admin().addLogsFinancial(session.getAttribute("user").toString().concat(", ").concat(ret.getPosition()).concat(", ").concat("has Logged-In."));
                        request.getRequestDispatcher("accounting.jsp").forward(request,response);
                   }
               }
               else
               {
                   if(!ret.isPass_changed())
                   {
                        boolean isExpired = checker.checkExpiration(ret.getTs());
                        if(isExpired)
                        {
                            request.getRequestDispatcher("exit_controller").forward(request,response);
                        }
                        else
                        {
                            session.setAttribute("passChange", "true");
                            session.setAttribute("sessionName", "product");
                            response.sendRedirect("changeadmin_pass.jsp");
                        }
                   }
                   else
                   {
                  boolean insertLog = new log_admin().addLogsProduct(session.getAttribute("user").toString().concat(", ").concat(ret.getPosition()).concat(", ").concat("has Logged-In."));
                   request.getRequestDispatcher("product_manager.jsp").forward(request,response);
                   }
               }

            }
            else
                {

               session.setAttribute("loggedIn_admin", null);
             //  session.setAttribute("user", login_user.getUsername());
    //           session.setMaxInactiveInterval(60);                     
               response.sendRedirect("staff_login.jsp");

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
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(admin_login_controller.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(admin_login_controller.class.getName()).log(Level.SEVERE, null, ex);
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
