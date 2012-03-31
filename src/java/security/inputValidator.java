/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import dbconnection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            if(inputs[i].matches("(?i).*[<>/(){}=\\n].*"))
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
            if(inputs[i].matches("<(\"[^\"]*\"|'[^']*'|[^'\">])*>"))
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
            if(inputs[i].matches("<(\"[^\"]*\"|'[^']*'|[^'\">])*>"))
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
            if(inputs[i].matches("<(\"[^\"]*\"|'[^']*'|[^'\">])*>"))
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
    
}
