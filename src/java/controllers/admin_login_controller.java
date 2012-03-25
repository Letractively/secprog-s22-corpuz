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
import javax.servlet.http.HttpSession;
import security.login_checkUserifFailed;
import security.login_temp;
import security.retriever;

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
            throws ServletException, IOException {
        HttpSession session = request.getSession(false); 
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /*
             * TODO output your page here. You may use following sample code.
             */
            String check_staff;
            int forJs = 0;
            retriever ret = new retriever();
            login_temp login_user = new login_temp();
            admin_controller checker = new admin_controller();
            login_user.setUsername(request.getParameter("username"));
            login_user.setPassword(request.getParameter("password"));
                       
            login_checkUserifFailed temp_check = new login_checkUserifFailed();
            ret = temp_check.checkStaff(login_user);
            if(ret.getUsername() != null)
            {
               
               session.setAttribute("loggedIn_admin", "true");
               session.setAttribute("user", login_user.getUsername());
              // session.setMaxInactiveInterval(60);
               if((ret.getPosition()).contentEquals("admin"))
                    request.getRequestDispatcher("administrator.jsp").forward(request,response);
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
      
                            request.getRequestDispatcher("accounting.jsp").forward(request,response);
                        }
                   }
                   else
                   request.getRequestDispatcher("accounting.jsp").forward(request,response);
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
                            
                            request.getRequestDispatcher("product_manager.jsp").forward(request,response);
                        }
                   }
                   else
                   request.getRequestDispatcher("product_manager.jsp").forward(request,response);
               }

            }
            else
                {

               session.setAttribute("loggedIn_admin", null);
             //  session.setAttribute("user", login_user.getUsername());
    //           session.setMaxInactiveInterval(60);                     
               request.getRequestDispatcher("staff_login.jsp").forward(request,response);

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
