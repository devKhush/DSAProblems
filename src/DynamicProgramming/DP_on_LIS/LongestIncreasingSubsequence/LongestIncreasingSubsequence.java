package DynamicProgramming.DP_on_LIS.LongestIncreasingSubsequence;

// Most Difficult DP Problem ever
// Very difficult to tabulate and space optimize
// https://takeuforward.org/data-structure/longest-increasing-subsequence-dp-41/
// https://takeuforward.org/data-structure/printing-longest-increasing-subsequence-dp-42/
// https://youtu.be/ekcwMsSIzVc
// https://youtu.be/IFfYfonAFGc
// https://leetcode.com/problems/longest-increasing-subsequence/description/

public class LongestIncreasingSubsequence {
    /****************************************** Recursion ****************************************
     * Intuition: Simple Recursion Thinking of Take and Not take
     * Time Complexity: O(2^n) ~ exponential
        * All subsequence will be generated
     * Space Complexity: O(n)
        * Recursion Stack Space
     */
    public int lengthOfLIS_recur(int[] nums) {
        int n = nums.length;

        // f(i, nextGreaterIndex) means LIS from nums[0...i] by
        // considering nums[j] to the next greater number after nums[i]
        // f(n-1, n) means LIS from nums[0...n-1] with next greater element as infinity (at n)
        return f(n - 1, n, nums);
    }
    private int f(int i, int nextGreaterIndex,int[] nums){
        if (i < 0)
            return 0;

        // Take into the Increasing subsequence (when the next element is inf. or greater)
        int take = 0;
        if (nextGreaterIndex == nums.length || nums[i] < nums[nextGreaterIndex])
            take = 1 + f(i - 1, i, nums);

        // Not take into the Increasing subsequence
        int notTake = 0 + f(i - 1, nextGreaterIndex, nums);

        // Return Maximum of Take and Not-Take
        return Math.max(take, notTake);
    }

    /************************************ Memoization ********************************************
     * Memoization of above solution
     * Time Complexity: O(n*n)
        * Only two changing states: i and next_smaller_index
     * Space Complexity: O(n*n) + O(N)
        * DP Array + Recursion stack space
     */
    public int lengthOfLIS_memo(int[] nums) {
        int n = nums.length;
        Integer[][] dp = new Integer[n][n + 1];

        return f(n - 1, n, nums, dp);
    }
    private int f(int i, int nextGreaterIndex, int[] nums, Integer[][] dp){
        if (i < 0)
            return 0;
        if (dp[i][nextGreaterIndex] != null)
            return dp[i][nextGreaterIndex];

        // Take into the Increasing subsequence (when the next element is inf. or greater)
        int take = 0;
        if (nextGreaterIndex == nums.length || nums[i] < nums[nextGreaterIndex])
            take = 1 + f(i - 1, i, nums, dp);

        // Not take into the Increasing subsequence
        int notTake = f(i - 1, nextGreaterIndex, nums, dp);

        // Return Maximum of Take and Not-Take
        return dp[i][nextGreaterIndex] = Math.max(take, notTake);
    }

    /*************************************** Tabulation *******************************************
     * Tabulation Solution of above memoization
     * Time Complexity: O(n*n)
        * Two states are changing, i and next_smaller_index
     * Space Complexity: O(n*n)
     */
    private int lengthOfLIS_tabu(int[] nums){
        int n = nums.length;

        // Apply shifting of indices to only Rows of dp[][] array to handle base case for "i<0"
        // Columns of DP Array is still 0-based indexed. Now, Base case becomes 0 for "i==0"
        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++){
            // Due to shifting of index, next greater index will start from i and not from i+1
            for (int nextGreaterIndex = i; nextGreaterIndex <= n; nextGreaterIndex++){
                // Due to shifting of index, dp[i-1][i] is stored in dp[i-1][i-1]
                int take = 0;
                if (nextGreaterIndex == n || nums[i - 1] < nums[nextGreaterIndex])
                    take = 1 + dp[i - 1][i - 1];

                int notTake = dp[i - 1][nextGreaterIndex];

                dp[i][nextGreaterIndex] = Math.max(take, notTake);
            }
        }
        return dp[n][n];
    }


    /*************************************** Space Optimization *******************************************
     * Space Optimization of above Tabulation
     * Time Complexity: O(n*n)
         * Two states are changing, i and next_smaller_index
     * Space Complexity: O(2*n)
        * Two DP Arrays
     */
    private int lengthOfLIS_space(int[] nums){
        int n = nums.length;
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++){
            int[] curr_DP = new int[n + 1];
            for (int nextGreaterIndex = i; nextGreaterIndex <= n; nextGreaterIndex++){
                // Take into subsequence
                int take = 0;
                if (nextGreaterIndex == n || nums[i - 1] < nums[nextGreaterIndex])
                    take = 1 + dp[i - 1];

                // Don't take into subsequence
                int notTake = dp[nextGreaterIndex];

                curr_DP[nextGreaterIndex] = Math.max(take, notTake);
            }
            dp = curr_DP;
        }
        return dp[n];
    }

    /********************************** Space Optimization to 1D-DP ***********************************
     * 1D Space Optimization of above Tabulation
     * To compute the current value DP Array, we need just "above value in previous row" (dp[i-1][nextGreaterIndex])
        and "value at index less than index i in previous row" (dp[i-1][i-1]).
     * So, we can use a single DP Array. We fill it in reverse direction.

     * Time Complexity: O(n*n)
        * Two states are changing, i and next_smaller_index
     * Space Complexity: O(n)
        * One DP Array
     */
    private int lengthOfLIS_space_1DDP(int[] nums){
        int n = nums.length;
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++){
            for (int nextGreaterIndex = n; nextGreaterIndex >= i; nextGreaterIndex--){
                int take = 0;
                if (nextGreaterIndex == n || nums[i - 1] < nums[nextGreaterIndex])
                    take = 1 + dp[i - 1];

                int notTake = dp[nextGreaterIndex];

                dp[nextGreaterIndex] = Math.max(take, notTake);
            }
        }
        return dp[n];
    }
}
