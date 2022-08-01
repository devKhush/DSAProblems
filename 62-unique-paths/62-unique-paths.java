class Solution {
    public int uniquePaths(int m, int n) {
        Integer[][] dp = new Integer[m][n];
        
        return findTotalPaths(m - 1, n - 1, dp);
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