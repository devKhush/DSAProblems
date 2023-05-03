package Graphs.MaximumNumberOfFishInGrid;
import java.util.ArrayDeque;
import java.util.Queue;

// https://leetcode.com/problems/maximum-number-of-fish-in-a-grid/submissions/

public class MaximumNumberOfFishInGrid {
    /************************************** DFS Solution ***********************************************
     * Simple DFS Algorithm
     * Time Complexity: O(m*n)
     * Space Complexity: O(m*n)
     */
    public int findMaxFish_dfs(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int maxFishSum = 0;

        for (int i = 0; i < m; i++){
            for (int j = 0; j <n; j++){
                if (grid[i][j] != 0){
                    int currFishSum = dfs(i, j, grid, visited);
                    maxFishSum = Math.max(maxFishSum, currFishSum);
                }
            }
        }
        return maxFishSum;
    }

    private int dfs(int i, int j, int[][] grid, boolean[][] visited) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0 || visited[i][j])
            return 0;

        visited[i][j] = true;
        int fishSum = grid[i][j];
        fishSum += dfs(i + 1, j, grid, visited);
        fishSum += dfs(i - 1, j, grid, visited);
        fishSum += dfs(i, j + 1, grid, visited);
        fishSum += dfs(i, j - 1, grid, visited);
        return fishSum;
    }


    /************************************** BFS Solution ***********************************************
     * Simple BFS Algorithm
     * Time Complexity: O(m*n)
     * Space Complexity: O(m*n)
     */
    public int findMaxFish_bfs(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int maxFishSum = 0;

        int[] di = {1, -1, 0, 0};
        int[] dj = {0, 0, 1, -1};

        for (int i = 0; i < m; i++){
            for (int j = 0; j <n; j++){
                if (grid[i][j] != 0){
                    Queue<int[]> queue = new ArrayDeque<>();
                    queue.add(new int[]{i, j});
                    visited[i][j] = true;
                    int currFishSum = grid[i][j];

                    while (!queue.isEmpty()){
                        int r = queue.peek()[0];
                        int c = queue.remove()[1];

                        for (int a = 0; a < 4; a++){
                            int new_r = r + di[a];
                            int new_c = c + dj[a];
                            if (new_r >= 0 && new_c >= 0 && new_r < m && new_c < n && grid[new_r][new_c] != 0 && !visited[new_r][new_c]){
                                queue.add(new int[]{new_r , new_c});
                                visited[new_r][new_c] = true;
                                currFishSum += grid[new_r][new_c];
                            }
                        }
                    }
                    maxFishSum = Math.max(maxFishSum, currFishSum);
                }
            }
        }
        return maxFishSum;
    }
}
