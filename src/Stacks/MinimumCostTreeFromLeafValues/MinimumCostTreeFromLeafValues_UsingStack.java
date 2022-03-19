package Stacks.MinimumCostTreeFromLeafValues;
import java.util.Stack;

// https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/discuss/339959/One-Pass-O(N)-Time-and-Space
// https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/discuss/476513/Java-Mono-Stack-98-Example-to-explain

public class MinimumCostTreeFromLeafValues_UsingStack {
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