package DynamicProgramming.DP_on_Arrays.CheckIfThereIsValidPartitionForArray;

// https://leetcode.com/problems/check-if-there-is-a-valid-partition-for-the-array/description/

public class CheckIfThereIsValidPartitionForArray {
    /************************************** Memoization ****************************************
     * Intuition: Recursion + Memoization
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public boolean validPartition__memo(int[] nums) {
        int n = nums.length;
        Boolean[] dp = new Boolean[n];
        return f(n-1, nums, dp);
    }

    private boolean f(int i, int[] nums, Boolean[] dp){
        if (i < 0)
            return true;
        if (dp[i] != null)
            return dp[i];

        boolean equal2 = false, equal3 = false, increasing3 = false;
        if (i > 0 && nums[i] == nums[i-1])
            equal2 = f(i-2, nums, dp);
        if (i > 1 && nums[i] == nums[i-1] && nums[i] == nums[i-2])
            equal3 = f(i-3, nums, dp);
        if (i > 1 && nums[i] == nums[i-1] + 1 && nums[i-1] == nums[i-2] + 1)
            increasing3 = f(i-3, nums, dp);
        return dp[i] = equal2 || equal3 || increasing3;
    }


    /*************************************** Tabulation *********************************************
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public boolean validPartition__tabu(int[] nums) {
        int n = nums.length;
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        for (int i = 1; i <= n; i++){
            int ind = i - 1;
            if (ind > 0  &&  nums[ind] == nums[ind - 1])
                dp[i] |= dp[i - 2];
            if (ind > 1 && nums[ind] == nums[ind-1] && nums[ind] == nums[ind-2])
                dp[i] |= dp[i - 3];
            if (ind > 1 && nums[ind] == nums[ind-1] + 1 && nums[ind-1] == nums[ind-2] + 1)
                dp[i] |= dp[i - 3];
        }
        return dp[n];
    }

    /*************************************** Space Optimized ******************************
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public boolean validPartition__space(int[] nums) {
        int n = nums.length;
        if (n <= 1)
            return false;
        boolean prev3 = true, prev2 = false, prev1 = nums[0] == nums[1];

        for (int i = 2; i < n; i++){
            boolean curr = false;
            if (i > 0  &&  nums[i] == nums[i - 1])
                curr |= prev2;
            if (i > 1 && nums[i] == nums[i-1] && nums[i] == nums[i-2])
                curr |= prev3;
            if (i > 1 && nums[i] == nums[i-1] + 1 && nums[i-1] == nums[i-2] + 1)
                curr |= prev3;
            prev3 = prev2;
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }
}
