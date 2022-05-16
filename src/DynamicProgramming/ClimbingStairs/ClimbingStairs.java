package DynamicProgramming.ClimbingStairs;

// https://youtu.be/mLfjzJsN8us
// https://takeuforward.org/data-structure/dynamic-programming-climbing-stairs/

public class ClimbingStairs {
    
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
    public int climbStairs(int n, int[] dp){
        if (n==1 || n==2)
            return n;

        if (dp[n] == 0)
            dp[n] = dp[n-1] + dp[n-2];
        return dp[n];
    }
    public int climbStairs_Memoization(int n){
        int[] dp = new int[n+1];
        return climbStairs(n, dp);
    }

}