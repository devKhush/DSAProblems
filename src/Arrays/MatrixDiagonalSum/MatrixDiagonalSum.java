package Arrays.MatrixDiagonalSum;

class MatrixDiagonalSum {

    // Simple solution
    public int diagonalSum(int[][] mat) {
        int n = mat.length;
        int diagSum = 0;

        for (int i = 0; i < n; i++)
            diagSum += mat[i][i];

        for (int i = 0; i < n; i++)
            diagSum += mat[n - 1 - i][i];

        if (n % 2 == 1)
            diagSum -= mat[n / 2][n / 2];

        return diagSum;
    }
}