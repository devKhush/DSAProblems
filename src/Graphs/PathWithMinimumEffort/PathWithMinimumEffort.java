package Graphs.PathWithMinimumEffort;
import java.util.Arrays;
import java.util.PriorityQueue;

// https://youtu.be/0ytpZyiZFhA
// https://leetcode.com/problems/path-with-minimum-effort/description/
// https://takeuforward.org/data-structure/g-37-path-with-minimum-effort/

public class PathWithMinimumEffort {
    /****************************************** BFS Solution ****************************************
     * Intuition: Dijkstra Algorithm has been used.
     * If we saw a path_1 previous having a greater effort, then we later saw another path_1 passing
        through path_1. Then, we ignore the previous effort of path_1, and include the effort of path_2
        in path_1. Because at the end we need path with smaller effort. So, no need to keep the
        previous effort of path_1.

     * Time Complexity: O(E * log(E))   =  O(4 * m * n * log(4 * m * n))  ~  O(mn * log(mn))
        * Number of edges are 4*m*n
     * Space Complexity: O(mn) + O(E)  ~  O(mn)
        * PQ_size and Shortest_path_array
     */
    public int minimumEffortPath(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        int[] di = {1, -1, 0, 0};
        int[] dj = {0, 0, 1, -1};

        // Shortest distance array
        int[][] efforts = new int[m][n];
        for (int[] row : efforts){
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        efforts[0][0] = 0;

        // MinHeap PQ
        PriorityQueue<Pair> minHeap = new PriorityQueue<>((a, b) -> (a.effort - b.effort));
        minHeap.add(new Pair(0, 0, 0));

        // Dijkstra Algorithm
        while (!minHeap.isEmpty()){
            Pair node = minHeap.remove();
            int i = node.i;
            int j = node.j;
            int effort = node.effort;

            // If the node with min effort from minHeap is destination node.
            // Then this is the least effort path to dest. node, because we are storing in minHeap.
            if (i == m-1 && j == n-1)
                return effort;

            for (int a = 0; a < 4; a++){
                int newI = i + di[a];
                int newJ = j + dj[a];
                if (newI >= 0 && newJ >= 0 && newI < m && newJ < n){
                    int pathEffort = Math.max(effort, Math.abs(heights[i][j] - heights[newI][newJ]));
                    if (efforts[newI][newJ] > pathEffort){
                        efforts[newI][newJ] = pathEffort;
                        minHeap.add(new Pair(newI, newJ, pathEffort));
                    }
                }
            }
        }
        return 0;
    }


    static class Pair{
        int i, j, effort;
        public Pair(int i, int j, int effort) {
            this.i = i;
            this.j = j;
            this.effort = effort;
        }
    }
}
