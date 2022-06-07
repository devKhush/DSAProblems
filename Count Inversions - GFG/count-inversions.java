// { Driver Code Starts
//Initial Template for Java

import java.util.*;
import java.io.*;
import java.lang.*;

class Sorting
{
    public static void main (String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        long t = sc.nextLong();
        
        while(t-- > 0)
        {
            long n = sc.nextLong();
            long arr[] = new long[(int)n];
            
            for(long i = 0; i < n; i++)
             arr[(int)i] = sc.nextLong();
             
            System.out.println(new Solution().inversionCount(arr, n));
            
        }
    }
}
// } Driver Code Ends


//User function Template for Java

class Solution
{
    // arr[]: Input Array
    // N : Size of the Array arr[]
    //Function to count inversions in the array.
   
    public static long mergeAndSplitInversion(long[] arr, long[] temp, int low, int mid, int high){
        int i = low, j = mid+1, k = low;
        long inversionCount = 0;

        while(i <= mid  &&  j <= high){
            if (arr[i] <= arr[j]) {
                temp[k] = arr[i];
                i++;
            }
            else{
                inversionCount += mid + 1 - i;
                temp[k] = arr[j];
                j++;
            }
            k++;
        }

        while (i <= mid)
            temp[k++] = arr[i++];
        
        
        while (j <= high)
            temp[k++] = arr[j++];
        

        for (int index = low; index <=high; index++)
            arr[index] = temp[index];

        return inversionCount;
    }

    public static long sortAndCountInversion(long[] arr, long[] temp, int low, int high){
        if (low<high){
            int mid = (low+high)/2;
            long leftInversionCount = sortAndCountInversion(arr, temp, low, mid);
            long rightInversionCount = sortAndCountInversion(arr, temp, mid+1, high);
            long splitInversionCount = mergeAndSplitInversion(arr, temp, low, mid, high);
            return leftInversionCount + rightInversionCount + splitInversionCount;
        }
        return 0;
    }


    public static long inversionCount(long[] arr, long N) {
        return sortAndCountInversion(arr, new long[(int) N], 0, (int) (N-1));
    }



}