<%-- 
    Document   : registration1
    Created on : 01 25, 12, 10:32:59 PM
    Author     : Loowah
--%>

<%@page import="security.inputValidator"%>
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
                
                    <h2>Log-in Information:</h2><br>
                    
       <%
       if(request.getParameter("Home")!=null)
                    {
                        response.sendRedirect("index.jsp");
                    }
        if(request.getParameter("Submit")!=null)
               {
                customer newCustomer = new customer();

                newCustomer.setCust_id(request.getParameter("username"));
                newCustomer.setPassword(request.getParameter("password"));
                String password2 = request.getParameter("repass");
                newCustomer.setFname(request.getParameter("firstName"));
                newCustomer.setMname(request.getParameter("middleName"));
                newCustomer.setLname(request.getParameter("lastName"));
                newCustomer.setEmail(request.getParameter("email"));
                String shipHomeNo = request.getParameter("shipHomeNo");
                String shipStreet = request.getParameter("shipStreet");
                String shipCity = request.getParameter("shipCity");
                String shipCountry = request.getParameter("shipCountry");
                String shipPostal = request.getParameter("shipPostal");
                newCustomer.setBilling(shipHomeNo + " " + shipStreet + " " + shipCity+ " " + shipCountry + " " + shipPostal);
                
                String checker[] = {(String)request.getParameter("username"), (String) request.getParameter("firstName"), (String)request.getParameter("middleName"), (String)request.getParameter("lastName"), (String) request.getParameter("email"), (String)  request.getParameter("shipHomeNo"), (String)  request.getParameter("shipStreet"), (String) request.getParameter("shipCity"), (String)  request.getParameter("shipCountry"),(String) request.getParameter("shipPostal")};
                inputValidator iv = new inputValidator();
                

                String[] errors = new String[100];
                int counter = 0;
                
                if(!iv.isValid10(checker))
                {
                   errors[counter] = "Special characters not allowed!";
                   counter++;
                }
                else if(iv.searchUser(newCustomer.getCust_id()))   
                {
                    errors[counter] = "Username is already taken!";
                    counter++;                                                 
                }
                else if( newCustomer.getCust_id().length()<8)
                {
                    errors[counter] = "Your username is too short!";
                    counter++;
                }
                else if(newCustomer.getCust_id().length()>25)
                {
                    errors[counter] = "Your username is too long!";
                    counter++;
                }
              
                else if( newCustomer.getPassword().length()<6)
                {
                    errors[counter] = "Your password is too short!";
                    counter++;
                }
                else if(!newCustomer.getPassword().contentEquals(password2))
                {
                    errors[counter] = "Your two inputted passwords do not match!";
                    counter++;
                }
                
                else if (newCustomer.getFname().length() < 1)
                {
                    errors[counter] = "You did not input a First name";
                    counter++;
                }
                
                else if (newCustomer.getMname().length() < 1)
                {
                    errors[counter] = "You did not input a Middle name";
                    counter++;
                }
                
                else if (newCustomer.getLname().length() < 1)
                {
                    errors[counter] = "You did not input a Last name";
                    counter++;
                }
                
                if(!newCustomer.getEmail().matches(".*@.*..*"))
                {
                    errors[counter] = "You have not entered a valid email address!";
                    counter++;
                }
                
                 else if (newCustomer.getLname().length() < 1)
                {
                    errors[counter] = "You did not input a Last name";
                    counter++;
                }
                
                 else if (newCustomer.getLname().length() < 1)
                {
                    errors[counter] = "You did not input a Last name";
                    counter++;
                }
                
                 else if (shipHomeNo.length() < 1)
                {
                    errors[counter] = "You did not input a Home No.";
                    counter++;
                }
                
                  else if (shipHomeNo.length() < 1)
                {
                    errors[counter] = "You did not input a Home No.";
                    counter++;
                }
                
                  else if (shipStreet.length() < 1)
                {
                    errors[counter] = "You did not input a Street";
                    counter++;
                }
                
                  else if (shipCity.length() < 1)
                {
                    errors[counter] = "You did not input a City";
                    counter++;
                }
                
                  else if (shipPostal.length() < 1)
                {
                    errors[counter] = "You did not input Postal";
                    counter++;
                }

                if(errors[0]!=null)
                    for(int counter2 = 0; counter2 < counter; counter2++)
                    {
                        out.println(errors[counter2]+"<br/>");
                    }
                 else
                {
                        session.setAttribute("customerID", newCustomer.getCust_id());
                         session.setAttribute("reg_ok", true);
                        request.setAttribute("customer",newCustomer);
                        String strViewPage="addcustomer_controller";
                        
                        RequestDispatcher dispatcher = request.getRequestDispatcher(strViewPage);
                        
                        if (dispatcher != null)
                        {
                            dispatcher.forward(request, response);
                            
                        }
                       
                        %><script type="text/javascript">document.location="addcustomer_controller";</script><%
                                           
                }
                

            }
        %>
        
                    <form name="regForm1" method ="post" action="" autocomplete="off">
                        
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
                        <input type="submit" class="registerButton" name ="Home" value="<<Back">
                        <input type="submit"  class="registerButton" name ="Submit" value="Save>>"><br>
                    </form>
                
                </div>
            </center>
        </div>
    </body>
</html>
