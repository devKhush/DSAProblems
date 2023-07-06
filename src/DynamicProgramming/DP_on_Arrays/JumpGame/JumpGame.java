package DynamicProgramming.DP_on_Arrays.JumpGame;

// https://leetcode.com/problems/jump-game/description/

public class JumpGame {
    /******************************************* Recursion ************************************
     * Intuition:
        * Basic recursion thinking
     * Time Complexity: exponential
     * Space Complexity: O(n)
        * stack space
     */
    public boolean canJump_rec(int[] nums) {
        return f(0, nums);
    }
    private boolean f(int i, int[] nums){
        if (i >= nums.length - 1)
            return true;
        if (i + nums[i] >= nums.length - 1)
            return true;

        for(int j = 1; j <= nums[i]; j++){
            if (f(i + j, nums))
                return true;
        }
        return false;
    }


    /******************************************* Memoization ************************************
     * Time Complexity: O(n)
        * 1 DP states of size n
     * Space Complexity: O(n)
        * Stack space + DP Array
     */
    public boolean canJump_memo(int[] nums) {
        Boolean[] dp = new Boolean[nums.length];
        return f(0, nums, dp);
    }
    private boolean f(int i, int[] nums, Boolean[] dp){
        if (i >= nums.length - 1)
            return true;
        if (i + nums[i] >= nums.length - 1)
            return dp[i] = true;

        if (dp[i] != null)
            return dp[i];

        for(int j = 1; j <= nums[i]; j++){
            if (f(i + j, nums, dp))
                return dp[i] = true;
        }
        return dp[i] = false;
    }


    /******************************************* Tabulation ************************************
     * Time Complexity: O(n)
        * 1 DP states of size n
     * Space Complexity: O(n)
        * DP Array
     */
    public boolean canJump(int[] nums) {
        int n = nums.length;

        // DP Array & Base case
        boolean[] dp = new boolean[n];
        dp[n - 1] = true;

        // Remaining parameters in opposite order to memoization
        for (int i = n - 2; i >= 0; i--){
            if (i + nums[i] >= nums.length - 1){
                dp[i] = true;
                continue;
            }
            for (int j = 1; j <= nums[i] ; j++){
                if (i + j >= n || dp[i + j]){
                    dp[i] = true;
                    break;
                }
            }
            // No need of dp[i] = false at end, it is false by default
        }
        return dp[0];
    }

}
