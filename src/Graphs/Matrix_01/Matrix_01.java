package Graphs.Matrix_01;
import java.util.ArrayDeque;
import java.util.Queue;

// https://youtu.be/edXdVwkYHF8
// https://takeuforward.org/graph/distance-of-nearest-cell-having-1/
// https://leetcode.com/problems/01-matrix/description/
// https://www.geeksforgeeks.org/distance-nearest-cell-1-binary-matrix/

public class Matrix_01 {
    /************************************* My Solution **************************************
     * Intuition: Start BFS from the all the boundary having 1's
     * Time Complexity: O(4*mn) + O(4*mn)  ~  O(mn)
     * Space Complexity: O(mn) + O(mn)
        * O(mn) for BFS Queue
        * O(mn) for output/visited array
     */
    public int[][] updateMatrix(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] distances = new int[m][n];      // works like visited array
        int[] di = {1, -1, 0, 0};
        int[] dj = {0, 0, 1, -1};

        Queue<int[]> queue = new ArrayDeque<>();
        int dist = 1;
        for (int i = 0; i < m; i++){
            loop:
            for (int j = 0; j < n; j++){
                if (grid[i][j] == 1){
                    for (int a = 0; a < 4; a++){
                        int newI = i + di[a];
                        int newJ = j + dj[a];
                        if (newI >= 0 && newJ >= 0 && newI < m && newJ < n && grid[newI][newJ] == 0){
                            queue.add(new int[]{i, j});
                            distances[i][j] = dist;
                            continue loop;
                        }
                    }
                }
            }
        }

        dist++;
        while (!queue.isEmpty()){
            int size = queue.size();
            while (size-- > 0){
                int i = queue.peek()[0];
                int j = queue.peek()[1];
                queue.remove();

                for (int a = 0; a < 4; a++){
                    int newI = i + di[a];
                    int newJ = j + dj[a];
                    if (newI >= 0 && newJ >= 0 && newI < m && newJ < n && grid[newI][newJ] == 1 && distances[newI][newJ]==0){
                        distances[newI][newJ] = dist;
                        queue.add(new int[]{newI, newJ});
                    }
                }
            }
            dist++;
        }
        return distances;
    }


    /*************************************** Another Solution **********************************
     * Intuition: Start BFS from the all the cells with 0's and move to neighbour cells with 1's and
            update the distance.
     * Time Complexity: O(mn) + O(4*mn)  ~  O(mn)
     * Space Complexity: O(mn) + O(mn)
        * O(mn) for BFS Queue
        * O(mn) for output/visited array
     */
    public int[][] updateMatrix_V2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] di = {1, -1, 0, 0};
        int[] dj = {0, 0, 1, -1};

        int[][] distances = new int[m][n];          // works like visited array
        Queue<int[]> queue = new ArrayDeque<>();

        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (grid[i][j] == 0){
                    queue.add(new int[]{i, j});
                    distances[i][j] = 0;
                }
            }
        }

        int dist = 1;
        while (!queue.isEmpty()){
            int size = queue.size();
            while (size-- > 0){
                int i = queue.peek()[0];
                int j = queue.peek()[1];
                queue.remove();

                for (int a = 0; a < 4; a++){
                    int newI = i + di[a];
                    int newJ = j + dj[a];

                    if (newI >= 0 && newJ >= 0 && newI < m && newJ < n && grid[newI][newJ]==1 && distances[newI][newJ]==0){
                        distances[newI][newJ] = dist;
                        queue.add(new int[]{newI, newJ});
                    }
                }
            }
            dist++;
        }
        return distances;
    }
}
