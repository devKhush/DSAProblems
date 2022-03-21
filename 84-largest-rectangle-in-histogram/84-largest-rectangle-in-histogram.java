class Solution {
     public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] nextSmaller = new int[n];
        int[] prevSmaller = new int[n];
        prevSmaller[0] = -1;
        nextSmaller[n-1] = n;
         
        for (int i=1; i<n; i++){
            int low = i-1;
            while (low >= 0 && heights[low] >= heights[i])
                low = prevSmaller[low];
            prevSmaller[i] = low;
        }
         
        for (int i = n-2; i >= 0; i--){
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
    
    /*
    public int largestRectangleArea(int[] heights) {
        int[] nextSmaller = nextSmallerElement(heights, heights.length);
        int[] prevSmaller = previousSmallerElement(heights, heights.length);
        
        int maxArea = 0;
        for (int i=0; i<heights.length; i++){
            maxArea = Math.max(maxArea, (nextSmaller[i]-prevSmaller[i]-1)*heights[i]);
        }
        return maxArea;
    }
    
    public int[] nextSmallerElement(int[] arr, int n){
        int[] nextSmallerElement = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        for (int i=0; i<n; i++){
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]){
                nextSmallerElement[stack.pop()] = i;
            }
            stack.push(i);
        }
        while(!stack.isEmpty())
            nextSmallerElement[stack.pop()] = n;
        return nextSmallerElement ;
    }
    
    public int[] previousSmallerElement(int[] arr, int n){
        int[] prevSmallerElement = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        for (int i=n-1; i>=0; i--){
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]){
                prevSmallerElement[stack.pop()] = i;
            }
            stack.push(i);
        }
        while(!stack.isEmpty())
            prevSmallerElement[stack.pop()] = -1;
        return prevSmallerElement ;
    }
     */
}