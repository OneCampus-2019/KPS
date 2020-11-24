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
public class Meanings {

    LinkedList wordmeaning=new LinkedList();
    LinkedList wordexample=new LinkedList();
    LinkedList wordreference=new LinkedList();
     public Meanings()
    {
    }
    public Meanings(String mean,String ex,String ref)
    {
        wordmeaning.add(mean);
        wordexample.add(ex);
        wordreference.add(ref);
    }
    public void setMeaning(String m)
    {
        wordmeaning.add(m);
    }
    public void setExample(String ex)
    {
        wordexample.add(ex);
    }
    public void setReference(String re)
    {
        wordreference.add(re);
    }
    public LinkedList getWordMeaning()
    {
        return wordmeaning;
    }
     public LinkedList getWordExample()
    {
        return wordexample;
    }
      public LinkedList getWordReference()
    {
        return wordreference;
    }
    public int getlastref()
            {
              int last=1;
              Connection con=null;
     try
        {
           
      con=DBconnect.dbconnect();
      String req="SELECT * FROM Mreferences ORDER BY rfid DESC LIMIT 1";    
      Statement st=con.createStatement();
      ResultSet result=st.executeQuery(req);
      while(result.next())
      {
        last=result.getInt("rfid");
      }
        }catch(Exception t)
        {
            t.printStackTrace();
        }
     DBconnect.connectclose(con);
     return last;
     
}   
            }
    
    
    
    
    
