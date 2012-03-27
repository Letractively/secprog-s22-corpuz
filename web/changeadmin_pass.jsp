<%-- 
    Document   : changeadmin_pass
    Created on : 03 25, 12, 8:25:41 PM
    Author     : unseen
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
            if((String) session.getAttribute("passChange") == "true")
            {
                out.println("Change Password first");
            }
            String username = (String) session.getAttribute("user");
            
            if(request.getParameter("change_adminpass")!=null)
            {
                String password = request.getParameter("password");
                String repass = request.getParameter("repass");
                String npass = request.getParameter("npassword");
                System.out.print("yey");
                if(!npass.contentEquals(repass))
                {
                    %>
                    <script type="text/javascript">alert("New passwords dont match");</script>
                    <%
                }
                else
                {
                    
                    session.setAttribute("sessionName", session.getAttribute("sessionName"));
                    session.setAttribute("Password", npass);
                    session.setAttribute("UserName", username);
                    
                    request.getRequestDispatcher("reset_adminpass").forward(request,response);
                }
            
           }
    %>
        
        <form method ="post" action ="">
                    Old Password: <input type="password" name="password"><br>
                    New Password: <input type="password" name="npassword"><br>
                    Retype New Password: <input type="password" name="repass"><br>
                    <input type="submit"  name ="change_adminpass" value="Change"><br>
                    <hr>
                    
        </form>
    </body>
</html>
