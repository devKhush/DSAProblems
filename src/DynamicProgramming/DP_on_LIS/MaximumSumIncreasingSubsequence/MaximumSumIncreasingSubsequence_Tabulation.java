package DynamicProgramming.DP_on_LIS.MaximumSumIncreasingSubsequence;

// Pre-Requisite: Intuitive Tabulation of "Longest Increasing Subsequence (LIS)"
// https://practice.geeksforgeeks.org/problems/maximum-sum-increasing-subsequence4749/1

public class MaximumSumIncreasingSubsequence_Tabulation {
    /************************************* Another Tabulation Method ***********************************
     * This is an intuitive DP solution without memoization.
     * dp[i] -> indicates the sum of increasing Subsequence, by including nums[i] into the sub-sequence

     * Time Complexity: O(n*n)
        * Two loops
     * Space Complexity: O(n)
        * One DP Array
     */
    public int maxSumIS(int[] arr, int n)  {
        int[] dp = new int[n];
        int maxSum = 0;

        for (int i = 0; i < n; i++){
            dp[i] = arr[i];

            for (int prev = 0; prev < i; prev++){
                if (arr[prev] < arr[i]  &&  dp[i] < arr[i] + dp[prev]){
                    dp[i] = arr[i] + dp[prev];
                }
            }
            maxSum = Math.max(maxSum, dp[i]);
        }
        return maxSum;
    }
}
