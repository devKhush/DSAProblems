package DynamicProgramming.DP_on_Grids.MaximumPathSumInTheMatrix;

// https://youtu.be/N_aJ5qQbYA0
// https://takeuforward.org/data-structure/minimum-maximum-falling-path-sum-dp-12/
// https://www.codingninjas.com/codestudio/problems/maximum-path-sum-in-the-matrix_797998?source=youtube&campaign=striver_dp_videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_dp_videos&leftPanelTab=0


public class MaximumPathSumInTheMatrix_SpaceOptimization {

    // ***************************** Approach 1: Space Optimization ************************************
    // In this approach in which we iterate over each element in the last row of matrix and
    // find the maximum path sum in going from the jth element of the last row to the first
    // row via for loops and take maximum of each of these paths

    // T.C ==>  O(m*n)  (two for loops)
    // S.C ==>  O(DP array) = O(n)

    public int getMaxPathSum_1(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        // Base case along with DP array
        // This DP array always stores previous row values as we need only previous row values to compute
        // current row values bcoz from (i,j) we can move to (i-1,j) or (i-1,j-1) or (i-1,j+1)
        int[] dp = matrix[0];

        for (int i = 1; i < m; i++) {
            int[] tempDP = new int[n];

            for (int j = 0; j < n; j++) {

                int maxPathSumByMovingUp = dp[j] + matrix[i][j];
                int maxPathSumByMovingUpLeft = (j-1 >= 0) ? dp[j-1] + matrix[i][j] : Integer.MIN_VALUE/2;
                int maxPathSumByMovingUpRight = (j+1 < n) ? dp[j+1] + matrix[i][j] : Integer.MIN_VALUE/2;

                tempDP[j] = Math.max(maxPathSumByMovingUp, Math.max(maxPathSumByMovingUpRight, maxPathSumByMovingUpLeft));
            }
            dp = tempDP;
        }

        int maxPathSum = Integer.MIN_VALUE;
        for (int currentPathSumInLastRow : dp)
            maxPathSum = Math.max(maxPathSum, currentPathSumInLastRow);
        return maxPathSum;
    }


    // ***************************** Approach 2: Space Optimization ************************************
    // Note there can be one more approach in which we iterate over each element in the 1st row
    // of matrix and find the maximum path sum in going from the jth element of the first row
    // to the last row via for loops and take maximum of each of these paths

    // T.C ==>  O(m*n)  (two for loops)
    // S.C ==>  O(DP array) = O(n)

    public int getMaxPathSum_2(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        // Base case along with DP array
        // This DP array always stores next row values as we need only previous row values to compute
        // current row values bcoz from (i,j) we can move to (i-1,j) or (i-1,j-1) or (i-1,j+1)
        int[] dp = matrix[m-1];

        for (int i = m-2; i >= 0; i--) {
            int[] tempDP = new int[n];

            for (int j = 0; j < n; j++) {

                int maxPathSumByMovingDown = dp[j] + matrix[i][j];
                int maxPathSumByMovingDownLeft =  (j-1 >= 0)  ?  dp[j-1] + matrix[i][j] : Integer.MIN_VALUE/2;
                int maxPathSumByMovingDownRight = (j+1 < n)  ? dp[j+1] + matrix[i][j] : Integer.MIN_VALUE/2;

                tempDP[j] = Math.max(maxPathSumByMovingDown, Math.max(maxPathSumByMovingDownLeft, maxPathSumByMovingDownRight));
            }
            dp = tempDP;
        }

        int maxPathSum = Integer.MIN_VALUE;
        for (int currentPathSumInFirstRow : dp)
            maxPathSum = Math.max(maxPathSum, currentPathSumInFirstRow);
        return maxPathSum;
    }
}
