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
public class Dictionary {
    Word word;
    LinkedList allwords=new LinkedList();
    LinkedList sallwords=new LinkedList();
    LinkedList allwordtypes=new LinkedList();
    LinkedList allwordforms=new LinkedList();
    LinkedList wordids=new LinkedList();
    LinkedList swordids=new LinkedList();
    public Dictionary()
    {
     LoadAllwords();
    }
    public Dictionary(String z)
    {
     LoadAllwords(z);
     LoadAllwords();
    }
    public LinkedList getAllwords()
    {
        return allwords;
    }
    public LinkedList getsAllwords()
    {
        return sallwords;
    }
    public LinkedList getAllwordtypes()
    {
        return allwordtypes;
    }
    public LinkedList getAllwordforms()
    {
        return allwordforms;
    }
    public void LoadAllwords()
    {

    Connection con=null;
    try
        {
      con=DBconnect.dbconnect();
      
      //getting words
      String wordsql="SELECT * FROM wordentries ORDER BY word;";
      Statement st=con.createStatement();
      ResultSet resultb=st.executeQuery(wordsql);
      //System.out.println(result2.next());
      //int countme=0;
      while(resultb.next())
      {
          String w="<b>"+resultb.getString("word")+"</b>";
          allwords.add(w);
          wordids.add(resultb.getInt("wid"));
        
         
      }
        }catch(Exception t)
        {
            t.printStackTrace();
        } 
    DBconnect.connectclose(con);
    }
     public void LoadAllwords(String search)
    {

    Connection con=null;
    try
        {
      con=DBconnect.dbconnect();
      
      //getting words
      String wordsql="SELECT * FROM wordentries where word like '%"+search+"' or '%"+search+"%' or '"+search+"%' ORDER BY word;";
      Statement st=con.createStatement();
      ResultSet resultb=st.executeQuery(wordsql);
      //System.out.println(result2.next());
      //int countme=0;
      while(resultb.next())
      {
          String w="<b>"+resultb.getString("word")+"</b>";
          System.out.println(w);
          sallwords.add(w);
          swordids.add(resultb.getInt("wid"));
        
         
      }
        }catch(Exception t)
        {
            t.printStackTrace();
        } 
    DBconnect.connectclose(con);
    }
    public LinkedList getwordids()
    {
        return wordids;
    }
    public LinkedList getswordids()
    {
        return swordids;
    }
    public String[] loadAllderv(int x)
    {
       String derivs[]=new String[10];
       String dsymbol[]=new String[10];
       String derivdef[]=new String[10];
       
          //getting word types
        Connection con=null;
        try
        {
        con=DBconnect.dbconnect();
        String formsql="select*from wordentries,wordderiv,derivtypes where wordderiv.wid=wordentries.wid and wordderiv.derivtype=derivtypes.type and wordentries.wid='"+x+"';";
        Statement st=con.createStatement();
          
          
          //getting word forms
        
          ResultSet fresult=st.executeQuery(formsql);
          int count=0;
          while(fresult.next())
          {
              if(fresult.getString("derivword")!="")
              {
                     count++;
              dsymbol[count]="<font color=\"green\">"+fresult.getString("symbol")+"</font>";
              derivdef[count]="<b>"+fresult.getString("derivword")+"</b>";
              derivs[count]=dsymbol[count]+""+derivdef[count];
       
          }
           
          }
          
        }catch(Exception e)
        {
            e.printStackTrace();
        }
         //countme++;
         DBconnect.connectclose(con);
         return derivs;
         
    }
    
    public String getWordForm(int f)
    {
       String wordform="";
          //getting word types
        Connection con=null;
        try
        {
        con=DBconnect.dbconnect();
        String formsql="select*from forms,wform where forms.fid=wform.fid and wform.wid='"+f+"';";
        Statement st=con.createStatement();
          
          
          //getting word forms
        
          ResultSet fresult=st.executeQuery(formsql);
          while(fresult.next())
          {
              String symbol="<font color=\"green\">"+fresult.getString("fsymbol")+"</font>";
              String fdef="<b>"+fresult.getString("fdefinition")+"</b>";
              wordform=symbol+""+fdef;
       
          }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
         //countme++;
         DBconnect.connectclose(con);
         return wordform;
        
    }
    public String[] getAllexpressions(int ex)
    {
         String expression[]=new String[30];
         String symbol[]=new String[30];
         String express[]=new String[30];
         String xmean[]=new String[30];
          //getting word types
        Connection con=null;
        try
        {
        con=DBconnect.dbconnect();
        String expsql="select*from wordentries,expressions,exptypes where wordentries.wid=expressions.wid and expressions.exptype=exptypes.exptype and wordentries.wid='"+ex+"';";
        Statement st=con.createStatement();
          
          
          //getting word forms
        
          ResultSet fresult=st.executeQuery(expsql);
          int count=0;
          while(fresult.next())
          {
              if(fresult.getString("expression")!=""){
               count++;
              symbol[count]="<font color=\"green\">"+fresult.getString("expsymbol")+"</font>";
              express[count]="<b>"+fresult.getString("expression")+"</b>";
              xmean[count]="<b>"+fresult.getString("expmeaning")+"</b>";
              expression[count]=symbol[count]+""+express[count]+""+xmean[count];
            
          
          }
             
          }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
         //countme++;
         DBconnect.connectclose(con);
         return expression;
         
    }
    public String getWordType(int t)
    {
        String wordtype="";
          //getting word types
        Connection con=null;
        try
        {
      con=DBconnect.dbconnect();
      String typesql="select*from wordentries,wtypes where wordentries.w_type=wtypes.tid and wordentries.wid='"+t+"';"; 
      Statement st=con.createStatement();
       ResultSet typeresult=st.executeQuery(typesql);
       while(typeresult.next())
          {
             wordtype="<b><i>"+typeresult.getString("symbol")+"</i></b>";
            
          }
    }catch(Exception x)
    {
        x.printStackTrace();
    }
        return wordtype;
    }
    public void printSelectedWords(String keyword)
    {
        
    }
    public String[] fetchwordmeanings(int w)
    {
        Connection con=null;
        String[] m=new String[10];
        String[] example=new String[10];
        String meaning[]=new String[10];
        
         try
        {
          con=DBconnect.dbconnect();
      
      Statement st=con.createStatement();
      
          
         //getting word meanings
         
          String msql="select * from wmeanings,Mreferences,wordentries where wmeanings.rfid=Mreferences.rfid and wmeanings.wid=wordentries.wid and wmeanings.wid='"+w+"';";
          ResultSet mresult=st.executeQuery(msql);
          int count=0;
          while(mresult.next())
          { 
              if(mresult.getString("meaning")!=""){
                  
                  
               count++;
              
       
             
             if(mresult.getString("referenced").equals("noref")){
              m[count]="<i>"+mresult.getString("meaning")+"</i>";
             }else{m[count]="<i>"+mresult.getString("meaning")+"</i>"+"."+"<b>rab."+mresult.getString("referenced")+"</b>";
             
             }
             example[count]="<b>"+mresult.getString("example")+"</b>.";
                
             //System.out.println(count);
             meaning[count]=m[count]+":"+example[count];
             
             
            // count++;
             //System.out.println(count+mresult.getString("meaning"));
          }
        }
          }
        
       catch(Exception t)
        {
            t.printStackTrace();
        } 
    DBconnect.connectclose(con);
    return meaning;
}
}
   
