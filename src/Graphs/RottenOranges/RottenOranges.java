package Graphs.RottenOranges;
import java.util.ArrayDeque;
import java.util.Queue;

// https://youtu.be/yf3oUhkvqA0
// https://practice.geeksforgeeks.org/problems/rotten-oranges2536/1
// https://leetcode.com/problems/rotting-oranges/description/
// https://www.geeksforgeeks.org/minimum-time-required-so-that-all-oranges-become-rotten/
// https://takeuforward.org/data-structure/rotten-oranges/

public class RottenOranges {
    /*********************************** BFS Solution *********************************************
     * Time Complexity: O(4*mn) + O(mn)  ~  O(mn)
     * Space Complexity: O(mn)
        * Queue size in worst case
     */
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] di = { 1, -1, 0, 0 };
        int[] dj = { 0, 0, 1, -1 };

        int time = 0;
        int freshOranges = 0, orangesRotten = 0;
        Queue<int[]> queue = new ArrayDeque<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2)
                    queue.add(new int[] { i, j });
                if (grid[i][j] == 1)
                    freshOranges++;
            }
        }

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int $ = 0; $ < size; $++) {
                int i = queue.peek()[0];
                int j = queue.peek()[1];
                queue.remove();

                for (int a = 0; a < 4; a++) {
                    int newI = i + di[a];
                    int newJ = j + dj[a];
                    if (newI >= 0 && newJ >= 0 && newI < m && newJ < n && grid[newI][newJ] == 1) {
                        grid[newI][newJ] = 2;                   // rot fresh orange
                        orangesRotten++;
                        queue.add(new int[] { newI, newJ });    // add to queue
                    }
                }
            }
            if (!queue.isEmpty())
                time++;
        }
        // if all fresh orange couldn't be rotten, return -1
        return freshOranges == orangesRotten ? time : -1;
    }
}
