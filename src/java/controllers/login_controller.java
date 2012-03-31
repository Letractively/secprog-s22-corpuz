/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import classes.log_admin;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import security.*;
import security.Captchas;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

/**
 *
 * @author arvin
 */
public class login_controller extends HttpServlet {

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
        
        try 
        {
            boolean check_username, check_password;
            login_temp login_user = new login_temp();
            login_user.setUsername((String) request.getAttribute("UserName"));
            login_user.setPassword((String) request.getAttribute("Password"));
            login_checkUserifFailed temp_check = new login_checkUserifFailed();
            check_username = temp_check.checkUsername(login_user);
            check_password = temp_check.checkPassword(login_user);
            
            int flagger = 0, correctCaptcha = 0;
            int RawFiller=0, RetriesCatcher=0, test=0;
            String ValueTransformer = null;
            
            Integer accessCount = (Integer)session.getAttribute("accessCount");
            
              if(accessCount==null)
               {
                   accessCount = new Integer(1);     
               }
               else
               {
                   accessCount = new Integer(accessCount.intValue() + 1);
               }
            
           if(accessCount>=3)
           {
                
            /*    
            //
            
            // Construct the captchas object
            // Use same settings as in query.jsp
            Captchas captchas = new security.Captchas(
            request.getSession(true),     // Ensure session
            "demo",                       // client
            "secret"                      // secret
            );
        // Read the form values
        
        String password = request.getParameter("passwordCaptcha");

       // Check captcha
        String body = null;
       switch (captchas.check(password)) {
       case 's':
           {
          correctCaptcha = 1;
       body = "Session seems to be timed out or broken. ";
       body += "Please try again or report error to administrator.";
            
            }
       case 'm':
       {
        correctCaptcha = 2;
       body = "Every CAPTCHA can only be used once. ";
       body += "The current CAPTCHA has already been used. ";
       body += "Please use back button and reload";
    
      }
      case 'w':
      {
       correctCaptcha = 3;
      body = "You entered the wrong password. ";
      body += "Please use back button and try again. ";
     
      }
     default:
     {
   //  correctCaptcha = 8;
    //body = "Your message was verified to be entered by a human and is \"" + message + "\"";
     }
     out.println(body + correctCaptcha);
     * 
}*/
            try
            {
            String remoteAddr = request.getRemoteAddr();
            ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
            reCaptcha.setPrivateKey("6Lf2Z84SAAAAABPEKVj6o9hz3alo11oyQT8uwyN7");
            
            String challenge = request.getParameter("recaptcha_challenge_field");
            String uresponse = request.getParameter("recaptcha_response_field");
            ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, uresponse);
            
            if(reCaptchaResponse.isValid())
            {
                System.out.print("Answer was entered correctly");
        
            }
            else
            {
                System.out.print("Answer is wrong");
                correctCaptcha=1;
            }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }

         }
           
            if((check_username == true && check_password == true))
            {
                         
               flagger = 1;
                
               session.setAttribute("loggedIn", "true");
               session.setAttribute("user", login_user.getUsername());
               
                ValueTransformer = Integer.toString(accessCount);   
                session.setAttribute("accessCount",accessCount);
                session.setAttribute("Retries",ValueTransformer);
               
               if(accessCount<3)
               {
               session.setMaxInactiveInterval(60);
               session.setAttribute("flagLoggedIn",flagger);
               boolean result = new log_admin().addLogsCustomer("Username: " + session.getAttribute("user") + " has Logged In.");
               request.getRequestDispatcher("home.jsp").forward(request,response);
               System.out.print("REDIRECTING");
               }
               else
               {
                   
               if(correctCaptcha == 0)
               {               
             
                System.out.print("Visits: "+ accessCount);
              
              
                
                session.setMaxInactiveInterval(60);
                session.setAttribute("flagLoggedIn",flagger);
                boolean result = new log_admin().addLogsCustomer("Username: " + session.getAttribute("user") + " has Logged In.");
                request.getRequestDispatcher("home.jsp").forward(request,response);
           
               }
               else
               {
                session.setAttribute("loggedIn", null);
                session.setAttribute("brute", "set");  
                session.setAttribute("CaptchaError","true");
                response.sendRedirect("index.jsp");
                
                
               }
               
               }
                   
            }
            else if(check_username == true && check_password == false)
            {
               
               
                ValueTransformer = Integer.toString(accessCount);   
                session.setMaxInactiveInterval(2);
                session.setAttribute("accessCount",accessCount);
                session.setAttribute("Retries",ValueTransformer);
                session.setAttribute("loggedIn", "false");
                session.setAttribute("brute", "set");  
                //session.setAttribute("flagLoggedIn",flagger);
               // session.getLastAccessedTime(); 
                
                 
               if(correctCaptcha == 0)
                 session.setAttribute("CaptchaError","true");
              
            
              response.sendRedirect("index.jsp");  
            }
            else
            {  
                System.out.print("Visits: "+ accessCount);
              
                ValueTransformer = Integer.toString(accessCount);  
                session.setMaxInactiveInterval(2);
                session.setAttribute("accessCount",accessCount);
                session.setAttribute("Retries",ValueTransformer);
                session.setAttribute("loggedIn", null);
                session.setAttribute("brute", "set");  
                session.setAttribute("CaptchaError","true");
                response.sendRedirect("index.jsp");
            }    
        }
        catch(Exception ex)
                {
                  //  ex.printStackTrace();
                   
                }
        finally
        {            
            out.close();
        }
        
    }
   public class setCookie extends HttpServlet
   {
        @Override
    public void doPost( HttpServletRequest request,
                      HttpServletResponse response)
                      throws IOException, ServletException {
    
    response.setContentType("text/html");

    login_temp login_user = new login_temp();
    login_user.setUsername((String) request.getAttribute("UserName"));    
    login_checkUserifFailed cookieAtt = new login_checkUserifFailed();
    
    String catcher = cookieAtt.fetchCookieAttributor(login_user);
    // Get the username from the submitted form page
    // Create a new cookie
    Cookie cookie = new Cookie("UserLogging", catcher);
    
    // Keep it alive on the client for 30 minutes
    cookie.setMaxAge(120);

    // Add the cookie as a "Set-Cookie" response header
    response.addCookie(cookie);

    RequestDispatcher view = request.getRequestDispatcher("displayCookie.jsp");
    view.forward(request, response);
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
