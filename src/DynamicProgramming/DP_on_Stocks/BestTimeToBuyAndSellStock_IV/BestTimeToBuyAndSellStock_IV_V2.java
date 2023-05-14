package DynamicProgramming.DP_on_Stocks.BestTimeToBuyAndSellStock_IV;

// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/description/
// https://youtu.be/IV1dHbk5CDc

public class BestTimeToBuyAndSellStock_IV_V2 {
    /************************************ Recursion **************************************
     * Intuition: Another Recursive Solution but in "2D DP"
     * There are total of 2*k transactions: "Buy, Sell, Buy, Sell, ..."
     * At even index we do buy and at odd index we do selling

     * Time Complexity: O(2^n)  ~ Exponential
        * At each step, we either buy/sell or dontBuy/dontSell in transaction
     * Space Complexity: O(n)
        * Recursion stack space
     */
    public int maxProfit_recur(int k, int[] prices) {
        return f(0, 0, k, prices);
    }
    private int f(int i, int transaction, int k, int[] prices){
        if (i == prices.length || transaction == 2*k)
            return 0;

        // Buy case for even transaction
        if (transaction % 2 == 0)           // Buy and Don't Buy
            return Math.max(-prices[i] + f(i + 1, transaction + 1, k, prices),
                                0 + f(i + 1, transaction, k, prices));
        // Sell case for odd transaction
        else                                // Sell and Don't Sell
            return Math.max(prices[i] + f(i + 1, transaction + 1, k, prices),
                                0 + f(i + 1, transaction, k, prices));
    }

    /************************************** Memoization **********************************************
     * Intuition: Another Recursive Solution but in "2D DP"
     * There are total of 2*k transactions: "Buy, Sell, Buy, Sell, ..."
     * At even index we do buy and at odd index we do selling

     * Time Complexity: O(n * 2k)  ~  O(n*k)
        * At each step, we either buy/sell or dontBuy/dontSell in transaction
     * Space Complexity: O(n * 2k) + O(n)
        * DP_array + Recursion_stack_space
     */
    private int maxProfit_memo(int k, int[] prices){
        int n = prices.length;
        Integer[][] dp = new Integer[n][2*k];

        return f(0, 0, k, prices, dp);
    }
    private int f(int i, int transaction, int k, int[] prices, Integer[][] dp){
        if (i == prices.length || transaction == 2*k)
            return 0;
        if (dp[i][transaction] != null)
            return dp[i][transaction];

        // Buy case for even transaction and Sell for odd transaction
        if (transaction % 2 == 0)        // Buy and Don't Buy
            return dp[i][transaction] = Math.max(-prices[i] + f(i + 1, transaction + 1, k, prices, dp),
                                                    f(i + 1, transaction, k, prices, dp));
        else                             // Sell and Don't Sell
            return dp[i][transaction] = Math.max(prices[i] + f(i + 1, transaction + 1, k, prices, dp),
                                                    f(i + 1, transaction, k, prices, dp));
    }

    /*************************************** Tabulation ********************************************
     * Intuition: Tabulation of above memoization solution
     * There are total of 2*k transactions: "Buy, Sell, Buy, Sell, ..."
     * At even index we do buy and at odd index we do selling

     * Time Complexity: O(n * 2k)
        * Only two states ar changing: 'index i' and 'transaction'
     * Space Complexity: O(n * 2k)
        * DP Array
     */
    private int maxProfit_tabu(int k ,int[] prices){
        int n = prices.length;
        int[][] dp = new int[n + 1][2*k + 1];

        for (int i = n - 1; i >= 0; i--){
            for (int transaction = 2*k - 1; transaction >= 0; transaction--){
                if (transaction % 2 == 0)
                    dp[i][transaction] = Math.max(-prices[i] + dp[i + 1][transaction + 1], dp[i + 1][transaction]);
                else
                    dp[i][transaction] = Math.max(prices[i] + dp[i + 1][transaction + 1], dp[i + 1][transaction]);
            }
        }
        return dp[0][0];
    }

    /*************************************** Space Optimization ********************************************
     * Intuition: Space Optimization of above tabulation solution
     * There are total of 2*k transactions: "Buy, Sell, Buy, Sell, ...."
     * At even index we do buy and at odd index we do selling

     * Time Complexity: O(n * 2k)
        * Only two states ar changing: 'index i' and 'transaction'
     * Space Complexity: O(2*k)
        * Only constant size DP Array
     */
    private int maxProfit_space(int k, int[] prices){
        int n = prices.length;
        int[] dp = new int[2*k + 1];

        for (int i = n - 1; i >= 0; i--){
            int[] curr_DP = new int[2*k + 1];
            for (int transaction = 2*k - 1; transaction >= 0; transaction--){
                if (transaction % 2 == 0)
                    curr_DP[transaction] = Math.max(-prices[i] + dp[transaction + 1], dp[transaction]);
                else
                    curr_DP[transaction] = Math.max(prices[i] + dp[transaction + 1], dp[transaction]);
            }
            dp = curr_DP;
        }
        return dp[0];
    }
}
