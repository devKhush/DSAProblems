package DynamicProgramming.DP_on_Squares.MaximalSquare;

// https://leetcode.com/problems/maximal-square/description/

public class MaximalSquare {
    /**************************************** Tabulation Solution **********************************
     * dp[i][j] indicates the "Length of largest square which ends at (i,j) as the bottom right corner"
     * dp[i][j] also indicates "How many squares end at (i,j), with (i,j) as the bottom right corner"

     * Time Complexity: O(m*n)
     * Space Complexity: O(m*n)
     */
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        // DP Array
        int[][] dp = new int[m][n];

        // Base cases for 0th row & 0th column
        for (int j = 0; j < n; j++){
            dp[0][j] = matrix[0][j] == '1' ? 1 : 0;
        }
        for (int i = 0; i < m; i++){
            dp[i][0] = matrix[i][0] == '1' ? 1 : 0;
        }

        // Remaining cases
        for (int i = 1; i < m; i++){
            for (int j = 1; j < n; j++){
                if (matrix[i][j] == '0')
                    dp[i][j] = 0;
                else
                    dp[i][j] = 1 + Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1]));
            }
        }
        int largestSide = 0;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                largestSide = Math.max(largestSide, dp[i][j]);
            }
        }
        return largestSide * largestSide;
    }


    /**************************************** Tabulation Solution 2 **********************************
     * Same Solution as above, but the length calculation is done in single loop (dp array loop)
     */
    public int maximalSquare_v1(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        // DP Array
        int largestSide = 0;
        int[][] dp = new int[m][n];

        // Base cases
        for (int j = 0; j < n; j++){
            if (matrix[0][j] == '1'){
                dp[0][j] = largestSide = 1;
            }
        }
        for (int i = 0; i < m; i++){
            if (matrix[i][0] == '1'){
                dp[i][0] = largestSide = 1;
            }
        }

        // Remaining cases
        for (int i = 1; i < m; i++){
            for (int j = 1; j < n; j++){
                if (matrix[i][j] == '0')
                    dp[i][j] = 0;
                else{
                    dp[i][j] = 1 + Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1]));
                    largestSide = Math.max(largestSide, dp[i][j]);
                }
            }
        }
        return largestSide * largestSide;
    }
}
