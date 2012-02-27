<%-- 
    Document   : staff_login
    Created on : Feb 23, 2012, 5:40:58 PM
    Author     : arvin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import=" security.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        
        
        <h1>Hello World!</h1>
        <form name="loginForm" method="post" action="admin_login_controller">
                        
                        Username: <input type="text" name="username">*<br>
                        Password: <input type="password" name="password">*<br>
                        <input type="submit" class="loginButton" name="loginButton"><br><br>
                        
         </form>
    </body>
</html>
