package DynamicProgramming.DP_on_Arrays.MaximumLengthOfPairChain;
import java.util.Arrays;

// https://leetcode.com/problems/maximum-length-of-pair-chain/

public class MaximumLengthOfPairChain {
    /************************************ DP + Binary Search **************************************
     * Intuition:
        * Recursion
     * Time Complexity: O(n*log(n)) + O(n)
     * Space Complexity: O(n)
        * 1-DP states
     */
    public int findLongestChain_dp(int[][] pairs) {
        int n = pairs.length;
        Arrays.sort(pairs, (a, b) -> a[1] - b[1]);
        Integer[] dp = new Integer[n];
        return f(n-1, pairs, dp);
    }

    private int f(int i, int[][] pairs, Integer[] dp){
        if (i < 0) return 0;
        if (dp[i] != null) return dp[i];
        int take = 1 + f(lower_bound(i, pairs, pairs[i][0]), pairs, dp);
        int notTake = f(i-1, pairs, dp);
        return dp[i] = Math.max(take, notTake);
    }

    private int lower_bound(int high, int[][] pairs, int target){
        int low = 0;
        while (low <= high){
            int mid = low + ((high - low)>>1);
            if (pairs[mid][1] < target) low = mid + 1;
            else high = mid - 1;
        }
        return high;
    }

    /**************************************** Optimal Greedy Solution ******************************
     * Intuition:
        * N-meeting in a room
        * Based on fact that we want to maximize the intervals, so it makes sense to sort by end time
     * Time Complexity: O(n*log(n)) + O(n)
     * Space Complexity: O(1)
     */
    public int findLongestChain(int[][] pairs) {
        int n = pairs.length;
        Arrays.sort(pairs, (a, b) -> a[1] - b[1]);

        int end = -(int)1e9;
        int length = 0;
        for (int[] pair : pairs){
            if (pair[0] > end){
                end = pair[1];
                length++;
            }
        }
        return length;
    }
}
