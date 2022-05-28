class Solution {
     public int uniquePathsWithObstacles(int[][] obstacleGrid) {
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