<%-- 
    Document   : home
    Created on : 01 23, 12, 3:27:37 AM
    Author     : arvin
--%>
<%
HttpSession HomeSessionChecker, Invalidator;
HomeSessionChecker = request.getSession(false);

if(session.getAttribute("flagLoggedIn")!=null&&HomeSessionChecker!=null)  
{    
%>

 <%
}
else
{      

%>
<script language="javascript">alert('You are not authorized to access this system. Please Register or Log-in.')</script>
<%--<script type="text/javascript">document.location="index.jsp";</script>--%>
<%
}



%>
<%-- <script language="javascript">alert('Welcome to Foobar Bookstore! This is your Session ID for Testing Purposes Only.=  <%=identifier%>')</script>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "java.util.ArrayList" %>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="users.css">
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Foobar Bookshop</title>
        
        <%
            session.setAttribute("exists", null);
            response.setHeader("Pragma","no-cache");
            response.setHeader("Cache-Control","no-store");
            response.setHeader("Expires","0");
            response.setDateHeader("Expires",-1);
        %>
        <%
            if(session.getAttribute("loggedIn")==null)
            {
                %>
                <br><br><br><br><br>
                <center><div class="redirect"><br>You have not yet logged in! You will be redirected in <span id='redirect'>5</span> seconds.</div></center>
                <script type="text/javascript">
                    var redirect=4;
                    setInterval(
                    function()
                    {
                        if(redirect<1)
                        {
                            window.location='index.jsp';
                        }
                            else
                            {
                                document.getElementById('redirect').innerHTML = redirect--;
                            }
                    }
                    , 1000); 
               
                </script>
              <%  } 
              
         else 
            {%>
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
                    <form name="loginForm" method="post" action="exit_controller">
                        
                        <%
                        out.print( "<b>Welcome " +  session.getAttribute("user") + "!<br/></b>");
                        session.setAttribute("UserName", session.getAttribute("user"));
                        
                      //  Logged in as: <%=  <br> 
 
                          if(request.getAttribute("buySuccessful")!= null)
                          { %>
                          <script type="text/javascript">alert("Transaction is Successful!");</script> <%
                          }
                        %>
                        <input type="submit" class="loginButton" name="logoutButton" value="Logout"><br><br>
                    </form>
                    
                </div>
            </td></tr>
            </table>
            
            <div align="center">
                <br><br><br>
                <form name="searchForm" method="post" action="product_controller">
                    
                    <select name="type">
                        <option value="all">ALL</option>
                        <option value="book">Books</option>
                        <option value="magazine">Magazines</option>
                        <option value="cd">Audio CDs</option>
                        <option value="dvd">DVDs</option>
                    </select>
                    <input type="text" size="40" value="" name="searchBox" autocomplete="off"> 
                    <%
                        if((String) request.getAttribute("hasBuy")!=null)
                        { %>
                                <input type="submit" class="loginButton" name="searchButton1" value="Search"> <%
                        }
                         else
                        { %>
                                <input type="submit" class="loginButton" name="searchButton" value="Search"> <%
                        }
                         
                    %>
                     
                </form>
                
                
                <form method ="post" action="purchase_controller">
              
                <%
                
                 
                
        if((ArrayList) request.getAttribute("productsResult")!=null)
        {
                 ArrayList result = (ArrayList) request.getAttribute("productsResult");
                 %>
                 <table border ="1">
                <tr>
                
                <th></th>
                <th>Product Type</th>
                <th>Product Name</th>
                <th>Synopsis</th>
                <th>Price (PHP)</th>
                </tr>
                <%
                 if (!(result.isEmpty())) {
                     int i = 0;
                     //   for (int i = 0; i < result.size(); i++) {
                        while (i<result.size()){
                         %>
                            <tr>
                                <td><input type="radio" name="choice" value="<%= result.get(i) %>"></td>
                                <td> <% i++; out.println(result.get(i)); i++; %> </td>
                                <td> <% out.println(result.get(i)); i++; %> </td>
                                <td> <% out.println(result.get(i)); i++;%> </td>
                                <td> <% out.println(result.get(i)); i++; %> </td>
                            </tr> <%
                            //  System.out.println(result2.get(i));
                        }
                    }
        }
          
                   
                %>
                 </table>
                 <br><br><br>
                  <input type="text" size="3" value = "1" name="quantity"> 
               <%   if((String) request.getAttribute("hasBuy")!=null)
                 {
                  %>      <input type="submit" name ="hasPurchase" value="Add More To Cart"> <br> <%                  
                 }
                     else
                     {                     
                            %> <input type="submit" name ="purchase" value="Add To Cart"> <br> <%
                     }
                 %>
                
                 </form>
                 
                 <br><br>
                 <form method ="post" action ="changepassword.jsp">
                     <input type="submit"   name ="Submit" value="Change Password"><br>
                 </form>
                 
                  <form method ="post" action ="purchase_controller">
                     <input type="submit"   name ="viewCart" value="View Cart"><br>
                 </form>
                 
                  <form method ="post" action ="view_purchase_product">
                     <input type="submit"  name ="viewProduct" value="View Purchase Product"><br>
                 </form>
                 
            </div><br>
        </div>
         <% } %>
    </body>
</html>
