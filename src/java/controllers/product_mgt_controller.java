/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import classes.products;
import dbconnection.ConnectionFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JM
 */
public class product_mgt_controller extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
      
        ArrayList ResultCatcher = new ArrayList();
        HttpSession session = request.getSession(false);
        
        try
        {
      
        session.setAttribute("Success",null);
        

            String ManageType = null;
            
            if(session.getAttribute("DefaultPN")!=null) 
            {
                ManageType = (String)session.getAttribute("DefaultPN");
                //out.println(ManageType);
            }
            
            /*
            if(session.getAttribute("SignalNew")!=null)
            {
                ManageType = (String)request.getParameter("ProdType");
                out.println("New Entrant:" + ManageType);
                session.setAttribute("DefaultPN", ManageType);
                session.setAttribute("SignalNew",null);
            }
            */
            
            if(session.getAttribute("loggedIn_prod")!=null)
            {
                if(session.getAttribute("mgr_pos").toString().contentEquals("B-DM"))
                {
                    ManageType = "DVD";
                }
                if(session.getAttribute("mgr_pos").toString().contentEquals("B-ACM"))
                {
                    ManageType = "CD";
                }
                if(session.getAttribute("mgr_pos").toString().contentEquals("B-BM"))
                {
                    ManageType = "Book";
                }
                if(session.getAttribute("mgr_pos").toString().contentEquals("B-MM"))
                {
                    ManageType = "Magazine";
                }
                
             
            }
            
            
            ConnectionFactory myFactory = ConnectionFactory.getFactory();
            Connection conn = myFactory.getConnection();
            
          if(request.getParameter("ChangeRole")!=null)
          {
             request.getRequestDispatcher("CentralProdMgr.jsp").forward(request,response); 
          }
         
          if(request.getParameter("ExitSystem")!=null)
          {
            session.setMaxInactiveInterval(10);
            request.getRequestDispatcher("index.jsp").forward(request,response);
          }
          
          if("Modify".equals(request.getParameter("Operation"))&&request.getParameter("choice")!=null)
            {
              String newPID = null,newPType = null, newPTitle=null,newPSyn=null,newPPrice=null;
              
              String getSelectedUpdated = request.getParameter("choice");
              PreparedStatement UpdaterFetcher = conn.prepareStatement("SELECT * FROM PRODUCTS WHERE PROD_ID = ?");
              
              UpdaterFetcher.setString(1,""+getSelectedUpdated+"");
              
              ResultSet GenThrower = UpdaterFetcher.executeQuery();
              
              while(GenThrower.next())
              {
                  newPID = GenThrower.getString("prod_id");
                  newPType = GenThrower.getString("prod_type");
                  newPTitle = GenThrower.getString("prod_title");
                  newPSyn= GenThrower.getString("prod_syn");
                  newPPrice = GenThrower.getString("prod_price");
                  
              }
              out.println(""+newPID+""+newPType+""+newPTitle+""+newPSyn+""+newPPrice);
              
              session.setAttribute("PortaltoEdit",true);
              session.setAttribute("EDnewPID",newPID);
              session.setAttribute("EDnewPType",newPType);
              session.setAttribute("EDnewPTitle",newPTitle);
              session.setAttribute("EDnewPSyn",newPSyn);
              session.setAttribute("EDnewPPrice",newPPrice);

            }
          else
          {
               
              session.setAttribute("PortaltoEdit",null);
              /*
              session.setAttribute("EDnewPID",null);
              session.setAttribute("EDnewPType",null);
              session.setAttribute("EDnewPTitle",null);
              session.setAttribute("EDnewPSyn",null);
              session.setAttribute("EDnewPPrice",null);
              * 
              */
   
          }
        
            //out.println(request.getParameter("Operation"));
            
            
           if("Delete".equals(request.getParameter("Operation"))&&request.getParameter("choice")!=null)
           {
                String getSelectedRadio = request.getParameter("choice");
                products PrepDelete = new products();
                PrepDelete.setProd_id(getSelectedRadio);
                
                boolean EradicateItem = new productmanagement().DeleteProduct(PrepDelete);
                
                if(EradicateItem == true)
                {
                  session.setAttribute("Eradicate", true);
                  System.out.println("Delete success");
                }
                else
                {
                  session.setAttribute("FailDel",true);
                  System.out.println("Delete fail");
                }
           }
            
           if(request.getParameter("UpdateDetails")!=null)
           {
          //   out.println("Potential update");  
               out.println("Error!");
                out.println("<a href = 'CentralProdMgr.jsp'>Back</a>");
              products UpdateProducts = new products();  
             
             String StayingIDType = session.getAttribute("EDnewPID").toString();
             String UpdtdProdType = session.getAttribute("EDnewPType").toString();
             
          //   out.println(session.getAttribute("EDnewPType").toString());
             
             String UpdtdProdRecName = request.getParameter("GetPTitle");
             String UpdtdProdRecPrice = request.getParameter("GetPPrice");
             
             StringBuffer text = new StringBuffer(request.getParameter("GetPSyn"));
 
                int loc = (new String(text)).indexOf('\n');
                while(loc > 0)
                {
                text.replace(loc, loc+1, "<BR>");
                 loc = (new String(text)).indexOf('\n');
                
                    
                }
              String UpdtdSynopsis = text.toString();
            
              float floatPrice = new Float(UpdtdProdRecPrice);
              
              UpdateProducts.setProd_id(StayingIDType);
              UpdateProducts.setProd_price(floatPrice);
              UpdateProducts.setProd_title(UpdtdProdRecName);
              UpdateProducts.setProd_type(UpdtdProdType);
              UpdateProducts.setProd_syn(UpdtdSynopsis);
            
              System.out.println(StayingIDType+","+floatPrice+","+UpdtdProdRecName+","+UpdtdProdType+","+UpdtdSynopsis);
              
              boolean PerformUpdate = new productmanagement().UpdateProduct(UpdateProducts);
              
              if(PerformUpdate==true)
              {
                  System.out.println("update was successful");
                  session.setAttribute("FlagUpdate",true);
              }
              else
              {
                 
              }
           }
            
            
            if(request.getParameter("AddProds")!=null)
            { 
                out.println("Error!");
                out.println("<a href = 'CentralProdMgr.jsp'>Back</a>");
                String ManagerType = (String)session.getAttribute("ProductType");
                System.out.println("Product type is : " + ManagerType);
                products AddProduct = new products();
                
                
                String ProdID = request.getParameter("ProdID");
                String ProdRecName = request.getParameter("ProdName");
                String ProdRecPrice = request.getParameter("ProdPrice");
                StringBuffer text = new StringBuffer(request.getParameter("Synopsis"));
 
                int loc = (new String(text)).indexOf('\n');
                while(loc > 0)
                {
                text.replace(loc, loc+1, "<BR>");
                 loc = (new String(text)).indexOf('\n');
                
                    
                }
                
                AddProduct.setProd_id(ProdID);
                AddProduct.setProd_title(ProdRecName);
                
                Float Price = new Float(ProdRecPrice);
                AddProduct.setProd_price(Price);
                AddProduct.setProd_type(ManagerType);
                
                String Synopsis = text.toString();
                AddProduct.setProd_syn(Synopsis);
                
                System.out.println("Product type is : " + ProdID);
                System.out.println("Product type is : " + ProdRecName);
                System.out.println("Product type is : " + Price);
                System.out.println("Product type is : " + ManagerType);
                System.out.println("Product type is : " + Synopsis);
                
                
                //out.println(text); 
                
                boolean execute = new productmanagement().AddProduct(AddProduct);
                
                if(execute==true)
                {
                    System.out.println("Pasok");
                     session.setAttribute("Success",true);
                  //  ManageType = "DVD";
                }
                else
                {
                    //session.setAttribute("",);
                }
               
            }//by DB adding
            
            
   
              PreparedStatement CentralCatcher = conn.prepareStatement("SELECT prod_id, prod_type, prod_title, prod_syn, prod_price  FROM PRODUCTS WHERE PROD_TYPE = ? and isDeleted = 0");
              CentralCatcher.setString(1,""+ManageType+"");
              
              ResultSet GenerateRS = CentralCatcher.executeQuery();
             
              int i=0;
              
              while(GenerateRS.next())
              {
                  ResultCatcher.add(GenerateRS.getString("prod_id"));
                  ResultCatcher.add(GenerateRS.getString("prod_type"));
                  ResultCatcher.add(GenerateRS.getString("prod_title"));
                  ResultCatcher.add(GenerateRS.getString("prod_syn"));
                  ResultCatcher.add(GenerateRS.getString("prod_price"));
                //  i++;
              }
              
        
              //System.out.println("Rows:"+i);
              
            /*
             * TODO output your page here. You may use following sample code.
             
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet product_mgt_controller</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet product_mgt_controller at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");*/
//              conn.close();
            session.setAttribute("ProductName",ManageType);
           // request.setAttribute("ProdType", ManageType); 
            session.setAttribute("ArrayPacks", ResultCatcher);
            request.setAttribute("ArrayPacks",ResultCatcher);
            request.getRequestDispatcher("CentralProdMgr.jsp").forward(request,response);
            
            if(request.getAttribute("PassProdType")!=null)
            {
                out.println("has laman");
            }
            
           
            
    
                
        } 
        catch(SQLException ex)
        {            
         System.out.println(ex.getMessage());   
        }
        finally
        {
            out.close();
        }
           
      
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(product_mgt_controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(product_mgt_controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
