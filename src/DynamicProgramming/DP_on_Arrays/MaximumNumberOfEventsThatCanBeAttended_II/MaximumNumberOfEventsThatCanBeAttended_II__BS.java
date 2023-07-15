package DynamicProgramming.DP_on_Arrays.MaximumNumberOfEventsThatCanBeAttended_II;
import java.util.Arrays;

// https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended-ii/description/
// https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended-ii/editorial/

public class MaximumNumberOfEventsThatCanBeAttended_II__BS {
    /*********************************** Memoization ********************************************
     * Intuition:
        * 0/1 Knapsack  +  Merge Intervals  +  N meetings in one room
        * Sort events by end time, they will automatically sort by start time.
        * Take or not Take technique.
        * For take condition, directly move to the next smaller time using binary search
     * Time Complexity: O(n*k*log(n) + n*log(n))  ~  O(n*k*log(n))
        * n*log(n) for sorting
        * n*k*log(n) for DP (two states) and binary search inside each state
     * Space Complexity: O(n * k)
     */
    public int maxValue__memo(int[][] events, int k) {
        int n = events.length;
        Arrays.sort(events, (a,b) -> Integer.compare(a[1], b[1]));
        Integer[][] dp = new Integer[n][k + 1];

        return f(n-1, k, events, dp);
    }

    private int f(int i, int k, int[][] events, Integer[][] dp){
        if (k == 0 || i < 0)
            return 0;
        if (dp[i][k] != null)
            return dp[i][k];

        // Take condition
        int nextIndex = lower_bound(events, events[i][0]);
        int take = events[i][2] +  f(nextIndex, k-1, events, dp);

        // Not take
        int notTake = f(i - 1, k, events, dp);
        return dp[i][k] = Math.max(take, notTake);
    }

    // Binary search to find lower end time given the next event's start time
    private int lower_bound(int[][] events, int startTime){
        int low = 0, high = events.length - 1;
        while (low <= high){
            int mid = low  + ((high - low)>>1);
            if (events[mid][1] < startTime)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return high;
    }


    /************************************ Tabulation *************************************
     * Tabulation of above memoization
     * Time Complexity: O(n*k*log(n) + n*log(n))  ~  O(n*k*log(n))
        * n*log(n) for sorting
        * n*k*log(n) for DP (two states) and binary search inside each state
     * Space Complexity: O(n * k)
     */
    public int maxValue(int[][] events, int k) {
        int n = events.length;
        Arrays.sort(events, (a,b) -> a[1] - b[1]);

        // DP Array with Base case conditions as 0
        int[][] dp = new int[n + 1][k + 1];

        // Remaining DP states with shifting of indices
        for (int i = 1; i <= n; i++){
            for (int limit = 1; limit <= k; limit++){
                int nextIndex = lower_bound(events, events[i - 1][0]) + 1;      // +1 for shifting of index
                int take = events[i - 1][2] +  dp[nextIndex][limit - 1];
                int notTake = dp[i - 1][limit];
                dp[i][limit] = Math.max(take, notTake);
            }
        }
        return dp[n][k];
    }

}
