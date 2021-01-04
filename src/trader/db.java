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
public class db {
    
   /* Connection conn = null;
    public static Connection ConnectDB(){
             try{
           
          Class.forName("com.mysql.jdbc.Driver");
         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/traders","root","");
          return con;
            
        }catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, e);
            return null;
    }      
}*/
    
    
    Connection conn = null;

    
      public static Connection ConnectDB()
    {
        try
        {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/majid","admin","qwerty123");
         
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

