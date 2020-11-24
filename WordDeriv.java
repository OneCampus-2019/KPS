/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kamusi_proj;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

/**
 *
 * @author HP
 */
public class WordDeriv {
    
   LinkedList derivtype=new LinkedList();
   LinkedList derivdef=new LinkedList();
   LinkedList allderiv=new LinkedList();
   LinkedList allderivtypes=new LinkedList();
    public WordDeriv()
   {
      
   }
   public WordDeriv(String a,String b)
   {
       derivtype.add(a);
          Connection con=null;
       try
        {
      con=DBconnect.dbconnect();
      String req="SELECT * FROM wordentries where word='"+b+"';";
      Statement st=con.createStatement();
      ResultSet result=st.executeQuery(req);
      while(result.next())
      {
          
      derivdef.add(result.getInt("wid"));
      }
        }catch(Exception t)
        {
            t.printStackTrace();
        }
       DBconnect.connectclose(con);
   }
   public void setderivtype(String t)
   {
       derivtype.add(t);
       
   }
    public void setderivdef(String def)
   {
       
      derivdef.add(def);
     
       
   }
    public LinkedList getderivtype()
    {
        return derivtype;
        
    }
     public LinkedList getderivdef()
    {
        return derivdef;
        
    }
     public  LinkedList allderiv()
     {
           try
        {
      Connection con=DBconnect.dbconnect();
      String r="SELECT * FROM derivtypes";
      Statement st=con.createStatement();
      ResultSet result=st.executeQuery(r);
      while(result.next())
      {
          
      allderivtypes.add(result.getString("type"));
      }
        }catch(Exception t)
        {
            t.printStackTrace();
        }
           return allderivtypes;
      
    }
     
     }
    

