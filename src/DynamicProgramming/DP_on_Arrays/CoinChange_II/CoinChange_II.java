package DynamicProgramming.DP_on_Arrays.CoinChange_II;

public class CoinChange_II {
    /************************************** Memoization ***************************************
     * Time Complexity: O(n * Target)
        * 2 states of problem
     * Space Complexity: O(n * Target) + O(Target)
        * DP array + recursion stack space
        * Consider coin 1
     */
    public int change(int amount, int[] coins) {
        int n = coins.length;
        Integer[][] dp = new Integer[n][amount + 1];

        return f(n - 1, amount, coins, dp);
    }

    private static int f(int i, int amount, int[] arr, Integer[][] dp) {
        if (amount == 0)
            return 1;
        if (i == 0)
            return amount % arr[0] == 0 ? 1 : 0;

        if (dp[i][amount] != null)
            return dp[i][amount];

        int take = amount >= arr[i] ? f(i, amount - arr[i], arr, dp) : 0;
        int notTake = f(i - 1, amount, arr, dp);
        return dp[i][amount] = take + notTake;
    }


    /*************************************** Tabulation *******************************************
     * Time Complexity: O(n * Target)
     * Space Complexity: O(n * Target)
        * DP array
     */
    public int coinChange_2(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];

        // Base case
        for (int target = 0; target <= amount; target++) {
            dp[0][target] = target % coins[0] == 0 ? 1 : 0;
        }

        // Remaining cases
        for (int i = 1; i < n; i++) {
            for (int target_amt = 0; target_amt <= amount; target_amt++) {
                int takeCoin = target_amt >= coins[i] ? dp[i][target_amt - coins[i]] : 0;
                int notTakeCoin = dp[i - 1][target_amt];
                dp[i][target_amt] = takeCoin + notTakeCoin;
            }
        }
        return dp[n - 1][amount];
    }


    /*************************************** Space Optimization *************************************
     * Time Complexity: O(n * Target)
     * Space Complexity: O(Target)
        * 1D DP array
     */
    public int coinChange_2_space(int amount, int[] coins) {
        int n = coins.length;
        int[] dp = new int[amount + 1];  

        // Base case
        for (int target = 0; target <= amount; target++) {
            dp[target] = target % coins[0] == 0 ? 1 : 0;
        }

        // Remaining cases
        for (int i = 1; i < n; i++) {
            int[] tempDP = new int[amount + 1];
            tempDP[0] = 1;
            for (int target_amt = 0; target_amt <= amount; target_amt++) {
                int takeCoin = target_amt >= coins[i] ? tempDP[target_amt - coins[i]] : 0;
                int notTakeCoin = dp[target_amt];
                tempDP[target_amt] = takeCoin + notTakeCoin;
            }
            dp = tempDP;
        }
        return dp[amount];
    }

}
