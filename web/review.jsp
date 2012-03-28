<%-- 
    Document   : review
    Created on : 03 27, 12, 10:50:12 PM
    Author     : unseen
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Make Your Review!</h1>
        <%
        out.println(session.getAttribute("product").toString());
        session.setAttribute("user", session.getAttribute("user"));
        session.setAttribute("choice", session.getAttribute("choice"));
        if((ArrayList) request.getAttribute("a")!=null)
        {
                
 ;                ArrayList result = (ArrayList) request.getAttribute("a");
                 %>
                 <table border ="1">
                <tr>
                
               
                <th>Buyer</th>
                <th>Date</th>
                <th>Comment</th>
                </tr>
                <%
                 if (!(result.isEmpty())) {
                     int j = 0;
                     //   for (int i = 0; i < result.size(); i++) {
                        while (j<result.size()){
                         %>
                            <tr>
                                <td> <% out.println(result.get(j)); j++; %> </td>
                                <td> <% out.println(result.get(j).toString()); j++; %> </td>
                                <td> <% out.println(result.get(j)); j++;%> </td>
                 
                            </tr> <%
                            //  System.out.println(result2.get(i));
                        }
                    }
        }
               else 
                                     {
                            %><h4> No Reviews yet</h4><%
               }
          
                   
                %>
                 </table>
                <%if(session.getAttribute("isView").toString().contentEquals("false"))
                {
                %>
                 <form name="addComment" method="post" action="add_comment">
                  
                    <input type="text" size="50" name="comment_box" autocomplete="off"> 
                    <input type="submit" class="loginButton" name="commentButton" value="Post Review"> 
                </form>
                <%
                 }
        %>
        <a href ="home.jsp">Home</a>
    </body>
</html>
