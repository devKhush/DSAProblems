package DynamicProgramming.DP_on_LIS.MaximumSumIncreasingSubsequence;

// Pre-Requisite: Longest Increasing Subsequence (LIS)
// https://practice.geeksforgeeks.org/problems/maximum-sum-increasing-subsequence4749/1

public class MaximumSumIncreasingSubsequence {
    /***************************************** Memoization *********************************************
     * Intuition:
        * Try out all possible SubSequences
        * Binary Search won't work here, bcoz it might happen smaller sum is associated with the longest subsequence
     * Time Complexity: O(n^2)
        * two states, each of n size
     * Space Complexity: O(n^2) + O(n)
        * n^2 for DP Array
        * n for stack space
     */
    public int maxSumIS_memo(int[] arr, int n)  {
        Integer[][] dp = new Integer[n][n + 1];
        return f(n -1, n, arr, dp);
    }

    private int f(int i, int nextGreater, int[] arr, Integer[][] dp){
        if (i < 0)
            return 0;
        if (dp[i][nextGreater] != null)
            return dp[i][nextGreater];

        int take = nextGreater == arr.length || arr[i] < arr[nextGreater] ? arr[i] + f(i - 1, i, arr, dp) : 0;
        int notTake = f(i - 1, nextGreater, arr, dp);
        return dp[i][nextGreater] = Math.max(take, notTake);
    }


    /************************************** Tabulation *********************************************
     * Tabulation of above memoization solution
     * Time Complexity: O(n^2)
        * two loops (for two states)
     * Space Complexity: O(n^2)
        * DP array
     */
    public int maxSumIS_tabu(int[] arr, int n)  {
        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++){
            for (int nextGreater = i; nextGreater <= n; nextGreater++){
                int take = nextGreater == n || arr[i-1] < arr[nextGreater] ? arr[i - 1] + dp[i-1][i-1] : 0;
                int notTake = dp[i - 1][nextGreater];
                dp[i][nextGreater] = Math.max(take, notTake);
            }
        }
        return dp[n][n];
    }

    /*************************** Single 1D Array Space Optimization ***********************************
     * Can also do Double 1D Array Space Optimization

     * Tabulation of above memoization solution
        * Time Complexity: O(n^2)
        * two loops (for two states)
     * Space Complexity: O(n)
        * DP array
     */
    public int maxSumIS(int[] arr, int n)  {
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++){
            for (int nextGreater = i; nextGreater <= n; nextGreater++){
                int take = nextGreater == n || arr[i-1] < arr[nextGreater] ? arr[i - 1] + dp[i-1] : 0;
                int notTake = dp[nextGreater];
                dp[nextGreater] = Math.max(take, notTake);
            }
        }
        return dp[n];
    }
}
