<%-- 
    Document   : changepassword
    Created on : Feb 23, 2012, 3:28:50 AM
    Author     : arvin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%
        String username = (String) session.getAttribute("UserName");
        if(request.getParameter("Submit")!=null)
        {
            String password = request.getParameter("password");
            String repass = request.getParameter("repass");
            String npass = request.getParameter("npassword");
            if(password != repass)
            {
                %>
                <script type="text/javascript">alert("Passwords dont match");</script>
                <%
            }
            else
            {
                
                request.setAttribute("Password", password);
                request.setAttribute("nPassword", npass);
                request.setAttribute("UserName", username);
                String strViewPage="reset_password";
                RequestDispatcher dispatcher = request.getRequestDispatcher(strViewPage);
                if (dispatcher != null)
                {
                    dispatcher.forward(request, response);
                }
                
                %>
                <script type="text/javascript">document.location="reset_password";</script>
                <%
            }
                
            
        }
    %>
    </head>
    
    <body>
      
        <form action ="">
            Old Password: <input type="password" name="password"><br>
            Retype Password: <input type="password" name="repass"><br>
            New Password: <input type="password" name="npassword"><br>
            <input type="submit"  class="registerButton" name ="Submit" value="Change>>"><br>
        </form>
    </body>
</html>
