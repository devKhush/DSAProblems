class Solution {
    
     public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;


       for (int i = 0; i < m; i++){
            for (int j  = 0; j < n; j++){
                if (matrix[i][j] == 0){

                    int row = i -1;
                    while (row >= 0){
                        if (matrix[row][j] != 0)
                            matrix[row][j] = -999;
                        row--;
                    }

                    row = i + 1;
                    while (row < m){
                        if (matrix[row][j] != 0)
                            matrix[row][j] = -999;
                        row++;
                    }

                    int column = j -1;
                    while (column >= 0){
                        if (matrix[i][column] != 0)
                            matrix[i][column] = -999;
                        column--;
                    }

                    column = j + 1;
                    while (column < n){
                        if (matrix[i][column] != 0)
                            matrix[i][column] = -999;
                        column++;
                    }
                }
            }
        }
        
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == -999)
                    matrix[i][j] = 0;
            }
        }
    }
    
    
    public void setZeroes_(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

       for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){

                if (matrix[i][j] == 0)
                    matrix[i][0] = matrix[0][j] = 0;
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0)
                    matrix[i][j] = 0;
            }
        }
    }
}