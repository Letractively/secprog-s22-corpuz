/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;



import dbconnection.ConnectionFactoryLogs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

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
        
        public ArrayList viewLogsAdmin()
        {
            ArrayList name = new ArrayList();
            try
            {
                //opens DB Connection
                ConnectionFactoryLogs myFactory1 = ConnectionFactoryLogs.getFactory();
                Connection conn = myFactory1.getConnection();


                //include parameters
               

                //SQL Query
                PreparedStatement pstmt = conn.prepareStatement("select * from administrators order by A_DATE_TIME desc");

                ResultSet rs = pstmt.executeQuery();

                while(rs.next())
                    {
                        name.add(rs.getString("A_DATE_TIME"));
                        name.add(rs.getString("A_LOGS"));
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
        
         public ArrayList viewLogsCustomer()
        {
            ArrayList name = new ArrayList();
            try
            {
                //opens DB Connection
                ConnectionFactoryLogs myFactory1 = ConnectionFactoryLogs.getFactory();
                Connection conn = myFactory1.getConnection();


                //include parameters
               

                //SQL Query
                PreparedStatement pstmt = conn.prepareStatement("select * from customer order by DATE_TIME desc");

                ResultSet rs = pstmt.executeQuery();

                while(rs.next())
                    {
                        name.add(rs.getString("DATE_TIME"));
                        name.add(rs.getString("LOGS"));
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
         
          public ArrayList viewLogsFinancial()
        {
            ArrayList name = new ArrayList();
            try
            {
                //opens DB Connection
                ConnectionFactoryLogs myFactory1 = ConnectionFactoryLogs.getFactory();
                Connection conn = myFactory1.getConnection();


                //include parameters
               

                //SQL Query
                PreparedStatement pstmt = conn.prepareStatement("select * from financial order by F_DATE_TIME desc");

                ResultSet rs = pstmt.executeQuery();

                while(rs.next())
                    {
                        name.add(rs.getString("F_DATE_TIME"));
                        name.add(rs.getString("F_LOGS"));
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
          
           public ArrayList viewLogsProducts()
        {
            ArrayList name = new ArrayList();
            try
            {
                //opens DB Connection
                ConnectionFactoryLogs myFactory1 = ConnectionFactoryLogs.getFactory();
                Connection conn = myFactory1.getConnection();


                //include parameters
               

                //SQL Query
                PreparedStatement pstmt = conn.prepareStatement("select * from product order by P_DATE_TIME desc");

                ResultSet rs = pstmt.executeQuery();

                while(rs.next())
                    {
                        name.add(rs.getString("P_DATE_TIME"));
                        name.add(rs.getString("P_LOGS"));
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


