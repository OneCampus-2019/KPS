/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kamusi_proj;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

/**
 *
 * @author HP
 */
public class WordExpression {
    LinkedList wexpression=new LinkedList();
    LinkedList wexptype=new LinkedList();
    LinkedList wexpmeaning=new LinkedList();
    LinkedList allexptypes=new LinkedList();
     public WordExpression()
    {
       
    }
    public WordExpression(String wex,String extype,String expmeaning)
    {
        wexpression.add(wex);
        wexpmeaning.add(expmeaning);
        Connection con=null;
       try
        {
      con=DBconnect.dbconnect();
      String req="SELECT * FROM exptypes where exptype='"+extype+"';";
      Statement st=con.createStatement();
      ResultSet result=st.executeQuery(req);
      while(result.next())
      {
          
      wexptype.add(result.getInt("exptid"));
      }
        }catch(Exception t)
        {
            t.printStackTrace();
        }
       DBconnect.connectclose(con);
    }
    public void setwexpression(String wex)
    {
       wexpression.add(wex); 
    }
    public void setwexptype(String wext)
    {
       wexptype.add(wext); 
    }
    public void setwexpmeaning(String wexm)
    {
       wexpmeaning.add(wexm); 
    }
     public LinkedList getwexpression()
    {
       return wexpression; 
    }
    public LinkedList getwexptype()
    {
       return wexptype; 
    }
    public LinkedList getwexpmeaning()
    {
       return wexpmeaning;
    }
    public void allexp()
    {
        try
        {
      Connection con=DBconnect.dbconnect();
      String req="SELECT * FROM exptypes";
      Statement st=con.createStatement();
      ResultSet result=st.executeQuery(req);
      while(result.next())
      {    
      allexptypes.add(result.getString("exptype"));
      }
        }catch(Exception t)
        {
            t.printStackTrace();
        }
      
    }
    public LinkedList getAllexptypes()
    {
        return allexptypes;
    }
    
}
