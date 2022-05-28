class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        int[][] dp = new int[m][n];
        for (int[] row: dp){
            Arrays.fill(row, -1);
        }
        return memoizationSolution(m-1, n-1, grid, dp);

    }
    
    private int memoizationSolution(int i, int j, int[][] grid, int[][] dp){
        if (i==0 && j==0)
            return grid[0][0];
        if (i<0 || j<0)
            return Integer.MAX_VALUE/2;
        if (dp[i][j] != -1)
            return dp[i][j];

        int minPathByMovingUp = memoizationSolution(i-1, j, grid, dp) + grid[i][j];
        int minPathByMovingLeft = memoizationSolution(i, j-1, grid, dp) + grid[i][j];

        return dp[i][j] = Math.min(minPathByMovingUp, minPathByMovingLeft);
    }
}