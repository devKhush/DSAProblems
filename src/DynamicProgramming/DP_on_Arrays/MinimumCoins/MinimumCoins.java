package DynamicProgramming.DP_on_Arrays.MinimumCoins;
import java.util.Arrays;

// Pre-requisite: "Combination Sum-II" AND "Knapsack"
// https://youtu.be/myPeWb3Y68A
// https://takeuforward.org/data-structure/minimum-coins-dp-20/

public class MinimumCoins {
    /************************************* Simple recursion ***********************************
     * Time Complexity:     O(2 ^ Target)  >>> O(2^n)    (exponential)
     * Space Complexity:    O(Target)
        * Consider coin 1
        * Recursion stack space
     */
    public static int minimumElements(int[] arr, int target) {
        int n = arr.length;
        int minCoins = f(n - 1, target, arr);
        return minCoins < (int) (1e9) ? minCoins : -1;
    }

    private static int f(int i, int target, int[] arr) {
        if (target == 0)
            return 0;
        if (i == 0)
            return target % arr[0] == 0 ? target / arr[0] : (int) (1e9);

        int takeCoin = target >= arr[i] ? 1 + f(i, target - arr[i], arr) : (int) (1e9);
        int noTakeCoin = f(i - 1, target, arr);
        return Math.min(takeCoin, noTakeCoin);
    }


    /************************************** Memoization ***************************************
     * Time Complexity: O(n * Target)
        * 2 states of problem
     * Space Complexity: O(n * Target) + O(Target)
        * DP array + recursion stack space
     */
    public static int minimumElements(int[] coins, int amount, int n) {
        int[][] dp = new int[n][amount + 1];
        for (int[] row : dp)
            Arrays.fill(row, -1);

        int minCoins = f(n - 1, amount, coins, dp);
        return minCoins < 1e9 ? minCoins : -1;
    }

    private static int f(int i, int target, int[] arr, int[][] dp) {
        if (target == 0)
            return 0;
        if (i == 0)
            return target % arr[0] == 0 ? target / arr[0] : (int) (1e9);
        if (dp[i][target] != -1)
            return dp[i][target];

        int takeCoin = target >= arr[i] ? 1 + f(i, target - arr[i], arr, dp) : (int) (1e9);
        int noTakeCoin = f(i - 1, target, arr, dp);
        return dp[i][target] = Math.min(takeCoin, noTakeCoin);
    }


    /*************************************** Tabulation *******************************************
     * Time Complexity: O(n * Target)
     * Space Complexity: O(n * Target)
        * DP array
     */
    public static int minimumElements(int n, int[] coins, int amount) {
        int[][] dp = new int[n][amount + 1];

        // base case
        for (int target = 0; target <= amount; target++)
            dp[0][target] = (target % coins[0] == 0) ? target / coins[0] : (int)1e9;

        // remaining case
        for (int i = 1; i < n; i++){
            for (int target = 0; target <= amount; target++){
                int notTakeCoin = dp[i - 1][target];
                int takeCoin = target >= coins[i] ? 1 + dp[i][target - coins[i]] : (int)1e9;

                dp[i][target] = Math.min(takeCoin, notTakeCoin);
            }
        }
        return dp[n - 1][amount] < 1e9 ? dp[n - 1][amount] : -1;
    }



    /*************************************** Space Optimization *************************************
     * Time Complexity: O(n * Target)
     * Space Complexity: O(Target)
        * 1D DP array
     */
    public static int minimumCoins(int[] coins, int n, int amount) {
        int[] dp = new int[amount + 1];

        // base case
        for (int target = 0; target <= amount; target++)
            dp[target] = (target % coins[0] == 0) ? target / coins[0] : (int)1e9;

        // remaining case
        for (int i = 1; i < n; i++){
            int[] tempDP = new int[amount + 1];

            for (int target = 0; target <= amount; target++){
                int notTakeCoin = dp[target];
                int takeCoin = target >= coins[i] ? 1 + tempDP[target - coins[i]] : (int)1e9;

                tempDP[target] = Math.min(takeCoin, notTakeCoin);
            }
            dp = tempDP;
        }
        return dp[amount] < 1e9 ? dp[amount] : -1;
    }

}
