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
            if(request.getParameter("loginButton")!=null)
            {
                //anti brute force
                if(session.getAttribute("brute") == null)
                {
                    
                }
                if(session.getAttribute("brute")== "set")
                {
                    
                }
                
                //code proper
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                //data validation
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
            if(session.getAttribute("loggedIn") == "false")
            {
                %>Incorrect Password<%
                
            }
            
            
        %>
                    <br>
                    <form name="loginForm" method="post" action="">
                        
                        Username: <input type="text" name="username">*<br>
                        Password: <input type="password" name="password">*<br>
                        <input type="submit" class="loginButton" name="loginButton"><br><br>
                        Not a member? <a href="registration1.jsp">Register now!</a>
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
