class Solution {
    public int largestRectangleArea(int[] heights) {
        int[] prevSmall = prevSmaller(heights, heights.length); 
        int[] nextSmall = nextSmaller(heights, heights.length); 
        
        // System.out.println(Arrays.toString(nextSmall));
        // System.out.println(Arrays.toString(prevSmall));
        
        int maxArea = 0;
        
        for (int i=0; i<heights.length; i++)
            maxArea = Math.max(maxArea, (nextSmall[i] - prevSmall[i] -1)*heights[i]);
        
        return maxArea;
    }
    
    public int[] nextSmaller(int[] arr, int n){
        Stack<Integer> stack = new Stack<>();
        int[] nextSmall = new int[n];
        
        for (int i=0; i<n; i++){
            while(!stack.isEmpty() && arr[stack.peek()] > arr[i]){
                nextSmall[stack.pop()] = i;
            }
            stack.push(i);
        }
        while(!stack.isEmpty())
            nextSmall[stack.pop()] = n;
        return nextSmall;
    }
    
    public int[] prevSmaller(int[] arr, int n){
        Stack<Integer> stack = new Stack<>();
        int[] prevSmall = new int[n];
        
        for (int i=n-1; i>=0; i--){
            while(!stack.isEmpty() && arr[i] < arr[stack.peek()]){
                prevSmall[stack.pop()] = i;
            }
            stack.push(i);
        }
        while(!stack.isEmpty())
            prevSmall[stack.pop()] = -1;
        return prevSmall;
    }
}