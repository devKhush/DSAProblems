// { Driver Code Starts
// Initial Template for Java

import java.io.*;
import java.util.*;

  public class GFG {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T > 0) {
            int n = sc.nextInt();
            int arr[] = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }

            int key = sc.nextInt();
            Solution g = new Solution();
            System.out.println(g.binarysearch(arr, n, key));
            T--;
        }
    }
}
// } Driver Code Ends


// User function Template for Java

class Solution {
    int search(int[] arr, int low, int high, int val){
        if (low > high)
            return -1;
        int mid = (low + high)/2;
        
        if (arr[mid] == val)
            return mid;
        
        if (arr[mid] > val)
            return search(arr, low, mid-1, val);
        else
            return search(arr, mid+1, high, val);
    }
    
    int binarysearch(int arr[], int n, int k) {
        return search(arr, 0, n-1, k);
    }
}