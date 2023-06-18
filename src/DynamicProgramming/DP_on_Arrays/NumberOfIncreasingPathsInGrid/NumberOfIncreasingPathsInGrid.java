package DynamicProgramming.DP_on_Arrays.NumberOfIncreasingPathsInGrid;
import java.util.Arrays;

// https://leetcode.com/problems/number-of-increasing-paths-in-a-grid/description/
// https://leetcode.com/problems/number-of-increasing-paths-in-a-grid/editorial/
// https://youtu.be/7txOymD4EiA
// Standard Easy DP Problem

public class NumberOfIncreasingPathsInGrid {
    /********************************* Graph + DFS + DP *************************************************
     * Time complexity: O(m*n)
        * Graph DFS
     * Space complexity: O(m*n)
        * DP_Array + Recursion_Stack_Space
     */
    int MOD = (int)(1e9 + 7);
    public int countPaths(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int[] row : dp)
            Arrays.fill(row, -1);

        int totalIncreasingPaths = 0;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                totalIncreasingPaths = (totalIncreasingPaths + dfs(i, j, grid, m, n, dp)) % MOD;
            }
        }
        return totalIncreasingPaths;
    }

    private int dfs(int i, int j, int[][] grid, int m, int n, int[][] dp){
        if (dp[i][j] != -1)
            return dp[i][j];
        int increasingPaths = 1;
        int[] di = {0, 0, -1, +1};
        int[] dj = {-1, +1, 0, 0};

        for (int a = 0; a < 4; a++){
            int I = i + di[a];
            int J = j + dj[a];
            if (I >= 0 && J >= 0 && I < m && J < n && grid[I][J] > grid[i][j])
                increasingPaths = (increasingPaths + dfs(I, J, grid, m, n, dp)) % MOD;
        }
        return dp[i][j] = increasingPaths;
    }
}
