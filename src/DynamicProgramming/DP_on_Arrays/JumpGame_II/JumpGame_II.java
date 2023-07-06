package DynamicProgramming.DP_on_Arrays.JumpGame_II;

// Pre-requisite: "Jump Game - I"
// https://leetcode.com/problems/jump-game-ii/description/

public class JumpGame_II {
    /******************************************* Recursion ************************************
     * Intuition:
        * Basic recursion thinking
     * Time Complexity: exponential
     * Space Complexity: O(n)
        * stack space
     */
    public int jump_rec(int[] nums) {
        return f(0, nums);
    }

    private int f(int i, int[] nums){
        if (i >= nums.length - 1)
            return 0;
        if (i + nums[i] >= nums.length - 1)
            return 1;

        int minJumps = (int)1e9;
        for (int j = 1; j <= nums[i]; j++){
            minJumps = Math.min(minJumps, 1 + f(i + j, nums));
        }
        return minJumps;
    }

    /******************************************* Memoization ************************************
     * Time Complexity: O(n)
        * 1 DP states of size n
     * Space Complexity: O(n)
        * Stack space + DP Array
     */
    public int jump_memo(int[] nums) {
        Integer[] dp = new Integer[nums.length];
        return f(0, nums, dp);
    }
    private int f(int i, int[] nums, Integer[] dp){
        if (i >= nums.length - 1)
            return 0;
        if (i + nums[i] >= nums.length - 1)
            return 1;
        if (dp[i] != null)
            return dp[i];

        int minJumps = (int)1e9;
        for (int j = 1; j <= nums[i]; j++){
            minJumps = Math.min(minJumps, 1 + f(i + j, nums, dp));
        }
        return dp[i] = minJumps;
    }


    /******************************************* Tabulation ************************************
     * Time Complexity: O(n)
        * 1 DP states of size n
     * Space Complexity: O(n)
        * DP Array
     */
    public int jump(int[] nums) {
        int n = nums.length;

        int[] dp = new int[n];
        dp[n - 1] = 0;

        for (int i = n - 2; i >= 0; i--){
            if (i + nums[i] >= n - 1){
                dp[i] = 1;
                continue;
            }
            int minJumps = (int)1e9;
            for (int j = 1; j <= nums[i]; j++){
                minJumps = Math.min(minJumps, 1 + dp[i + j]);
            }
            dp[i] = minJumps;
        }
        return dp[0];
    }

}
