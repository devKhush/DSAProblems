package Arrays.BestTimeToBuyAndSellStock;

//https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
// https://takeuforward.org/data-structure/stock-buy-and-sell/
// Question of SDE Sheet

public class BestTimeToBuyAndSellStock {

    public int maxProfit1(int[] prices) {
        // Brute force checking each sell-price for each buy price
        int maxProfit = 0;
        for (int i = 0; i<prices.length; i++){
            int buyPrice = prices[i];
            for (int j = i; j<prices.length; j++){
                int sellPrice = prices[j];
                if (sellPrice-buyPrice > maxProfit)
                    maxProfit = sellPrice-buyPrice;
            }
        }
        return maxProfit;
    }

    public int maxProfit2(int[] prices) {
        // Brute force optimizing using O(n) space
        // creating an array that stores max future value of stock price
        // In this we buy at each day
        // Here we first find max. selling prices available in future on or after the index at
        // which we are currently buying each stock everyday
        int[] maxStockPriceAvailableInFutureAfterIthIndex = new int[prices.length];
        maxStockPriceAvailableInFutureAfterIthIndex[prices.length-1] = prices[prices.length-1];
        for (int i = prices.length-2; i>=0; i--){
            maxStockPriceAvailableInFutureAfterIthIndex[i] = Integer.max(prices[i], maxStockPriceAvailableInFutureAfterIthIndex[i+1]);
        }

        int maxProfit = 0;
        for (int i=0; i<prices.length; i++){
            maxProfit = Integer.max(maxProfit, maxStockPriceAvailableInFutureAfterIthIndex[i]-prices[i]);
        }
        return maxProfit;
    }

    public int maxProfit3(int[] prices) {
        // Here we keep track of min. buying prices as we move on each day
        // Here we are storing min. buying prices so far & try to sell on each day
        // and calculate profit on each day
        int minBuyPriceSoFar = prices[0];
        int maxProfit = 0;

        for (int i = 0; i < prices.length; i++){
            maxProfit = Math.max(maxProfit, prices[i] - minBuyPriceSoFar);
            minBuyPriceSoFar = Math.min(minBuyPriceSoFar, prices[i]);
        }
        return maxProfit;
    }


    // Buy and sell from last day
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int maxSellPrice = prices[n - 1];
        int profit = 0;

        for (int i = n - 1; i >= 0; i--){
            profit = Math.max(profit, maxSellPrice - prices[i]);
            maxSellPrice = Math.max(maxSellPrice, prices[i]);
        }
        return profit;
    }


    public static void main(String[] args) {
        int[] stocks = new int[]{7,1,5,3,6,8};
        System.out.println(new BestTimeToBuyAndSellStock().maxProfit3(stocks));
    }
}
