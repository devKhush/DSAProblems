class Solution {
    public int largestRectangleArea(int[] heights) {
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