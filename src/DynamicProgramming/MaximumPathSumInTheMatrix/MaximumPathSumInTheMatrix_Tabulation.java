package DynamicProgramming.MaximumPathSumInTheMatrix;

// // https://youtu.be/N_aJ5qQbYA0
//// https://takeuforward.org/data-structure/minimum-maximum-falling-path-sum-dp-12/
//// https://www.codingninjas.com/codestudio/problems/maximum-path-sum-in-the-matrix_797998?source=youtube&campaign=striver_dp_videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_dp_videos&leftPanelTab=0

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

        for (int i = 1; i < m; i++){
            for (int j = 0; j < n; j++){
                int maxPathSumByMovingUp = dp[i-1][j] + matrix[i][j];
                int maxPathSumByMovingUpLeft =  (j-1 >= 0)  ?  dp[i-1][j-1] + matrix[i][j] : Integer.MIN_VALUE/2;
                int maxPathSumByMovingUpRight = (j+1 < n)  ? dp[i-1][j+1] + matrix[i][j] : Integer.MIN_VALUE/2;

                dp[i][j] = Math.max(maxPathSumByMovingUp, Math.max(maxPathSumByMovingUpLeft, maxPathSumByMovingUpRight));
            }
        }

        int maxPathSum = Integer.MIN_VALUE;
        for (int currentPathSumInLastRow: dp[m-1])
            maxPathSum = Math.max(maxPathSum, currentPathSumInLastRow);

        return maxPathSum;
    }



    // ***************************** Approach 2: Tabulation ************************************
    // Note there can be one more approach in which we iterate over each element in the 1st row
    // of matrix and find the maximum path sum in going from the jth element of the first row
    // to the last row via for loops and take maximum of each of these paths

    // T.C ==>  O(m*n)  (two for loops)
    // S.C ==>  O(DP array) = O(mn)

    public static int tabulationSolution_2(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];

        // Base cases
        dp[m-1] = matrix[m-1];

        for (int i = m-2; i >= 0; i--){
            for (int j = 0; j < n; j++){
                int maxPathSumByMovingDown = dp[i+1][j] + matrix[i][j];
                int maxPathSumByMovingDownLeft =  (j-1 >= 0)  ?  dp[i+1][j-1] + matrix[i][j] : Integer.MIN_VALUE/2;
                int maxPathSumByMovingDownRight = (j+1 < n)  ? dp[i+1][j+1] + matrix[i][j] : Integer.MIN_VALUE/2;

                dp[i][j] = Math.max(maxPathSumByMovingDown, Math.max(maxPathSumByMovingDownLeft, maxPathSumByMovingDownRight));
            }
        }

        int maxPathSum = Integer.MIN_VALUE;
        for (int currentPathSumInLastRow: dp[0])
            maxPathSum = Math.max(maxPathSum, currentPathSumInLastRow);

        return maxPathSum;
    }
}
