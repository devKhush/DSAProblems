class Solution {
    
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        
        int low = 0, high = m*n - 1;
        
        while (low <= high){
            int mid = (low + high)/2;
            
            int row = mid / n;
            int column = mid % n;
            
            if (matrix[row][column] == target)
                return true;
            
            else if (matrix[row][column] > target)
                high = mid -1;
            
            else if (matrix[row][column] < target)
                low = mid + 1;    
        }
        
        return false;
    }
    
    
    // Binary Search Recusrive **************************************************************
    private boolean binarySearchMatrix(int low, int high, int target, int[][] matrix, int totalRow, int totalColumn){
        if (low > high)
            return false;
    
        int mid = (low + high)/2;
        int row = mid / totalColumn;
        int column = mid % totalColumn;
        
        if (matrix[row][column] == target)
            return true;
        else if (matrix[row][column] > target)
            return binarySearchMatrix(low, mid - 1, target, matrix, totalRow, totalColumn);        
        else
            return binarySearchMatrix(mid + 1, high, target, matrix, totalRow, totalColumn);
    }
    
    public boolean searchMatrix_Recursive(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        return binarySearchMatrix(0, m*n -1, target, matrix, m, n);
    }
    
    
    // Brute force : Linear search ***********************************************************
    public boolean searchMatrix_BruteForce(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
                 
        for (int[] row : matrix)
            for (int elementInRow : row)
                if (elementInRow == target)
                    return true;
        return false;
    }
}