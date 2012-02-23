<%-- 
    Document   : registration2
    Created on : 01 25, 12, 11:10:58 PM
    Author     : Loowah
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import=" classes.*" %>
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
                    
                    <%
                    if(request.getParameter("Home")!=null)
                    {
                        response.sendRedirect("index.jsp");
                    }
        if(request.getParameter("submit")!=null)
               {
                // info_tracker newTracker = new info_tracker();
               
                String FirstName = request.getParameter("firstName");
                String MiddleName = request.getParameter("middleName");
               String LastName = request.getParameter("lastName");
              
                 String billHomeNo = request.getParameter("billHomeNo");
                String billStreet = request.getParameter("billStreet");
                String billCity = request.getParameter("billCity");
                String billCountry = request.getParameter("billCountry");
                String billPostal = request.getParameter("billPostal");             

                String[] errors = new String[100];
                int counter = 0;
                
                
                
                 if (FirstName.length() < 1)
                {
                    errors[counter] = "You did not input a First name";
                    counter++;
                }
                
                else if (MiddleName.length() < 1)
                {
                    errors[counter] = "You did not input a Middle name";
                    counter++;
                }
                
                else if (LastName.length() < 1)
                {
                    errors[counter] = "You did not input a Last name";
                    counter++;
                }
                
               

                  else if (billHomeNo.length() < 1)
                {
                    errors[counter] = "You did not input a Home No.";
                    counter++;
                }
                
                  else if (billHomeNo.length() < 1)
                {
                    errors[counter] = "You did not input a Home No.";
                    counter++;
                }
                
                  else if (billStreet.length() < 1)
                {
                    errors[counter] = "You did not input a Street";
                    counter++;
                }
                
                  else if (billCity.length() < 1)
                {
                    errors[counter] = "You did not input a City";
                    counter++;
                }
                
                  else if (billPostal.length() < 1)
                {
                    errors[counter] = "You did not input Postal";
                    counter++;
                }
                
                 else if (billCountry.length() < 1)
                {
                    errors[counter] = "You did not input Country";
                    counter++;
                }

                if(errors[0]!=null)
                    for(int counter2 = 0; counter2 < counter; counter2++)
                    {
                        out.println(errors[counter2]+"<br/>");
                    }
                 else
                {
                                     
                        %><script type="text/javascript">alert("Registration Successful");document.location="index.jsp";</script><%
                                           
                }

            }
        %>
        
                    <form name="regForm2" method ="post" action="">
                       
                        Name on Card: <input type="text" name="firstName" size="7"> <input type="text" name="middleName" size="7"> <input type="text" name="lastName" size="7"><br>
                        Credit Card #: <input type="text" name="ccNo"><br>
                        Type: <select name="ccType">
                            <option value="visa">Visa</option>
                            <option value="mastercard">MasterCard</option>
                            <option value="americanex">American Express</option>
                            <option value="jcb">JCB</option>
                        </select><br>
                        Expiry Date: <input type="">
                                            <br><br><hr>
                        
                    <h2>Billing Information</h2><br>    
                        Home #: <input type="text" name="billHomeNo" size="7"><br>
                        Street: <input type="text" name="billStreet"><br>
                        City: <input type="text" name="billCity"><br>
                        Country: <input type="text" name="billCountry"><br>
                        Postal Code: <input type="text" name="billPostal" size="5"><br><br>
                        <input type="submit" class="registerButton" name ="Home" value="Home">
                        <input type="submit" class="registerButton" name ="submit" value="Save »"><br>
                    </form>
                
                </div>
            </center>
    </body>
</html>