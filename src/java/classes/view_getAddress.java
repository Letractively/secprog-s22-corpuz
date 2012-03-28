/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import dbconnection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Lyle
 */
public class view_getAddress {
    
    public ArrayList getBillingAddress(String custId)
    {
       // String[] name = new String[100];
        
        ArrayList name = new ArrayList();
        
       try
        {
            //opens DB Connection
            ConnectionFactory myFactory = ConnectionFactory.getFactory();
            Connection conn = myFactory.getConnection();
                       
            //SQL Query
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT billing_id, billing_add FROM info_billing where cust_id = ?");
            
            pstmt.setString(1, custId);
            
            ResultSet rs = pstmt.executeQuery();

                while(rs.next())
                    {
                        name.add(rs.getString("billing_id"));
                        name.add(rs.getString("billing_add"));
                    }
            
             //close DB connection
            conn.close();

        }
        catch(SQLException ex)
            {
              ex.printStackTrace();
            }
            return name;
        }
    
}
