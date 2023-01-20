//{ Driver Code Starts
//Initial Template for Java

import java.util.*;
import java.lang.*;
import java.io.*;

class GFG{
    public static void main(String args[]) throws IOException{
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        while(t-->0){
            int n=sc.nextInt();
            int d=sc.nextInt();
            
            int []arr=new int[n];
            for(int i=0;i<n;i++){
                arr[i]=sc.nextInt();
            }
            
            Solution obj=new Solution();
            int res=obj.countPartitions(n, d, arr);
            System.out.println(res);
        }
    }
}
// } Driver Code Ends


//User function Template for Java

//Back-end complete function Template for Java

class Solution{

    public int countPartitions(int n, int D, int arr[]){
        /*
         int totalSum = 0;
         for (int val : arr)
             totalSum += val;
         if (totalSum - D < 0 || (totalSum - D)%2 != 0)
             return 0;
         int[][] dp = new int[n][totalSum +1];
         for (int[] row : dp)
             Arrays.fill(row, -1);
         return f(n - 1, (totalSum - D)/2, arr, dp);
        */
        
        int totalSum = 0;
        for (int val : arr)
            totalSum += val;

        // Two condition for problem
        if (totalSum - D < 0 || (totalSum - D)%2 != 0)
            return 0;
        
        int[][] dp = new int[n][totalSum + 1];

        // Not-Take base case at 0th index
        dp[0][0] = 1;
        // Take base case at 0th index
        if (arr[0] <= totalSum)
            dp[0][arr[0]] += 1;

        for (int i = 1; i < n; i++){
            for (int target = 0; target <= totalSum; target++){
                int take = arr[i] <= target ? dp[i-1][target - arr[i]] : 0;
                int notTake = dp[i-1][target];
                dp[i][target] = (take + notTake) % (int)(1e9+7);
            }
        }
        // We search for target sum "(totalSum - D)/2"
        return dp[n-1][(totalSum - D)/2];
    }
    
    private static int f(int i, int target, int[] arr, int[][] dp){
        if (i < 0)
            return target == 0 ? 1 : 0;
        if (dp[i][target] != -1)
            return dp[i][target];

        int take = arr[i] <= target ? f(i - 1, target - arr[i], arr, dp) : 0;
        int notTake = f(i - 1, target, arr, dp);
        return dp[i][target] = (take + notTake) % (int)(1e9+7);
    }
}