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
public abstract class ConnectionFactory {
    public static String driver, url, username, password;

        public static ConnectionFactory getFactory()
        {

         ResourceBundle settings = ResourceBundle.getBundle("FOOBAR");
         driver = settings.getString("driver");
         url = settings.getString("url");
         username = settings.getString("username");
         password = settings.getString("password");

         return new ConnectionFactoryImpl(driver,url,username,password);

        }

        public abstract Connection getConnection();
}
