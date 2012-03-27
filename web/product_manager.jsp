<%-- 
    Document   : product_manager
    Created on : 03 25, 12, 2:48:08 PM
    Author     : unseen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Product Manager:</h1>
        <h2>You can add Products here: (Select Your Managerial Level):</h2>
        <br>
        <form name="ProductMgt" method="post" action="product_mgt_controller">
            <select name="ProdType">
                <option value="Book">Book Manager</option>
                <option value="Magazine">Magazine Manager</option>
                <option value="CD">Audio CD Manager</option>
                <option value="DVD">DVD Manager</option>
            </select>
            <input type="submit" value="Manage Contents">
        </form>
    </body>
</html>
