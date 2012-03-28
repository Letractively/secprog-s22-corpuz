/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author arvin
 */
class ConnectionFactoryImplLogs extends ConnectionFactoryLogs {

    public ConnectionFactoryImplLogs(String driver, String url, String username, String password) 
    {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public Connection getConnection() {
         try{
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url,username,password);

            return conn;
        }
        catch(SQLException ex){}
        catch(ClassNotFoundException ex){}
        return null;
    }
    
}
