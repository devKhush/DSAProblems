class Solution {
     public int uniquePathsWithObstacles(int[][] obstacleGrid) {
         int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];

        // If obstacle is at the (0,0), then we can't reach from (0,0) to (m-1,n-1) or vice-versa
        if (obstacleGrid[0][0] == 1)
            return 0;
        
        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i==0 && j==0)
                    continue;
                
                if (obstacleGrid[i][j] == 1){
                    dp[i][j] = 0;
                    continue;
                }
                
                int totalWaysByMovingUp = 0, totalWaysByMovingLeft = 0;
                if (i > 0) totalWaysByMovingUp = dp[i-1][j];
                if (j > 0) totalWaysByMovingLeft = dp[i][j-1]; 
                dp[i][j] = totalWaysByMovingLeft + totalWaysByMovingUp;
            }
        }
        
        return dp[m-1][n-1];
    }
    
    public int uniquePathsWithObstacles_Memoization(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        
        
        int[][] dp = new int[m][n];
        for (int[] row : dp)
            Arrays.fill(row, -1);
        
        return solve(m-1, n-1, dp, obstacleGrid);
    }
    
    public int solve(int i, int j, int[][] dp, int[][] arr){
        if (i<0 || j<0)
            return 0;
        
        if (arr[i][j] == 1)
            return 0;
        
        if (i==0 && j==0)
            return 1;
        
        if (dp[i][j] != -1)
            return dp[i][j];
        
        int totalWaysByMovingUp = solve(i-1, j, dp, arr);      
        int totalWaysByMovingLeft = solve(i, j-1, dp, arr);

        return dp[i][j] = totalWaysByMovingLeft + totalWaysByMovingUp;
    }
}