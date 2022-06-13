class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        
        int[][] dp = new int[n][n];
        for (int[] row : dp)
            Arrays.fill(row, -1);
        
        return minPath_Memoization(0, 0, triangle, dp, n);
    }
    
    private int minPath_Memoization(int i, int j, List<List<Integer>> triangle, int[][] dp, int n){
        if (i == n-1)
            return triangle.get(n-1).get(j);
        
        if (dp[i][j] != -1)
            return dp[i][j];
        
        int moveDown =  triangle.get(i).get(j) +  minPath_Memoization(i + 1, j, triangle, dp, n);
        int moveDownRight = triangle.get(i).get(j) + minPath_Memoization(i + 1, j + 1, triangle, dp, n);
            
        return dp[i][j] = Math.min(moveDownRight, moveDown);
    }
}