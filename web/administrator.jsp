<%-- 
    Document   : administrator
    Created on : Feb 4, 2012, 1:44:53 PM
    Author     : Lyle
--%>

<%@page import="controllers.admin_controller"%>
<%@page import="sun.security.x509.NetscapeCertTypeExtension"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "classes.*" %>
<%@page import = "java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrator</title>
    </head>
    <body>
        <h1>Welcome Back Administrator!</h1>
        
        <a href="admin-createaccount.jsp">Create an Account</a><br/>
        <a href="admin-unlockaccount.jsp">Lock/Unlock an Account</a><br/>
        
        <%
            if(request.getParameter("Submit")!=null)
            {
                staff newStaff = new staff();
                
                newStaff.setStaff_id(request.getParameter("username"));
                newStaff.setPassword(request.getParameter("password"));
                String rePass = request.getParameter("repass");
                newStaff.setState("unlock");
                newStaff.setFname(request.getParameter("firstName"));
                newStaff.setLname(request.getParameter("lastName"));
                newStaff.setMname(request.getParameter("middleName"));
                newStaff.setEmail(request.getParameter("email"));
                newStaff.setAddress(request.getParameter("homeadd"));
                newStaff.setPosition(request.getParameter("position"));
              //  out.println(request.getParameter("position"));
                
                ArrayList result2 = new admin_controller().checkAccountParameters(newStaff, rePass);
               // System.out.println("hehe" + result2.size());
                if(!(result2.isEmpty()))
                { 
                    for(int i=0; i<result2.size(); i++)
                     {
                        out.println(result2.get(i)+ "<br/>");
                      //  System.out.println(result2.get(i));
                     }
                }
                else
                {
                boolean result = new admin_controller().addAccount(newStaff);
                
                
                 if(result==true)
                    {
                        %><script type="text/javascript">alert("Adding Successful!");</script><%
                       
                    }
                    else
                    {
                        %><script type="text/javascript">alert("Adding failed!");</script><%
                        
                    }
               }
             }
        %>
        
        <h2>Create Account</h2>
        <form method ="post" action="">
                        
        Username: <input type="text" name="username"><br>
        Password: <input type="password" name="password"><br>
        Retype Password: <input type="password" name="repass"><br>
        Name: <input type="text" name="firstName" size="7"> <input type="text" name="middleName" size="7"> <input type="text" name="lastName" size="7"><br>
        E-Mail Address: <input type="text" name="email"><br>
        Home Address: <input type="text" name="homeadd"><br>       
        Position: 
        <select name="position">
        <option value="A-AM">Accounting Manager</option>
        <option value="B-ACM">Audio CD Manager</option>
        <option value="B-BM">Book Manager</option>
        <option value="B-DM">DVD Manager</option>
        <option value="B-MM">Magazine Manager</option>
        </select>
        <br><br>
        <input type="submit" name ="Submit" value="Submit"><br>
        </form>
        
        <br><br>
        
        <%
            if(request.getParameter("changeState")!=null)
            {
                staff newStaff = new staff();
                
                newStaff.setStaff_id(request.getParameter("staffID"));
                newStaff.setState(request.getParameter("choose"));
                out.println(request.getParameter("staffID"));
                out.println(request.getParameter("choose"));
                boolean result = new admin_controller().updateState(newStaff);
                
                 if(result==true)
                    {
                        %><script type="text/javascript">alert("Changing State Successful!");</script><%
                       
                    }
                    else
                    {
                        %><script type="text/javascript">alert("Changing State Failed!");</script><%
                        
                    }
            }
        %>
        
        <h2>Lock/Unlock Account</h2>
        <form method ="post" action="">
                        
        Username: 
        <select name="staffID">
        
        <% ArrayList result = new admin_controller().getAccount(); 
        
        for(int counter2 = 0; counter2 < result.size(); counter2++)
                    { %>
                    <option value= <%= result.get(counter2) %>> <%= result.get(counter2) %> </option>
                    <%
                    }
                    %>
      </select>
      <br>
       Choose:
       <select name="choose">
       <option value="Lock">Lock</option>
       <option value="UnLock">Unlock</option>
       </select>
       <br><br>
        
        <input type="submit" name ="changeState" value="Submit"><br>
        </form>
        
    </body>
</html>
