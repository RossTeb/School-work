import java.util.ArrayList;
import java.util.List;

public class Assignment_6_2
{
   private static int []a;

    public static void main(String[] args) 
    {
        int size = 0;
        int count = 0;
        long startTime;
        long endTime;
        long [][] results = new long[6][6];
        while(count<6)
        {
        
           
           size = size + 50000;
           System.out.println("size of array: "+size+"\n");
           
           startTime = System.nanoTime();
           System.out.println("\nArray to be sorted: "); 
           a = getArray(size);
           printArray(a);
           SelectionSort(a);
           System.out.println("Selection Sort: ");
           printArray(a);
           System.out.println();
           endTime = System.nanoTime();
           System.out.println("calculation time: " + (endTime - startTime)/1000000 + " ms");
           results[count][0] = (endTime - startTime)/1000000;           
           
           startTime = System.nanoTime();
           System.out.println("\nArray to be sorted: "); 
           a = getArray(size);
           printArray(a);
           bubbleSort(a);
           System.out.println("Bubble Sort: ");
           printArray(a);
           endTime = System.nanoTime();
           System.out.println("calculation time: " + (endTime - startTime)/1000000 + " ms");
           results[count][1] = (endTime - startTime)/1000000;   
           
           startTime = System.nanoTime();
           System.out.println("\nArray to be sorted: ");
           a = getArray(size);
           printArray(a);
           sortMerge();
           System.out.println("Merge Sort: ");
           printArray(a);
           endTime = System.nanoTime();
           System.out.println("calculation time: " + (endTime - startTime)/1000000 + " ms");
           results[count][2] = (endTime - startTime)/1000000;   
           
           startTime = System.nanoTime();
           System.out.println("\nArray to be sorted: "); 
           a = getArray(size);
           printArray(a);
           sortQuick();
           System.out.println("Quick Sort: ");
           printArray(a);
           endTime = System.nanoTime();
           System.out.println("calculation time: " + (endTime - startTime)/1000000 + " ms");
           results[count][3] = (endTime - startTime)/1000000;   
           
           startTime = System.nanoTime();
           System.out.println("\nArray to be sorted: "); 
           a = getArray(size);
           printArray(a);
           sortHeap(a);
           System.out.println("Heap Sort: ");
           printArray(a);
           endTime = System.nanoTime();
           System.out.println("calculation time: " + (endTime - startTime)/1000000 + " ms");
           results[count][4] = (endTime - startTime)/1000000;     
            
           startTime = System.nanoTime();
           System.out.println("\nArray to be sorted: "); 
           a = getArray(size);
           printArray(a);
           radixSort(a);
           System.out.println("Radix Sort: ");
           printArray(a);
           System.out.println();
           endTime = System.nanoTime();
           System.out.println("calculation time: " + (endTime - startTime)/1000000 + " ms");
           results[count][5] = (endTime - startTime)/1000000;   
          
           count++;
       }
       printArray2(results);
    }
    
    public static int[] getArray(int size)
    {
        
        int []array = new int[size];
        int item = 0;
        for(int i=0;i<size;i++)
        {
            item = (int)(Math.random()*1000); 
            array[i] = item;
        }
        return array;
    }

    public static void printArray(int []array)
    {
        for(int i : array)
        {
            System.out.print(i+" ");
        }
        System.out.println();
    }
    
    public static void printArray2(long matrix[][]) 
    {
       for (int row = 0; row < matrix.length; row++) 
       {
           for (int column = 0; column < matrix[row].length; column++) 
           {
               System.out.print(matrix[row][column] + " ms ");
           }
           System.out.println();
       }
    }

    public static void SelectionSort (int[] arr)
      {
         
        for (int i = 0; i < arr.length - 1; i++)
        {
            int index = i;
            for (int j = i + 1; j < arr.length; j++)
                if (arr[j] < arr[index])
                    index = j;
      
            int smallerNumber = arr[index]; 
            arr[index] = arr[i];
            arr[i] = smallerNumber;
        }
    }


    public static void bubbleSort(int[] list)
    {
        for(int i=0; i<list.length; i++)
        {
            for(int j=i + 1; j<list.length; j++)
            {
                if(list[i] > list[j])
                {
                    int temp = list[i];
                    list[i] = list[j];
                    list[j] = temp;
                }
            }
        }
    }
     
    public static void sortMerge()
    {
       int []tempArray = new int[a.length];
        mergeSort(tempArray,0,a.length-1);
    }

    public static void mergeSort(int []tempArray,int lowerIndex,int upperIndex)
    {
        if(lowerIndex == upperIndex)
        {
            return;
        }
        else
        {
            int mid = (lowerIndex+upperIndex)/2;
            mergeSort(tempArray, lowerIndex, mid);
            mergeSort(tempArray, mid+1, upperIndex);
            merge(tempArray,lowerIndex,mid+1,upperIndex);
        }
    }
     
    public static void merge(int []tempArray,int lowerIndexCursor,int higerIndex,int upperIndex)
    {
        int tempIndex=0;
        int lowerIndex = lowerIndexCursor;
        int midIndex = higerIndex-1;
        int totalItems = upperIndex-lowerIndex+1;
        while(lowerIndex <= midIndex && higerIndex <= upperIndex)
        {
            if(a[lowerIndex] < a[higerIndex])
            {
                tempArray[tempIndex++] = a[lowerIndex++];
            }
            else
            {
                tempArray[tempIndex++] = a[higerIndex++];
            }
        }
   
        while(lowerIndex <= midIndex)
        {
            tempArray[tempIndex++] = a[lowerIndex++];
        }

       
        while(higerIndex <= upperIndex)
        {
            tempArray[tempIndex++] = a[higerIndex++];
        }
         
        for(int i=0;i<totalItems;i++)
        {
            a[lowerIndexCursor+i] = tempArray[i];
        }
    }

    private static int N;
    public static void sortHeap(int arr[])
    {       
        heapify(arr);        
        for (int i = N; i > 0; i--)
        {
            swap(arr,0, i);
            N = N-1;
            maxheap(arr, 0);
        }
    }     
       
    public static void heapify(int arr[])
    {
        N = arr.length-1;
        for (int i = N/2; i >= 0; i--)
            maxheap(arr, i);        
    }
       
    public static void maxheap(int arr[], int i)
    { 
        int left = 2*i ;
        int right = 2*i + 1;
        int max = i;
        if (left <= N && arr[left] > arr[i])
            max = left;
        if (right <= N && arr[right] > arr[max])        
            max = right;
 
        if (max != i)
        {
            swap(arr, i, max);
            maxheap(arr, max);
        }
    }    

    public static void swap(int arr[], int i, int j)
    {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp; 
    } 
   
   public static void sortQuick()
   {
        int left = 0;
        int right = a.length-1;   
        quickSort(left, right);
    }

    private static void quickSort(int left,int right)
    {
        if(left >= right)
            return;
            
        int pivot = a[right];
        int partition = partition(left, right, pivot);

        quickSort(0, partition-1);
        
        quickSort(partition+1, right);

    }

    private static int partition(int left,int right,int pivot){
        int leftCursor = left-1;
        int rightCursor = right;
        while(leftCursor < rightCursor)
        {
                while(a[++leftCursor] < pivot);
                {
                   while(rightCursor > 0 && a[--rightCursor] > pivot);
                   {
                     if(leftCursor >= rightCursor)
                     {
                         break;
                     }
                     else
                     {
                         swap(a,leftCursor, rightCursor);
                     }
                   }
                }
        }
        swap(a,leftCursor, right);
        return leftCursor;
    }

   
    public static void radixSort(int[] input) 
    {
        final int RADIX = 10;

        List<Integer>[] bucket = new ArrayList[RADIX];
        for (int i = 0; i < bucket.length; i++) 
        {
            bucket[i] = new ArrayList<Integer>();
        }
       
        boolean maxLength = false;
        int tmp = -1, placement = 1;
        while (!maxLength) 
        {
          maxLength = true;
          for (Integer i : input) 
          {
            tmp = i / placement;
            bucket[tmp % RADIX].add(i);
            if (maxLength && tmp > 0) 
            {
              maxLength = false;
            }
          }
          int a = 0;
          for (int b = 0; b < RADIX; b++) 
          {
            for (Integer i : bucket[b]) 
            {
              input[a++] = i;
            }
            bucket[b].clear();
          }
          placement *= RADIX;
        }
   }
}
