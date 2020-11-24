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
import static kamusi_proj.DBconnect.dbconnect;

/**
 *
 * @author HP
 */
public class Word {
LinkedList word=new LinkedList(); 
LinkedList wordtypes=new LinkedList();
LinkedList defwordtypes=new LinkedList();
LinkedList newword=new LinkedList();
Meanings meaning=new Meanings(); 
WordForms Wordforms=new WordForms();
WordDeriv deriv=new WordDeriv();
WordExpression expp=new WordExpression();
WordSection sect;
public Word()
{
  
}
public Word(String w,Meanings m,WordForms wf,WordDeriv der,WordExpression we,WordSection sec)
{
  word.add(w); 
  meaning=m;
  Wordforms=wf;
  deriv=der;
  expp=we;
  sect=sec;
}

public void setmeaning(Meanings m)
{
    meaning=m;
}
public void setform(WordForms f)
{
  Wordforms=f;  
}
public void setDeriv(WordDeriv d)
{
    deriv=d;
}
public void setexpression(WordExpression x)
{
   expp=x; 
}
public void setSection(WordSection s)
{
    sect=s;
}
public void setWord(String w)
{
 newword.add(w);   
}
public LinkedList getword()
{
    return word;
}
public int getlastWord()
{
     int last=1;
     try
        {
           
      Connection con=DBconnect.dbconnect();
      String req="SELECT * FROM wordentries ORDER BY wid DESC LIMIT 1";    
      Statement st=con.createStatement();
      ResultSet result=st.executeQuery(req);
      while(result.next())
      {
        last=result.getInt("wid");
      }
        }catch(Exception t)
        {
            t.printStackTrace();
        }
     return last;
}
public void setwtype(String w)
{
      try
        {
      Connection con=DBconnect.dbconnect();
      String req="SELECT * FROM wtypes where type='"+w+"';";
      Statement st=con.createStatement();
      ResultSet result=st.executeQuery(req);
      while(result.next())
      {
        defwordtypes.add(result.getInt("tid"));  
      }
        }catch(Exception t)
        {
            t.printStackTrace();
        }
    
}
public LinkedList allwordtypes()
{
      try
        {
      Connection con=DBconnect.dbconnect();
      String re="SELECT * FROM wtypes";
      Statement st=con.createStatement();
      ResultSet result=st.executeQuery(re);
      while(result.next())
      {
          
      wordtypes.add(result.getString("type"));
      }
        }catch(Exception t)
        {
            t.printStackTrace();
        }
      return wordtypes;
      
    }

public LinkedList allwords()
{
     try
        {
      Connection con=DBconnect.dbconnect();
      String ree="SELECT * FROM wordentries";
      Statement st=con.createStatement();
      ResultSet result=st.executeQuery(ree);
      while(result.next())
      {
          
      word.add(result.getString("word"));
      }
        }catch(Exception t)
        {
            t.printStackTrace();
        }
      return word; 
}
public static boolean is_defined(String w)
{
    boolean res=false;
    Connection con=null;
    try
        {
      con=DBconnect.dbconnect();
      String l="SELECT * FROM wordentries where word='"+w+"';";
      Statement st=con.createStatement();
      ResultSet result2=st.executeQuery(l);
      //System.out.println(result2.next());
      if(result2.next())res=true;
      else res=false; 
     
        }catch(Exception t)
        {
            t.printStackTrace();
        } 
    DBconnect.connectclose(con);
    return res;
    
}
public boolean commitEntry()
{
  
   
    LinkedList m=meaning.getWordMeaning();
    LinkedList exam=meaning.getWordExample();
    LinkedList ref=meaning.getWordReference();
    LinkedList forms=Wordforms.getFormdesc();
    LinkedList ftype=Wordforms.getFormtype();
    LinkedList der=deriv.getderivdef();
    LinkedList dert=deriv.getderivtype();
    LinkedList wexp=new LinkedList();
    LinkedList wexptype=new LinkedList();;
    LinkedList wexpmeaning=new LinkedList();;
    LinkedList deforms=Wordforms.getFormtype();
    LinkedList formdesc=Wordforms.getFormdesc();
    LinkedList wordref=meaning.getWordReference();
    LinkedList wmean=meaning.getWordMeaning();
    LinkedList wexam=meaning.getWordExample();
    LinkedList derit=new LinkedList();
    LinkedList derivdef=new LinkedList();
    boolean inserted=false;
    
    //word entry first
    Connection con=null;
     try
     {
      con=DBconnect.dbconnect();
      Statement st=con.createStatement();
     
      for(int z=0;z<newword.size();z++)
      {
         
      String req="insert into wordentries values(null,'"+newword.get(z)+"','"+defwordtypes.get(z)+"','kirundi','20/5/2019',1);";
      //String req2="delete from wordentries;";
      st.executeUpdate(req);
          }
      //inserting word forms;
       for(int z=0;z<deforms.size();z++)
      {
         
      String reqn="insert into wform values(null,'"+deforms.get(z)+"','"+getlastWord()+"','"+formdesc.get(z)+"');";
      st.executeUpdate(reqn);
          }
      //word=new LinkedList();
      //wordtypes=new LinkedList();
      
      //inserting reference
      
      for(int b=0;b<deforms.size();b++)
      {
         
      String reqr="insert into Mreferences values(null,'"+wordref.get(b)+"');";
      st.executeUpdate(reqr);
          }
        
        //inserting Meaning
        
         for(int y=0;y<wmean.size();y++)
      {
         
      String reqr="insert into wmeanings values(null,'"+wmean.get(y)+"','"+wexam.get(y)+"','','"+getlastWord()+"','"+meaning.getlastref()+"');";
      st.executeUpdate(reqr);
          }
         
         //inserting derivation
        
         if(deriv!=null)
         {
          derit=deriv.getderivtype();
          derivdef=deriv.getderivdef();
      for(int q=0;q<derit.size();q++)
      {
         
      String reqr="insert into wordderiv values(null,'"+derit.get(q)+"','"+getlastWord()+"','"+derivdef.get(q)+"');";
      st.executeUpdate(reqr);
          } 
         }
         
         // inserting expression
         
          if(expp!=null)
         {
          wexp=expp.getwexpression();
          wexptype=expp.getwexptype();
          wexpmeaning=expp.getwexpmeaning();
      for(int q=0;q<wexp.size();q++)
      {
         
      String reqr="insert into expressions values(null,'"+wexptype.get(q)+"','"+wexp.get(q)+"','"+wexpmeaning.get(q)+"','"+getlastWord()+"');";
      st.executeUpdate(reqr);
          } 
         }
         
          DBconnect.connectclose(con);
            inserted=true;
        }catch(Exception t)
        {
            t.printStackTrace();
            inserted=false;
        }
       
 
    return inserted;
    }

   }
    
