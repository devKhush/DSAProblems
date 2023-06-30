package Graphs.LastDayWhereYouCanStillCross;
import java.util.*;

public class BinarySearch_BFS {
    /*************************************** Binary Search + BFS *********************************
     * Slowest Solution (30%)

     * Time complexity: O(row * col * log(row * col))
        * The binary search over a search space of size m*n takes O(log(m*n)) steps to find the last day
            that we can still cross. The size of our search space is row*col.
        * The BFS method visits each cell at most once, which takes O(row*col) time.

     * Space complexity: O(row⋅ * col)
        * We need to build two 2-D array of size row×col.
        * The queue space from the BFS could use up to O(row × col) space.
     */
    int row, col;
    public int latestDayToCross(int row, int col, int[][] cells) {
        this.row = row;
        this.col = col;

        int low = 0, high = row*col - 1;
        while (low <= high){
            int mid = low + ((high - low)>>1);
            if (canReachTopToBottom(cells, mid))
                low = mid + 1;
            else
                high = mid - 1;
        }
        return high + 1;
    }

    private boolean canReachTopToBottom(int[][] cells, int uptoDay){
        int[][] grid = new int[row][col];
        for (int day = 0; day <= uptoDay; day++){
            grid[cells[day][0] - 1][cells[day][1] - 1] = 1;
        }

        int[] di = {1, -1, 0, 0};
        int[] dj = {0, 0, 1, -1};
        Queue<int[]> queue = new ArrayDeque<>();
        for (int j = 0; j < col; j++){
            if (grid[0][j] == 0) {
                queue.add(new int[]{0, j});
                grid[0][j] = -1;
            }
        }

        while (!queue.isEmpty()){
            int i = queue.peek()[0];
            int j = queue.remove()[1];
            if (i == row - 1)
                return true;

            for (int a = 0; a < 4; a++){
                int nextI = i + di[a];
                int nextJ = j + dj[a];
                if (nextI < 0 || nextJ < 0 || nextI >= row || nextJ >= col || grid[nextI][nextJ] != 0)
                    continue;
                queue.add(new int[]{nextI, nextJ});
                grid[nextI][nextJ] = -1;
            }
        }
        return false;
    }
}
