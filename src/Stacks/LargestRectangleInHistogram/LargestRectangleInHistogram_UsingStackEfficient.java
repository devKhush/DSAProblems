package Stacks.LargestRectangleInHistogram;
import java.util.Stack;

// https://www.youtube.com/watch?v=qj4RcJbp03g
// Do DRy run for better understanding....

public class LargestRectangleInHistogram_UsingStackEfficient {
    
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        
        int maxArea = 0;
        
        for (int i=1; i<n; i++){
            // If current element in the histogram/array is less than element at the top of stack,
            // then pop one by one till the height on top of stack becomes greater or equal
            // to current element and compute area
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]){
                int height = heights[stack.pop()];

                // If after popping from stack, it becomes empty then, width is i
                // DO a DRY Run
                if (stack.isEmpty())
                    maxArea = Math.max(maxArea, i * height);
                // Else, it ensures for maxArea width is (i - stack.peek() -1)
                else
                    maxArea = Math.max(maxArea, height * (i - stack.peek() -1));
            }
            stack.push(i);
        }

        // When all the elements of the histogram are traversed,
        // then pop one by one till the stack becomes empty and compute area
        while (!stack.isEmpty()){
            int height = heights[stack.pop()];

            // If after popping from stack, it becomes empty then, width is n
            // DO a DRY Run
            if (stack.isEmpty())
                maxArea = Math.max(maxArea, height * n);
            // Else, it ensures for maxArea width is (i - stack.peek() -1)
            else{
                int width = n - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
        }
        
        return maxArea;
        
    }

}