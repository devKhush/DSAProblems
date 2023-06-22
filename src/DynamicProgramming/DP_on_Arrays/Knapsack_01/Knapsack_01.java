package DynamicProgramming.DP_on_Arrays.Knapsack_01;
import java.util.Arrays;

// https://youtu.be/GqOmJHQZivw
// Pre-requisite: Count & Find Subset sum
// https://takeuforward.org/data-structure/0-1-knapsack-dp-19/
// https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/

public class Knapsack_01 {
    /************************************ Recursion *************************************
     * Time Complexity: O(2^n)
        * Basic take and not take approach
     * Space Complexity: O(n)
        * Recursion stack space
     */
    private static int recursion(int i, int availableWt, int[] weights, int[] values) {
        if (availableWt == 0)
            return 0;
        if (i == 0)
            return availableWt >= weights[0] ? values[0] : 0;

        // At every index, either current item can be a part of my bag or not (just take or not take)
        int notTakeIntoBag = recursion(i - 1, availableWt, weights, values);
        int takeIntoBag = availableWt < weights[i] ? 0 :
                recursion(i - 1, availableWt - weights[i], weights, values) + values[i];

        return Math.max(takeIntoBag, notTakeIntoBag);
    }


    /************************************ Memoization *************************************
     * Time Complexity: O(n * maxWeight)
        * Number of states
     * Space Complexity: O(n) + O(n * maxWeight)
        * DP array + Recursion stack space
     */
    public static int knapsack(int[] weight, int[] value, int n, int maxWeight) {
        int[][] dp = new int[n][maxWeight + 1];
        for (int[] row : dp)
            Arrays.fill(row, -1);

        return f(n - 1, maxWeight, weight, value, dp);
    }

    private static int f(int i, int availableWt, int[] weights, int[] values, int[][] dp) {
        if (availableWt == 0)
            return 0;
        if (i == 0)
            return availableWt >= weights[0] ? values[0] : 0;

        if (dp[i][availableWt] != -1)
            return dp[i][availableWt];

        // At every index, either current item can be a part of my bag or not (just take or not take)
        int notTakeIntoBag = f(i - 1, availableWt, weights, values, dp);
        int takeIntoBag = availableWt < weights[i] ? 0 :
                f(i - 1, availableWt - weights[i], weights, values, dp) + values[i];

        return dp[i][availableWt] = Math.max(takeIntoBag, notTakeIntoBag);
    }
    /*
    This also works...
    static int f(int i, int capacity, int[] val, int[] wts, Integer[][] dp) {
        if (i < 0)
            return 0;
        if (dp[i][capacity] != null)
            return dp[i][capacity];
        int take = wts[i] <= capacity ? val[i] + f(i-1, capacity - wts[i], val, wts, dp) : 0;
        int notTake = f(i-1, capacity, val, wts, dp);
        return dp[i][capacity] = Math.max(take, notTake);
    }*/


    /************************************ Tabulation *************************************
     * Time Complexity: O(n * maxWeight)
        * Number of states
     * Space Complexity: O(n * maxWeight)
        * DP array
     */
    public static int knapsack_tabulation(int[] weights, int[] values, int n, int maxWeight) {
        int[][] dp = new int[n][maxWeight + 1];

        // Base case
        for (int wt = weights[0]; wt <= maxWeight; wt++) {
            dp[0][wt] = values[0];
        }

        // Remaining states/problems
        for (int i = 1; i < n; i++) {
            for (int wt = 0; wt <= maxWeight; wt++) {
                int notTakeIntoBag = dp[i - 1][wt];
                int takeIntoBag = wt >= weights[i] ? dp[i - 1][wt - weights[i]] + values[i] : 0;
                dp[i][wt] = Math.max(notTakeIntoBag, takeIntoBag);
            }
        }
        return dp[n - 1][maxWeight];
    }
    /*
    This also works...
    static int knapSack_tabu(int W, int wts[], int val[], int n) {
        int[][] dp = new int[n + 1][W + 1];
        for (int i = 1; i <= n; i++){
            for (int cap = 0; cap <= W; cap++){
                int take = cap >= wts[i-1] ? val[i-1] + dp[i-1][cap - wts[i-1]] : 0;
                int notTake = dp[i-1][cap];
                dp[i][cap] = Math.max(take, notTake);
            }
        }
        return dp[n][W];
    }*/


    /************************************ Space optimization *************************************
     * Time Complexity: O(n * maxWeight)
        * Number of states
     * Space Complexity: O(maxWeight)
        * 1D DP array
     */
    public static int knapsack_spaceoptimized1(int[] weights, int[] values, int n, int maxWeight) {
        int[] dp = new int[maxWeight + 1];
        // Base case
        for (int wt = weights[0]; wt <= maxWeight; wt++) {
            dp[wt] = values[0];
        }

        // Remaining states/problems
        for (int i = 1; i < n; i++) {
            int[] currDP = new int[maxWeight + 1];
            for (int wt = 0; wt <= maxWeight; wt++) {
                int notTakeIntoBag = dp[wt];
                int takeIntoBag = wt >= weights[i] ? dp[wt - weights[i]] + values[i] : 0;
                currDP[wt] = Math.max(notTakeIntoBag, takeIntoBag);
            }
            dp = currDP;
        }
        return dp[maxWeight];
    }

    /************************************ Space optimization to 1D array *************************************
     * Time Complexity: O(n * maxWeight)
     * Number of states
     * Space Complexity: O(maxWeight)
     * 1D DP array
     */
    public static int knapsack_spaceoptimized2(int[] weights, int[] values, int n, int maxWeight) {
        int[] dp = new int[maxWeight + 1];
        // Base case
        for (int wt = weights[0]; wt <= maxWeight; wt++) {
            dp[wt] = values[0];
        }

        // Remaining states/problems
        // Start the weight loop from end, bcoz current weight needs only lesser wights of previous row
        for (int i = 1; i < n; i++) {
            for (int wt = maxWeight; wt >= 0; wt--) {
                int notTakeIntoBag = dp[wt];
                int takeIntoBag = wt >= weights[i] ? dp[wt - weights[i]] + values[i] : 0;
                dp[wt] = Math.max(notTakeIntoBag, takeIntoBag);
            }
        }
        return dp[maxWeight];
    }
    /*
    This also works...
    static int knapSack(int W, int wts[], int val[], int n) {
        int[] dp = new int[W + 1];
        for (int i = 1; i <= n; i++){
            for (int cap = W; cap >= 0; cap--){
                int take = cap >= wts[i-1] ? val[i-1] + dp[cap - wts[i-1]] : 0;
                int notTake = dp[cap];
                dp[cap] = Math.max(take, notTake);
            }
        }
        return dp[W];
    }*/
}