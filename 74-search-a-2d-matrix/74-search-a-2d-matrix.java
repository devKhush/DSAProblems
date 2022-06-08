class Solution {
    private boolean binarySearchMatrix(int low, int high, int target, int[][] matrix){
        return true;
    }
    
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
                 
        for (int[] row : matrix)
            for (int elementInRow : row)
                if (elementInRow == target)
                    return true;
        return false;
    }
}