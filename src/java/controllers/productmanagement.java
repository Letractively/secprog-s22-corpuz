/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import dbconnection.*;
import classes.products;
import java.sql.*;
/**
 *
 * @author JM
 */
public class productmanagement
{
   public boolean AddProduct(products ProdType)
   {
       boolean result = false;
       try
       {
        ConnectionFactory myFactory = ConnectionFactory.getFactory();
        Connection conn = myFactory.getConnection();
        
        int k=1;
        
        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO PRODUCTS VALUES(?,?,?,?,?)");
        
        pstmt.setString(k++,ProdType.getProd_id());
        pstmt.setString(k++,ProdType.getProd_type());
        pstmt.setString(k++,ProdType.getProd_title());
        pstmt.setString(k++,ProdType.getProd_syn());
        
        Float receive = ProdType.getProd_price();
        String RecConv = receive.toString();
        
        pstmt.setString(k++,RecConv);
        pstmt.executeUpdate();
        
        conn.close();
        
        result = true;
        
       }
       catch(SQLException ex)
       {
         System.out.println(ex.getMessage());  
       }
       
       return result;
   }
    
   public boolean DeleteProduct(products DeletedType)
   {
       boolean result = false;
       
       try
       {
           ConnectionFactory myFactory = ConnectionFactory.getFactory();
           Connection conn = myFactory.getConnection();
           
           int m=1;
           
           PreparedStatement DeleteStmt = conn.prepareStatement("DELETE FROM PRODUCTS WHERE PROD_ID = ?");
           DeleteStmt.setString(m,DeletedType.getProd_id());
           DeleteStmt.executeUpdate();
           
           conn.close();
           result=true;
       }
       catch(SQLException ex)
       {
          System.out.println(ex.getMessage());   
       }
       return false;
   }
   
   public boolean UpdateProduct(products UpdateType)
   {
       boolean result=false;
       
       try
       {
           ConnectionFactory myFactory = ConnectionFactory.getFactory();
           Connection conn = myFactory.getConnection();
           
           int m=1;
           
           PreparedStatement UpdateStmt = conn.prepareStatement("UPDATE PRODUCTS SET PROD_TYPE = ?, PROD_TITLE = ?, PROD_SYN = ?, PROD_PRICE = ? WHERE PROD_ID = ?");
           
           UpdateStmt.setString(m++,UpdateType.getProd_type());
           UpdateStmt.setString(m++,UpdateType.getProd_title());
           UpdateStmt.setString(m++,UpdateType.getProd_syn());
           
           Float TranscribeString = UpdateType.getProd_price();
           String TransString = TranscribeString.toString();
           UpdateStmt.setString(m++,TransString);
           UpdateStmt.setString(m++,UpdateType.getProd_id());
          
           UpdateStmt.executeUpdate();
           conn.close();
           result = true;
       }
       catch(SQLException ex)
       {
               System.out.println(ex.getMessage());   
       }
       
       return result;
   
   }  
}
