package DynamicProgramming.DP_on_Arrays.TargetSum;
import java.util.Arrays;

// Pre-requisite: "Count partitions with given Difference"
// https://youtu.be/d6F4B5KxRo8
// https://takeuforward.org/data-structure/target-sum-dp-21/

public class TargetSum {
    /************************************** Memoization ***************************************
     * Based on the idea of "Count partitions with given Difference"
     * Observe that,
                   target +x -x  = target
     * From total sum of array, we extract the remaining 'sum - target' part, and divide them into
        equal +ve & -ve part to make their sum 0.
     * S1 ->   target + x
     * S2 ->   x
     * S1 - S2  =  Difference/target        ("Count partitions with given Difference")

     * Time Complexity: O(n * sum)
        * 2 states of problem
     * Space Complexity: O(n * sum) + O(sum)
        * DP array + recursion stack space
     */
    public static int targetSum(int[] arr, int target) {
        int n = arr.length;
        int sum = 0;
        for (int val : arr)
            sum += val;

        int[][] dp = new int[n][sum + 1];
        for (int[] row : dp)
            Arrays.fill(row, -1);

        // We need to make target +ve in case it is -ve, since sign can be -ve too
        // For eg, [1,1,1,1,1] and target is -5 or -3.
        if (target < 0) target *= -1;
        if ((sum - target < 0) || (sum - target) % 2 == 1)
            return 0;

        return countSubset(n - 1, arr, (sum - target)/2, dp);
    }

    private static int countSubset(int i, int[] arr, int target, int[][] dp) {
        if (i == -1)
            return target == 0 ? 1 : 0;

        if (dp[i][target] != -1)
            return dp[i][target];

        int take = target >= arr[i] ? countSubset(i - 1, arr, target - arr[i], dp) : 0;
        int notTake = countSubset(i - 1, arr, target, dp);
        return dp[i][target] = take + notTake;
    }


    /*************************************** Tabulation *******************************************
     * Time Complexity: O(n * sum)
        * 2 states of problem
     * Space Complexity: O(n * sum)
        * DP array
     */
    public int findTargetSumWays(int[] arr, int target) {
        int n = arr.length;
        int sum = 0;
        for (int val : arr)
            sum += val;

        // making target +ve
        if (target < 0)
            target *= -1;

        // edge case
        if ((sum - target < 0) || ((sum - target) % 2 == 1))
            return 0;

        // count subsets with Sum
        int[][] dp = new int[n][sum + 1];
        dp[0][0] = 1;
        if (arr[0] <= sum)
            dp[0][arr[0]] += 1;

        for (int i = 1; i < n; i++) {
            for (int tar = 0; tar <= sum; tar++) {
                int take = arr[i] <= tar ? dp[i - 1][tar - arr[i]] : 0;
                int notTake = dp[i - 1][tar];
                dp[i][tar] = take + notTake;
            }
        }
        return dp[n - 1][(sum - target) / 2];
    }


    /*************************************** Space optimization **************************************
     * Time Complexity: O(n * sum)
        * 2 states of problem
     * Space Complexity: O(sum)
        * 1-D DP array
     */
    public int findTargetSumWay(int[] arr, int target) {
        int n = arr.length;
        int sum = 0;
        for (int val : arr)
            sum += val;

        // making target +ve
        if (target < 0)
            target *= -1;

        // edge case
        if ((sum - target < 0) || ((sum - target) % 2 == 1))
            return 0;

        // count subsets with Sum
        int[] dp = new int[sum + 1];
        dp[0] = 1;
        if (arr[0] <= sum)
            dp[arr[0]] += 1;

        for (int i = 1; i < n; i++) {
            int[] tempDP = new int[sum + 1];
            for (int tar = 0; tar <= sum; tar++) {
                int take = arr[i] <= tar ? dp[tar - arr[i]] : 0;
                int notTake = dp[tar];
                tempDP[tar] = take + notTake;
            }
            dp = tempDP;
        }
        return dp[(sum - target) / 2];
    }
}