/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dbconnection;
import java.sql.Connection;
import java.util.ResourceBundle;
/**
 *
 * @author arvin
 */
public abstract class ConnectionFactoryLogs {
    public static String driver, url, username, password;

        public static ConnectionFactoryImplLogs getFactory()
        {

         ResourceBundle settings = ResourceBundle.getBundle("logsfoobar");
         driver = settings.getString("driver");
         url = settings.getString("url");
         username = settings.getString("username");
         password = settings.getString("password");

         return new ConnectionFactoryImplLogs(driver,url,username,password);

        }

        public abstract Connection getConnection();
}
