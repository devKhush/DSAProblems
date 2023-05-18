package DynamicProgramming.DP_on_LIS.NumberOfLongestIncreasingSubsequence;

// https://youtu.be/cKVl1TFdNXg
// https://leetcode.com/problems/number-of-longest-increasing-subsequence/description/
// https://takeuforward.org/data-structure/number-of-longest-increasing-subsequences-dp-47/

public class NumberOfLongestIncreasingSubsequence {
    /****************************************** DP Tabulation *****************************************
     * Intuition:
        * Same as DP Tabulation of the Longest Increasing Subsequence
        * Maintain a count array for storing counts

     * Prerequisite: DP Tabulation of the Longest Increasing Subsequence
        * dp[i] -> denotes the length of the longest increasing sub-sequence by including nums[i] in it
        * count[i] -> denotes the count of the longest increasing sub-sequence by including nums[i] in it

     * Time Complexity: O(n*n) + O(n)
        * DP Tabulation
     * Space Complexity: O(2*n)
        * DP and Count Array
     */
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;

        // dp[i] -> denotes the length of the longest increasing sub-sequence by including nums[i] in it
        int[] dp = new int[n];

        // count[i] -> denotes the count of the longest increasing sub-sequence by including nums[i] in it
        int[] count = new int[n];
        int lengthLIS = 0;

        for (int i = 0; i < n; i++){
            // Initialize DP & Count Array with 1
            dp[i] = 1;
            count[i] = 1;
            for (int prev_small = 0; prev_small < i; prev_small++){
                if (nums[i] > nums[prev_small]){
                    if (dp[i] < 1 + dp[prev_small]){
                        dp[i] = 1 + dp[prev_small];

                        // The number of ways to make prev seq. ending with nums[j] is same as
                        // the number of ways to make longer seq. by attaching nums[i] to it
                        count[i] = count[prev_small];
                    }
                    else if (dp[i] == 1 + dp[prev_small]){
                        // no. of ways to make same length seq. increases by no. of ways of seq. ending with nums[j]
                        count[i] += count[prev_small];
                    }
                }
            }
            lengthLIS = Math.max(lengthLIS, dp[i]);
        }

        // Count of the Longest Increasing SubSequence
        int countLIS = 0;
        for (int i = 0; i < n; i++){
            if (dp[i] == lengthLIS)
                countLIS += count[i];
        }
        return countLIS;
    }
}
