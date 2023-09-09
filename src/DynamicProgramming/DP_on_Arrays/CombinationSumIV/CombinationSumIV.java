package DynamicProgramming.DP_on_Arrays.CombinationSumIV;

// https://leetcode.com/problems/combination-sum-iv/description/

public class CombinationSumIV {
    /************************************** Memoization ***********************************************
     * Time Complexity: O(target)
        * Recursion
        * 1 DP state
     * Space Complexity: O(target)
     */
    public int combinationSum4__memo(int[] nums, int target) {
        Integer[] dp = new Integer[target + 1];
        return f(target, nums, dp);
    }
    private int f(int target, int[] nums, Integer[] dp){
        if (target == 0)
            return 1;
        if (dp[target] != null)
            return dp[target];
        int ways = 0;
        for (int val : nums){
            if (target >= val){
                ways += f(target - val, nums, dp);
            }
        }
        return dp[target] = ways;
    }

    /************************************ Tabulation *******************************************
     * Time Complexity: O(target)
        * 1 DP state
     * Space Complexity: O(target)
     */
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int tar = 1; tar <= target; tar++){
            int ways = 0;
            for (int val : nums){
                if (tar >= val){
                    ways += dp[tar - val];
                }
            }
            dp[tar] = ways;
        }
        return dp[target];
    }
}
