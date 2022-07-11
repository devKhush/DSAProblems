class Solution {
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        
        int[] histogram = new int[n];
        int maxRectangleArea = 0;
        
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (matrix[i][j] == '1')
                    histogram[j]++;
                else
                    histogram[j] = 0;
            }
            maxRectangleArea = Math.max(maximumAreaInHistogram(histogram, n), maxRectangleArea);
        }
        return maxRectangleArea;
    }
    
    public int maximumAreaInHistogram(int[] arr, int n){
        Stack<Integer> stack = new Stack<>();
        int maximumAreaInHistogram = 0;
        
        for (int i = 0; i <= n; i++){
            while (!stack.isEmpty()  &&  (i == n || arr[stack.peek()] >= arr[i])){
                int height = arr[stack.pop()];
                int width;
                
                if (!stack.isEmpty())
                    width = i - stack.peek() - 1;
                else 
                    width = i;
                
                maximumAreaInHistogram = Math.max(height * width, maximumAreaInHistogram);
            }
            stack.push(i);
        }
        return maximumAreaInHistogram;
    }
    
    
    public int maximumAreaInHistogram_V1(int[] arr, int n){
        int[] PSE = new int[n];
        int[] NSE = new int[n];
        
        PSE[0] = -1;
        NSE[n - 1] = n;
        
        for (int i = 1; i < n; i++){
            int low = i - 1;
            
            while (low >= 0  &&  arr[low] >= arr[i])
                low = PSE[low];
            
            PSE[i] = low;
        }
        
        for (int i = n - 1; i >= 0; i--){
            int high = i + 1;
            
            while (high < n  &&  arr[i] <= arr[high])
                high = NSE[high];
            
            NSE[i] = high;
        }
        
        int maxAreaInHistogram = 0;
        
        for (int i = 0; i < n; i++){
            int width = NSE[i] - PSE[i] - 1;
            int height = arr[i];
            maxAreaInHistogram = Math.max(height * width, maxAreaInHistogram);
        }
        return maxAreaInHistogram;
    }
}