class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
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