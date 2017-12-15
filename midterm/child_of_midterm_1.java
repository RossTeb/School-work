import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;

public class child_of_midterm_1
{
   public static void main (String[] args)
   {
      List<String> holder = new ArrayList();
      try
      { 
         holder = countWords("file1.txt");
      }
      catch (FileNotFoundException e)
		{
			System.out.println(args[0] + " is not found");
		}
		 catch (Exception e)
		{
			System.out.println("unknown error occurred - terminating");
		}
      System.out.print("These are The Distinct authors: ");
      System.out.print(holder);
      
      
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
         keys[count] = tokens[1];
         
         if(wordCount.containsKey(tokens[1]))
         {
            int value = wordCount.get(tokens[1]);
            wordCount.put(tokens[1],value+1);
         }
         else
         {
            wordCount.put(tokens[1],1);
         }
         count++;  
      }
      
      for(int i = 0;i< wordCount.size();i++)
      {
            if(wordCount.get(keys[i]) == 1)
               temp.add(keys[i]);

      }
      return temp;
   }
}