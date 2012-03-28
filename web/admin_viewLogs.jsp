<%-- 
    Document   : admin_viewLogs
    Created on : Mar 29, 2012, 12:23:10 AM
    Author     : Lyle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "classes.*" %>
<%@page import = "java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Logs</title>
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
            if(session.getAttribute("loggedIn_admin")==null)
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
        <h1>View Logs</h1>
        <form method ="post" action="">               
        Logs for: 
        <select name="position">
        <option value="Administrator">Administrator</option>
        <option value="Customer">Customer</option>
        <option value="Financial">Financial</option>
        <option value="Product">Product</option>    
        </select>       
        <input type="submit" name ="Submit" value="Submit"><br>
        </form>
        <%
            if(request.getParameter("Submit") != null)
            {
                if( request.getParameter("position").toString().contentEquals("Administrator"))
                {
                    ArrayList result = new log_admin().viewLogsAdmin();
                    
                    %>
                 
                  <br><br>
                 <table border ="1">
                <tr>
                
                
                <th>TIMESTAMP</th>
                <th>LOGS</th>
               
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
                
                 if( request.getParameter("position").toString().contentEquals("Customer"))
                {
                    ArrayList result = new log_admin().viewLogsCustomer();
                    
                    %>
                 
                  <br><br>
                 <table border ="1">
                <tr>
                
                
                <th>TIMESTAMP</th>
                <th>LOGS</th>
               
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
                
                if( request.getParameter("position").toString().contentEquals("Financial"))
                {
                    ArrayList result = new log_admin().viewLogsFinancial();
                    
                    %>
                 
                  <br><br>
                 <table border ="1">
                <tr>
                
                
                <th>TIMESTAMP</th>
                <th>LOGS</th>
               
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
                 
                
                if( request.getParameter("position").toString().contentEquals("Product"))
                {
                    ArrayList result = new log_admin().viewLogsProducts();
                    
                    %>
                 
                  <br><br>
                 <table border ="1">
                <tr>
                
                
                <th>TIMESTAMP</th>
                <th>LOGS</th>
               
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
               <% } %>
            
                
        
        <% } %>
    </body>
    
</html>
