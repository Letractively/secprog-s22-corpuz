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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author arvin
 */
public class login_checkUserifFailed 
{
    public boolean checkUsername(login_temp temp)
    {
        boolean isIdentified = false;
        int i = 1;
        ConnectionFactory myFactory = ConnectionFactory.getFactory();
        Connection conn = myFactory.getConnection();
        try 
        {
            PreparedStatement pstmt = conn.prepareStatement("select * from customer where cust_id = ?");
            pstmt.setString(i++, temp.getUsername());
            
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next())
            {
              isIdentified = true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(login_checkUserifFailed.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(login_checkUserifFailed.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return isIdentified;
    }
    
    public boolean checkPassword(login_temp temp)
    {
        boolean isIdentified = false;
        int i = 1;
        ConnectionFactory myFactory = ConnectionFactory.getFactory();
        Connection conn = myFactory.getConnection();
        try 
        {
            PreparedStatement pstmt = conn.prepareStatement("select * from customer where password = ?");
            pstmt.setString(i++, temp.getPassword());
            
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next())
            {
                isIdentified = true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(login_checkUserifFailed.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(login_checkUserifFailed.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return isIdentified;
    }
}
