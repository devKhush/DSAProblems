package DynamicProgramming.DP_on_Grids.GridUniquePathWithObstacles;
import java.util.Arrays;

// https://youtu.be/TmhpgXScLyY
// https://takeuforward.org/data-structure/grid-unique-paths-2-dp-9/

public class GridUniquePathWithObstacles {

    // ******************************** Memoization Solution ********************************
    // T.C -> O(mn)   (see previous question)
    // S.C -> O(m+n)
    public  int memoizationSolution(int i, int j, int[][] dp, int[][] arr){
        if (i<0 || j<0) return 0;

        // We need to write this condition before the condition on (i,j)=(0,0)
        // because, (0,0) index in array can also have an obstacle. For eg, [[1]]
        if (arr[i][j] == 1)
            return 0;

        if (i==0 & j==0) return 1;

        if (dp[i][j] != -1)
            return dp[i][j];

        // same as previous question on Total unique paths on grids
        int totalWaysByMovingUp = memoizationSolution(i-1, j, dp, arr);
        int totalWaysByMovingLeft = memoizationSolution(i, j-1, dp, arr);

        return dp[i][j] = totalWaysByMovingLeft + totalWaysByMovingUp;
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

    public int tabulationSolution(int[][] obstacleGrid){
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];

        // If obstacle is at the (0,0), then we can't reach from (0,0) to (m-1,n-1) or vice-versa
        // If obstacle is at the (0,0), then entire dp[][] will be full of zeros (think carefully & logically)
        // Actually no need of this condition here, this will be insured in for loops
        if (obstacleGrid[0][0] == 1)
            return 0;

        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1){
                    dp[i][j] = 0;
                    continue;
                }
                if (i==0 && j==0) continue;

                int totalWaysByMovingUp = 0, totalWaysByMovingLeft = 0;
                if (i > 0) totalWaysByMovingUp = dp[i-1][j];
                if (j > 0) totalWaysByMovingLeft = dp[i][j-1];
                dp[i][j] = totalWaysByMovingLeft + totalWaysByMovingUp;
            }
        }
        return dp[m-1][n-1];
    }



    // ******************************** Space Optimized Solution ********************************
    // T.C -> O(mn)   (see previous question)
    // S.C -> O(n)
    public int spaceOptimizedSolution(int[][] obstacleGrid){
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[] dp = new int[n];
        dp[0] = 1;

        for (int i=0; i<m; i++){
            for(int j=0; j<n; j++){

                if (obstacleGrid[i][j] == 1){
                    dp[j] = 0;
                    continue;
                }
                if (i==0 && j==0) continue;

                int totalWaysByMovingUp = 0, totalWaysByMovingLeft = 0;

                if (i > 0) totalWaysByMovingUp = dp[j];
                if (j > 0) totalWaysByMovingLeft = dp[j-1];

                dp[j] = totalWaysByMovingLeft + totalWaysByMovingUp;
            }
        }
        return dp[n-1];
    }
}
