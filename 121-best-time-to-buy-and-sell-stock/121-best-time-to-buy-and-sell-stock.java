class Solution {
    public int maxProfit(int[] prices) {
        int minPrice = prices[0];
        int profit = 0;
        
        for (int i=0; i<prices.length; i++){
            profit = Math.max(profit, prices[i] - minPrice);
            if (minPrice > prices[i])
                minPrice = prices[i];
        }
        return profit;
    }
}