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
          <%
            response.setHeader("Pragma","no-cache");
            response.setHeader("Cache-Control","no-store");
            response.setHeader("Expires","0");
            response.setDateHeader("Expires",-1);
        %>
        
        <form name="loginForm" method="post" action="exit_controller">
                        
                        <%
                        out.print( "<b>Welcome " +  session.getAttribute("user") + "!<br/></b>");
                  //      session.setAttribute("UserName", session.getAttribute("user"));
                        
                      //  Logged in as: <%=  <br> 
 
                          
                        %>
                        <input type="submit" class="loginButton" name="logoutButton" value="Logout"><br><br>
         </form>
    
        <%
            if(session.getAttribute("loggedIn_admin")==null)
            {
                %>
                <center>You have not yet logged in! You will be redirected in <span id='redirect'>5</span> seconds.</center>
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
        <h1>Welcome Back Administrator!</h1>
        
            
         <%
            if ((ArrayList) request.getAttribute("resulta") != null) {
                    ArrayList result23 = (ArrayList) request.getAttribute("resulta");
                    if (!(result23.isEmpty())) {
                        for (int i = 0; i < result23.size(); i++) {
                            out.println(result23.get(i) + "<br/>");
                            //  System.out.println(result2.get(i));
                        }
                    }
                }
                   
              if ((String) request.getAttribute("flagKo") != null )
                  {
                
                        String resultChecker = (String) request.getAttribute("flagKo");

                        if(resultChecker.contentEquals("tama"))
                        {
                                %><script type="text/javascript">alert("Adding Successful!");</script><% 
                        }
                        else
                        {
                                %><script type="text/javascript">alert("Adding failed!");</script><% 
                        }

                   }
                    
                        
         %>
         
         
                        
        <h2>Create Account</h2>
        <form method ="post" action="admin_reg_controller">
                        
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
        
            
            if ((String) request.getAttribute("flagKo2") != null )
                  {
                
                        String resultChecker = (String) request.getAttribute("flagKo2");

                        if(resultChecker.contentEquals("tama"))
                        {
                                %><script type="text/javascript">alert("Changing State Successful!");</script><% 
                        }
                        else
                        {
                                %><script type="text/javascript">alert("Changing State Failed!");</script><% 
                        }

                   }
            
            
            
        %>
        
        <% 
         if ((ArrayList) request.getAttribute("resulta1") != null) {
                    ArrayList result23 = (ArrayList) request.getAttribute("resulta1");
                    if (!(result23.isEmpty())) {
                        for (int i = 0; i < result23.size(); i++) {
                            out.println(result23.get(i) + "<br/>");
                            //  System.out.println(result2.get(i));
                        }
                    }
                }
        %>
        <h2>Lock/Unlock Account</h2>
        
        <form method ="post" action="admin_accountList_controller">
         
            Filter List of Username From: 
                
            <select name="position">
            <option value="A-AM">Accounting Manager</option>
            <option value="B-ACM">Audio CD Manager</option>
            <option value="B-BM">Book Manager</option>
            <option value="B-DM">DVD Manager</option>
            <option value="B-MM">Magazine Manager</option>
            <option value="Customer">Customer</option>
            </select>
            <input type="submit" name ="filterPosition" value="Filter">
            <br> <br>
        </form>
        
        
        
        <form method ="post" action="admin_lock_controller">
         
            
            <h3> List of Username from 
        <%  if((String) request.getAttribute("positionName") != null)
            {
                out.print( (String) request.getAttribute("positionName"));
                session.setAttribute("Position", request.getAttribute("positionName").toString());
            }
            
            
        %>: </h3> Username: 
        <select name="staffID">
           
            <%
            
            if((ArrayList) request.getAttribute("resultSet") != null)
            {
                ArrayList result = (ArrayList) request.getAttribute("resultSet");
            int counter2 = 0;
       // for(int counter2 = 0; counter2 < result.size(); counter2++)
            while(counter2 < result.size())  
                    { %>
                    <option value= <%= result.get(counter2) %>> <% out.print(result.get(counter2)); counter2++; out.print(" - " + result.get(counter2)); counter2++; %> </option>
                    <%
                    }
            }
           
                    %>
                  
      </select>
                    
                    
      <br><br>
       Choose:
       <select name="choose">
       <option value="Lock">Lock/(0)</option>
       <option value="UnLock">Unlock/(1)</option>
       </select>
       <br><br>
        
        <input type="submit" name ="changeState" value="Submit"><br>
        </form>
                    <br> <br>
                    <a href ="admin_viewLogs.jsp">VIEW LOGS</a>
        <% } %>
    </body>
</html>
