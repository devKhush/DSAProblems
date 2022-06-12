class Solution {
    public int minPathCost(int[][] grid, int[][] moveCost) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        for (int[] row : dp)
            Arrays.fill(row, -1);

        int minCost = Integer.MAX_VALUE;

        for (int j = 0; j < n; j++){
            minCost = Math.min(minCost, minPath(0, j, grid, moveCost, dp, m, n));
        }
        return minCost;
    }

    public int minPath(int i, int j, int[][] arr, int[][] moveCost, int[][] dp, int m, int n){
        if (j < 0 || j >= n)
            return Integer.MAX_VALUE;

        if (i == m-1)
            return arr[m-1][j];

        if (dp[i][j] != -1)
            return dp[i][j];

        int minPathCost = Integer.MAX_VALUE;

        for (int dj = 0; dj < n; dj++){
            int cellValue = arr[i][j];
            int pathCost = moveCost[cellValue][dj] + minPath(i+1, dj, arr, moveCost, dp, m, n);
            int currPathCost = cellValue + pathCost;

            minPathCost = Math.min(minPathCost, currPathCost);
        }

        return dp[i][j] = minPathCost;
    }
}