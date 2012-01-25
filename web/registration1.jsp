<%-- 
    Document   : registration1
    Created on : 01 25, 12, 10:32:59 PM
    Author     : Loowah
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="users.css">
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Foobar Bookshop | Registration</title>
    </head>
    <body>
        <div>
            <table class=""><tr><td>
        
            <img src="Images/uLogo.png">
        
            </td></tr>
            </table>
            
            <center>
                <div class="registration">
                
                    <h2>Log-in Information:</h2><br>
                    <form name="regForm1" method ="post" action="registration2.jsp">
                        
                        Username: <input type="text" name="username"><br>
                        Password: <input type="password" name="password"><br>
                        Retype Password: <input type="password" name="repass"><br>
                        Name: <input type="text" name="firstName" size="7"> <input type="text" name="middleName" size="7"> <input type="text" name="lastName" size="7"><br>
                        E-Mail Address: <input type="text" name="email"><br><br><hr>
                        
                    <h2>Shipping Information</h2><br>    
                        Home #: <input type="text" name="shipHomeNo" size="7"><br>
                        Street: <input type="text" name="shipStreet"><br>
                        City: <input type="text" name="shipCity"><br>
                        Country: <input type="text" name="shipCountry"><br>
                        Postal Code: <input type="text" name="shipPostal" size="5"><br><br>
                        <input type="submit" class="registerButton" value="Next »"><br>
                    </form>
                
                </div>
            </center>
    </body>
</html>
