class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        boolean[] zeroRowFound = new boolean[m];
        boolean[] zeroColumnFound = new boolean[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0)
                    zeroRowFound[i] = zeroColumnFound[j] = true;
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (zeroRowFound[i] || zeroColumnFound[j])
                    matrix[i][j] = 0;
            }
        }
    }
}