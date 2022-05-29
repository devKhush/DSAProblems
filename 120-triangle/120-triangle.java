class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];
        for (int[] row: dp)
            Arrays.fill(row, -1);
        
        for (int i = 0; i < n; i++){
            for (int j = 0; j <= i; j++){
                // Base case
                if (i==0 && j==0){
                    dp[0][0] = triangle.get(0).get(0);
                    continue;
                }
                
                int minPathByMovingUp = triangle.get(i).get(j);
                int minPathByMovingUpLeft = triangle.get(i).get(j);
                
                if (j <= i-1)
                    minPathByMovingUp += dp[i-1][j];
                else
                    minPathByMovingUp += Integer.MAX_VALUE/2;
                
                if (j > 0)
                    minPathByMovingUpLeft += dp[i-1][j-1];
                else
                    minPathByMovingUpLeft += Integer.MAX_VALUE/2;
                
                dp[i][j] = Math.min(minPathByMovingUp, minPathByMovingUpLeft);
            }
        }
        
        int minPathFromBottomToTop = Integer.MAX_VALUE;
        
        for (int j = 0; j < n; j++)
            minPathFromBottomToTop = Math.min(minPathFromBottomToTop, dp[n-1][j]);
        
        return minPathFromBottomToTop;
        
    }
}