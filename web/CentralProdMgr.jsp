<%-- 
    Document   : CentralProdMgr
    Created on : Mar 27, 2012, 8:03:43 PM
    Author     : JM
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "java.util.ArrayList" %>


<!DOCTYPE html>
<%
HttpSession MainMgrSession;

MainMgrSession = request.getSession(false);

if(MainMgrSession!=null)
{
String ProdName;
ArrayList TransferProds;
ProdName = (String)MainMgrSession.getAttribute("ProductName");
TransferProds = (ArrayList) MainMgrSession.getAttribute("ArrayPacks");
System.out.println("ProductName:"+ ProdName);


MainMgrSession.setAttribute("DefaultPN",ProdName);

}

if(MainMgrSession.getAttribute("DefaultPN")!=null)
{
System.out.println("Item Restored.");
}

if(MainMgrSession.getAttribute("Success")!=null)
{
%><script language="javascript">alert('Successful Product Adding!');</script><%    
}



    
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Welcome Product Manager!</h1>
        <h2> Manage Products Here: </h2>
        <form method="post" action="">
        
            <%
        
          String ProductType = (String)session.getAttribute("ProductName");
          session.setAttribute("ProductType",ProductType);  
    
       
        
        if((ArrayList) request.getAttribute("ArrayPacks")!=null || MainMgrSession.getAttribute("ArrayPacks")!=null)
         {
          ArrayList FinalRes = (ArrayList)MainMgrSession.getAttribute("ArrayPacks");
          
        %>    
         <table border ="1">
                <tr>
                
                <th>Selection</th>
                <th>Product Type</th>
                <th>Product Name</th>
                <th>Synopsis</th>
                <th>Price (PHP)</th>
                </tr>
                
                <form method="post" action="">
                
                <%
                 if (!(FinalRes.isEmpty())) 
                 {
                     int i = 0;
                     //   for (int i = 0; i < result.size(); i++) {
                        while (i<FinalRes.size()){
                         %>
                            <tr>
                                <td><input type="radio" name="choice" value="<%= FinalRes.get(i) %>"></td> 
                                <td> <% i++; out.println(FinalRes.get(i)); i++; %> </td>
                                <td> <% out.println(FinalRes.get(i)); i++; %> </td>
                                <td> <% out.println(FinalRes.get(i)); i++;%> </td>
                                <td> <% out.println(FinalRes.get(i)); i++; %> </td>
                            </tr> <%
                            //  System.out.println(result2.get(i));
                        }
                    }
        }
       else
           {
           
           
           }
          
                   
                %>
         </table>
         <br>
          Manage selected product:
          <br>
         <select name="Operation">
             <option value="Modify">Modify Product Information</option>
             <option value="Delete">Delete Product Information</option>
         </select>
        
         <input type="submit" name="ManageProds" value="Manage">
         </form>
   
         <br>
         <h2> Add a new product here: </h2>
         <form method="post" action="product_mgt_controller">
          ID of Product: <input type="text" name="ProdID"><br>
          Name of Product: <input type="text" name="ProdName"><br>
          Price of Product: <input type="text" name="ProdPrice"><br>
          Synopsis:<br>
          <textarea name="Synopsis" cols="40" rows="5">
          </textarea>
          <br>
    
          <input type="submit" value="Add Product" name="AddProds">
          <br>
          <br>
          <input type="submit" value="Change Managerial Role" name="ChangeRole">
         
         </form>
         
        
    
</body>
</html>
