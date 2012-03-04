<%-- 
    Document   : banned
    Created on : Feb 23, 2012, 1:19:09 AM
    Author     : arvin
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
HttpSession EngageLockout;
EngageLockout = request.getSession(false);

if(EngageLockout==null)
 {
%>
 <script language="javascript">alert('You are not authorized to enter this page.');</script>
 <%
response.sendRedirect("index.jsp");  
}
else
{
EngageLockout = request.getSession();
EngageLockout.setMaxInactiveInterval(300);
EngageLockout.setAttribute("FlagLockout", true);
}
%>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="users.css">
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Foobar Bookshop</title>
         <center><div class="redirect"><br>You have reached the maximum log-in attempts for this system. You will be revalidated in 
                 <span id='redirect'>5</span> minutes.<center>
       
                
               <%-- <script language=javascript>
                  
        
               var redirect = 5;
          
               
               setInterval("minuteDeductor()",60000);
  
               function minuteDeductor()
               {
               //var d=new Date();
              // var t=d.toLocaleTimeString();
               //document.getElementById("clock").value=t;
                 document.getElementById('redirect').innerHTML = redirect--;
                 
                 if(redirect==0)
                      window.location='index.jsp';
               }
        
      
               
               </script>--%>
               

    

      
    </head>
    <body>
 
    
    </body> 
</html>
