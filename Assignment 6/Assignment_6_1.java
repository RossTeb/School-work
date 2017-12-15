 import java.util.Comparator;
 import java.lang.Comparable;
 
 public class Assignment_6_1<T extends Comparable<T>> implements Comparator<T>
 { 
   public static void main (String[] args)
   {  
      String[] words = {"a","b","c"};
      boolean ascending =false;
      Integer[] num = {7,6,3,1}; 
      Comparator<Integer> comp= new Assignment_6_1();
      Comparator<String> comp1= new Assignment_6_1();
      System.out.println("(7,6,3,1) is sorted in decending order?: "+ordered(num,comp,ascending));
      System.out.println("(a,b,c) is sorted in decending order?: "+ordered(words,comp1,ascending));
      ascending = true;
      System.out.println("(7,6,3,1) is sorted in ascending order?: "+ordered(num,comp,ascending));
      System.out.println("(a,b,c) is sorted in ascending order?: "+ordered(words,comp1,ascending));
   }
   
   @Override
   public int compare(T o1,T o2)
   {
       return (o1.compareTo(o2));
   }
   
   public static <E> boolean ordered(E[] list, Comparator<? super E> comp, boolean ascending)
   {
      if(ascending)
      {
         for(int i = 0;i < list.length-1; i++)
         {
            if(comp.compare(list[i],list[i+1])>0)
            {
               return false;
            }
         }
         
      }
      else
      {
         for(int i = 0;i < list.length-1; i++)
         {
            if(comp.compare(list[i],list[i+1])<0)
            {
               return false;
            }
         }
      }
      return true;
   }
}
