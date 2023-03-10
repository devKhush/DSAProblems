package Graphs.ShortestPathInBinaryMatrix;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

// PRE-REQUISITE: Shortest Path in Undirected Graph with Unit Weights
// This is 2D version of that problem
// https://youtu.be/U5Mw4eyUmw4
// https://leetcode.com/problems/shortest-path-in-binary-matrix/
// https://takeuforward.org/data-structure/g-36-shortest-distance-in-a-binary-maze/

public class ShortestPathInBinaryMatrix {
    /************************************ BFS Solution 1 ***********************************************
     * Time Complexity: O(n * n) + O(8 * n * n)  ~  O(n * n)
     * Space Complexity: O(2 * n * n)
        * Queue and Shortest_Path_array
     */
    public int shortestPath_v1(int[][] grid, int[] src, int[] dest) {
        int n = grid.length;
        int[] di = {+1, -1, 0, 0, +1, +1, -1, -1};
        int[] dj = {0, 0, +1, -1, +1, -1, +1, -1};

        int[][] shortestPath = new int[n][n];
        for (int[] row : shortestPath)
            Arrays.fill(row, (int)1e7);
        shortestPath[0][0] = 1;

        Queue<int[]> queue = new ArrayDeque<>();
        if (grid[src[0]][src[1]] == 0)
            queue.add(src);

        while (!queue.isEmpty()){
            int[] node = queue.remove();
            if (node[0]==dest[0] && node[1]==dest[1])
                return shortestPath[dest[0]][dest[1]];

            for (int a = 0; a < 8; a++){
                int i = node[0] + di[a];
                int j = node[1] + dj[a];
                if (i >= 0 && j >= 0 && i < n && j < n && grid[i][j] == 0
                        && shortestPath[i][j] > shortestPath[node[0]][node[1]] + 1){
                    shortestPath[i][j] = shortestPath[node[0]][node[1]] + 1;
                    queue.add(new int[]{i, j});
                }
            }
        }
        return -1;
    }


    /**************************************** BFS Solution 2 ************************************
     * Time Complexity:  O(8 * n * n)  ~  O(n * n)
     * Space Complexity: O(2 * n * n)  ~  O(n * n)
     */
    public int shortestPath_v2(int[][] grid, int[] src, int[] dest) {
        int n = grid.length;
        if (grid[src[0]][src[1]] == 1 || grid[dest[0]][dest[1]] == 1)
            return -1;

        int[] di = {+1, -1, 0, 0, +1, +1, -1, -1};
        int[] dj = {0, 0, +1, -1, +1, -1, +1, -1};

        // Visited array
        boolean[][] visited = new boolean[n][n];

        // BFS Queue
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(src);
        visited[src[0]][src[1]] = true;
        int distance = 1;

        // Standard BFS
        while (!queue.isEmpty()){
            int size = queue.size();
            while (size-- > 0){
                int[] node = queue.remove();
                if (node[0] == dest[0] && node[1] == dest[1])
                    return distance;

                for (int a = 0; a < 8; a++){
                    int i = node[0] + di[a];
                    int j = node[1] + dj[a];

                    if (i >= 0 && j >= 0 && i < n && j < n && grid[i][j] == 0 && !visited[i][j]){
                        visited[i][j] = true;
                        queue.add(new int[]{i, j});
                    }
                }
            }
            distance++;
        }
        return -1;
    }
}
