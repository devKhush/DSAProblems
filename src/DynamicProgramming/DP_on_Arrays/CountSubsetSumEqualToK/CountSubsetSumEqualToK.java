package DynamicProgramming.DP_on_Arrays.CountSubsetSumEqualToK;
import java.util.Arrays;

// PRE_REQUISITE: 'Contains subset sum equal to K'
// https://youtu.be/ZHyb-A2Mte4
// https://www.codingninjas.com/codestudio/problems/number-of-subsets_3952532
// https://takeuforward.org/data-structure/count-subsets-with-sum-k-dp-17/
// https://www.geeksforgeeks.org/count-of-subsets-with-sum-equal-to-x/

public class CountSubsetSumEqualToK {
    /********************************* Memoization Solution ****************************************
     * Time Complexity: O(n * K)
        * number of states is n*K
     * Space Complexity: O(n * K) + O(n)
        * Dp array + recursion stack space
     */
    public static int findWays_memo(int K, int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][K + 1];
        for (int[] row : dp)
            Arrays.fill(row, -1);

        return f(n - 1, K, arr, dp);
    }

    private static int f(int i, int target, int[] arr, int[][] dp) {
        // New base case, previous base case will not work when there are 0's in the array. Think...
        if (i < 0)
            return  target == 0 ? 1 : 0;

        if (dp[i][target] != -1)
            return dp[i][target];

        int take = arr[i] <= target ? f(i - 1, target - arr[i], arr, dp) : 0;
        int notTake = f(i - 1, target, arr, dp);
        return dp[i][target] = take + notTake;
    }


    /********************************* Tabulation Solution ****************************************
     * Time Complexity: O(n * K)
        * number of states is n*K
     * Space Complexity: O(K)
        * Dp array
     */
    public static int findWays(int[] arr, int K) {
        int n = arr.length;
        int[][] dp = new int[n][K + 1];

        // Two Base case:
        // When arr[0] is 0, dp[0][0] will be 2 (i.e, two subset at which target is 0 at 0th index)
        // Not-Take base case at 0th index
        dp[0][0] = 1;
        // Take base case at 0th index
        if (arr[0] <= K)
            dp[0][arr[0]] += 1;

        // Remaining states
        for (int i = 1; i < n; i++){
            for (int target = 0; target <= K; target++){
                int take = arr[i] <= target ? dp[i-1][target - arr[i]] : 0;
                int notTake = dp[i-1][target];
                dp[i][target] = take + notTake;
            }
        }
        return dp[n-1][K];
    }

    /********************************* Space Optimized Solution ****************************************
     * Time Complexity: O(n * K)
        * number of states is n*K
     * Space Complexity: O(K)
        * K size Dp array
     */
    public static int findWays_space(int[] arr, int K) {
        int n = arr.length;
        int[] dp = new int[K + 1];

        // Two Base case:
        // When arr[0] is 0, dp[0][0] will be 2 (i.e, two subset at which target is 0 at 0th index)
        // Not-Take base case at 0th index
        dp[0] = 1;
        // Take base case at 0th index
        if (arr[0] <= K)
            dp[arr[0]] += 1;

        for (int i = 1; i < n; i++){
            int[] tempDP = new int[K + 1];
            for (int target = 0; target <= K; target++){
                int take = arr[i] <= target ? dp[target - arr[i]] : 0;
                int notTake = dp[target];
                tempDP[target] = take + notTake;
            }
            dp = tempDP;
        }
        return dp[K];
    }
}
