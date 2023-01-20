//{ Driver Code Starts
import java.util.*;
import java.io.*;
import java.lang.*;

class gfg
{
    public static void main(String args[])throws IOException
    {
        //reading input using BufferedReader class
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        
        //reading total testcases
        int t = Integer.parseInt(read.readLine());
        
        while(t-- > 0)
        {
            //reading number of elements and weight
            int n = Integer.parseInt(read.readLine());
            int w = Integer.parseInt(read.readLine());
            
            int val[] = new int[n];
            int wt[] = new int[n];
            
            String st[] = read.readLine().trim().split("\\s+");
            
            //inserting the values
            for(int i = 0; i < n; i++)
              val[i] = Integer.parseInt(st[i]);
             
            String s[] = read.readLine().trim().split("\\s+"); 
            
            //inserting the weigths
            for(int i = 0; i < n; i++)
              wt[i] = Integer.parseInt(s[i]);
              
            //calling method knapSack() of class Knapsack
            System.out.println(new Solution().knapSack(w, wt, val, n));
        }
    }
}




// } Driver Code Ends


class Solution { 
    static int knapSack(int maxWeight, int weights[], int values[], int n){ 
         int[][] dp = new int[n][maxWeight + 1];

        // Base case
        for (int wt = weights[0]; wt <= maxWeight; wt++) {
            dp[0][wt] = values[0];
        }

        // Remaining states/problems
        for (int i = 1; i < n; i++) {
            for (int wt = 0; wt <= maxWeight; wt++) {
                int notTakeIntoBag = dp[i - 1][wt];
                int takeIntoBag = wt >= weights[i] ? dp[i - 1][wt - weights[i]] + values[i] : 0;
                dp[i][wt] = Math.max(notTakeIntoBag, takeIntoBag);
            }
        }
        return dp[n - 1][maxWeight];
    } 
}


