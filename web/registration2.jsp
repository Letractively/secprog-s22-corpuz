<%-- 
    Document   : registration2
    Created on : 01 25, 12, 11:10:58 PM
    Author     : Loowah
--%>


<%@page import="security.inputValidator"%>
<%@page import="java.sql.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import=" classes.*" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="users.css">
        <script language="javaScript" type="text/javascript" src="calendar.js"></script>
        <link href="calendar.css" rel="stylesheet" type="text/css">
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
                    
                    System.out.print(request.getAttribute("customer"));
                    if(request.getParameter("Home")!=null)
                    {
                        response.sendRedirect("index.jsp");
                    }
                if(request.getParameter("submit")!=null)
               {
                info_tracker newTracker = new info_tracker();
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
  
                newTracker.setCustomerID((String) session.getAttribute("customerID"));
                newTracker.setCard_name(request.getParameter("firstName") + " " + request.getParameter("middleName") + " " + request.getParameter("lastName"));
                newTracker.setCard_num(request.getParameter("ccNo"));
                newTracker.setCard_type(request.getParameter("ccType"));
                java.util.Date date = formatter.parse(request.getParameter("datum1"));
               
                java.sql.Date sqlDate = new Date(date.getTime());
                newTracker.setExp_date(sqlDate);
                
                
              
                String billHomeNo = request.getParameter("billHomeNo");
                String billStreet = request.getParameter("billStreet");
                String billCity = request.getParameter("billCity");
                String billCountry = request.getParameter("billCountry");
                String billPostal = request.getParameter("billPostal");
                newTracker.setShipping(billHomeNo + " " + billStreet + " " + billCity + " " + billCountry + " " + billPostal);                             
                
                String checker[] = {(String) request.getParameter("firstName"), (String)request.getParameter("middleName"), (String)request.getParameter("lastName"), (String) request.getParameter("ccNo"),(String) request.getParameter("ccType"), (String) request.getParameter("datum1"), (String) request.getParameter("billHomeNo"), (String) request.getParameter("billStreet"), (String) request.getParameter("billCity"), (String) request.getParameter("billCountry"), (String) request.getParameter("billPostal")};
                inputValidator iv = new inputValidator();
                
                String[] errors = new String[100];
                int counter = 0;
                
                
                if((request.getParameter("ccType").toString().contentEquals("visa")))
                {
                    if(!(request.getParameter("ccNo").toString().matches("^4[0-9]{12}(?:[0-9]{3})?$")))
                     {
                   errors[counter] = "Not a valid card number for Visa Card";
                    counter++;
                     }
                }
                else if((request.getParameter("ccType").toString().contentEquals("mastercard")))
                {
                    if(!(request.getParameter("ccNo").toString().matches("^5[1-5][0-9]{14}$")))
                     {
                   errors[counter] = "Not a valid card number for Master Card";
                    counter++;
                     }
                }
                
                
                else if(!iv.isValid11(checker))
                {
                   errors[counter] = "Special characters not allowed!";
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
                        
                        request.setAttribute("newTracker", newTracker);
                        String strViewPage="addcustomer_controller2";
                        
                        RequestDispatcher dispatcher = request.getRequestDispatcher(strViewPage);
                        
                        if (dispatcher != null)
                        {
                            dispatcher.forward(request, response);
                            
                        }
                        %><script type="text/javascript">document.location="addcustomer_controller2";</script><%
                                           
                }

            }
        %>
        
                    <form name="regForm2" method ="post" action="">
                       
                        Name on Card: <input type="text" name="firstName" size="7"> <input type="text" name="middleName" size="7"> <input type="text" name="lastName" size="7"><br>
                        Credit Card #: <input type="text" name="ccNo"><br>
                        Type: <select name="ccType">
                            <option value="visa">Visa</option>
                            <option value="mastercard">MasterCard</option>
                            
                        </select><br>
                        Expiry Date: <input type="text" name="datum1"><a href="#" onClick="setYears(2012, 2060);
                                        showCalender(this, 'datum1');">
                                        <img src="Images/calender.png"></a>
                                            <br><br><hr>
                        <table id="calenderTable">
                         <tbody id="calenderTableHead">
                            <tr>
            <td colspan="4" align="center">
	          <select onChange="showCalenderBody(createCalender(document.getElementById('selectYear').value,
	           this.selectedIndex, false));" id="selectMonth">
	              <option value="0">Jan</option>
	              <option value="1">Feb</option>
	              <option value="2">Mar</option>
	              <option value="3">Apr</option>
	              <option value="4">May</option>
	              <option value="5">Jun</option>
	              <option value="6">Jul</option>
	              <option value="7">Aug</option>
	              <option value="8">Sep</option>
	              <option value="9">Oct</option>
	              <option value="10">Nov</option>
	              <option value="11">Dec</option>
	          </select>
            </td>
            <td colspan="2" align="center">
			    <select onChange="showCalenderBody(createCalender(this.value, 
				document.getElementById('selectMonth').selectedIndex, false));" id="selectYear">
				</select>
			</td>
            <td align="center">
			    <a href="#" onClick="closeCalender();"><font color="#003333" size="+1">X</font></a>
			</td>
		  </tr>
       </tbody>
       <tbody id="calenderTableDays">
         <tr style="">
           <td>Sun</td><td>Mon</td><td>Tue</td><td>Wed</td><td>Thu</td><td>Fri</td><td>Sat</td>
         </tr>
       </tbody>
       <tbody id="calender"></tbody>
    </table>
                        
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