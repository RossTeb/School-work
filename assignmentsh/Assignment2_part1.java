import java.util.*;
import java.util.ArrayList;

class Assignment2_part3
{


	public static void main(String[] args)
	{
		try
		{
			ArrayList<Integer> data = new ArrayList<Integer>();
         data.add(5);
         data.add(9);
         data.add(4);
         data.add(8);
         data.add(10);
         data.add(2);
         data = distinct(data);
         for(int i = 0; i <   data.size()-1; i++)
         {
			   System.out.print(data.get(i) + ",");
         }
         System.out.print(data.get(data.size()-1));
      }
		catch (IllegalArgumentException e)
		{
			System.out.println (e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println ("unknown error occurred - terminating");
		}
	}
	

   public static <E extends Comparable<E>> ArrayList<E> distinct (ArrayList<E> list) {
      
      
      //instantiate empty arraylist for temp
      ArrayList<E> temp = new ArrayList<E>();
      for (int i = 0; i < list.size()-1; i++)
         {
            //check if list[i] is in the temp list
            if(list.contains(temp.get(i)))
            else
            {
               temp.add(list.get(i));
            }
            //Add list[i] to temp if not already in it
            //If not in temp, print
            
         }
         return temp;
   }

}