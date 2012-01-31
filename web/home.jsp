<%-- 
    Document   : home
    Created on : 01 23, 12, 3:27:37 AM
    Author     : arvin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="users.css">
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Foobar Bookshop</title>
    </head>
    <body>
        
        <div>
            <table class=""><tr><td>
        
            <img src="Images/uLogo.png">
            
            </td>
            <td><div class="space"><p></div></td>
            
            <td>
                <div class="login">
                    <br>
                    <form name="loginForm" method="post" action="login_controller">
                        
                        Logged in as: ~username~<br>
                        <input type="submit" class="loginButton" name="logoutButton" value="Logout"><br><br>
                    </form>
                    
                </div>
            </td></tr>
            </table>
            
            <div align="center">
                <br><br><br>
                <form name="searchForm" method="post" action="">
                    
                    <select name="type">
                        <option value="book">Books</option>
                        <option value="magazine">Magazines</option>
                        <option value="cd">Audio CDs</option>
                        <option value="dvd">DVDs</option>
                    </select>
                    <input type="text" size="40" value="Search..." name="searchBox" autocomplete="off"><input type="submit" class="registerButton" name="searchButton" value="Search">
                </form>
            </div><br>
        </div>
        
    </body>
</html>
