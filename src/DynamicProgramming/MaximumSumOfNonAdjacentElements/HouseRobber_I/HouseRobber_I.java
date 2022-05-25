package DynamicProgramming.MaximumSumOfNonAdjacentElements.HouseRobber_I;

// Exactly same solution as Maximum Sum of non-adjacent array subsequence
// https://leetcode.com/problems/house-robber/

class HouseRobber_I {

    public int rob(int[] arr) {
        int n = arr.length;

        int[] dp = new int[n];
        dp[0] = arr[0];
        
        for (int i = 1; i < n; i++){
            
            int maxSumByPicking = arr[i];
            if (i-2 >= 0)
                maxSumByPicking += dp[i-2];
                
            int maxSumByNotPicking = 0 + dp[i-1];
            
            dp[i] = Math.max(maxSumByPicking, maxSumByNotPicking);
        }

        return dp[n-1];
    }
}