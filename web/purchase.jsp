<%-- 
    Document   : purchase
    Created on : Feb 22, 2012, 2:18:17 PM
    Author     : Lyle
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "java.util.ArrayList" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
                         out.print( "<b>Welcome " +  session.getAttribute("loggedIn") + "!<br/></b>");
                      //  Logged in as: <%=  <br> 
        %>
        
        
         <%
                if( request.getAttribute("clear")!= null)
                {
                    out.println("No Pending Order");
                    
                }
               else
                {
            %>
            
        <form method ="post" action="purchase_controller">
        
            
           
            
        <%
         if((ArrayList) request.getAttribute("purchaseResult")!= null)
        {
                 ArrayList result = (ArrayList) request.getAttribute("purchaseResult");
                 %>
                 <table border ="1">
                <tr>
                
                <th></th>
                <th>Product Type</th>
                <th>Product Title</th>
                <th>Quantity</th>
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
          
                 <% 
                    if((ArrayList) request.getAttribute("purchaseResult1")!= null)
                    {
                        ArrayList result = (ArrayList) request.getAttribute("purchaseResult1");
                        
                        for(int i=0; i<result.size();i++)
                        { 
                            out.print("<h3><b> TOTAL PRICE: " + result.get(i) + "</h3>");
                        }
                    }
                    

                %>
                
          <br><br><br>
          <input type="submit" name ="removeProd" value="Remove Product"> <br> <br>
          
          <hr>
          <input type="submit" name ="buyMore" value="Buy More"> 
          
          <input type="submit" name ="checkout" value="Check-Out"> 
          </form>
         <% 
                       }
     } %>  
    </body>
</html>
