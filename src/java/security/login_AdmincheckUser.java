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
public class login_AdmincheckUser {
    
    public boolean checkUsername(login_temp temp)
    {
        boolean isIdentified = false;
        int i = 1;
        ConnectionFactory myFactory = ConnectionFactory.getFactory();
        Connection conn = myFactory.getConnection();
        try 
        {
            PreparedStatement pstmt = conn.prepareStatement("select * from staff where staff_id = ? and state = 'unlock' or state = 'UnLock' ");
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
            PreparedStatement pstmt = conn.prepareStatement("select * from staff where password = ?");
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
    
    public boolean checkOldPassword(String password)
    {
        boolean isIdentified = false;
        int i = 1;
        ConnectionFactory myFactory = ConnectionFactory.getFactory();
        Connection conn = myFactory.getConnection();
        try 
        {
            PreparedStatement pstmt = conn.prepareStatement("select * from oldpass where password = ?");
            pstmt.setString(i++, password);
            
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
    public void checkState(login_temp temp)
    {
        boolean isIdentified = false;
        int i = 1;
        ConnectionFactory myFactory = ConnectionFactory.getFactory();
        Connection conn = myFactory.getConnection();
        try 
        {
            PreparedStatement pstmt = conn.prepareStatement("select state from customer where cust_id = ?");
            pstmt.setString(i++, temp.getUsername());
            
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next())
            {
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(login_checkUserifFailed.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(login_checkUserifFailed.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
    }
    
    public void changePassword(login_temp temp)
    {
        boolean isIdentified = false;
        int i = 1;
        ConnectionFactory myFactory = ConnectionFactory.getFactory();
        Connection conn = myFactory.getConnection();
        try 
        {
            PreparedStatement pstmt = conn.prepareStatement("insert into oldpass values(?,?)");
            pstmt.setString(i++, temp.getUsername());
            pstmt.setString(i++, temp.getPassword());
            pstmt.executeUpdate();
            
            i = 1;
            pstmt = conn.prepareStatement("update customer set password = ? where cust_id = ?");
            pstmt.setString(i++, temp.getPassword());
            pstmt.setString(i++, temp.getUsername());
            pstmt.executeUpdate();
            
            conn.close();
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(login_checkUserifFailed.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(login_checkUserifFailed.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
    }
}
