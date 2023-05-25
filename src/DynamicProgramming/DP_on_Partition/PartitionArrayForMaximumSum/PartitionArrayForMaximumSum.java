package DynamicProgramming.DP_on_Partition.PartitionArrayForMaximumSum;

// https://leetcode.com/problems/partition-array-for-maximum-sum/description/
// https://takeuforward.org/data-structure/partition-array-for-maximum-sum-front-partition-dp-54/
// https://youtu.be/PhWWJmaKfMc

public class PartitionArrayForMaximumSum {
    /***************************************** Recursion ******************************************
     * Intuition:
        * Basic Recursion Thinking

     * Time Complexity: Exponential
     * Space Complexity: O(n)
        * Because in each call partitions will get smaller, so the max recursion stack space is O(n)
     */
    public int maxSumAfterPartitioning_rec(int[] arr, int k) {
        return f(0, k, arr);
    }
    private int f(int i, int k, int[] arr){
        if (i == arr.length)
            return 0;

        int maxSum = 0;
        int maxVal = 0;
        for (int j = i; j < Math.min(i + k, arr.length); j++){
            maxVal = Math.max(maxVal, arr[j]);
            int sum = maxVal*(j - i + 1) + f(j + 1, k, arr);
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }

    /***************************************** Memoization ******************************************
     * Memoization of above recursive solution

     * Time Complexity: O(n*k)
        * There is only one changing states, i
        * Another loop for j will run for each state "k" time
     * Space Complexity: O(n) + O(n)
        * DP Array + Recursion Stack space
     */
    public int maxSumAfterPartitioning_memo(int[] arr, int k) {
        Integer[] dp = new Integer[arr.length];
        return f(0, k, arr, dp);
    }
    private int f(int i, int k, int[] arr, Integer[] dp){
        if (i == arr.length)
            return 0;
        if (dp[i] != null)
            return dp[i];

        int maxSum = 0;
        int maxVal = 0;
        for (int j = i; j < Math.min(i + k, arr.length); j++){
            maxVal = Math.max(maxVal, arr[j]);
            int sum = maxVal*(j - i + 1) + f(j + 1, k, arr, dp);
            maxSum = Math.max(maxSum, sum);
        }
        return dp[i] = maxSum;
    }

    /****************************************** Tabulation ******************************************
     * Tabulation of above memoization

     * Time Complexity: O(n*k)
        * One changing states: i
        * One partition loop for each state: j (runs k time)
     * Space Complexity: O(n)
        * DP Array
     */
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;

        // DP Array (base case dp[n] = 0)
        int[] dp = new int[n + 1];

        for (int i = n-1; i >= 0; i--){
            int maxSum = 0;
            int maxVal = 0;
            for (int j = i; j < Math.min(i + k, n); j++){
                maxVal = Math.max(maxVal, arr[j]);
                int sum = maxVal*(j-i+1) + dp[j + 1];
                maxSum = Math.max(maxSum, sum);
            }
            dp[i] = maxSum;
        }
        return dp[0];
    }
}
