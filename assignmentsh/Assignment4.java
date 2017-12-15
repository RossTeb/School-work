import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Iterator;

public class Assignment4
{
   public static void main (String[] args)
   {
      Set one = new LinkedHashSet<String>();
      Set two = new LinkedHashSet<String>();
      one.add("George");
      one.add("Jim");
      one.add("Blake");
      one.add("Kevin");
      one.add("Micheal");
      two.add("George");
      two.add("Katie");
      two.add("Kevin");
      two.add("Michelle");
      two.add("Ryan");
      
      System.out.println(union(one,two).toString());
      //System.out.println(one.toString());
      System.out.println(intersection(one,two).toString());
      System.out.println(difference(one,two).toString());
      
   }
   
   public static Set<String> union(Set<String> set1, Set<String> set2)
   {
      Set result = new LinkedHashSet<String>();
      result.addAll(set1);
      result.addAll(set2);
      return result;
   }
   
   public static Set<String> intersection(Set<String> set1, Set<String> set2)
   {
      Iterator<String> iter = set1.iterator();
      String temp;
      Set result = new LinkedHashSet<String>();
      while(iter.hasNext())
      {
         temp = iter.next();
         if(set2.contains(temp))
         {
            result.add(temp);
         }
      }
      return result;
   }
   
      public static Set<String> difference(Set<String> set1, Set<String> set2)
   {
      Iterator<String> iter = set2.iterator();
      String temp;
      Set result = new LinkedHashSet<String>();
      result.addAll(set1);
      while(iter.hasNext())
      {
         temp = iter.next();
         if(result.contains(temp))
         {
            result.remove(temp);
         }
      }
      return result;
   }
   
}