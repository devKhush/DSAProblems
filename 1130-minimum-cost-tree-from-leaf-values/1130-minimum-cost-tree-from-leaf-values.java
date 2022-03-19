class Solution {
    public int mctFromLeafValues(int[] arr) {
        int minSum = 0;
        Stack<Integer> stack = new Stack<>();
        
        for (int num: arr){
            while(!stack.isEmpty()  &&  num >= stack.peek()){
                int leafNode  = stack.pop();
                if (stack.isEmpty())
                    minSum += leafNode * num;
                else
                    minSum += leafNode * Math.min(num, stack.peek());
            }
            stack.push(num);
        }
        
        while(stack.size() >= 2)
            minSum += stack.pop() * stack.peek();
        
        return minSum;
    }
}