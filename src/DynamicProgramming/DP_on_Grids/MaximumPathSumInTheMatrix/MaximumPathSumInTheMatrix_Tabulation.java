package DynamicProgramming.DP_on_Grids.MaximumPathSumInTheMatrix;

// https://youtu.be/N_aJ5qQbYA0
// https://takeuforward.org/data-structure/minimum-maximum-falling-path-sum-dp-12/
// https://www.codingninjas.com/codestudio/problems/maximum-path-sum-in-the-matrix_797998?source=youtube&campaign=striver_dp_videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_dp_videos&leftPanelTab=0

public class MaximumPathSumInTheMatrix_Tabulation {

    // ***************************** Approach 1: Tabulation ************************************
    // In this approach in which we iterate over each element in the last row of matrix and
    // find the maximum path sum in going from the jth element of the last row to the first
    // row via for loops and take maximum of each of these paths

    // T.C ==>  O(m*n)  (two for loops)
    // S.C ==>  O(DP array) = O(mn)

    public static int tabulationSolution_1(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];

        // Base cases
        // for (int j = 0; j < n; j++)
        //     dp[0][j] = matrix[0][j];
        dp[0] = matrix[0];

        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int up = dp[i - 1][j];
                int upLeft = j > 0 ? dp[i - 1][j - 1] : Integer.MIN_VALUE;
                int upRight = j != n - 1 ? dp[i - 1][j + 1] : Integer.MIN_VALUE;

                dp[i][j] = Math.max(up, Math.max(upLeft, upRight)) + matrix[i][j];
            }
        }

        int maxPathSum = Integer.MIN_VALUE;
        for (int j = 0; j < n; j++)
            maxPathSum = Math.max(maxPathSum, dp[m - 1][j]);
        return maxPathSum;
    }
}
