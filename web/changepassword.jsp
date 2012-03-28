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
        <title>Foobar Bookshop | Change Password</title>
        
        <link rel="stylesheet" type="text/css" href="users.css">       
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
            if(!npass.contentEquals(repass))
                {
                    %>
                <script type="text/javascript">alert("New passwords dont match");</script>
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
    <div>
            <table class=""><tr><td>
        
            <img src="Images/uLogo.png">
        
            </td></tr>
            </table>
            
            <center>
                <div class="changepassword">
                    <h3>Change Password:</h3>
                <form method ="post" action ="">
                    Old Password: <input type="password" name="password"><br>
                    New Password: <input type="password" name="npassword"><br>
                    Retype New Password: <input type="password" name="repass"><br>
                    <input type="submit"  class="registerButton" name ="changePass" value="Change"><br>
                    <hr>
                    <br><br><br><br><br><br><br>
                    <p align="right"><a href="home.jsp">Back to home page</a></p>
                </form>
                    
                </div>
            </center>
    </body>
</html>
