class Solution { 
    
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        boolean row0_HasToBeZero = false;
        boolean column0_HasToBeZero = false;
        
        for (int i = 0; i < m; i++)
            if (matrix[i][0] == 0)
                column0_HasToBeZero = true;
        
        for (int j = 0; j < n; j++)
            if (matrix[0][j] == 0)
                row0_HasToBeZero = true;
        
        for (int i = 1; i < m; i++){
            for (int j = 1; j < n; j++)
               if (matrix[i][j] == 0)
                   matrix[0][j] = matrix[i][0] = 0;
        }
            
        for (int i = 1; i < m; i++){
            for (int j = 1; j < n; j++){
                if (matrix[i][0] == 0 || matrix[0][j] == 0)
                    matrix[i][j] = 0;
            }
        }
        
        if (row0_HasToBeZero){
            for (int j = 0; j < n; j++)
                matrix[0][j] = 0;
        }
        
        if (column0_HasToBeZero)
            for (int i = 0; i < m; i++)
                matrix[i][0] = 0;
        
    }
}