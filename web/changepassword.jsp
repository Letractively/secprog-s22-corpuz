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
        
    </head>
    
    <body>
      <%
            if((String) session.getAttribute("exists") == "true")
            {
                out.println("Password Exists");
            }
        String username = (String) session.getAttribute("UserName");
        if(request.getParameter("changePass")!=null)
        {
            String password = request.getParameter("password");
            String repass = request.getParameter("repass");
            String npass = request.getParameter("npassword");
            if(!password.contentEquals(repass))
                {
                    %>
                <script type="text/javascript">alert("Passwords dont match");</script>
                <%
                }
            else
            {
                session.setAttribute("Password", password);
                session.setAttribute("nPassword", npass);
                session.setAttribute("UserName", username);
                response.sendRedirect("reset_password");
            }
            
                       }
    %>
        <form method ="post" action ="">
            Old Password: <input type="password" name="password"><br>
            Retype Password: <input type="password" name="repass"><br>
            New Password: <input type="password" name="npassword"><br>
            <input type="submit"  class="registerButton" name ="changePass" value="Change>>"><br>
            <a href="home.jsp">Back to home page</a>
        </form>
    </body>
</html>
