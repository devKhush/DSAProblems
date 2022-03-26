class Solution {
    public int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        // stack.push(0);
        
        for (int i=0; i<heights.length; i++){
            while (!stack.isEmpty()  &&  heights[i] < heights[stack.peek()]){
                int height = heights[stack.pop()];
                
                if (!stack.isEmpty())
                    maxArea = Math.max(maxArea, (i - stack.peek() - 1)*height);
                else
                    maxArea = Math.max(maxArea, i*height);
            }
            stack.push(i);
        }
        
        while (!stack.isEmpty()){
            int height = heights[stack.pop()];
            if (!stack.isEmpty())
                maxArea = Math.max(maxArea, (n - stack.peek() - 1)*height);
            else
                maxArea = Math.max(maxArea, n*height);   
        }
        return maxArea;
    }
    
    
    public int largestRectangleArea_WithoutStack(int[] heights) {
        int n = heights.length;
        int[] nextSmaller = new int[n];
        int[] prevSmaller = new int[n];

         // Similar to the problem of Next Greater element, this is the code for problem of
         // Previous Smaller Element without using stack
        for (int i=0; i<n; i++){
            int low = i-1;
            while (low >= 0 && heights[low] >= heights[i])
                low = prevSmaller[low];
            prevSmaller[i] = low;
        }

         // Similar to the problem of Next Greater element, this is the code for problem of
         // Next Smaller Element without using stack
        for (int i = n-1; i >= 0; i--){
            int high = i+1;
            while (high < n && heights[i] <= heights[high])
                high = nextSmaller[high];
            nextSmaller[i] = high;
        }
        
        
        int maxArea = 0;
        for (int i=0; i<n; i++){
            maxArea = Math.max(maxArea, (nextSmaller[i]-prevSmaller[i]-1)*heights[i]);
        }
        return maxArea;
     }
}