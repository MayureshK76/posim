/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trader;
import java.io.File;
import java.nio.file.Path;
import java.sql.*;
import javax.swing.*;
import javax.swing.JOptionPane;
/**
 *
 * @author sony
 */
public class db1 {
    
    Connection conn = null;
    /*public static Connection java_db()
    {
        try
        {
            String drivername = "oracle.jdbc.driver.OracleDriver";
            Class.forName(drivername);
            String serverName =  "localhost";
            String serverPort = "1522";
            String sid = "orcl2";
            String url = "jdbc:oracle:thin:@" +serverName+":"+serverPort+":"+sid;
            String username = "user1";
            String password = "321321";
            Connection conn = DriverManager.getConnection(url,username,password);
            System.out.println("Successfully connected to database");
            return conn;
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
            return null;
        }
    }*/
    
      public static Connection java_db2()
    {
        try
        {
            String drivername = "org.sqlite.JDBC";
            Class.forName(drivername);
            /*File fObj = new File("employeepayroll");
            Path path = Paths.get(fObj.class.getResource(".").toURI());
            System.out.println(path.getParent());               // <-- Parent directory
            //System.out.println(path.getParent().getParent());*/   // <-- Parent of parent directory
            String currentDir=System.getProperty("user.dir");
            Connection conn = DriverManager.getConnection("JDBC:sqlite:"+currentDir+"\\Database\\majid.sqlite");
            //Connection conn = DriverManager.getConnection("JDBC:sqlite:\\Database\\user.sqlite");
            System.out.println("Successfully connected to database");
            return conn;
           
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
            return null;
        }
    }
}
