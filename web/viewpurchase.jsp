<%-- 
    Document   : viewpurchase
    Created on : Mar 27, 2012, 11:03:37 PM
    Author     : Lyle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Purchase Product</title>
         <%
            response.setHeader("Pragma","no-cache");
            response.setHeader("Cache-Control","no-store");
            response.setHeader("Expires","0");
            response.setDateHeader("Expires",-1);
        %>
        <%
            if(session.getAttribute("loggedIn")==null)
            {
                %>
    <center>You have not yet logged in! You will be redirected in  <span id='redirect'>5</span> seconds. </center>
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
         <%
                        out.print( "<b>Welcome " +  session.getAttribute("user") + "!<br/></b>");                    
        %>
        
        <h1>View Purchase Product</h1>
        <form name="addComment" method="post" action="purchase_controller">
        <%
        session.setAttribute("user", session.getAttribute("user"));
        session.setAttribute("isView", "false");
        if((ArrayList) request.getAttribute("queryResultA")!=null)
        {
            
                 ArrayList result = (ArrayList) request.getAttribute("queryResultA");
                
                 %>
                 
                 <br><br>
                 
                 <table border ="1">
                <tr>
                
                <th>Select</th>
                <th>Paid Date</th>
                <th>Product Type</th>
                <th>Product Title</th>
                <th>Quantity</th>
                <th>Selling Price (PHP)</th>
                <th>Total (PHP)</th>
                </tr>
                <%
                 if (!(result.isEmpty())) {
                     int i = 0;
                     //   for (int i = 0; i < result.size(); i++) {
                        while (i<result.size()){
                         %>
                            <tr>
                               
                                <td> <input type="radio" name="choice" value="<%= result.get(i) %>"></td>
                                <td> <% i++; out.println(result.get(i)); i++; %> </td>
                                <td> <% out.println(result.get(i)); i++;%> </td>
                                <td> <% out.println(result.get(i)); i++;%> </td>
                                <td> <% out.println(result.get(i)); i++; %> </td>
                                <td> <% out.println(result.get(i)); i++; %> </td>
                                <td> <% out.println(result.get(i)); i++; %> </td>
          
                            </tr> <%
                            //  System.out.println(result2.get(i));
                        }
                    }
                }
          
                   
                %>
                 </table>
           
                    <input type="submit" name="comment" value="View/Make Reviews"> 
                </form>
                 <br><br>
                 <a href ="home.jsp">Home</a>
    </body>
    <% 
                       }
      %>  
</html>
