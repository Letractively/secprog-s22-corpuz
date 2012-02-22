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
            if(request.getParameter("loginButton")!=null)
            {
                
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                
                //data validation yung kasunod neto lyle, ilagay mo sa loob ng else pag nag pass sa input validation mo :)
                
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
                     int i = 0;
                     //   for (int i = 0; i < result.size(); i++) {
                        while (i<result.size()){
                         %>
                            <tr>
                                    <% result.get(i); i++; %>
                                <td> <% out.println(result.get(i)); i++; %> </td>
                                <td> <% out.println(result.get(i)); i++; %> </td>
                                <td> <% out.println(result.get(i)); i++;%> </td>
                                <td> <% out.println(result.get(i)); i++; %> </td>
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
