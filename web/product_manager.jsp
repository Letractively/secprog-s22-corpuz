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
<script language="javascript">alert('You are not authorized to access this system.')</script>
<%
response.sendRedirect("index.jsp");
}

%>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Product Manager:</h1>
        <h2>You can add manage products here: (Select Managerial Level):</h2>
        <br>
        <form name="ProductMgt" method="post" action="product_mgt_controller">
            <select name="ProdType">
                <option value="Book">Book Manager</option>
                <option value="Magazine">Magazine Manager</option>
                <option value="CD">CD Manager</option>
                <option value="DVD">DVD Manager</option>
            </select>
            <input type="submit" value="Manage Products">    
        </form>
    </body>
</html>
