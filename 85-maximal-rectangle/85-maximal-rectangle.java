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
    
    // Maximum area in a Histogram Problem
    public int maximumAreaInHistogram(char[] arr){
        int n = arr.length;
        int[] nextSmaller = new int[n];
        int[] prevSmaller = new int[n];
        
        prevSmaller[0] = -1;
        nextSmaller[n-1] = n;
        
        for (int i=1; i<n; i++){
            int low = i-1;         
            while (low>=0 && arr[low] >= arr[i])
                low = prevSmaller[low];
            
            prevSmaller[i] = low;
        }
        
        for (int i = n-2; i>=0; i--){
            int high = i+1;
            while (high<n && arr[i] <= arr[high])
                high = nextSmaller[high];
            nextSmaller[i] = high;
        }
        
        int maxArea = 0;
        for (int i=0; i<n; i++){
            int height = arr[i];
            maxArea = Math.max(maxArea, (nextSmaller[i] - prevSmaller[i] - 1)*height);
        }
        return maxArea;
    }
}