package DynamicProgramming.DP_on_Grids.MaximumPathSumInTheMatrix;
import java.util.Arrays;

// https://youtu.be/N_aJ5qQbYA0
// https://takeuforward.org/data-structure/minimum-maximum-falling-path-sum-dp-12/
// https://www.codingninjas.com/codestudio/problems/maximum-path-sum-in-the-matrix_797998?source=youtube&campaign=striver_dp_videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_dp_videos&leftPanelTab=0


public class MaximumPathSumInTheMatrix_Memoization {

    // ***************************** Approach 1: Memoization ************************************
    // In this approach in which we iterate over each element in the last row of matrix and
    // find the maximum path sum in going from the jth element of the last row to the first
    // row and take maximum of each of these paths

    // There will be only one call for one unique element in the matrix. Overlapping problems stored
    // & solved in DP array yield in O(1) time complexity
    // T.C ==>  O(m*n)
    // S.C ==> O(Path length from last row to first row) + O(DP array) = O(n) + O(mn) = O(mn)

    public int getMaxPathSum_1(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        for (int[] row : dp)
            Arrays.fill(row, -1);

        int maxPathSum = Integer.MIN_VALUE;

        for (int j=0; j<n; j++){
            // current Path Sum from jth element in lastRow to any element in firstRow
            int currPathSum = f(m-1, j,  m, n, matrix, dp);
            maxPathSum = Math.max(maxPathSum, currPathSum);
        }
        return maxPathSum;
    }

    private static int f(int i, int j, int m, int n, int[][] matrix, int[][] dp) {
        if (j < 0 || j >= n)
            return Integer.MIN_VALUE;
        if (i == 0)
            return matrix[i][j];
        if (dp[i][j] != -1)
            return dp[i][j];

        int up = f(i - 1, j, m, n, matrix, dp);
        int upLeft = f(i - 1, j - 1, m, n, matrix, dp);
        int upRight = f(i - 1, j + 1, m, n, matrix, dp);

        return dp[i][j] = Math.max(up, Math.max(upLeft, upRight)) + matrix[i][j];
    }
}
