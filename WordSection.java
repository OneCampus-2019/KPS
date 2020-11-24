/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kamusi_proj;

import java.util.LinkedList;

/**
 *
 * @author HP
 */
public class WordSection {
    
    LinkedList sectitle;
    LinkedList secontent;
    
    public WordSection(String s, String t)
    {
        sectitle.add(s);
        secontent.add(t);
        
        
    }
    public void settitle(String title)
    {
        sectitle.add(title);
       
    }
     public void setcontent(String cont)
    {
        secontent.add(cont);
       
    }
     public LinkedList getsectitle()
     {
         return sectitle;
     }
     public LinkedList getcontent()
     {
         return secontent;
     }
    
    
}
