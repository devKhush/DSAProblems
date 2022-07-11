class Solution {
    public int[] finalPrices(int[] prices) {
        int n = prices.length;
        int[] finalPrices = new int[n];
        
        for (int i = 0; i < n; i++){
            int j = i + 1;
            while (j < n  &&  prices[i] < prices[j])
                j++;
            
            finalPrices[i] = j != n ? prices[i] - prices[j] : prices[i];
        }
        return finalPrices;
    }
    
    public int[] finalPrices_StackSolution_1(int[] prices) {
        int[] finalPrices = new int[prices.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < prices.length; i++){
            while (!stack.isEmpty()  &&  prices[stack.peek()] >= prices[i]){
                int index = stack.pop();
                finalPrices[index] = prices[index] - prices[i];
            }
            stack.push(i);
        }

        while (!stack.isEmpty()){
            int index = stack.pop();
            finalPrices[index] = prices[index];
        }
        return finalPrices;
    }

    
    public int[] finalPrices_StackSolution_2(int[] prices) {
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