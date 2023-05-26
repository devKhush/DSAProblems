package DynamicProgramming.DP_on_Squares.CountSquareSubmatricesWithAllOnes;

// https://youtu.be/auS1fynpnjo
// https://takeuforward.org/data-structure/count-square-submatrices-with-all-1s-dp-on-rectangles-dp-56/
// https://leetcode.com/problems/count-square-submatrices-with-all-ones/description/

public class CountSquareSubMatricesWithAllOnes {
    /**************************************** Tabulation Solution **********************************
     * dp[i][j] indicates the "Length of largest square which ends at (i,j) as the bottom right corner"
     * dp[i][j] also indicates how many squares end at (i,j), with (i,j) as the bottom right corner

     * Time Complexity: O(m*n)
     * Space Complexity: O(m*n)
     */
    public int countSquares_(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        // DP Array
        int[][] dp = new int[m][n];

        // Base case
        for (int j = 0; j < n; j++)
            dp[0][j] = matrix[0][j];
        for (int i = 0; i < m; i++)
            dp[i][0] = matrix[i][0];

        // Remaining cases
        for (int i = 1; i < m; i++){
            for (int j = 1; j < n; j++){
                if (matrix[i][j] == 0)
                    dp[i][j] = 0;
                else
                    dp[i][j] = 1 + Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1]));
            }
        }

        int allSubMatrix = 0;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                allSubMatrix += dp[i][j];
            }
        }
        return allSubMatrix;
    }


    /**************************************** Tabulation Solution **********************************
     * Same Solution as above, but the count calculation is done in single loop (dp array loop)
     */
    public int countSquares(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        // DP Array
        int[][] dp = new int[m][n];
        int allSubMatrix = 0;

        // Base case
        for (int j = 0; j < n; j++) {
            dp[0][j] = matrix[0][j];
            allSubMatrix += matrix[0][j];
        }
        for (int i = 1; i < m; i++) {
            dp[i][0] = matrix[i][0];
            allSubMatrix += matrix[i][0];
        }

        // Remaining cases
        for (int i = 1; i < m; i++){
            for (int j = 1; j < n; j++){
                if (matrix[i][j] == 0)
                    dp[i][j] = 0;
                else
                    dp[i][j] = 1 + Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1]));
                allSubMatrix += dp[i][j];
            }
        }
        return allSubMatrix;
    }
}
