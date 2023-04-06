package Graphs.NumberOfClosedIslands;

// https://leetcode.com/problems/number-of-closed-islands/description/
// https://leetcode.com/problems/number-of-closed-islands/editorial/

public class NumberOfClosedIslands_DFS {
    /************************************ BFS Solution ******************************************
     * Intuition:
        * While traversing the island, we look to see if any node in the graph corresponds to a cell
            at the grid's boundary.
        * The island does not form a closed island if any node on it is on the grid's boundary.
        * Otherwise, a closed island is formed if there is no node on the grid's boundary.

     * TC -> O(mn) + O(4mn) ~ O(mn)
        * One_for_loop + DFS
     * SC -> O(mn) + O(mn) ~ O(mn)
        * Visited_array + DFS_Recursion_stack
     */
    public int closedIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int closeIslands = 0;

        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (grid[i][j] == 0  && !visited[i][j]){
                    if (dfs(i, j, m, n, grid, visited))
                        closeIslands++;
                }
            }
        }
        return closeIslands;
    }

    private boolean dfs(int i, int j, int m, int n, int[][] grid, boolean[][] visited){
        if (i < 0 || j < 0 || i >= m || j >= n)
            return false;
        if (grid[i][j] == 1 || visited[i][j])
            return true;

        visited[i][j] = true;

        int[] di = {0, 1, 0, -1};
        int[] dj = {-1, 0, 1, 0};
        boolean isClosed = true;
        for (int a = 0; a < 4; a++) {
            int r = i + di[a];
            int c = j + dj[a];
            if (!dfs(r, c, m, n, grid, visited)) {
                isClosed = false;
            }
        }
        return isClosed;
    }
}
