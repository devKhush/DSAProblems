class Solution {
    
    // DP via Tabulation
    public int climbStairs_Tabulation(int n) {
        if (n==1 || n==2)
            return n;
        
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        
        for (int i=3; i<=n; i++)
            dp[i] = dp[i-2] + dp[i-1];
        return dp[n];
    }
    
    
    // DP via memoization
    public int climbStairs_Memoization(int n, int[] dp){
        if (n==1 || n==2)
            return n;

        if (dp[n] == 0)
            dp[n] = climbStairs_Memoization(n-1, dp) + climbStairs_Memoization(n-2, dp);
        return dp[n];
    }
    public int climbStairs(int n){
        int[] dp = new int[n+1];
        return climbStairs_Memoization(n, dp);
    }
}