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
         shuffle(data);
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
	
	public static <E> void shuffle (ArrayList<E> list)
  	{
		if (list == null)
			throw new IllegalArgumentException ("null array argument");
		if (list.size() == 0)
			throw new IllegalArgumentException ("empty array argument");
      int[] a = new int[6];
		for (int i = 0; i < list.size()-1; i++)
      {
         //Get ArrayList[i] 
         E temp = list.get(i); 
         int index =(int)(Math.random() * list.size());
         //Store ArrayList[MAth.random()]
         System.out.println("Random index chosen: " + index);
         //Move ArrayList[i] to ArrayList[Math.random()]
         E temp1 = list.get(index);
         list.set(index,temp);
         //Move ArrayList[Math.random()] to ArrayList[i] 
         list.set(i,temp1); 
      }
	}

}