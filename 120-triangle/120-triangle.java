class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        // Triangle is Right angle triangle with equal base and height (both of n)
        int n = triangle.size();
        int minPathSum = Integer.MAX_VALUE;
        int[][] dp = new int[n][n];
        
        for (int[] row : dp)
            Arrays.fill(row, -1);
        

        // Since the minimum path sum can be form (0,0) to any element in the last row of triangle
        // So, we need to find the minimum path sum from (0,0) to every element (index) in the last row
        // and take minimum of that
        for (int j = 0; j < n; j++){
            int pathSumBySelecting_ith_ElementInLastRow = memoizationSolution(triangle, n-1, j, dp);
            minPathSum = Math.min(minPathSum, pathSumBySelecting_ith_ElementInLastRow);
        }

        return minPathSum;
    }
    
   // (i,j) --> (ith Row, jth column)
    public int memoizationSolution(List<List<Integer>> arr, int i, int j, int[][] dp){
        // Base case when at (0,0) (only element in 0th row), consider it in the current path
        if (i==0 && j==0) {
            dp[0][0] = arr.get(i).get(j);
            return dp[0][0];
        }

        // Last condition can happen when we move from straight up in same column at last index
        // for example from (1,1) to (0,1)
        if (i<0 || j<0 || j >= arr.get(i).size())
            return Integer.MAX_VALUE/2;

        // If this answer has been calculated previously then return it
        if (dp[i][j] != -1)
            return dp[i][j];

        // Now we move straight up in the triangular grid and consider the current path cost into consideration
        int minPathByMovingUp = memoizationSolution(arr, i-1, j, dp) + arr.get(i).get(j);

        // Now we move diagonally (up & left) in the triangular grid and consider the current path cost into consideration
        int minPathByMovingUpLeft = memoizationSolution(arr, i-1, j-1, dp) + arr.get(i).get(j);

        // Take minimum of the path sum of Straight-up path & diagonally (left-up) path
        int minPathAt_i_j_Index = Math.min(minPathByMovingUp, minPathByMovingUpLeft);

        return dp[i][j] = minPathAt_i_j_Index;
    }
}