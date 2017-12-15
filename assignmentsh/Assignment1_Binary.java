import java.util.Scanner;

class Assignment1_Binary
{  
        public static void main(String args[])
        {
           int input; 
           Scanner scan = new Scanner(System.in);
           
           System.out.println("enter a number!");
           input = scan.nextInt();
           System.out.println(convertToBinary(input));
        }

        
        public static String convertToBinary(int number) 
        {         
            if (number <= 1)
            return Integer.toString(number);
            return convertToBinary(number/2) + Integer.toString(number%2);
        }
}
