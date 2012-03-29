/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import dbconnection.ConnectionFactory;
import java.security.NoSuchAlgorithmException;
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
    
    public String fetchCookieAttributor(login_temp temp)
    {
        String isIdentified = null;
        
        int i = 1;
        
        try 
        {
            ConnectionFactory myFactory = ConnectionFactory.getFactory();
            Connection conn = myFactory.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("select email_add from cust_acct where cust_id = ?");
            pstmt.setString(i++, temp.getUsername());
            
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next())
            {
           
             isIdentified = rs.getString("email_add");      
            }
          
            
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(login_checkUserifFailed.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        return isIdentified;
    }
    
    public boolean checkPassword(login_temp temp) throws NoSuchAlgorithmException
    {
        boolean isIdentified = false;
        hasher hs = new hasher();
        int i = 1;
        String passChecksum = hs.setHash(temp.getPassword());
        try 
        {
            ConnectionFactory myFactory = ConnectionFactory.getFactory();
            Connection conn = myFactory.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("select * from customer where password = ?");
            pstmt.setString(i++, passChecksum);
            
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next())
            {
                isIdentified = true;
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(login_checkUserifFailed.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        return isIdentified;
    }
    
    public boolean checkUsername(login_temp temp)
    {
        boolean isIdentified = false;
        int i = 1;
        
        try 
        {
            ConnectionFactory myFactory = ConnectionFactory.getFactory();
            Connection conn = myFactory.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("select * from customer where cust_id = ?");
            pstmt.setString(i++, temp.getUsername());
            
            ResultSet rs = pstmt.executeQuery();
            
            if(rs.next())
            {
              isIdentified = true;
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(login_checkUserifFailed.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        return isIdentified;
    }

     public retriever checkStaff(login_temp temp)
    {
        retriever gr = new retriever();
        gr.setUsername(null);
        int i = 1;
        
        try 
        {
            ConnectionFactory myFactory = ConnectionFactory.getFactory();
            Connection conn = myFactory.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("select * from staff where staff_id = ? and password = ?");
            pstmt.setString(i++, temp.getUsername());
            pstmt.setString(i++, temp.getPassword());
            
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next())
            {
              gr.setUsername(rs.getString("staff_id"));
              gr.setPosition(rs.getString("position"));
              gr.setTs(rs.getTimestamp("pass_tracker"));
              gr.setPass_changed(rs.getBoolean("pass_changed"));
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(login_checkUserifFailed.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        return gr;
    }
  
    public boolean checkOldPassword(String password) throws NoSuchAlgorithmException
    {
        boolean isIdentified = false;
        int i = 1;
        hasher hs = new hasher();
        String passwordHash = hs.setHash(password);
        ConnectionFactory myFactory = ConnectionFactory.getFactory();
        Connection conn = myFactory.getConnection();
        try 
        {
            PreparedStatement pstmt = conn.prepareStatement("select * from oldpass where password = ?");
            pstmt.setString(i++, passwordHash);
            
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
    
    public void changePassword(login_temp temp) throws NoSuchAlgorithmException
    {
        boolean isIdentified = false;
        int i = 1;
        
        hasher hs = new hasher();
        String passChecksum = hs.setHash(temp.getPassword());
            
        try 
        {
            
        
            ConnectionFactory myFactory = ConnectionFactory.getFactory();
            Connection conn = myFactory.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("insert into oldpass values(?,?)");
            pstmt.setString(i++, temp.getUsername());
            pstmt.setString(i++,  passChecksum);
            pstmt.executeUpdate();
            
            i = 1;
            pstmt = conn.prepareStatement("update customer set password = ? where cust_id = ?");
            pstmt.setString(i++,  passChecksum);
            pstmt.setString(i++, temp.getUsername());
            pstmt.executeUpdate();
            
            conn.close();
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(login_checkUserifFailed.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
       
    }
    public void changeAdminPassword(login_temp temp)
    {
        boolean isIdentified = false;
        int i = 1;
        
        try 
        {
            ConnectionFactory myFactory = ConnectionFactory.getFactory();
            Connection conn = myFactory.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("update staff set password = ?, pass_changed = ? where staff_id = ?");
            pstmt.setString(i++, temp.getPassword());
            pstmt.setBoolean(i++, true);
            pstmt.setString(i++, temp.getUsername());
            pstmt.executeUpdate();
            
            conn.close();
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(login_checkUserifFailed.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
       
    }
}
