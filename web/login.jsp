<%-- 
    Document   : login
    Created on : 03 1, 12, 6:26:17 PM
    Author     : Loowah
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Foobar Bookstore | Log-In</title>
        
        <link rel="stylesheet" href="users.css" type="text/css">
    </head>
    <body>
        
        <div>
            <table class=""><tr><td>
        
            <img src="Images/uLogo.png">
        
            </td></tr>
            </table>
            
            <center>
                <div class="reLogin">
                    <form name="loginForm" method="post" action="">
                    Username: <input type="text" name="username">*<br>
                    Password: <input type="password" name="password">*<br>
                    <hr>
                    < captcha here ><br>
                    
                    <input type="submit" class="loginButton" name="loginButton" value="Login"><br><br>
                    </form>
                </div>
            </center>
        
    </body>
</html>
