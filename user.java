/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kamusi_proj;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author HP
 */
public class user {
    
static String username;
static String password;
public user(String a,String b)
{
    username=a;
    password=b;
}
public boolean userconnect()
{
   Connection con=null;
   boolean connected=false;
       try
        {
      con=DBconnect.dbconnect();
      String req="SELECT * FROM user where username='"+username+"' and password='"+password+"';";
      Statement st=con.createStatement();
      ResultSet result=st.executeQuery(req);
     if(result.next())
     {
         String login="update user set status='on' where username='"+username+"' and password='"+password+"';";
         st.executeUpdate(login);
         connected=true;  
     }
     else{
         connected=false;
     }
        }
       catch(Exception e)
       {
           e.printStackTrace();
       }
       DBconnect.connectclose(con);  

    return connected;
}
public static boolean userdisconnect()
{
    
return true;

}
}
