<%-- 
    Document   : registration2
    Created on : 01 25, 12, 11:10:58 PM
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
                
                    <h2>Credit Card Information:</h2><br>
                    <form name="loginForm" method ="post" action="index.jsp">
                       
                        Name on Card: <input type="text" name="firstName" size="7"> <input type="text" name="middleName" size="7"> <input type="text" name="lastName" size="7"><br>
                        Credit Card #: <input type="text" name="email"><br>
                        Type: <input type="text" name="email"><br>
                        Expiry Date: <input type="text" name="email"><br><br><hr>
                        
                    <h2>Billing Information</h2><br>    
                        Home #: <input type="text" name="billHomeNo" size="7"><br>
                        Street: <input type="text" name="billStreet"><br>
                        City: <input type="text" name="billCity"><br>
                        Country: <input type="text" name="billCountry"><br>
                        Postal Code: <input type="text" name="billPostal" size="5"><br><br>
                        <input type="submit" class="registerButton" value="Next »"><br>
                    </form>
                
                </div>
            </center>
    </body>
</html>