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
public class WordForms {
    LinkedList formdesc=new LinkedList();
    LinkedList formtype=new LinkedList();
    LinkedList allforms=new LinkedList();
    LinkedList formsymbol=new LinkedList();
    LinkedList asymbol=new LinkedList();
    
    public WordForms()
    {
   
    }
    
    public WordForms(String form,String type)
    {
     formdesc.add(form);
     Connection con=null;
       try
        {
      con=DBconnect.dbconnect();
      String req="SELECT * FROM forms where ftype='"+type+"';";
      Statement st=con.createStatement();
      ResultSet result=st.executeQuery(req);
      while(result.next())
      {
          
      formtype.add(result.getInt("fid"));
      }
        }catch(Exception t)
        {
            t.printStackTrace();
        }
       DBconnect.connectclose(con);
    }
    public String getsymbol(String s)
    {
        String sym=null;
        try
        {
      Connection con=DBconnect.dbconnect();
      String req1="SELECT * FROM forms where ftype='"+s+"';";
      Statement st=con.createStatement();
      ResultSet result=st.executeQuery(req1);
      while(result.next())
      {
          
      sym=result.getString("fsymbol");
      }
        }catch(Exception t)
        {
            t.printStackTrace();
        }
        return sym;
        
    }
    public void setform(String fm)
    {
        formdesc.add(fm);
    }
    public void setformType(String type)
    {
         try
        {
      Connection con=DBconnect.dbconnect();
      String req="SELECT * FROM forms where ftype='"+type+"';";
      Statement st=con.createStatement();
      ResultSet result=st.executeQuery(req);
      while(result.next())
      {
          
      formtype.add(result.getInt("fid"));
      }
        }catch(Exception t)
        {
            t.printStackTrace();
        }
        
    }
    public LinkedList getFormdesc()
    {
        return formdesc;
    }
    public LinkedList getFormtype()
    {
        return formtype;
    }
    public void allforms() throws SQLException
    {
        try
        {
      Connection con=DBconnect.dbconnect();
      String req="SELECT * FROM forms";
      Statement st=con.createStatement();
      ResultSet result=st.executeQuery(req);
      while(result.next())
      {
          
      allforms.add(result.getString("ftype"));
      formsymbol.add(result.getString("fsymbol"));
      }
        }catch(Exception t)
        {
            t.printStackTrace();
        }
      
    }
    public LinkedList getallforms()
    {
        return allforms;
    }
    public LinkedList getfsymbols()
    {
        return formsymbol;
    }
    
}
