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
            int currPathSum = memoizationSolution_1(m-1, j, matrix, dp, m, n);
            maxPathSum = Math.max(maxPathSum, currPathSum);
        }

        return maxPathSum;
    }

    public int memoizationSolution_1(int i, int j, int[][] arr, int[][] dp, int m, int n){
        if (j < 0 || j >= n)
            return Integer.MIN_VALUE/2;

        if (i == 0)
            return arr[0][j];

        if (dp[i][j] != -1)
            return dp[i][j];

        int maxPathSumByMovingUp = arr[i][j] + memoizationSolution_1(i-1, j, arr, dp, m, n);
        int maxPathSumByMovingUpLeft = arr[i][j] + memoizationSolution_1(i-1, j-1, arr, dp, m, n);
        int maxPathSumByMovingUpRight = arr[i][j] + memoizationSolution_1(i-1, j+1, arr, dp, m, n);

        return dp[i][j] = Math.max(maxPathSumByMovingUp, Math.max(maxPathSumByMovingUpLeft, maxPathSumByMovingUpRight));

    }


    // ***************************** Approach 2: Memoization ************************************
    // Note there can be one more approach in which we iterate over each element in the 1st row
    // of matrix and find the maximum path sum in going from the jth element of the first row
    // to the last row and take maximum of each of these paths

    // There will be only one call for one unique element in the matrix. Overlapping problems stored
    // & solved in DP array yield in O(1) time complexity
    // T.C ==>  O(m*n)
    // S.C ==> O(Path length from last row to first row) + O(DP array) = O(n) + O(mn) = O(mn)

    public int getMaxPathSum(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];

        for (int[] row : dp)
            Arrays.fill(row, -1);

        int maxPathSum = Integer.MIN_VALUE;

        for (int j=0; j<n; j++){
            // current Path Sum from jth element in lastRow to any element in firstRow
            int currPathSum = memoizationSolution_2(0, j, matrix, dp, m, n);
            maxPathSum = Math.max(maxPathSum, currPathSum);
        }

        return maxPathSum;
    }

    public int memoizationSolution_2(int i, int j, int[][] arr, int[][] dp, int m, int n){
        if (j < 0 || j >= n)
            return Integer.MIN_VALUE/2;

        if (i == m-1)
            return arr[i][j];

        if (dp[i][j] != -1)
            return dp[i][j];

        int maxPathSumByMovingDown = arr[i][j] + memoizationSolution_2(i+1, j, arr, dp, m, n);
        int maxPathSumByMovingDownLeft = arr[i][j] + memoizationSolution_2(i+1, j-1, arr, dp, m, n);
        int maxPathSumByMovingDownRight = arr[i][j] + memoizationSolution_2(i+1, j+1, arr, dp, m, n);

        return dp[i][j] = Math.max(maxPathSumByMovingDown, Math.max(maxPathSumByMovingDownLeft, maxPathSumByMovingDownRight));

    }
}
