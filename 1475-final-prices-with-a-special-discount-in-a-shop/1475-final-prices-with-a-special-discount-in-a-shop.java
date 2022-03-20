class Solution {
    public int[] finalPrices(int[] prices) {
        int[] answer = new int[prices.length];
        Stack <Integer> stack = new Stack<>();
        
        for (int i=0; i<prices.length; i++){
            
            while(!stack.isEmpty()  &&  prices[i] <= prices[stack.peek()]){
                int index =  stack.pop();
                answer[index] = prices[index] - prices[i];
            }
            
            stack.push(i);
        }
        
        while (!stack.isEmpty()){
            int index = stack.pop();
            answer[index] = prices[index];
        }
        
        return answer;
    }
}