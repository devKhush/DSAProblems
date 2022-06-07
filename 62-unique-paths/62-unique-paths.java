class Solution {
    
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (i == 0 && j == 0) continue;
                
                int moveUp = 0, moveLeft = 0;
                
                if (i > 0) moveUp = dp[i-1][j];
                if (j > 0) moveLeft = dp[i][j-1];
                
                dp[i][j] = moveUp + moveLeft;
            }
        }
        return dp[m-1][n-1];
    }
    
    
    // Memoization ************************************************************************
    public int uniquePaths_memo(int m, int n) {
        int[][] dp = new int[m][n];

        for (int[] row : dp)
            Arrays.fill(row, -1);
        
        return totalUniquePaths(m-1, n-1, dp);
    }
    
    public int totalUniquePaths(int i, int j, int[][]dp){
        if (i == 0 && j == 0)
            return 1;
        
        if (i < 0 || j < 0)
            return 0;
        
        if (dp[i][j] != -1)
            return dp[i][j];
        
        int moveUp = totalUniquePaths(i - 1, j, dp);
        int moveLeft = totalUniquePaths(i, j-1, dp);
        
        return dp[i][j] = moveUp + moveLeft;
    }
}