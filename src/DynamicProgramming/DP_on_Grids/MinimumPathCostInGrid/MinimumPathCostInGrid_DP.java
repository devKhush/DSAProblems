package DynamicProgramming.DP_on_Grids.MinimumPathCostInGrid;
import java.util.Arrays;

public class MinimumPathCostInGrid_DP {

    // **************************** Memoization Solution ****************************
    // Because we have m*n problems/states, and we are looping over next column for each sub-problem
    // TC -> O(m * n * n)
    // SC -> O(m) + O(m*n)          Dp array & Recursion stack space
    // Here Memoization is bottom to top

    public int minPathCost_Memoization(int[][] grid, int[][] moveCost) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        for (int[] row : dp)
            Arrays.fill(row, -1);

        int minCost = Integer.MAX_VALUE;

        for (int j = 0; j < n; j++){
            minCost = Math.min(minCost, minPath(0, j, grid, moveCost, dp, m, n));
        }
        return minCost;
    }

    public int minPath(int i, int j, int[][] arr, int[][] moveCost, int[][] dp, int m, int n){
        // We don't need this condition here as we will never go out of boundary
        // if (j < 0 || j >= n)
        //     return Integer.MAX_VALUE;

        if (i == m-1)
            return arr[m-1][j];

        if (dp[i][j] != -1)
            return dp[i][j];

        int minPathCost = Integer.MAX_VALUE;

        for (int dj = 0; dj < n; dj++){
            int cellValue = arr[i][j];
            int pathCost = moveCost[cellValue][dj] + minPath(i+1, dj, arr, moveCost, dp, m, n);
            int currPathCost = cellValue + pathCost;

            minPathCost = Math.min(minPathCost, currPathCost);
        }

        return dp[i][j] = minPathCost;
    }



    // **************************** Tabulation Solution ****************************
    // Because we have m*n problems/states, and we are looping over next column for each sub-problem
    // TC -> O(m * n * n)       Two for loops for m*n states/diff. problems
    // SC -> O(m*n)          Dp array
    // Here tabulation is top to bottom

    public int minPathCost_Tabulation(int[][] grid, int[][] moveCost) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        // Base case
        for (int j = 0; j < n; j++)
            dp[m-1][j] = grid[m-1][j];

        for (int i = m-2; i >= 0; i--){
            for (int j = 0; j < n; j++){
                int minPathCost = Integer.MAX_VALUE;

                for (int dj = 0; dj < n; dj++){
                    int cellValue = grid[i][j];
                    int pathCost = moveCost[cellValue][dj] + dp[i+1][dj];
                    int currPathCost = cellValue + pathCost;

                    minPathCost = Math.min(minPathCost, currPathCost);
                }

                dp[i][j] = minPathCost;
            }
        }

        int totalMinPathCost = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++)
            totalMinPathCost = Math.min(totalMinPathCost, dp[0][j]);

        return totalMinPathCost;
    }




    // **************************** Space Optimized Solution ****************************
    // Because we have m*n problems/states, and we are looping over next column for each sub-problem
    // TC -> O(m * n * n)       Two for loops for m*n states/diff. problems
    // SC -> O(n)          Dp array
    // Here tabulation is top to bottom

    public int minPathCost_SpaceOptimized(int[][] grid, int[][] moveCost) {
        int m = grid.length;
        int n = grid[0].length;
        int[] dp = new int[n];

        // Base case
        for (int j = 0; j < n; j++)
            dp[j] = grid[m-1][j];

        for (int i = m-2; i >= 0; i--){
            int[] tempDP = new int[n];

            for (int j = 0; j < n; j++){
                int minPathCost = Integer.MAX_VALUE;

                for (int dj = 0; dj < n; dj++){
                    int cellValue = grid[i][j];
                    int pathCost = moveCost[cellValue][dj] + dp[dj];

                    minPathCost = Math.min(minPathCost, cellValue + pathCost);
                }

                tempDP[j] = minPathCost;
            }
            dp = tempDP;
        }

        int totalMinPathCost = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++)
            totalMinPathCost = Math.min(totalMinPathCost, dp[j]);

        return totalMinPathCost;
    }
}