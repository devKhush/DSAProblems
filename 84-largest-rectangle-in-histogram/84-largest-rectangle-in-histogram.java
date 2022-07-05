class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] prevSmallerIndex = new int[n];
        int[] nextSmallerIndex = new int[n];

        prevSmallerIndex[0] = -1;
        for (int i = 1; i < n; i++){
            int j = i - 1;
            while (j >= 0  &&  heights[j] >= heights[i])
                j = prevSmallerIndex[j];

            prevSmallerIndex[i] = j;
        }

        nextSmallerIndex[n - 1] = n;
        for (int i = n - 2; i >= 0; i--){
            int j = i + 1;
            while (j < n  &&  heights[i] <= heights[j])
                j = nextSmallerIndex[j];

            nextSmallerIndex[i] = j;
        }

        int maxAreaInHistogram = 0;
        for (int i = 0; i < n; i++) {
            int area = (nextSmallerIndex[i] - prevSmallerIndex[i] - 1) * heights[i];
            maxAreaInHistogram = Math.max(area, maxAreaInHistogram);
        }
        return maxAreaInHistogram;
    }
    
    
    // Approach 1 Using Next Greater & Smaller Element **************************************************
    public int largestRectangleArea_Solution1(int[] heights) {
        int n = heights.length;
        int[] NSE = nextSmallerElement(heights, n);
        int[] PSE = previousSmallerElement(heights, n);
        
        int maxAreaInHistogram = 0;
        
        for (int i = 0; i < n; i++){
            int height = heights[i];
            int width = NSE[i] - PSE[i] - 1;
            int area = height * width;
            maxAreaInHistogram = Math.max(area, maxAreaInHistogram);
        }
        
        return maxAreaInHistogram;
    }
    
    private int[] nextSmallerElement(int[] arr, int n){
        Stack<Integer> stack = new Stack<>();
        int[] NSE = new int[n];
        
        for (int i = n - 1; i >= 0; i--){
            while (!stack.isEmpty()  &&  arr[i] <= arr[stack.peek()])
                stack.pop();
            
            NSE[i] = !stack.isEmpty() ? stack.peek() : n;
            stack.push(i);
        }
        return NSE;
    }
    
    private int[] previousSmallerElement(int[] arr, int n){
        Stack<Integer> stack = new Stack<>();
        int[] PSE = new int[n];

        for (int i = 0; i < n; i++){
            while (!stack.isEmpty()  &&  arr[stack.peek()] >= arr[i])
                stack.pop();
            
            PSE[i] = !stack.isEmpty() ? stack.peek() : -1;
            stack.push(i);
        }
        return PSE;
    }
}