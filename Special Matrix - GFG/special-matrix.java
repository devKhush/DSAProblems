// { Driver Code Starts
//Initial Template for Java

import java.util.*;
import java.lang.*;
import java.io.*;
class GFG
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        while(T-->0)
        {
            String[] s = br.readLine().trim().split(" ");
            int n = Integer.parseInt(s[0]);
            int m = Integer.parseInt(s[1]);
            int k = Integer.parseInt(s[2]);
            int[][] blocked_cells = new int[k][2];
            for(int i = 0; i < k; i++){
                String[] s1 = br.readLine().trim().split(" ");
                for(int j = 0; j < 2; j++){
                    blocked_cells[i][j] = Integer.parseInt(s1[j]);
                }
            }
            Solution obj = new Solution();
            int ans = obj.FindWays(n, m, blocked_cells);
            System.out.println(ans);

        }
    }
}
// } Driver Code Ends


//User function Template for Java

class Solution{
    public int FindWays(int m, int n, int[][] blocked){
        int[][] dp = new int[m][n];
        for (int[] row : dp)
            Arrays.fill(row, -1);
        return totalWays(m-1, n-1, blocked, dp) % 1000000007;
    }
    
    public int totalWays(int i, int j, int[][] blocked, int[][] dp){
        if (i < 0 || j < 0)
            return 0;
            
        for (int[] cell: blocked){
            if (cell[0]-1 == i && cell[1]-1 == j)
                return 0;
        }
            
        if (dp[i][j] != -1)
            return dp[i][j];
        
        
        if (i == 0 && j == 0)
            return dp[0][0] = 1;
            
        return dp[i][j] = totalWays(i-1, j, blocked, dp) + totalWays(i, j-1, blocked, dp);
        
    }
}
