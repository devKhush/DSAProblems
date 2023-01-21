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
                    int v = sc.nextInt();
                    int m = sc.nextInt();
                    int coins[] = new int[m];
                    for(int i = 0;i<m;i++)
                        coins[i] = sc.nextInt();
                    Solution ob = new Solution();
                    System.out.println(ob.minCoins(coins, m, v));
                }
        }
}    
// } Driver Code Ends


class Solution{
	public int minCoins(int[] coins, int n, int amount) { 
        int[][] dp = new int[n][amount + 1];

        // base case
        for (int target = 0; target <= amount; target++)
            dp[0][target] = (target % coins[0] == 0) ? target / coins[0] : (int)1e9;

        // remaining case
        for (int i = 1; i < n; i++){
            for (int target = 0; target <= amount; target++){
                int notTakeCoin = dp[i - 1][target];
                int takeCoin = target >= coins[i] ? 1 + dp[i][target - coins[i]] : (int)1e9;

                dp[i][target] = Math.min(takeCoin, notTakeCoin);
            }
        }
        return dp[n - 1][amount] < 1e9 ? dp[n - 1][amount] : -1;
	} 
}