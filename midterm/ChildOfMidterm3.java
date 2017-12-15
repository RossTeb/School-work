import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.Collections;

public class ChildOfMidterm3
{
   public static void main(String[] args)
   {
      List<String> test = new ArrayList();
      try
      {  
         test = countWords("students.txt");
      }
      catch (FileNotFoundException e)
		{
			System.out.println(args[0] + " is not found");
		}
		 catch (Exception e)
		{
			System.out.println("unknown error occurred - terminating");
		}
      System.out.println(test);
   }
   
   public static List<String> countWords(String fileName)throws FileNotFoundException
   {
      HashMap<String, Integer> wordCount = new HashMap<String, Integer>();
      Scanner input = new Scanner(new File(fileName));
      List<String> temp = new ArrayList<String>();
      String[] keys= new String[50];
      int count=0;
      String change ="";
      while (input.hasNext())
		{
         
			String line = input.nextLine();
			String[] tokens = line.split(", ");
         keys[count] = tokens[0];
         
         if(wordCount.containsKey(tokens[0]))
         {
            int value = wordCount.get(tokens[0]);
            wordCount.put(tokens[0],value+1);
         }
         else
         {
            wordCount.put(tokens[0],1);
         }
         count++;  
      }
      
      for(int i = 0;i< wordCount.size();i++)
      {
         change = wordCount.get(keys[i])+" " +keys[i];
         temp.add(change);
      }
      
      for(int i = temp.size()-1;i>=0;i--)
      {
         for(int j = 0; j < i;j++)
         { 
               if(temp.get(j).compareTo(temp.get(j+1))<0)
               {
           
                  String temp1=temp.get(j+1);
                  temp.set(j+1,temp.get(j));
                  temp.set(j,temp1); 
               }
          }
      } 
      
      
      input.close();
      return temp;
   }
}
