package DynamicProgramming.DP_on_Arrays.PartitionIntoTwoSubsetWithMinimumAbsoluteDifference;

// PRE_REQUISITE: 'Contains subset sum equal to K'
// https://youtu.be/GS_OqZb2CWc
// https://www.geeksforgeeks.org/partition-a-set-into-two-subsets-such-that-the-difference-of-subset-sums-is-minimum/
// https://takeuforward.org/data-structure/partition-set-into-2-subsets-with-min-absolute-sum-diff-dp-16/
// https://www.codingninjas.com/codestudio/problems/partition-a-set-into-two-subsets-such-that-the-difference-of-subset-sums-is-minimum_842494

public class TwoSubsetWithMinimumAbsoluteDifference {
    /*********************************** Solution *****************************
     * Time Complexity: O(n * sum) + O(n) + O(sum/2)  =  O(n * sum) + O(n) + O(sum)  =  O(n * sum)
     * n -> size of array
     * sum -> sum of values in array
     * O(n * sum) for finding subset sum equal to sum
     * O(n) to find sum of all values
     * O(sum/2) to find all subset values at the last index of array

     * Space Complexity: O(n * sum)
     * We can space optimized this solution too.
     */
    public static int minimumDifference(int[] arr) {
        int n = arr.length;
        int totalSum = 0;
        for (int val : arr)
            totalSum += val;

        // Same as Problem 'Contains subset sum equal to K'
        boolean[][] dp = new boolean[n][totalSum + 1];

        for (int i = 0; i < n; i++)             // base case
            dp[i][0] = true;
        if (arr[0] <= totalSum)
            dp[0][arr[0]] = true;

        for (int i = 1; i < n; i++) {           // Recursion states
            for (int target = 0; target <= totalSum; target++) {
                boolean take = arr[i] <= target ? dp[i - 1][target - arr[i]] : false;
                boolean notTake = dp[i - 1][target];
                dp[i][target] = take || notTake;
            }
        }

        // Finding minimum possible absolute difference sum
        int minAbsDiff = (int) 1e9;
        for (int sum1 = 0; sum1 <= totalSum / 2; sum1++) {
            if (dp[n - 1][sum1]) {
                int sum2 = totalSum - sum1;
                minAbsDiff = Math.min(minAbsDiff, Math.abs(sum2 - sum1));
            }
        }
        return minAbsDiff;
    }
}
