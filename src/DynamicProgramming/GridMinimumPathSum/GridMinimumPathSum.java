package DynamicProgramming.GridMinimumPathSum;

import java.util.Arrays;

public class GridMinimumPathSum {

    // ******************************* Simple Recursion ***************************************
    // T.C -> O(2^(mn))     (because we are calling recursive function two times for every element)
    // S.C --> O(path length) = O(m+n)
    private int simpleRecursion(int i, int j, int[][] grid){
        if (i==0 && j==0)
            return grid[0][0];

        // if we become out of boundary, we will never take that path, so we assume it to be infinite long path
        if (i<0 || j<0)
            return Integer.MAX_VALUE/2;

        int minPathByMovingUp = simpleRecursion(i-1, j, grid) + grid[i][j];
        int minPathByMovingLeft = simpleRecursion(i, j-1, grid) + grid[i][j];
        return Math.min(minPathByMovingUp, minPathByMovingLeft);
    }

    private int minPathSum_Recursion(int[][] grid){
        return simpleRecursion(grid.length-1, grid[0].length-1, grid);
    }



    // ******************************* Memoization ***************************************
    // T.C -> O(mn)     (because we are calling recursive function two times for every element)
    // S.C --> O(path length = stack space for recursion) + O(DP array) = O(m+n + m*n)
    private int memoizationSolution(int i, int j, int[][] grid, int[][] dp){
        if (i==0 && j==0)
            return grid[0][0];

        // if we become out of boundary, we will never take that path, so we assume it to be infinite long path
        if (i<0 || j<0)
            return Integer.MAX_VALUE/2;

        // Return min path for (i,j) if it has been solved previously
        if (dp[i][j] != -1)
            return dp[i][j];

        // We consider current element into the path (to find min. path) & go to find/get
        // min. path for upper path & left path
        int minPathByMovingUp = memoizationSolution(i-1, j, grid, dp) + grid[i][j];
        int minPathByMovingLeft = memoizationSolution(i, j-1, grid, dp) + grid[i][j];

        // Minimum path for (i,j) will be min. of Upper path and Left path
        return dp[i][j] = Math.min(minPathByMovingUp, minPathByMovingLeft);
    }

    private int minPathSum(int[][] grid){
        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m][n];
        for (int[] row: dp){
            Arrays.fill(row, -1);
        }
        return memoizationSolution(m-1, n-1, grid, dp);
    }



    // ******************************* Tabulation ***************************************
    // T.C -> O(mn)     (teo for loops, one iteration to find min path for every element in grid)
    // S.C --> O(DP array) = O(m*n)

    public int tabulationSolution(int[][] grid){
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        for (int i=0; i<m; i++){
            for (int j=0; j<n; j++){

                // Base case
                if (i==0 && j==0){
                    dp[0][0] = grid[0][0];
                    continue;
                }

                // We consider current element into the path (to find min. path) & go to find/get
                // min. path for upper path & left path, only if we can go below or left
                // Else if we reach out of boundary, we take infinite path

                int minPathByMovingUp = i > 0 ? dp[i-1][j] + grid[i][j] : Integer.MAX_VALUE/2;

                int minPathByMovingLeft = j > 0 ? dp[i][j-1] + grid[i][j] : Integer.MAX_VALUE/2;

                // Minimum path for (i,j) will be min. of Upper path and Left path
                dp[i][j] = Math.min(minPathByMovingUp, minPathByMovingLeft);
            }
        }
        // Min. path for (m-1,n-1) will be stored in dp[m-1][n-1]
        return dp[m-1][n-1];
    }



    // ******************************* Space Optimization ***************************************
    // T.C -> O(mn)     (teo for loops, one iteration to find min path for every element in grid)
    // S.C --> O(one column) = O(n)
    public int spaceOptimizedSolution(int[][] grid){
        int m = grid.length;
        int n = grid[0].length;
        int[] dp = new int[n];

        for (int i=0; i<m; i++){
            for (int j=0; j<n; j++){

                // Base case
                if (i==0 && j==0){
                    dp[0] = grid[0][0];
                    continue;
                }

                // We consider current element into the path (to find min. path) & go to find/get
                // min. path for upper path & left path, only if we can go below or left
                // Else if we reach out of boundary, we take infinite path

                int minPathByMovingUp = i > 0 ? dp[j] + grid[i][j] : Integer.MAX_VALUE/2;
                int minPathByMovingLeft = j > 0 ? dp[j-1] + grid[i][j] : Integer.MAX_VALUE/2;

                // Minimum path for (i,j) will be min. of Upper path and Left path
                dp[j] = Math.min(minPathByMovingLeft, minPathByMovingUp);
            }
        }
        // Min. path for (m-1,n-1) will be stored in dp[n-1]
        return dp[n-1];
    }
}
