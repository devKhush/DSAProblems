package Graphs.NumberOfIslands;
import java.util.ArrayDeque;
import java.util.Queue;

// https://takeuforward.org/graph/breadth-first-search-bfs-level-order-traversal/
// https://leetcode.com/problems/number-of-islands/description/
// https://www.geeksforgeeks.org/find-the-number-of-islands-using-dfs/
// https://youtu.be/muncqlKJrH0

public class NumberOfIslands {
    /************************************** Using DFS ******************************************
     * Time Complexity: O(8*m*n) = O(m*n)
     * Space Complexity: O(m*n) + O(m*n)
        * O(mn) for loops
        * another O(mn) for recursion stack space in DFS traversal
     */
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int[] di = {1, -1, 0, 0, 1, 1, -1, -1};
        int[] dj = {0, 0, 1, -1, 1, -1, 1, -1};

        int island = 0;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (grid[i][j] == '1'  && !visited[i][j]){
                    island++;
                    dfs(grid, i, j, m, n, visited, di, dj);
                }
            }
        }
        return island;
    }

    private void dfs(char[][] grid, int i, int j, int m, int n, boolean[][] visited, int[] di, int[] dj){
        visited[i][j] = true;

        for (int a = 0; a < 8; a++){
            int newI = i + di[a];
            int newJ = j + dj[a];
            if (newI>=0 && newJ>=0 && newI<m && newJ<n && grid[newI][newJ]=='1' && !visited[newI][newJ]){
                dfs(grid, newI, newJ, m, n, visited, di, dj);
            }
        }
    }


    /************************************** Using BFS ******************************************
     * Time Complexity: O(9*m*n) = O(mn)
     * Space Complexity: O(mn) + O(mn)
        * O(mn) for loops
        * another O(mn) for Queue size in BFS traversal
     */
    public int numOfIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        int island = 0;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (grid[i][j] == '1'  && !visited[i][j]){
                    island++;
                    bfs(grid, i, j, m, n, visited);
                }
            }
        }
        return island;
    }

    private void bfs(char[][] grid, int i, int j, int m, int n, boolean[][] visited){
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{i, j});
        visited[i][j] = true;

        while (!queue.isEmpty()){
            int I = queue.peek()[0];
            int J = queue.peek()[1];
            queue.remove();

            for (int newI = I - 1; newI <= I + 1; newI++){
                for (int newJ = J - 1; newJ <= J + 1; newJ++) {
                    if (newI >= 0 && newJ >= 0 && newI < m && newJ < n && grid[newI][newJ] == '1' && !visited[newI][newJ]) {
                        visited[newI][newJ] = true;
                        queue.add(new int[]{newI, newJ});
                    }
                }
            }
        }
    }
}
