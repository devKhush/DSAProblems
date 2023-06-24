package DynamicProgramming.DP_on_Arrays.ContainsSubsetSumEqualToK;

// https://youtu.be/fWX9xDmIzRI
// https://takeuforward.org/data-structure/subset-sum-equal-to-target-dp-14/
// https://www.geeksforgeeks.org/subset-sum-problem-dp-25/

public class ContainsSubsetSumEqualToK_SpaceOptimized {
    /**************************** Space Optimized Solution ****************************
    // Time Complexity: O(N*K)  Reason: There are three nested loops
    // Space Complexity: O(K)    Reason: We are using an external array of size ‘K+1’ to store only one row.

    // Total no. of unique states/sub-problems = no. of changing parameters (target * n here)
    // Total no. of unique states/sub-problems = Size of DP array = No. of 'for loops' for iteration
    // Space optimized if we see something as 'xyz - 1' in DP array
     */
    private boolean subsetSumToK(int n, int targetSum, int[] arr){
        // DP array which is space optimized
        boolean[] dp = new boolean[targetSum + 1];

        // Base case for index == 0
        if (arr[0] <= targetSum)
            dp[arr[0]] = true;

        // Base case for target == 0
        dp[0] = true;

        // Other cases
        // At each index 'index' we determine whether we can obtain a 'target' subset sum.
        for (int i = 1; i < n; i++) {
            boolean[] nextDP = new boolean[targetSum + 1];
            nextDP[0] = true; // Base case for target == 0

            for (int target = 0; target <= targetSum; target++) {
                boolean foundByTake = arr[i] <= target ? dp[target - arr[i]] : false;
                boolean foundByNotTake = dp[target];

                nextDP[target] = foundByTake || foundByNotTake;
            }
            dp = nextDP;
        }
        // Answer for dp[n-1][targetSum] is stored in this position
        return dp[targetSum];
    }



    /**************************** Single 1D Array Space Optimized Solution ****************************
     * In above solution we had two 1D DP array
     * But we can do the same using only one 1D DP array.

     * Time Complexity: O(n * sum)  Reason: There are three nested loops
     * Space Complexity: O(sum)    Reason: We are using an external array of size ‘sum+1’ to store only one row.

     // Total no. of unique states/sub-problems = no. of changing parameters (target * n here)
     // Total no. of unique states/sub-problems = Dimension of DP array = No. of 'for loops' for iteration
     // Space optimized if we see something as 'xyz - 1' in DP array
     */

    private boolean containsSubsetSum(int[] nums, int n, int targetSum){
        // Single DP Array
        boolean[] dp = new boolean[targetSum + 1];

        // Two base cases
        dp[0] = true;
        if (nums[0] <= targetSum)
            dp[nums[0]] = true;

        // Two states
        for (int i = 1; i < n; i++){
            for (int target = targetSum; target >= 0; target--){
                boolean take = target >= nums[i] ? dp[target - nums[i]] : false;
                boolean notTake = dp[target];
                dp[target] = take || notTake;
            }
        }
        return dp[targetSum];
    }
}