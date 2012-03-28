<%-- 
    Document   : tryhashing
    Created on : 03 22, 12, 1:23:51 AM
    Author     : unseen
--%>

<%@page import="security.hasher"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    
    <body>
        <h1>Hello World!</h1>
        <form method ="post" action ="">
                    Password: <input type="password" name="password"><br>
        <input type="submit" name ="password" value="Check Hash">
        </form>
        <%
        hasher hs = new hasher();
        if(request.getParameter("password")!=null)
        {
            out.print(hs.setHash(request.getParameter("password")));
        }
        
        %>
    </body>
</html>
