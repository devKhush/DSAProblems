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
	   // Integer[][] dp = new Integer[n][amount + 1];
	   // int minCoins =  f(n - 1, amount, coins, n, dp);
	   // return minCoins != (int)1e9 ? minCoins : -1; 
	   
	   int[][] dp = new int[n][amount + 1];
	   for (int amt = 0; amt <= amount; amt++)
	        dp[0][amt] = amt % coins[0] == 0 ? amt / coins[0] : (int)1e9;
	        
	   for (int i = 1; i < n; i++){
	       for (int amt = 1; amt <= amount; amt++){
	           int take = amt >= coins[i] ? 1 + dp[i][amt - coins[i]] : (int)1e9;
	           int notTake = 0 + dp[i - 1][amt];
	           
	           dp[i][amt] = Math.min(take, notTake);
	       }
	   }
	   return dp[n - 1][amount] != (int)1e9 ? dp[n-1][amount] : -1;
	} 
	
	
	private int f(int i, int amount, int[] coins , int n, Integer[][] dp){
	    if (amount == 0)
	        return 0;
	    if (i == 0)
	        return amount % coins[0] == 0 ? amount / coins[0] : (int)1e9;
	    if (dp[i][amount] != null)
	        return dp[i][amount];
	    
	    int take = (int)1e9;
	    if (amount >= coins[i])
	        take = 1 + f(i, amount - coins[i], coins, n, dp);
	    int notTake = 0 + f(i - 1, amount, coins, n, dp);
	    
	    return dp[i][amount] = Math.min(take, notTake);
	}
}