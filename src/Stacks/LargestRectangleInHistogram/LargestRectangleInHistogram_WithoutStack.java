package Stacks.LargestRectangleInHistogram;

class LargestRectangleInHistogram_WithoutStack {
     public int largestRectangleArea(int[] heights) {
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