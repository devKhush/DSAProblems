class Solution {
    public int maximalRectangle(char[][] matrix) {
        int row = matrix.length;
        int column = matrix[0].length;
        
        char[] currentHistogram = matrix[0];
        for (int i=0; i<column; i++)
            currentHistogram[i] = (char)(currentHistogram[i] - '0');
        int maxArea = maximumAreaInHistogram(currentHistogram);
        
        
        for (int i = 1; i<row; i++){
            for (int j = 0; j<column; j++){
                if (matrix[i][j]=='1')
                    currentHistogram[j]++;
                else
                    currentHistogram[j] = 0;
            }
            maxArea = Math.max(maxArea, maximumAreaInHistogram(currentHistogram));
        }
        return maxArea;
    }
    
    public int maximumAreaInHistogram(char[] heights) {
        int[] nextSmallerElement = nextSmallerElementIndices(heights, heights.length);
        int[] prevSmallerElement = previousSmallerElementIndices(heights, heights.length);
        
        int maxArea = 0;
        
        for (int i=0; i<heights.length; i++){
            int area = (nextSmallerElement[i] - prevSmallerElement[i] -1) * heights[i];
            maxArea = Math.max(maxArea, area);
        }
        
        return maxArea;
    }

    // Similar to the problem of Next Greater element, this is the code for problem of
    // Next Smaller Element using stack
    public int[] nextSmallerElementIndices(char[] arr, int n){
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

    // Similar to the problem of Next Greater element, this is the code for problem of
    // Previous Smaller Element using stack
    public int[] previousSmallerElementIndices(char[] arr, int n){
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
    
//     // Maximum area in a Histogram Problem
//     public int maximumAreaInHistogram(char[] arr){
//         int n = arr.length;
//         int[] nextSmaller = new int[n];
//         int[] prevSmaller = new int[n];
        
//         prevSmaller[0] = -1;
//         nextSmaller[n-1] = n;
        
//         for (int i=1; i<n; i++){
//             int low = i-1;         
//             while (low>=0 && arr[low] >= arr[i])
//                 low = prevSmaller[low];
            
//             prevSmaller[i] = low;
//         }
        
//         for (int i = n-2; i>=0; i--){
//             int high = i+1;
//             while (high<n && arr[i] <= arr[high])
//                 high = nextSmaller[high];
//             nextSmaller[i] = high;
//         }
        
//         int maxArea = 0;
//         for (int i=0; i<n; i++){
//             int height = arr[i];
//             maxArea = Math.max(maxArea, (nextSmaller[i] - prevSmaller[i] - 1)*height);
//         }
//         return maxArea;
//     }
}