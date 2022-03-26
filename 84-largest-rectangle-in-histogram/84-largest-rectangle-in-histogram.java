class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int n = heights.length;
        
        int[] nextSmall = new int[n];
        int[] prevSmall = new int[n];
        

        for (int i=0; i<n; i++){
            int j = i-1;
            while (j>=0 && heights[j] >= heights[i])
                j = prevSmall[j];
            prevSmall[i] = j; 
        }
        
        for (int i=n-1; i>=0; i--){
            int j = i+1;
            while (j<n && heights[j] >= heights[i])
                j = nextSmall[j];
            nextSmall[i] = j; 
        }
        
        for (int i=0; i<n; i++)
            maxArea = Math.max(maxArea, (nextSmall[i] - prevSmall[i] - 1)*heights[i]);
        
        return maxArea;
    }
}