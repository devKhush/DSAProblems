class Solution {
    private boolean binarySearchMatrix(int low, int high, int target, int[][] matrix){
        return true;
    }
    
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
         
        // return binarySearchMatrix(0, m*n -1, target, matrix);
        
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (matrix[i][j] == target)
                    return true;
        
        return false;
    }
}