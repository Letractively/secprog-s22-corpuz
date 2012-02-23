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
        if(request.getParameter("changePass")!=null)
        {
            session.setAttribute("exists", null);
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
            if(session.getAttribute("exists") != null)
            {
                System.out.println("Password Exists");
            }
                       }
    %>
    </head>
    
    <body>
      
        <form method ="post" action ="">
            Old Password: <input type="password" name="password"><br>
            Retype Password: <input type="password" name="repass"><br>
            New Password: <input type="password" name="npassword"><br>
            <input type="submit"  class="registerButton" name ="changePass" value="Change>>"><br>
        </form>
    </body>
</html>
