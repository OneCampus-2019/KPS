/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kamusi_proj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class DBconnect {
    public static Connection dbconnect()
    {
        Connection con=null;
        try
        { 
          String url="jdbc:sqlite:kamusiDB.db";
          con=(Connection) DriverManager.getConnection(url);
          
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
           
        }
        return con;
    }
    public static void connectclose(Connection t)
    {
      try {
                t.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBconnect.class.getName()).log(Level.SEVERE, null, ex);
            }   
    }
    
}
