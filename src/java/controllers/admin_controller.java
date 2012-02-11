/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import classes.*;
import dbconnection.*;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Lyle
 */
public class admin_controller {
    
    public boolean addAccount(staff newStaff)
    {
        boolean result = false;
        
        try
        {
            //opens DB Connection
            ConnectionFactory myFactory = ConnectionFactory.getFactory();
            Connection conn = myFactory.getConnection();
            
             //include parameters
            int i = 1;
            
            //SQL Query
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO staff(staff_id, password, state, fname, lname, mname, email_add, address, position) VALUES(?,?,?,?,?,?,?,?,?)");
            
            pstmt.setString(i++, newStaff.getStaff_id());
            pstmt.setString(i++, newStaff.getPassword());
            pstmt.setString(i++, newStaff.getState());
            pstmt.setString(i++, newStaff.getFname());
            pstmt.setString(i++, newStaff.getLname());
            pstmt.setString(i++, newStaff.getMname());
            pstmt.setString(i++, newStaff.getEmail());
            pstmt.setString(i++, newStaff.getAddress());
            pstmt.setString(i++, newStaff.getPosition());
          
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
    
    public ArrayList getAccount(String Position)
    {
       // String[] name = new String[100];
        
        ArrayList name = new ArrayList();
        
       try
        {
            //opens DB Connection
            ConnectionFactory myFactory = ConnectionFactory.getFactory();
            Connection conn = myFactory.getConnection();
                       
            //SQL Query
            if(!(Position.contentEquals("Customer")))
            {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM staff where position = ?");
            
            pstmt.setString(1, Position);
            
            ResultSet rs = pstmt.executeQuery();

                while(rs.next())
                    {
                        name.add(rs.getString("staff_id"));
                    }
            
             //close DB connection
            conn.close();
            }
            
            if((Position.contentEquals("Customer")))
             {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM customer");
            
           
            
            ResultSet rs = pstmt.executeQuery();

                while(rs.next())
                    {
                        name.add(rs.getString("cust_id"));
                    }
            
             //close DB connection
            conn.close();
            }
                
            
        }
        catch(SQLException ex)
            {
              ex.printStackTrace();
            }
            return name;
        }
        
    public ArrayList checkAccountParameters(staff newStaff, String rePass)
    {
               
        ArrayList name = new ArrayList();
     //   boolean result = new admin_controller().searchAccount(newStaff);
        
        if(newStaff.getStaff_id().isEmpty() || newStaff.getPassword().isEmpty() || rePass.isEmpty() || newStaff.getEmail().isEmpty() || newStaff.getFname().isEmpty() || newStaff.getMname().isEmpty() || newStaff.getLname().isEmpty() || newStaff.getPosition().isEmpty())
            name.add("Please Supply ALL Fields.");
        if(!(newStaff.getPassword().contentEquals(rePass)))
            name.add("Two Password Do not Match.");
        if(!(newStaff.getEmail().matches(".*@.*..*")))
            name.add("Not a valid E-mail Address.");
        if(newStaff.getAddress().isEmpty())
            name.add("Please Supply Address Field.");
        if(newStaff.getFname().isEmpty())
            name.add("Please Supply First Name Field.");
        if(newStaff.getMname().isEmpty())
            name.add("Please Supply Middle Name Field.");
        if(newStaff.getLname().isEmpty())
            name.add("Please Supply Last Name Field.");
        if(!(newStaff.getPosition().contentEquals("A-AM") || newStaff.getPosition().contentEquals("B-ACM") ||  newStaff.getPosition().contentEquals("B-DM") || newStaff.getPosition().contentEquals("B-BM") || newStaff.getPosition().contentEquals("B-MM"))) 
            name.add("Position Input is not Valid.");
        if(searchAccount(newStaff) == true)
            name.add("Username is already Taken.");

        return name;
        }
    
     public ArrayList checkLockParameters(staff newStaff, String Position)
    {
               
        ArrayList name = new ArrayList();
     //   boolean result = new admin_controller().searchAccount(newStaff);
        
        if(newStaff.getStaff_id().isEmpty() || Position.isEmpty() || newStaff.getState().isEmpty())
            name.add("Please Supply ALL Fields.");
        if(!(Position.contentEquals("Accounting Manager") || Position.contentEquals("Audio CD Manager") || Position.contentEquals("Book Manager") || Position.contentEquals("DVD Manager") || Position.contentEquals("Magazine Manager") ||  Position.contentEquals("Customer"))) 
            name.add("Position Input is not Valid.");
        if(!(newStaff.getState().contentEquals("Lock") || newStaff.getState().contentEquals("UnLock") )) 
            name.add("State Input is not Valid.");
        if(searchAccount(newStaff) == false)
            name.add("Username is not exist.");

        return name;
        }
    
    public boolean updateState(staff newStaff, String positionName)
    {
        boolean result = false;

        try{
            //opens DB Connection
            ConnectionFactory myFactory = ConnectionFactory.getFactory();
            Connection conn = myFactory.getConnection();
            
            if(!(positionName.contentEquals("Customer")))
            {
            //include parameters
            int i = 1;
            
            //SQL Query
            PreparedStatement pstmt = conn.prepareStatement("UPDATE staff SET state = ? WHERE staff_id = ?");
         
            pstmt.setString(i++, newStaff.getState());
         
            pstmt.setString(i++, newStaff.getStaff_id());

            //execute query
            pstmt.executeUpdate();

            //close DB connection
            conn.close();

            result = true;
            }
            
             if(positionName.contentEquals("Customer"))
            {
                
                int stateNum = 0;
                
                if(newStaff.getState().contentEquals("Lock"))
                {
                    stateNum = 1;
                }
                
            //include parameters
            int i = 1;
            
            //SQL Query
            PreparedStatement pstmt = conn.prepareStatement("UPDATE customer SET state = ? WHERE cust_id = ?");
         
            pstmt.setInt(i++, stateNum);
         
            pstmt.setString(i++, newStaff.getStaff_id());

            //execute query
            pstmt.executeUpdate();

            //close DB connection
            conn.close();

            result = true;
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }

        return result;
    }
    
    public boolean searchAccount(staff newStaff)
    {
       // String[] name = new String[100];
        
        boolean result = false;
        
       try
        {
            //opens DB Connection
            ConnectionFactory myFactory = ConnectionFactory.getFactory();
            Connection conn = myFactory.getConnection();
                       
            //SQL Query
            PreparedStatement pstmt = conn.prepareStatement("SELECT staff_id FROM staff WHERE staff_id = ?");
            
            pstmt.setString(1, newStaff.getStaff_id());
            
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

