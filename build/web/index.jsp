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



<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="index.css">
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Foobar Bookshop</title>
    </head>
    <body>
               <%
// Construct the captchas object (Default Values)
Captchas captchas = new security.Captchas(
  request.getSession(true),     // Ensure session
  "demo",                       // client
  "secret"                      // secret
  );
// Construct the captchas object (Extended example)
// CaptchasDotNet captchas = new captchas.CaptchasDotNet(
//  request.getSession(true),     // Ensure session
//  "demo",                       // client
//  "secret",                     // secret
//  "01",                         // alphabet
//  16,                           // letters
//  500,                          // width
//  80                            // height
//  );
%>
        
        <div>
            <table class=""><tr><td>
        
            <img src="Images/logo.png">
        
            </td>
            
            <td><div class="space"><p></div></td>
            
            
            <td>
                <div class="login">
            <%
            int x = 0;
            
            if(session.getAttribute("CaptchaError") == "true")
            {
                out.println("Wrong captcha");
            }
            if(request.getParameter("loginButton")!=null)
            {
                
                //code proper
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                //data validation
                
                if(username.matches(" <(\"[^\"]*\"|'[^']*'|[^'\">])*> "))
                {   System.out.println("malicious");   %>
                    <script language="javascript">alert('Malicious Input');</script>
                <% }
                else
                {
                    //if data validation passed, eto na gagawin niya
                    request.setAttribute("UserName",username);
                    request.setAttribute("Password",password);
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
                    <form name="loginForm" method="post" action="">
                        
                        Username: <input type="text" name="username">*<br>
                        Password: <input type="password" name="password">*<br>
                        Not a member? <a href="registration1.jsp">Register now!</a><br>
                        <br>
                        <h3>Enter letters you see here:</h3><br>
                        <input type="text" name="passwordCaptcha" size="16">*<br>
                          <%=captchas.image() %> <br>
                          <a href="<%= captchas.audioUrl() %>">Try Audio.</a>
                          <br>
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
