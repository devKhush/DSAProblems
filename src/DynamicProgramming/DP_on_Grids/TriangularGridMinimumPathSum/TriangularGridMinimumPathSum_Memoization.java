package DynamicProgramming.DP_on_Grids.TriangularGridMinimumPathSum;
import java.util.Arrays;
import java.util.List;

// https://youtu.be/SrP-PiLSYC0
// https://takeuforward.org/data-structure/minimum-path-sum-in-triangular-grid-dp-11/

public class TriangularGridMinimumPathSum_Memoization {

    // *********************************** Memoization Solution ***********************************
    // Only one call for each element in the triangle (overlapping problems will result in O(1) calls)
    // T.C ==>  O(n*n)          (including for loop, two calls for every element)
    // S.C ==> O(Path length) + O(DP array) = O(root(2)*n) + O(n*n) = O(n*n)


    public int minimumTotal_V1(List<List<Integer>> triangle) {

        // Triangle is Right angle triangle with equal base and height (both of n)
        int n = triangle.size();
        int minPathSum = Integer.MAX_VALUE;
        int[][] dp = new int[n][n];

        for (int[] row : dp)
            Arrays.fill(row, -1);

        // Since the minimum path sum can be form (0,0) to any element in the last row of triangle
        // So, we need to find the minimum path sum from (0,0) to every element (index) in the last row
        // and take minimum of that (size of last row is n)
        for (int j = 0; j < n; j++){
            int pathSumBySelecting_ith_ElementInLastRow = memoizationSolution_V1(triangle, n-1, j, dp);
            minPathSum = Math.min(minPathSum, pathSumBySelecting_ith_ElementInLastRow);
        }

        return minPathSum;
    }

    // (i,j) --> (ith Row, jth column)
    public int memoizationSolution_V1(List<List<Integer>> arr, int i, int j, int[][] dp){
        // Base case when at (0,0) (only element in 0th row), consider it in the current path
        if (i==0 && j==0) {
            dp[0][0] = arr.get(i).get(j);
            return dp[0][0];
        }

        // Last condition can happen when we move from straight up in same column at last index
        // for example from (1,1) to (0,1)
        if (i<0 || j<0 || j > i)
            return Integer.MAX_VALUE/2;

        // If this answer has been calculated previously then return it
        if (dp[i][j] != -1)
            return dp[i][j];

        // Now we move straight up in the triangular grid and consider the current path cost into consideration
        int minPathByMovingUp = memoizationSolution_V1(arr, i-1, j, dp) + arr.get(i).get(j);

        // Now we move diagonally (up & left) in the triangular grid and consider the current path cost into consideration
        int minPathByMovingUpLeft = memoizationSolution_V1(arr, i-1, j-1, dp) + arr.get(i).get(j);

        // Take minimum of the path sum of Straight-up path & diagonally (left-up) path
        int minPathAt_i_j_Index = Math.min(minPathByMovingUp, minPathByMovingUpLeft);

        return dp[i][j] = minPathAt_i_j_Index;
    }



    // **************************** Another easier Memoization approach ********************************
    // In this we tend to move from bottom to top recursion (like tabulation one)
    // Using this approach we will never get out of boundary on the hypotenuse of isosceles right triangle
    // unlike to that of we were also reaching out of boundary in isosceles right triangle in Previous Solution
    // Think about this logically & visually

    // Only one call for each element in the triangle (overlapping problems will result in O(1) calls)
    // T.C ==>  O(n*n)          (one call for every element)
    // S.C ==> O(Path length) + O(DP array) = O(root(2)*n) + O(n*n) = O(n*n)

    public int minimumTotal_V2(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];
        for(int[] row: dp)
            Arrays.fill(row, -1);

        return memoizationSolution_V1(triangle, 0, 0, n, dp);
    }

    public int memoizationSolution_V1(List<List<Integer>> triangle, int i, int j, int n, int[][] dp) {
        if (i == n-1)
            return triangle.get(i).get(j);
        if (dp[i][j] != -1)
            return dp[i][j];

        // Minimum path by moving down
        int minPathByMovingDown = memoizationSolution_V1(triangle, i+1, j, n, dp) + triangle.get(i).get(j);

        // Minimum path by moving down & bottom (diagonally)
        int minPathByMovingDownBottom = memoizationSolution_V1(triangle, i+1, j+1, n, dp) + triangle.get(i).get(j);

        return dp[i][j] = Math.min(minPathByMovingDownBottom, minPathByMovingDown);
    }
}
