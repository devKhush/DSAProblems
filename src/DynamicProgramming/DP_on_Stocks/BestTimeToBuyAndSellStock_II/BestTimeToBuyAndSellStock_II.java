package DynamicProgramming.DP_on_Stocks.BestTimeToBuyAndSellStock_II;

// Very Interesting DP on Stock Problem
// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/
// https://takeuforward.org/data-structure/buy-and-sell-stock-ii-dp-36/
// https://youtu.be/nGJmxkUJQGs

public class BestTimeToBuyAndSellStock_II {
    /************************************** Recursion ***********************************************
     * Intuition: Complex Recursion Thinking
     * Time Complexity: O(2^n)
        * At each step, we either buy/sell or dontBuy/dontSell
     * Space Complexity: O(n)
        * Recursion stack space
     */
    public int maxProfit_recur(int[] prices) {
        return f(0, 1, prices);
    }
    private int f(int i, int buyStock, int[] prices){
        if (i == prices.length)
            return 0;

        int profit = 0;
        if (buyStock == 1){
            int buy = -prices[i] + f(i + 1, 0, prices);
            int dontBuy = f(i + 1, 1, prices);
            profit = Math.max(buy, dontBuy);
        }
        else{
            int sell =  prices[i] + f(i + 1, 1, prices);
            int dontSell = f(i + 1, 0, prices);
            profit = Math.max(sell, dontSell);
        }
        return profit;
    }

    /**************************************  Memoization ******************************************
     * Time Complexity: O(2*n)
        * Only two states for "index i" of size n and buyStock of size 2.
     * Space Complexity: O(2*n) + O(n)
        * DP_Array + Recursion_stack_space
     */
    public int maxProfit_memo(int[] prices) {
        Integer[][] dp = new Integer[prices.length][2];

        return f(0, 1, prices, dp);
    }
    private int f(int i, int buy, int[] prices, Integer[][] dp){
        if (i == prices.length)
            return 0;
        if (dp[i][buy] != null)
            return dp[i][buy];

        if (buy == 1)    // Buy and Don't Buy
            return dp[i][1] = Math.max(-prices[i] + f(i + 1, 0, prices, dp),
                                        0 + f(i + 1, 1, prices, dp));
        else                  // Sell and Don't Sell
            return dp[i][0] = Math.max(prices[i] + f(i + 1, 1, prices, dp),
                                        0 + f(i + 1, 0, prices, dp));
    }


    /****************************************** Tabulation ****************************************
     * Time Complexity: O(2*n)
        * Only two states for "index i" of size n and buyStock of size 2.
        * Two loops
     * Space Complexity: O(2*n)
        * DP array
     */
    public int maxProfit_tabu(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n + 1][2];

        for (int i = n - 1; i >= 0; i--){
            for (int buy = 0; buy <= 1; buy++){
                if (buy == 1)    // Buy and don't buy
                    dp[i][1] = Math.max(-prices[i] + dp[i + 1][0], dp[i + 1][1]);
                else             // Sell and don't sell
                    dp[i][0] = Math.max(prices[i] + dp[i + 1][1], dp[i + 1][0]);
            }
        }
        return dp[0][1];
    }

    /****************************************** Tabulation Optimised ****************************************
     * Removed one loop from Tabulation solution bcoz of only two values of 'buy'
     * Time Complexity: O(n)
         * Only two states for "index i" of size n and buyStock of size 2.
         * One loops
     * Space Complexity: O(2*n)
     * DP array
     */
    public int maxProfit_tabu_optimized(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n + 1][2];
        // dp[i][1] & dp[i][0] means buy and sell at index 'i' respectively

        for (int i = n - 1; i >= 0; i--){
            dp[i][1] = Math.max(-prices[i] + dp[i + 1][0], dp[i + 1][1]);
            dp[i][0] = Math.max(prices[i] + dp[i + 1][1], dp[i + 1][0]);
        }
        return dp[0][1];
    }

    /***************************************** Space Complexity *****************************************
     * Space Optimized Solution
     * Time Complexity: O(2*n)
        * Only two states for "index i" of size n and buyStock of size 2.
        * Two loops
     * Space Complexity: O(1)
        * Constant size DP array
     */
    public int maxProfit_space(int[] prices) {
        int n = prices.length;
        int[] dp = new int[2];

        for (int i = n - 1; i >= 0; i--){
            int[] curr_DP = new int[2];
            for (int buy = 0; buy <= 1; buy++){
                if (buy == 1)
                    curr_DP[1] = Math.max(-prices[i] + dp[0], dp[1]);
                else
                    curr_DP[0] = Math.max(prices[i] + dp[1], dp[0]);
            }
            dp = curr_DP;
        }
        return dp[1];
    }

    /************************************ Space Complexity Optimized *******************************
     * Optimized version of Space Optimized Solution
     * Time Complexity: O(n)
        * Only one loop
     * Space Complexity: O(1)
        * Variables are used for
     */
    public int maxProfit_space_optimized(int[] prices) {
        int n = prices.length;
        int nextBuy = 0, nextDontBuy = 0;

        for (int i = n - 1; i >= 0; i--){
            int currBuy = Math.max(-prices[i] + nextDontBuy, nextBuy);
            int currDontBuy = Math.max(prices[i] + nextBuy, nextDontBuy);

            nextBuy = currBuy;
            nextDontBuy = currDontBuy;
        }
        return nextBuy;
    }

    /*************************************** Greedy Solution ********************************************
     * Approach: If curr value is less than next value, then buy the stock
     * Intuition:
        * If b/w curr and next value, there is a greater value than curr but smaller than next,
            it doesn't affect the gap b/w curr and next.
        * If b/w curr and next value, there is a smaller value than curr, then we don't buy as per algorithm.
        * So, in this way we obtain max. profit

     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int maxProfit_greedy(int[] prices) {
        int profit = 0;
        for (int i = 0; i < prices.length-1; i++){
            if (prices[i + 1] > prices[i])
                profit += prices[i + 1] - prices[i];
        }
        return profit;
    }
}
