<%-- 
    Document   : index
    Created on : 01 19, 12, 6:25:47 PM
    Author     : Loowah
--%>

<%-- Sample Push by Me --%>
<%@page import = "java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "java.util.ArrayList" %>
<%@page import=" security.*" %>
<%@ page language="java" import="security.Captchas" %>
<%@ page import="net.tanesha.recaptcha.ReCaptcha "%>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory "%>

<%

 /*
 HttpSession TestSession;
 TestSession = request.getSession(false);
*/
String testLockout=null;
int LockoutInt; 

if(session.getAttribute("Retries")!=null)
{
 testLockout = (String)session.getAttribute("Retries");
 LockoutInt = Integer.parseInt(testLockout);
 

 
 if(LockoutInt == 5 || session.getAttribute("FlagLockout")!=null)
 {
 response.sendRedirect("banned.jsp");
 }
 
}


%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="index.css">
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Foobar Bookshop</title>
    </head>
    <body>
        
        <div>
            <table class=""><tr><td>
        
            <img src="Images/logo.png">
        
            </td>
            
            <td><div class="space"><p></div></td>
            
            
            <td>
                <div class="login">
            <%
            int x = 0;
            String stringer;
            int StringerTransformer=0;
            
            if(session.getAttribute("Retries")==null)
            {
                System.out.println("null");
            }
            else
            {
             stringer = (String)session.getAttribute("Retries");      
             StringerTransformer = Integer.parseInt(stringer);
             
             System.out.println(StringerTransformer);
            }
            
            if(session.getAttribute("CaptchaError") == "true")
            {
                //out.println("Wrong captcha");
            }
            if(request.getParameter("loginButton")!=null)
            {
                String checker[] = {(String)request.getParameter("username"), (String)request.getParameter("password")};
                inputValidator iv = new inputValidator();
                
                if(!iv.isValid2(checker))
                {
                    %><script type="text/javascript">alert("Special characters not allowed");</script><%
                }
                else
                {
                    //if data validation passed, eto na gagawin niya
                    request.setAttribute("UserName",(String)request.getParameter("username"));
                    request.setAttribute("Password",(String)request.getParameter("password"));
                    session.setAttribute("Username_log", (String)request.getParameter("username"));
                    String strViewPage="login_controller";
                    RequestDispatcher dispatcher = request.getRequestDispatcher(strViewPage);
                    if (dispatcher != null)
                    {
                        dispatcher.forward(request, response);
                    }

                    %>
                    <script type="text/javascript">document.location="login_controller";</script>
                    <%
                 }
                
            }
            if(session.getAttribute("loggedIn") == "false")
            {
                %>Incorrect Password<%
                
            }
            
            
        %>
                    <br>
                    <form name="loginForm" method="post" action="" autocomplete="off">
                        
                        Username: <input type="text" name="username">*<br>
                        Password: <input type="password" name="password">*<br>
                        Not a member? <a href="registration1.jsp">Register now!</a><br>
                        <br>
                       
                        <%
                        if(StringerTransformer>=3)
                        {%>
                        Oops! Foobar Bookstore detects you can't log-in.
                        <br>
                        Please enter the Captcha characters you see below.    
                        <%    
                        ReCaptcha c = ReCaptchaFactory.newReCaptcha("6Lf2Z84SAAAAAGsCsIuifS8Md1_sso74SsmwOGkX","6Lf2Z84SAAAAABPEKVj6o9hz3alo11oyQT8uwyN7",false);
                        out.print(c.createRecaptchaHtml(null,null));        
                         }
                        %>
                        
                        <%--
                        <h3>Enter letters you see here:</h3><br>
                        <input type="text" name="passwordCaptcha" size="16">*<br>
                          <%=captchas.image() %> <br>
                          <a href="<%= captchas.audioUrl() %>">Try Audio.</a>
                          <br>
                          --%>  
                        
                          <input type="submit" class="loginButton" name="loginButton" value="Login"><br><br>
                        
                    </form>
                </div>
            </td></tr></table>
            
            <div align="center">
                <br><br><br>
                <form name="searchForm" method="post" action="product_controller">
                    
                    <select name="type">
                        <option value="all">ALL</option>
                        <option value="book">Books</option>
                        <option value="magazine">Magazines</option>
                        <option value="cd">Audio CDs</option>
                        <option value="dvd">DVDs</option>
                    </select>
                    <input type="text" size="40" value="Search..." name="searchBox" autocomplete="off"> 
                    <input type="submit" class="loginButton" name="searchButton" value="Search"> 
                </form>
                <%
                
        if((ArrayList) request.getAttribute("productsResult")!=null)
        {
                 ArrayList result = (ArrayList) request.getAttribute("productsResult");
                 %>
                 <table border ="1">
                <tr>
                
               
                <th>Product Type</th>
                <th>Product Name</th>
                <th>Synopsis</th>
                <th>Price (PHP)</th>
                </tr>
                <%
                 if (!(result.isEmpty())) {
                     int j = 0;
                     //   for (int i = 0; i < result.size(); i++) {
                        while (j<result.size()){
                         %>
                            <tr>
                                    <% result.get(j); j++; %>
                                <td> <% out.println(result.get(j)); j++; %> </td>
                                <td> <% out.println(result.get(j)); j++; %> </td>
                                <td> <% out.println(result.get(j)); j++;%> </td>
                                <td> <% out.println(result.get(j)); j++; %> </td>
                            </tr> <%
                            //  System.out.println(result2.get(i));
                        }
                    }
        }
          
                   
                %>
                 </table>
            </div><br>
                
            
        </div>
        
        
    </body>
</html>
