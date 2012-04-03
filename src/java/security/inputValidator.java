/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import controllers.addcustomer_controller;
import dbconnection.ConnectionFactory;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
/**
 *
 * @author unseen
 */
public class inputValidator 
{
    public boolean isValid2(String[] inputs)
    {
        int i;
        boolean isValid = true;
        for(i = 0; i <= 1; i++)
        {
            if(inputs[i].matches("(?i).*[<>/();{}=\\n].*"))
            {
                isValid = false;
            }
        }
        
        return isValid;
    }
    
    public boolean isValid3(String[] inputs)
    {
        int i;
        boolean isValid = true;
        for(i = 0; i <= 2; i++)
        {
            if(inputs[i].matches("(?i).*[<>/();{}=\\n].*"))
            {
                isValid = false;
            }
        }
        
        return isValid;
    }
    
    
    public boolean isValid7(String[] inputs)
    {
        int i;
        boolean isValid = true;
        for(i = 0; i <= 6; i++)
        {
            if(inputs[i].matches("(?i).*[<>/();{}=\\n].*"))
            {
                isValid = false;
            }
        }
        return isValid;
    }
    public boolean isValid10(String[] inputs)
    {
        int i;
        boolean isValid = true;
        for(i = 0; i <= 9; i++)
        {
            if(inputs[i].matches("(?i).*[<>/();{}=\\n].*"))
            {
                isValid = false;
            }
        }
        return isValid;
    }
    
    public boolean isValid11(String[] inputs)
    {
        int i;
        boolean isValid = true;
        for(i = 0; i <= 10; i++)
        {
            if(inputs[i].matches("(?i).*[<>/();{}=\\n].*"))
            {
                isValid = false;
            }
        }
        return isValid;
    }
    
    public boolean searchUser(String username)
    {
        boolean result = false;
        try
        {
            //opens DB Connection
            ConnectionFactory myFactory = ConnectionFactory.getFactory();
            Connection conn = myFactory.getConnection();
                       
            //SQL Query
           
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM customer where cust_id = ?");
            pstmt.setString(1, username);
            
            ResultSet rs = pstmt.executeQuery();

                while(rs.next())
                    {
                        result = true;
                    }
            
             //close DB connection
            conn.close();
            }
                
            
        
        catch(SQLException ex)
            {
              ex.printStackTrace();
            }
            return result;
    }
    
    public boolean checkCard(String card, String user)
    {
        boolean result = false;
        
        
        
        hasher hs = new hasher();
            String cardN = null;
            try {
                cardN = hs.setHash(card);
                System.out.println(cardN);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(inputValidator.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        try
        {
            
            
            //opens DB Connection
            ConnectionFactory myFactory = ConnectionFactory.getFactory();
            Connection conn = myFactory.getConnection();
                       
            //SQL Query
           
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM info_tracker where card_num = ? and cust_id = ?");
            pstmt.setString(1, cardN);
            pstmt.setString(2, user);
            
            ResultSet rs = pstmt.executeQuery();

                while(rs.next())
                    {
                        result = true;
                    }
            
             //close DB connection
            conn.close();
            }
                
            
        
        catch(SQLException ex)
            {
              ex.printStackTrace();
            }
            return result;
    }
    
    public boolean checkCart(int orderID)
    {
        boolean result = false;
        
        
        
            
        try
        {
            
            
            //opens DB Connection
            ConnectionFactory myFactory = ConnectionFactory.getFactory();
            Connection conn = myFactory.getConnection();
                       
            //SQL Query
           
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM order_acct where order_id = ?");
            pstmt.setInt(1, orderID);
           
            
            ResultSet rs = pstmt.executeQuery();

                while(rs.next())
                    {
                        result = true;
                    }
            
             //close DB connection
            conn.close();
            }
                
            
        
        catch(SQLException ex)
            {
              ex.printStackTrace();
            }
            return result;
    }
}
