package DynamicProgramming.DP_on_Arrays.PartitionTwoEqualSubsetSum;

// PRE_REQUISITE: 'Contains subset sum equal to K'
// https://www.geeksforgeeks.org/partition-problem-dp-18/
// https://takeuforward.org/data-structure/partition-equal-subset-sum-dp-15/
// https://youtu.be/7win3dcgo3k
// https://leetcode.com/problems/partition-equal-subset-sum/description/

public class PartitionTwoEqualSubsetSum {
    /*********************************** Solution *****************************
     * Time Complexity: O(n * sum/2) + O(n)  =  O(n * sum) + O(n)
        * n -> size of array
        * sum -> sum of values in array
        * O(n * sum/2) for finding subset sum equal to sum/2
        * O(n) to find sum of all values

     * Space Complexity: O(n * sum/2) = O(n * sum)
     * We can space optimized this solution too.
     */
    public boolean canPartition(int[] arr) {
        int n = arr.length;
        int sum = 0;
        for (int val : arr)
            sum += val;

        if (sum % 2 == 1)
            return false;

        return containsSubsetEqualToK(arr, sum/2, n);
    }

    // Same Tabulation solution for "Contains subset sum equal to 'k'"
    private boolean containsSubsetEqualToK(int[] arr, int k, int n){
        boolean[][] dp = new boolean[n][k + 1];

        // Base case
        for (int i = 0; i < n; i++)
            dp[i][0] = true;
        if (arr[0] <= k)
            dp[0][arr[0]] = true;

        for (int i = 1; i < n; i++){
            for (int target = 0; target <= k; target++){
                boolean take = arr[i] <= target ? dp[i - 1][target - arr[i]] : false;
                boolean notTake = dp[i - 1][target];
                dp[i][target] = take || notTake;
            }
        }
        return dp[n - 1][k];
    }
}
