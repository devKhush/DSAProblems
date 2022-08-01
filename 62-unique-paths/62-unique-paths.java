class Solution {
    public int uniquePaths(int m, int n) {
        /*
        // Memoization Solution
        Integer[][] dp = new Integer[m][n];
        return findTotalPaths(m - 1, n - 1, dp);
        */
        
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (i == 0 && j == 0) continue;
                
                if (i > 0)
                    dp[i][j] += dp[i - 1][j];
                if (j > 0)
                    dp[i][j] += dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
        
    }
    
    public int findTotalPaths(int i, int j, Integer[][] dp){
        if (i < 0 || j < 0)
            return 0;
        
        if (i == 0 && j == 0)
            return dp[i][j] = 1;
        
        if (dp[i][j] != null)
            return dp[i][j];

        return dp[i][j] = findTotalPaths(i - 1, j, dp) + findTotalPaths(i, j - 1, dp);
    }
}