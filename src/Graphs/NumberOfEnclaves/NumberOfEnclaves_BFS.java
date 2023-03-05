package Graphs.NumberOfEnclaves;
import java.util.ArrayDeque;
import java.util.Queue;

// Similar Problem: "Surrounding Regions"
// https://leetcode.com/problems/number-of-enclaves/description/
// https://takeuforward.org/graph/number-of-enclaves/
// https://youtu.be/rxKcepXQgU4

public class NumberOfEnclaves_BFS {
    /*********************************** DFS Solution ******************************************
     * Time Complexity: O(mn)+ O(4*mn) + O(mn) ~  O(mn)
     * Space Complexity: O(mn) + O(mn)
         * BFS Queue + visited array
     */
    public int numEnclaves(int[][] grid) {
        int m = grid.length;
        int n =  grid[0].length;
        int[] di = {1, -1, 0, 0};
        int[] dj = {0, 0, 1, -1};
        boolean[][] visited = new boolean[m][n];

        // Start BFS on all the boundary 1's cells
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if ((i == 0 || j == 0 || i == m-1 || j == n-1)  &&  grid[i][j] == 1){
                    queue.add(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        // Visit all the 1's cells reachable to boundary 1's cells
        while (!queue.isEmpty()){
            int i = queue.peek()[0];
            int j = queue.remove()[1];

            for (int a = 0; a < 4; a++){
                int newI = i + di[a];
                int newJ = j + dj[a];
                if (newI >= 0 && newJ >= 0 && newI < m && newJ < n && grid[newI][newJ] == 1 && !visited[newI][newJ]){
                    visited[newI][newJ] = true;
                    queue.add(new int[]{newI, newJ});
                }
            }
        }

        // Count no. of 1's cells not reachable to boundary 1's cells
        int land = 0;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (grid[i][j] == 1 && !visited[i][j])
                    land++;
            }
        }
        return land;
    }
}
