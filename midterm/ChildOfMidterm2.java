import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class ChildOfMidterm2
{
   public static void main(String[] args)
   {
      List<String> names = new ArrayList<String>();
//      List<String> numbers = new ArrayList<String>();
      List<String> hours = new ArrayList<String>();
      List<String> gpa = new ArrayList<String>();
      try
      {  
         names = sort("students.txt",0);
//         numbers = sort("students.txt",1);
         hours = sort("students.txt",2);
         gpa = sort("students.txt",3);
      }
      catch (FileNotFoundException e)
		{
			System.out.println(args[0] + " is not found");
		}
		 catch (Exception e)
		{
			System.out.println("unknown error occurred - terminating");
		}
      for(int i = 0 ; i<names.size();i++)
      {
         System.out.print(names.get(i) +", "); 
      }
//       System.out.println("");
//       for(int i = 0 ; i<numbers.size();i++)
//       {
//       System.out.print(numbers.get(i) +", ");
//       }
      System.out.println("");
      for(int i = 0 ; i<hours.size();i++)
      {
         System.out.print(hours.get(i) +", ");
      }
      System.out.println("");
      for(int i = 0 ; i<gpa.size();i++)
      {
       System.out.print(gpa.get(i) +", ");
      }
      
   }
   
   public static List sort(String fileName, int option)throws FileNotFoundException
   {
      List<String> output = new ArrayList<String>();
      Scanner input = new Scanner(new File(fileName));
      while(input.hasNext())
      {
         String line = input.nextLine();
         String[] tokens = line.split(", ");
         output.add(tokens[option]); 
      } 
      for(int i = output.size()-1;i>=0;i--)
      {
         for(int j = 0; j < i;j++)
         { 
            if(option != 1 && option != 2){
               if(output.get(j).compareTo(output.get(j+1))>0)
               {
           
                  String temp=output.get(j+1);
                  output.set(j+1,output.get(j));
                  output.set(j,temp); 
            
               }
            }
            else if(option == 3) {
               Float temp1 = Float.parseFloat(output.get(j));
               Float temp2 = Float.parseFloat(output.get(j+1));
               if(temp1.compareTo(temp2)>0)
               {
           
                  String temp=output.get(j+1);
                  output.set(j+1,output.get(j));
                  output.set(j,temp); 
               
               } 

            }
            else {
               Integer temp1 = Integer.parseInt(output.get(j));
               Integer temp2 = Integer.parseInt(output.get(j+1));
               if(temp1.compareTo(temp2)>0)
               {
           
                  String temp=output.get(j+1);
                  output.set(j+1,output.get(j));
                  output.set(j,temp); 
               
               } 
            }
         }
      }
      return output; 
   }
}