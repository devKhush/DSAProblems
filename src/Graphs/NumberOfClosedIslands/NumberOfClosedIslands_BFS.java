package Graphs.NumberOfClosedIslands;
import java.util.ArrayDeque;
import java.util.Queue;

// https://leetcode.com/problems/number-of-closed-islands/description/
// https://leetcode.com/problems/number-of-closed-islands/editorial/

public class NumberOfClosedIslands_BFS {
    /************************************ BFS Solution ******************************************
     * Intuition:
        * While traversing the island, we look to see if any node in the graph corresponds to a cell
            at the grid's boundary.
        * The island does not form a closed island if any node on it is on the grid's boundary.
        * Otherwise, a closed island is formed if there is no node on the grid's boundary.

     * TC -> O(mn) + O(4mn) ~ O(mn)
        * One_for_loop + BFS
     * SC -> O(mn) + O(mn) ~ O(mn)
        * Visited_array + BFS_Queue
     */
    public int closedIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int closeIslands = 0;

        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (grid[i][j] == 0  && !visited[i][j]){
                    if (bfs(i, j, m, n, grid, visited))
                        closeIslands++;
                }
            }
        }
        return closeIslands;
    }

    private boolean bfs(int i, int j, int m, int n, int[][] grid, boolean[][] visited){
        int[] di = {1, -1, 0, 0};
        int[] dj = {0, 0, 1, -1};

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{i, j});
        visited[i][j] = true;
        boolean isClosed = true;

        while (!queue.isEmpty()){
            int I = queue.peek()[0];
            int J = queue.remove()[1];

            for (int a = 0; a < 4; a++){
                int newI = I + di[a];
                int newJ = J + dj[a];
                if (newI < 0 || newI >= m || newJ < 0 || newJ >= n)
                    isClosed = false;
                else if (grid[newI][newJ] == 0  &&  !visited[newI][newJ]){
                    queue.add(new int[]{newI, newJ});
                    visited[newI][newJ] = true;
                }
            }
        }
        return isClosed;
    }
}
