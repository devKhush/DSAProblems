class Solution {
    public int[] finalPrices(int[] prices) {
        int[] finalPrices = new int[prices.length];
        Stack<Integer> stack = new Stack<>();
        
        for (int i = prices.length - 1; i >= 0; i--){
            while (!stack.isEmpty()  &&  stack.peek() > prices[i])
                stack.pop();
            
            finalPrices[i] = !stack.isEmpty() ? prices[i] - stack.peek() : prices[i];
            
            stack.push(prices[i]);
        }
        return finalPrices;
    }
}