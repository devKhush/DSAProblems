//{ Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.*;
class GfG
{
    public static void main(String args[])
        {
            Scanner sc = new Scanner(System.in);
            int t = sc.nextInt();
            while(t-->0)
                {
                    int n = sc.nextInt();
                    int A[] = new int[n];
                    for(int i = 0;i<n;i++)
                        A[i] = sc.nextInt();
                    Solution ob = new Solution();
                    System.out.println(ob.minDifference(A,n));
                }
        }
}    
// } Driver Code Ends


//User function Template for Java

class Solution
{

	public int minDifference(int arr[], int n) 
	{ 
        int totalSum = 0;
        for (int val : arr)
            totalSum += val;

        // Same as Problem 'Contains subset sum equal to K'
        boolean[][] dp = new boolean[n][totalSum + 1];

        for (int i = 0; i < n; i++)             // base case
            dp[i][0] = true;
        if (arr[0] <= totalSum)
            dp[0][arr[0]] = true;

        for (int i = 1; i < n; i++) {           // Recursion states
            for (int target = 1; target <= totalSum; target++) {
                boolean take = arr[i] <= target ? dp[i - 1][target - arr[i]] : false;
                boolean notTake = dp[i - 1][target];
                dp[i][target] = take || notTake;
            }
        }

        // Finding minimum possible absolute difference sum
        int minAbsDiff = (int) 1e9;
        for (int sum1 = 0; sum1 <= totalSum / 2; sum1++) {
            if (dp[n - 1][sum1]) {
                int sum2 = totalSum - sum1;
                minAbsDiff = Math.min(minAbsDiff, Math.abs(sum2 - sum1));
            }
        }
        return minAbsDiff;
	} 
}
