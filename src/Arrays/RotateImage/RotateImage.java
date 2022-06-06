package Arrays.RotateImage;

class RotateImage {

    // ************************************ Brute Force ************************************
    // Make a new matrix & store the mirror image in it & then copy it into original matrix
    // T.C. --> O(n*n)
    // S.C. --> O(n*n)

    public void rotate(int[][] matrix) {
        int n =  matrix.length;

        int[][] temp = new int[n][n];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                temp[i][j] =  matrix[n -1 -j][i];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                matrix[i][j] = temp[i][j];
    }

}