<%-- 
    Document   : accounting
    Created on : 03 25, 12, 2:44:59 PM
    Author     : unseen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Accounting</title>
         <%
            response.setHeader("Pragma","no-cache");
            response.setHeader("Cache-Control","no-store");
            response.setHeader("Expires","0");
            response.setDateHeader("Expires",-1);
        %>
        
        <form name="loginForm" method="post" action="exit_controller">
                        
                        <%
                        out.print( "<b>Welcome " +  session.getAttribute("user") + "!<br/></b>");
                  //      session.setAttribute("UserName", session.getAttribute("user"));
                        
                      //  Logged in as: <%=  <br> 
 
                          
                        %>
                        <input type="submit" class="loginButton" name="logoutButton" value="Logout"><br><br>
         </form>
    
        <%
            if(session.getAttribute("loggedIn_acct")==null)
            {
                %>
                <center>You have not yet logged in! You will be redirected in <span id='redirect'>5</span> seconds.</center>
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
                    }, 1000); 
               
                </script>
              <%  }  else { %>
    </head>
    <body>
        <h1>ACCOUNTING JSP PAGE</h1>
        <%
        if((ArrayList) request.getAttribute("queryResult")!=null)
        {
            
                 ArrayList result = (ArrayList) request.getAttribute("queryResult");
                 String date1 = request.getParameter("date1");
                String date2 = request.getParameter("date2");
                
                out.println("<h3> Sales per Product Result from " + date1 + " to " + date2 + ": </h3> <br><br>");
                 %>
                 
                 
                 <table border ="1">
                <tr>
                
                
                <th>Product ID</th>
                <th>Product Type</th>
                <th>Product Title</th>
                <th>Total (PHP)</th>
                </tr>
                <%
                 if (!(result.isEmpty())) {
                     int i = 0;
                     //   for (int i = 0; i < result.size(); i++) {
                        while (i<result.size()){
                         %>
                            <tr>
                               
                                <td> <%  out.println(result.get(i)); i++; %> </td>
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
                 
                 <%
        if((ArrayList) request.getAttribute("queryResult1")!=null)
        {
            
                 ArrayList result = (ArrayList) request.getAttribute("queryResult1");
                 String date1 = request.getParameter("date1");
                String date2 = request.getParameter("date2");
                
                out.println("<h3> Sales per Product Type Result from " + date1 + " to " + date2 + ": </h3> <br><br>");
                 %>
                 
                 
                 <table border ="1">
                <tr>
                
                
              
                <th>Product Type</th>
                <th>Total (PHP)</th>
                </tr>
                <%
                 if (!(result.isEmpty())) {
                     int i = 0;
                     //   for (int i = 0; i < result.size(); i++) {
                        while (i<result.size()){
                         %>
                            <tr>
                               
                                <td> <%  out.println(result.get(i)); i++; %> </td>
                                <td> <% out.println(result.get(i)); i++; %> </td>
                               
                            </tr> <%
                            //  System.out.println(result2.get(i));
                        }
                    }
                }
          
                   
                %>
                 </table>
                  <%
        if((ArrayList) request.getAttribute("queryResult2")!=null)
        {
            
                 ArrayList result = (ArrayList) request.getAttribute("queryResult2");
                 String date1 = request.getParameter("date1");
                String date2 = request.getParameter("date2");
                
                out.println("<h3> Total Sales Result from " + date1 + " to " + date2 + ": </h3> <br><br>");
                 %>
                 
                 
                 <table border ="1">
                <tr>
                
                
              
                
                <th>Total (PHP)</th>
                </tr>
                <%
                 if (!(result.isEmpty())) {
                     int i = 0;
                     //   for (int i = 0; i < result.size(); i++) {
                        while (i<result.size()){
                         %>
                            <tr>
                               
                                <td> <%  out.println(result.get(i)); i++; %> </td>
                              
                               
                            </tr> <%
                            //  System.out.println(result2.get(i));
                        }
                    }
                }
          
                   
                %>
                 </table>
                 <br> <br> <br>
        
          <form method ="post" action="acct_query">
                        
       
              
        Get: 
        <select name="acct_query">
        <option value="salesProduct">Sales per Product</option>
        <option value="salesProducType">Sales per Product Type</option>
        <option value="totalSales">Total Sales</option>
        </select>
        <br>
         Start Date: <input type="text" name="date1"><br>
        End Date: <input type="text" name="date2"><br>
        <br><br>
        <input type="submit" name ="Go" value="Go"><br>
        </form>
         <% } %>
    </body>
</html>
