package DynamicProgramming.DP_on_Grids.GridUniquePathWithObstacles;
import java.util.Arrays;

// https://youtu.be/TmhpgXScLyY
// https://takeuforward.org/data-structure/grid-unique-paths-2-dp-9/

public class GridUniquePathWithObstacles {
    // ******************************** Memoization Solution ********************************
    // T.C -> O(mn)   (see previous question)
    // S.C -> O(m+n)
    public  int memoizationSolution(int i, int j, int[][] dp, int[][] arr){
        // We need to write this condition before the condition on (i,j)=(0,0)
        // because, (0,0) index in array can also have an obstacle. For eg, [[1]]
        if (i < 0 || j < 0 || arr[i][j] == 1)
            return 0;

        if (i == 0 && j == 0)
            return 1;

        if (dp[i][j] != -1)
            return dp[i][j];

        // same as previous question on Total unique paths on grids
        int totalWaysByMovingUp = memoizationSolution(i-1, j, dp, arr);
        int totalWaysByMovingLeft = memoizationSolution(i, j-1, dp, arr);

        return dp[i][j] = (totalWaysByMovingLeft + totalWaysByMovingUp) % (int)(1e9+7);
    }
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for (int[] row : dp)
            Arrays.fill(row, -1);

        return memoizationSolution(m-1, n-1, dp, obstacleGrid);
    }



    // ******************************** Tabulation Solution ********************************
    // T.C -> O(mn)   (see previous question)
    // S.C -> O(m+n)
    public int tabulationSolution(int[][] mat){
        int m = mat.length;
        int n = mat[0].length;
        // If obstacle is at the (0,0), then we can't reach from (0,0) to (m-1,n-1) or vice-versa
        // If obstacle is at the (0,0), then entire dp[][] will be full of zeros (think carefully & logically)
        // Actually no need of this condition here, this will be insured in for loops
        if (mat[0][0] == 1)
            return 0;

        int[][] dp = new int[m][n];
        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0)
                    continue;
                if (mat[i][j] == 1)     // dp[i][j] will be 0 in this case by initialize
                    continue;
                int up = i > 0 ? dp[i - 1][j] : 0;
                int left = j > 0 ? dp[i][j - 1] : 0;
                dp[i][j] = (up + left) % (int)(1e9+7);
            }
        }
        return dp[m - 1][n - 1];
    }



    // ******************************** Space Optimized Solution ********************************
    // T.C -> O(mn)   (see previous question)
    // S.C -> O(n)
    public int spaceOptimizedSolution(int[][] mat){
        int m = mat.length;
        int n = mat[0].length;
        if (mat[0][0] == 1)
            return 0;

        int[] dp = new int[n];
        dp[0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0)
                    continue;
                // we need to handle this case bcoz, previously dp[j] might not be 0
                // but dp[j] = 0 should be satisfied when there is obstacle
                if (mat[i][j] == 1){
                    dp[j] = 0;
                    continue;
                }
                int up = i > 0 ? dp[j] : 0;
                int left = j > 0 ? dp[j - 1] : 0;
                dp[j] = (up + left) % (int)(1e9+7);
            }
        }
        return dp[n - 1];
    }
}
