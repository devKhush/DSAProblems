package DynamicProgramming.DP_on_Grids.MaximumPathSumInTheMatrix;
import java.util.Scanner;

// This problem is extension of Triangular Minimum path sum
// Whenever the question states variable starting points & variable ending points,
// try to explore all the starting/ending points

// https://youtu.be/N_aJ5qQbYA0
// https://takeuforward.org/data-structure/minimum-maximum-falling-path-su-12/
// https://www.codingninjas.com/codestudio/problems/maximum-path-sum-in-the-matrix_797998?source=youtube&campaign=strive_videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=strive_videos&leftPanelTab=0

public class MaximumPathSumInTheMatrix_Recursion {

    // ***************************** Approach 1: Recursion ************************************
    // In this approach in which we iterate over each element in the last row of matrix and
    // find the maximum path sum in going from the jth element of the last row to the first
    // row and take maximum of each of these paths

    // There are three calls for every element in the matrix. So, 3^mn calls for mn elements
    // T.C ==>  O(3^(m*n))
    // S.C is due to depth of recursion
    // S.C ==> O(Path length from last row to first row) =  O(root(2) * n)  in worst case = O(n)

     public int getMaxPathSum(int[][] matrix) {
         int m = matrix.length;
         int n = matrix[0].length;
         int maxPathSum = Integer.MIN_VALUE;

         for (int j = 0; j < n; j++){
             // current Path Sum from jth element in lastRow to any element in firstRow
             int currentPathSum = f(m-1, j, m, n, matrix);
             maxPathSum = Math.max(maxPathSum, currentPathSum);
         }

         return maxPathSum;
     }

    private static int f(int i, int j, int m, int n, int[][] matrix) {
        if (j < 0 || j >= n)
            return Integer.MIN_VALUE;
        if (i == 0)
            return matrix[i][j];

        int up = f(i - 1, j, m, n, matrix);
        int upLeft = f(i - 1, j - 1, m, n, matrix);
        int upRight = f(i - 1, j + 1, m, n, matrix);

        return Math.max(up, Math.max(upLeft, upRight)) + matrix[i][j];
    }



    // ***************************** Approach 2: Recursion ************************************
    // Note there can be one more approach in which we iterate over each element in the 1st row
    // of matrix and find the maximum path sum in going from the jth element of the first row
    // to the last row and take maximum of each of these paths

    // There are three calls for every element in the matrix. So, 3^mn calls for mn elements
    // T.C ==>  O(3^(m*n))
    // S.C ==> O(Path length from last row to first row) =  O(root(2) * n)  in worst case = O(n)

    public int getMaxPathSum_(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int maxPathSum = Integer.MIN_VALUE/2;
        for (int j = 0; j < n; j++){
            int currPathSum_jth_element_firstRow_to_lastRow = recursionSolution_(0, j, matrix, m, n);
            maxPathSum = Math.max(maxPathSum, currPathSum_jth_element_firstRow_to_lastRow);
        }

        return maxPathSum;
    }

    private int recursionSolution_(int i, int j, int[][] matrix, int m ,int n){
        if (j < 0 || j >= n)
            return Integer.MIN_VALUE/2;

        if (i == m-1)
            return matrix[m-1][j];

        int maxSumByMovingDown = recursionSolution_(i+1, j, matrix, m, n) + matrix[i][j];
        int maxSumByMovingDownLeft = recursionSolution_(i+1, j-1, matrix, m, n) + matrix[i][j];
        int maxSumByMovingDownRight = recursionSolution_(i+1, j+1, matrix, m, n) + matrix[i][j];

        return Math.max(maxSumByMovingDown, Math.max(maxSumByMovingDownLeft, maxSumByMovingDownRight));
    }

}
