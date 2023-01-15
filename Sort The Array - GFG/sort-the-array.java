//{ Driver Code Starts
//Initial Template for Java
import java.io.*;
import java.util.*; 


class GFG{
    public static void main(String args[]) throws IOException { 
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        
        while(t-- > 0){
            int n = sc.nextInt(); 
            int arr[] = new int[n];
            for (int i = 0; i < n; ++i)
            {
                arr[i] = sc.nextInt();
            }
            Solution obj = new Solution();
            int []ans= obj.sortArr(arr, n);
            for(int i=0;i<n;i++)
            {
                System.out.print(ans[i]+" ");
            }
            System.out.println();
        }
        
    }
}
// } Driver Code Ends


//User function Template for Java
class Solution { 
    int[] sortArr(int[] arr, int n) { 
        // code here
        quickSort(0, n - 1, arr);
        return arr;
    }
    
    private static int partition(int low, int high, int[] arr){
        int i = low + 1, j = high;
        int pivot = arr[low];

        while (i <= j){
            while (i <= high && arr[i] <= pivot)
                i++;
            while (j > low && arr[j] > pivot)
                j--;
            if (i < j){
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        arr[low] = arr[j];
        arr[j] = pivot;
        return j;
    }

    private static void quickSort(int low ,int high, int[] arr){
        if (low >= high)
            return;
        int partitionIndex = partition(low, high, arr);
        quickSort(low, partitionIndex - 1, arr);
        quickSort(partitionIndex + 1, high, arr);
    }
} 