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
       if(ProdType.getProd_id().matches("(?i).*[<>/(){}=\\n].*") || ProdType.getProd_title().matches("(?i).*[<>/(){}=\\n].*") || ProdType.getProd_syn().matches("(?i).*[<>/(){}=\\n].*") || ProdType.getProd_price() < 0)
        {
                    System.out.println("bawalllllllllllllll");
                   result = false;
        }
     
       else
       {
       try
       {
        ConnectionFactory myFactory = ConnectionFactory.getFactory();
        Connection conn = myFactory.getConnection();
        
        int k=1;
        
        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO PRODUCTS (prod_id, prod_type, prod_title, prod_syn, prod_price) VALUES(?,?,?,?,?)");
        
        pstmt.setString(k++,ProdType.getProd_id());
        pstmt.setString(k++,ProdType.getProd_type());
        pstmt.setString(k++,ProdType.getProd_title());
        pstmt.setString(k++,ProdType.getProd_syn());
        
        Float receive = ProdType.getProd_price();
      //  String RecConv = receive.toString();
        
        pstmt.setFloat(k++,receive);
        pstmt.executeUpdate();
        
        conn.close();
        
        result = true;
        
       }
       catch(SQLException ex)
       {
         System.out.println(ex.getMessage());  
       }
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
           
           PreparedStatement DeleteStmt = conn.prepareStatement("UPDATE PRODUCTS SET isDeleted = 1 WHERE PROD_ID = ?");
           DeleteStmt.setString(m,DeletedType.getProd_id());
           DeleteStmt.executeUpdate();
           
           conn.close();
           result=true;
       }
       catch(SQLException ex)
       {
          System.out.println(ex.getMessage());   
       }
       return result;
   }
   
   public boolean UpdateProduct(products UpdateType)
   {
       boolean result=false;
         if(UpdateType.getProd_title().matches("(?i).*[<>/(){}=\\n].*") || UpdateType.getProd_syn().matches("(?i).*[<>/(){}=\\n].*") || UpdateType.getProd_price() < 0)
        {
                    System.out.println("bawal");
                   result = false;
        }
         else
         {
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
         //  String TransString = TranscribeString.toString();
           UpdateStmt.setFloat(m++,TranscribeString);
           UpdateStmt.setString(m++,UpdateType.getProd_id());
          
           UpdateStmt.executeUpdate();
           conn.close();
           result = true;
       }
       catch(SQLException ex)
       {
               System.out.println(ex.getMessage());   
       }
         }
       return result;
   
   }  
}

