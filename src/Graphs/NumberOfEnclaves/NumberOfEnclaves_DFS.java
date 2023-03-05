package Graphs.NumberOfEnclaves;

// Similar Problem: "Surrounding Regions"
// https://leetcode.com/problems/number-of-enclaves/description/
// https://takeuforward.org/graph/number-of-enclaves/
// https://youtu.be/rxKcepXQgU4

public class NumberOfEnclaves_DFS {
    /*********************************** DFS Solution ******************************************
     * Time Complexity: O(mn) + O(m) + O(n) + O(4*mn)  ~  O(mn)
        *
     * Space Complexity: O(mn) + O(mn)
        * Recursion stack space + visited array
     */
    public int numEnclaves(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        // total lands
        int totalLands = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1)
                    totalLands++;
            }
        }

        // check for boundary lands in first and last column, and subtract lands that can be reached to boundary
        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 1 && !visited[i][0])
                totalLands -= dfs(i, 0, grid, visited);

            if (grid[i][n - 1] == 1 && !visited[i][n - 1])
                totalLands -= dfs(i, n - 1, grid, visited);
        }

        // check for boundary lands in first and last row, and subtract lands that can be reached to boundary
        for (int j = 0; j < n; j++) {
            if (grid[0][j] == 1 && !visited[0][j])
                totalLands -= dfs(0, j, grid, visited);

            if (grid[m - 1][j] == 1 && !visited[m - 1][j])
                totalLands -= dfs(m - 1, j, grid, visited);
        }
        return totalLands;
    }

    private int dfs(int i, int j, int[][] grid, boolean[][] visited) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] != 1 || visited[i][j])
            return 0;

        visited[i][j] = true;
        int landsVisited = 1;
        landsVisited += dfs(i - 1, j, grid, visited);
        landsVisited += dfs(i + 1, j, grid, visited);
        landsVisited += dfs(i, j + 1, grid, visited);
        landsVisited += dfs(i, j - 1, grid, visited);
        return landsVisited;
    }
}
