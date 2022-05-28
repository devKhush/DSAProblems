class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] dp = new int[n];
        
        for (int i=0; i<m; i++){
            for (int j=0; j<n; j++){
                
                if (i==0 && j==0){
                    dp[0] = grid[0][0];
                    continue;
                }
                
                int minPathByMovingUp = i > 0 ? dp[j] + grid[i][j] : Integer.MAX_VALUE/2;
                int minPathByMovingLeft = j > 0 ? dp[j-1] + grid[i][j] : Integer.MAX_VALUE/2;
                
                dp[j] = Math.min(minPathByMovingLeft, minPathByMovingUp);
            }
        }        
        return dp[n-1];
    }
}