class Solution {
    public void rotate(int[][] matrix) {
        int n =  matrix.length;
                
        /// Transposing the matrix
        for (int i = 0; i < n; i++)
            for (int j = i; j < n; j++)
                swap(i, j, j, i, matrix);                
        
        for (int j = 0; j < n/2; j++)
            for (int i = 0; i < n; i++)
                swap(i, j, i, n - 1 -j, matrix);
    }
    
    
    private void swap(int i1, int j1, int i2, int j2, int[][] arr){
        int temp = arr[i1][j1];
        arr[i1][j1] = arr[i2][j2];
        arr[i2][j2] = temp;
    }
}