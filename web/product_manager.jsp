<%-- 
    Document   : product_manager
    Created on : 03 25, 12, 2:48:08 PM
    Author     : unseen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
HttpSession ProdMgrSession;
ProdMgrSession = request.getSession(false);


ProdMgrSession.setAttribute("SignalNew",true);
if(ProdMgrSession!=null)
{
%>
<script language="javascript">alert('Welcome to Product Management System!');</script>    
<%
}
else
{
%>
<script language="javascript">alert('You are not authorized to access this system.');</script>
<%
ProdMgrSession.invalidate();
response.sendRedirect("index.jsp");
}

%>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Foobar Bookshop | Product Management</title>
        
        <link rel="stylesheet" href="users.css">
    </head>
    <body>
        <table class=""><tr><td>
        
        <img src="Images/uLogo.png">
        
        </td>
        <td><div class="space"></div></td>
        <form name="ProductMgt" method="post" action="product_mgt_controller">
        <td><div class="login">
                <br><br>
            <input type="submit" name="ExitSystem" value="Logout" class="loginButton">
        </div></td></tr>
        <tr><td></td>
        <td><div align="center">
            <h2><p align="center">Product:</p></h2>
        
            <select name="ProdType">
                <option value="Book">Books</option>
                <option value="Magazine">Magazines</option>
                <option value="CD">CDs</option>
                <option value="DVD">DVDs</option>
            </select>
            <input type="submit" value="Manage" class="loginButton">
            <br>
            <br>
            </div></td></tr>
        </form>
    </body>
</html>
