package DynamicProgramming.DP_on_Stocks.BuyAndSellStocksWithCooldown;

// https://youtu.be/IGIe46xw3YY
// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/description/
// https://takeuforward.org/data-structure/buy-and-sell-stocks-with-cooldown-dp-39/

public class BuyAndSellStocksWithCoolDown {
    /*************************************** Recursion *******************************************
     * Intuition: Extending basic recursion approach of BuyAndSellStocks-II

     * Time Complexity: O(2^n) ~ exponential
        * take and not-take approach
     * Space Complexity: O(n)
        * Recursion stack space
     */
    public int maxProfit_recur(int[] prices) {
        return f(0, 1, prices);
    }
    private int f(int i, int buy, int[] prices){
        if (i >= prices.length)
            return 0;

        if (buy == 1)       // Buy and don't Buy
            return Math.max(-prices[i] + f(i + 1, 0, prices),
                                0  +  f(i + 1, 1, prices));
        else                // Sell (with cool down) and don't Sell
            return Math.max(prices[i] + f(i + 2, 1, prices),
                                0  +  f(i + 1, 0, prices));
    }


    /*************************************** Memoization *******************************************
     * Memoization of above solution

     * Time Complexity: O(2*n)
        * Only two changing states are there, 'i' and 'buy'
     * Space Complexity: O(2*n) + O(n)
        * DP_Array + Recursion_Stack_Space
     */
    public int maxProfit_memo(int[] prices) {
        int n = prices.length;
        Integer[][] dp = new Integer[n][2];

        return f(0, 1, prices, dp);
    }
    private int f(int i, int buy, int[] prices, Integer[][] dp){
        if (i >= prices.length)
            return 0;
        if (dp[i][buy] != null)
            return dp[i][buy];

        if (buy == 1)       // Buy and don't Buy
            return dp[i][buy] = Math.max(-prices[i] + f(i + 1, 0, prices, dp), f(i + 1, 1, prices, dp));
        else                // Sell (with cool down) and don't Sell
            return dp[i][buy] = Math.max(prices[i] + f(i + 2, 1, prices, dp), f(i + 1, 0, prices, dp));
    }


    /****************************************** Tabulation ********************************************
     * Tabulation of above solution

     * Time Complexity: O(2*n)
        * Only two changing states are there, 'i' and 'buy'. And two loops
     * Space Complexity: O(2*(n+2)) ~ O(2*n)
        * DP_Array
     */
    private int maxProfit_tabu(int[] prices){
        int n = prices.length;
        int[][] dp = new int[n + 2][2];
        // No need of base case for i==n and i==n+1

        for (int i = n - 1; i >= 0; i--){
            for (int buy = 0; buy <= 1; buy++){
                if (buy == 1)
                    dp[i][1] = Math.max(-prices[i] + dp[i + 1][0], dp[i + 1][1]);
                else
                    dp[i][0] = Math.max(prices[i] + dp[i + 2][1], dp[i + 1][0]);
            }
        }
        return dp[0][1];
    }

    /****************************************** Tabulation Optimized ****************************************
     * Tabulation of above solution

     * Time Complexity: O(n)
        * Since there are two values of buy, we removed the loop
        * Only two changing states are there, 'i' and 'buy'. And one loops
     * Space Complexity: O(2 * (n+2)) ~ O(2*n)
        * DP_Array
     */
    private int maxProfit_tabu_optim(int[] prices){
        int n = prices.length;
        int[][] dp = new int[n + 2][2];

        for (int i = n - 1; i >= 0; i--){
            dp[i][1] = Math.max(-prices[i] + dp[i + 1][0], dp[i + 1][1]);
            dp[i][0] = Math.max(prices[i] + dp[i + 2][1], dp[i + 1][0]);
        }
        return dp[0][1];
    }


    /****************************************** Space Optimization *************************************
     * Space Optimization of above tabulation solution

     * Time Complexity: O(2*n)
         * Only two changing states are there, 'i' and 'buy'. And two loops
     * Space Complexity: O(6) ~ O(1)
        * Constant DP_Array
     */
    private int maxProfits_space(int[] prices){
        int n = prices.length;
        int[] dp = new int[2], next_dp = new int[2];

        for (int i = n - 1; i >= 0; i--){
            int[] curr_dp = new int[2];
            for (int buy = 0; buy <= 1; buy++){
                if (buy == 1)
                    curr_dp[1] = Math.max(-prices[i] + dp[0], dp[1]);
                else
                    curr_dp[0] = Math.max(prices[i] + next_dp[1], dp[0]);
            }
            next_dp = dp;
            dp = curr_dp;
        }
        return dp[1];
    }

    /******************************** Optimization of Space Optimization *******************************
     * Optimization of above  solution

     * Time Complexity: O(2*n)
         * Only two changing states are there, 'i' and 'buy'. And one loops
     * Space Complexity: O(6) ~ O(1)
         * Constant DP_Array
     */
    private int maxProfits_space_optim(int[] prices){
        int n = prices.length;
        int[] dp = new int[2], next_dp = new int[2];

        for (int i = n - 1; i >= 0; i--){
            int[] curr_dp = new int[2];
            curr_dp[1] = Math.max(-prices[i] + dp[0], dp[1]);
            curr_dp[0] = Math.max(prices[i] + next_dp[1], dp[0]);

            next_dp = dp;
            dp = curr_dp;
        }
        return dp[1];
    }
}
