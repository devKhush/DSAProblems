class Solution {
    // Space Optimization ****************************************************************
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        
        int costTwoStepBehind = 0;
        int costOneStepBehind = 0;
        
        for (int i = 2; i <= n; i++){
            int currentCostTwoStepBehind = costTwoStepBehind + cost[i - 2];
            int currentCostOneStepBehind = costOneStepBehind + cost[i - 1];
            
            int costToReachCurrentIndex = Math.min(currentCostTwoStepBehind, currentCostOneStepBehind);
            
            costTwoStepBehind = costOneStepBehind;
            costOneStepBehind = costToReachCurrentIndex;
        }
        
        int costToReachEndOfArray = costOneStepBehind;
        return costToReachEndOfArray;
    }
    
    
    // TABULATION ****************************************************************************
    public int minCostClimbingStairs_Tabulation(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        
        dp[0] = dp[1] = 0;
        
        for (int i = 2; i <= n; i++){
            int costTwoStepBehind = dp[i - 2] + cost[i - 2];
            int costOneStepBehind = dp[i - 1] + cost[i - 1];
            
            dp[i] = Math.min(costTwoStepBehind, costOneStepBehind);
        }
        return dp[n];
    }
    
    
    // MEMOIZATION ***************************************************************************
    public int minCostClimbingStairs_Memoization(int[] cost) {
        int n = cost.length;
        
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        
        return climbStairs(n, cost, dp);
    }
    
    public int climbStairs(int index, int[] cost, int[] dp){
        if (index == 0 || index == 1)
            return dp[index] = 0;
        
        if (dp[index] != -1)
            return dp[index];
        
        int costTwoStepBehind = Integer.MAX_VALUE;
        if (index >= 2)
            costTwoStepBehind = climbStairs(index - 2, cost, dp) + cost[index - 2];
        
        int costOneStepBehind = Integer.MAX_VALUE;
        if (index >= 1)
            costOneStepBehind = climbStairs(index - 1, cost, dp) + cost[index - 1];
        
        return dp[index] = Math.min(costTwoStepBehind, costOneStepBehind);
    }
    
    
    // RECURSION ***************************************************************************
    public int climbStairs_Recursion(int index, int[] cost){
        if (index == 0 || index == 1)
            return 0;
        
        int costTwoStepBehind = Integer.MAX_VALUE;
        if (index >= 2)
            costTwoStepBehind = climbStairs_Recursion(index - 2, cost) + cost[index - 2];
        
        int costOneStepBehind = Integer.MAX_VALUE;
        if (index >= 1)
            costOneStepBehind = climbStairs_Recursion(index - 1, cost) + cost[index - 1];
        
        return Math.min(costTwoStepBehind, costOneStepBehind);
    }
}