package DynamicProgramming.DP_on_Stocks;

// https://youtu.be/excAOvwF_Wk
// https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
// https://takeuforward.org/data-structure/stock-buy-and-sell-dp-35/

public class BestTimeToBuyAndSellStock {
    /************************************** Solution 1 ***********************************************
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public int maxProfit_v1(int[] prices) {
        int n = prices.length;
        int[] maxPrices = new int[n];
        maxPrices[n - 1] = prices[n - 1];
        int maxProfit = 0;

        for (int i = prices.length - 2; i >= 0; i--){
            maxPrices[i] = Math.max(prices[i], maxPrices[i + 1]);
            maxProfit = Math.max(maxProfit, maxPrices[i] - prices[i]);
        }
        return maxProfit;
    }

    /************************************** Solution 2 ***********************************************
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int maxProfit_v2(int[] prices) {
        int maxProfit = 0;
        int max = 0;

        for (int i = prices.length - 1; i >= 0; i--){
            if (prices[i] > max) max = prices[i];
            if (max - prices[i] > maxProfit) maxProfit = max - prices[i];
        }
        return maxProfit;
    }

    /************************************** Solution 3 ***********************************************
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int maxProfit_v3(int[] prices) {
        int min = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int i = 0; i < prices.length; i++){
            if (prices[i] < min)
                min = prices[i];
            if (prices[i] - min > maxProfit)
                maxProfit = prices[i] - min;
        }
        return maxProfit;
    }
}
