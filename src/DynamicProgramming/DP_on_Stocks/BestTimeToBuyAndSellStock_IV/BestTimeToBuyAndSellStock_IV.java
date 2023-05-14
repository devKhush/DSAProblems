package DynamicProgramming.DP_on_Stocks.BestTimeToBuyAndSellStock_IV;

// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/description/
// https://youtu.be/IV1dHbk5CDc
// https://takeuforward.org/data-structure/buy-and-sell-stock-iv-dp-38/

public class BestTimeToBuyAndSellStock_IV {
    /************************************ Recursion **************************************
     * Intuition:
        * Extend Basic Recursion Thinking of BestTimeToBuyAndSellStock_II
        * Slight Change in Recursion of BestTimeToBuyAndSellStock_III

     * Time Complexity: O(2^n)  ~ Exponential
        * At each step, we either buy/sell or dontBuy/dontSell
     * Space Complexity: O(n)
        * Recursion stack space
     */
    public int maxProfit_recur(int k, int[] prices) {
        return f(0, 1, k, prices);
    }
    private int f(int i, int buy, int limit, int[] prices){
        if (i == prices.length || limit == 0)
            return 0;

        if (buy == 1)       // Buy or Don't Buy
            return Math.max( -prices[i] + f(i + 1, 0, limit, prices),
                    0 +  f(i + 1, 1, limit, prices));
        else                // Sell or Don't Sell
            return Math.max( prices[i] + f(i + 1, 1, limit - 1, prices),
                    0 + f(i + 1, 0, limit, prices));
    }


    /**************************************  Memoization ******************************************
     * Time Complexity: O(n * 2k)  ~ O(n*k)
        * Three changing states for "index i" (of size n) and "buyStock" (of size 2) and "limit" (of size k).
     * Space Complexity: O(n * 2k) + O(n)
        * DP_Array + Recursion_stack_space
     */
    public int maxProfit_memo(int k, int[] prices) {
        int n = prices.length;
        Integer[][][] dp = new Integer[n][2][k + 1];

        return f(0, 1, k, prices, dp);
    }
    private int f(int i, int buy, int limit, int[] prices, Integer[][][] dp){
        if (i == prices.length || limit == 0)
            return 0;
        if (dp[i][buy][limit] != null)
            return dp[i][buy][limit];

        if (buy == 1)   // Buy or Don't Buy
            return dp[i][buy][limit] = Math.max(-prices[i] + f(i + 1, 0, limit, prices, dp),
                    f(i + 1, 1, limit, prices, dp));
        else            // Sell or Don't Sell
            return dp[i][buy][limit] = Math.max(prices[i] + f(i + 1, 1, limit - 1, prices, dp),
                    f(i + 1, 0, limit, prices, dp));
    }

    /***************************************** Tabulation ***************************************
     * Time Complexity: O(n * 2k) ~ O(n*k)
        * Only three states, i.e, three loops
     * Space Complexity: O(n * 2k) ~ O(n*k)
        * DP Array
     */
    public int maxProfit_tabu(int k, int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n + 1][2][k + 1];
        // No need for base case, by default zero

        for (int i = n - 1; i >= 0; i--){
            for (int buy = 0; buy <= 1; buy++){
                for (int limit = 1; limit <= k; limit++){
                    if (buy == 1)
                        dp[i][1][limit] = Math.max(-prices[i] + dp[i + 1][0][limit], dp[i + 1][1][limit]);
                    else
                        dp[i][0][limit] = Math.max(prices[i] + dp[i + 1][1][limit - 1], dp[i + 1][0][limit]);
                }
            }
        }
        return dp[0][1][k];
    }

    /***************************************** Space Optimization ***************************************
     * Time Complexity: O(n * 2k) ~ O(n*k)
        * Only three states, i.e, three loops
     * Space Complexity: O(2*k) ~ O(k)
        * Constant DP Array
     */
    public int maxProfit_space(int k, int[] prices) {
        int n = prices.length;
        int[][] dp = new int[2][k + 1];

        for (int i = n - 1; i >= 0; i--){
            int[][] curr_DP = new int[2][k + 1];
            for (int buy = 0; buy <= 1; buy++){
                for (int limit = 1; limit <= k; limit++){
                    if (buy == 1)
                        curr_DP[1][limit] = Math.max(-prices[i] + dp[0][limit], dp[1][limit]);
                    else
                        curr_DP[0][limit] = Math.max(prices[i] + dp[1][limit - 1], dp[0][limit]);
                }
            }
            dp = curr_DP;
        }
        return dp[1][k];
    }
}
