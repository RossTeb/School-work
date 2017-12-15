import java.util.*;
import java.util.ArrayList;

class Assignment2_part3
{

	public static void main(String[] args)
	{
		try
		{
			ArrayList<Integer> data = new ArrayList<Integer>();
         data.add(1);
         data.add(9);
         data.add(4);
         data.add(8);
			Integer largest = max (data);
			System.out.println (largest);
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
	
	public static <E extends Comparable<E>> E max(ArrayList<E> list)
  	{
		if (list == null)
			throw new IllegalArgumentException ("null array argument");
		if (list.size() == 0)
			throw new IllegalArgumentException ("empty array argument");
		int location = 0;
		for (int i = 1; i < list.size(); i++)
			if (list.get(i).compareTo(list.get(location)) > 0)
				location = i;
		return list.get(location);
	}

}