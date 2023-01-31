package DynamicProgramming.DP_on_Arrays.Knapsack_01;

// https://youtu.be/OgvOZ6OrJoY
// PRE_REQUISITE: "0/1 Knapsack"
// https://www.geeksforgeeks.org/unbounded-knapsack-repetition-items-allowed/
// https://takeuforward.org/data-structure/unbounded-knapsack-dp-23/

public class Unbounded_KnapSack {
    /************************************ Memoization *************************************
     * Time Complexity: O(n * bagSize)
        * Number of states
     * Space Complexity: O(bagSize) + O(n * bagSize)
        * DP array + Recursion stack space
     */
    public static int unboundedKnapsack_memo(int n, int bagSize, int[] profit, int[] weight) {
        Integer[][] dp = new Integer[n][bagSize + 1];
        return f(n - 1, bagSize, profit, weight, dp);
    }

    private static int f(int i, int capacity, int[] profit, int[] weight, Integer[][] dp) {
        if (capacity == 0)
            return 0;
        if (i == 0)
            return (capacity / weight[0]) * profit[0];
        if (dp[i][capacity] != null)
            return dp[i][capacity];

        // At every index, current item can be inside my bag or not
        int takeItem = capacity >= weight[i] ?
                f(i, capacity - weight[i], profit, weight, dp) + profit[i] : 0;
        int notTakeItem = f(i - 1, capacity, profit, weight, dp);
        return dp[i][capacity] = Math.max(takeItem, notTakeItem);
    }


    /************************************ Tabulation *************************************
     * Time Complexity: O(n * maxWeight)
        * Number of states
     * Space Complexity: O(n * maxWeight)
        * DP array
     */
    public static int unboundedKnapsack_tabu(int n, int bagSize, int[] profit, int[] weight) {
        int[][] dp = new int[n][bagSize + 1];

        // base case
        for (int capacity = 0; capacity <= bagSize; capacity++) {
            dp[0][capacity] = (capacity / weight[0]) * profit[0];
        }

        // remaining states
        for (int i = 1; i < n; i++) {
            for (int capacity = 0; capacity <= bagSize; capacity++) {
                int takeItem = capacity >= weight[i] ? dp[i][capacity - weight[i]] + profit[i] : 0;
                int notTakeItem = dp[i - 1][capacity];
                dp[i][capacity] = Math.max(notTakeItem, takeItem);
            }
        }
        return dp[n - 1][bagSize];
    }


    /************************************ Space optimization to 1D array *************************************
     * Time Complexity: O(n * maxWeight)
        * Number of states
     * Space Complexity: O(maxWeight)
        * 1D DP array
     */
    public static int unboundedKnapsack(int n, int bagSize, int[] profit, int[] weight) {
        int[] dp = new int[bagSize + 1];

        // Base case
        for (int capacity = 0; capacity <= bagSize; capacity++) {
            dp[capacity] = (capacity / weight[0]) * profit[0];
        }

        // Remaining states
        for (int i = 1; i < n; i++) {
            for (int capacity = 0; capacity <= bagSize; capacity++) {
                int takeItem = capacity >= weight[i] ? dp[capacity - weight[i]] + profit[i] : 0;
                int notTakeItem = dp[capacity];
                dp[capacity] = Math.max(notTakeItem, takeItem);
            }
        }
        return dp[bagSize];
    }
}
