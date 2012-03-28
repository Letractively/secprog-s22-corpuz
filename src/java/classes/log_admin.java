/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;



import dbconnection.ConnectionFactoryLogs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;

/**
 *
 * @author Lyle
 */
public class log_admin {
    
     
        public boolean addLogsAdmin(String Logs)
        {
            boolean result = false;
            try
            {
                //opens DB Connection
                ConnectionFactoryLogs myFactory1 = ConnectionFactoryLogs.getFactory();
                Connection conn = myFactory1.getConnection();


                //include parameters
                int i = 1;

                //SQL Query
                PreparedStatement pstmt = conn.prepareStatement("INSERT INTO administrators(A_logs) VALUES(?)");

                pstmt.setString(i++, Logs);


                //execute query
                pstmt.executeUpdate();

                //close DB connection
                conn.close();

                result = true;
            }
            catch(SQLException ex)
                {
                ex.printStackTrace();
                }
            return result;
 }
        
        public boolean addLogsFinancial(String Logs)
        {
            boolean result = false;
            try
            {
                //opens DB Connection
                ConnectionFactoryLogs myFactory1 = ConnectionFactoryLogs.getFactory();
                Connection conn = myFactory1.getConnection();


                //include parameters
                int i = 1;

                //SQL Query
                PreparedStatement pstmt = conn.prepareStatement("INSERT INTO financial(F_logs) VALUES(?)");

                pstmt.setString(i++, Logs);


                //execute query
                pstmt.executeUpdate();

                //close DB connection
                conn.close();

                result = true;
            }
            catch(SQLException ex)
                {
                ex.printStackTrace();
                }
            return result;
 }
        
        public boolean addLogsProduct(String Logs)
        {
            boolean result = false;
            try
            {
                //opens DB Connection
                ConnectionFactoryLogs myFactory1 = ConnectionFactoryLogs.getFactory();
                Connection conn = myFactory1.getConnection();


                //include parameters
                int i = 1;

                //SQL Query
                PreparedStatement pstmt = conn.prepareStatement("INSERT INTO product(P_logs) VALUES(?)");

                pstmt.setString(i++, Logs);


                //execute query
                pstmt.executeUpdate();

                //close DB connection
                conn.close();

                result = true;
            }
            catch(SQLException ex)
                {
                ex.printStackTrace();
                }
            return result;
 }
        
        public boolean addLogsCustomer(String Logs)
        {
            boolean result = false;
            try
            {
                //opens DB Connection
                ConnectionFactoryLogs myFactory1 = ConnectionFactoryLogs.getFactory();
                Connection conn = myFactory1.getConnection();


                //include parameters
                int i = 1;

                //SQL Query
                PreparedStatement pstmt = conn.prepareStatement("INSERT INTO customer(logs) VALUES(?)");

                pstmt.setString(i++, Logs);


                //execute query
                pstmt.executeUpdate();

                //close DB connection
                conn.close();

                result = true;
            }
            catch(SQLException ex)
                {
                ex.printStackTrace();
                }
            return result;
 }
}


