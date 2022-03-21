class Solution {
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
     
}