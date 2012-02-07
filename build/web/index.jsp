<%-- 
    Document   : index
    Created on : 01 19, 12, 6:25:47 PM
    Author     : Loowah
--%>

<%-- Sample Push by Me --%>
<%@page import = "java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="index.css">
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Foobar Bookshop</title>
    </head>
    <body>
        <div>
            <table class=""><tr><td>
        
            <img src="Images/logo.png">
        
            </td>
            
            <td><div class="space"><p></div></td>
            
            
            <td>
                <div class="login">
                    <br>
                    <form name="loginForm" method="post" action="login_controller">
                        
                        Username: <input type="text" name="username"><br>
                        Password: <input type="password" name="password"><br>
                        <input type="submit" class="loginButton" name="loginButton"><br><br>
                        Not a member? <a href="registration1.jsp">Register now!</a>
                    </form>
                    
                </div>
            </td></tr></table>
                
            <div align="center">
                <br><br><br>
                <form name="searchForm" method="post" action="product_controller">
                    
                    <select name="type">
                        <option value="book">Books</option>
                        <option value="magazine">Magazines</option>
                        <option value="cd">Audio CDs</option>
                        <option value="dvd">DVDs</option>
                    </select>
                    <input type="text" size="40" value="Search..." name="searchBox" autocomplete="off"> 
                    <input type="submit" class="loginButton" name="searchButton" value="Search"> 
                </form>
                <%
        String line="";
        
        if((ResultSet)request.getAttribute("Result")!=null)
        {
          ResultSet receive = (ResultSet)request.getAttribute("Result");
           ResultSetMetaData transcribe = receive.getMetaData();
           out.println("Number of rows"+transcribe.getColumnCount()+"\n");
           
           int ColumnCount = transcribe.getColumnCount();
           
           for(int i=0;i<ColumnCount;i++)
            {
                if(i>0)
                {
                    line+=",";
                    
                }
                    line +=transcribe.getColumnName(i+1);
            }
            
            out.println(line);
            out.println("<br>");
            
            int rowCount = 0;
            
            while(receive.next())
            {
                rowCount++;
                
                line="";
                
                out.print("Fetching Result Row #: " + rowCount);
                
                for(int i=0;i<ColumnCount;i++)
                {
                  
                    if(i>0)
                    {
                        line+=",";
                    }
                    line+=receive.getString(i+1);
                    
                    
                }
                out.println(" "+line);
                out.println("<br>");
                
            }
                       }
          else   
          out.println("No results thrown.");
                   
    %>
            </div><br>
                
            
        </div>
        
        
    </body>
</html>
