class Solution {
    public int largestRectangleArea(int[] heights) {
        int[] nextSmallerElement = nextSmallerElementIndices(heights, heights.length);
        int[] prevSmallerElement = previousSmallerElementIndices(heights, heights.length);
        
        System.out.println(Arrays.toString(nextSmallerElement));
        System.out.println(Arrays.toString(prevSmallerElement));
        
        int maxArea = 0;
        
        for (int i=0; i<heights.length; i++){
            int area = (nextSmallerElement[i] - prevSmallerElement[i] -1) * heights[i];
            maxArea = Math.max(maxArea, area);
        }
        
        return maxArea;
    }
    
    public int[] nextSmallerElementIndices(int[] arr, int n){
        int[] nextSmallerElement = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        for (int i=0; i<n; i++){
            while(!stack.isEmpty() && arr[i] < arr[stack.peek()])
                nextSmallerElement[stack.pop()] = i;
            stack.push(i);
        }
        
        while(!stack.isEmpty())
            nextSmallerElement[stack.pop()] = n;

        return nextSmallerElement;
    }
    
    public int[] previousSmallerElementIndices(int[] arr, int n){
        int[] prevSmallerElement = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        for (int i=n-1; i>=0; i--){
            while(!stack.isEmpty() && arr[i] < arr[stack.peek()])
                prevSmallerElement[stack.pop()] = i;
            stack.push(i);
        }
        
        while(!stack.isEmpty())
            prevSmallerElement[stack.pop()] = -1;

        return prevSmallerElement;
    }
}