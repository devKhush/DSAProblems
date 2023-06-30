package Graphs.LastDayWhereYouCanStillCross;

// https://leetcode.com/problems/last-day-where-you-can-still-cross/description/
// https://leetcode.com/problems/last-day-where-you-can-still-cross/editorial/

public class BinarySearch_DFS {
    /*************************************** Binary Search + DFS *********************************
     * Somewhat Fast  Solution (60%)

     * Time complexity: O(row * col * log(row * col))
        * The binary search over a search space of size m*n takes O(log(m*n)) steps to find the last day
            that we can still cross. The size of our search space is row*col.
        * The DFS method visits each cell at most once, which takes O(row*col) time.

     * Space complexity: O(row⋅ * col)
        * We need to build two 2-D array of size row×col.
        * The recursion call stack from the DFS could use up to O(row × col) space.
     */
    int[][] grid;
    int row, col;

    public int latestDayToCross(int row, int col, int[][] cells) {
        this.grid = new int[row][col];
        this.row = row;
        this.col = col;

        int low = 0, high = row*col - 1;
        while (low <= high){
            int mid = low + ((high - low)>>1);

            // Apply smartness, make grid values 1 from low to mid
            for (int i = low; i <= mid; i++){
                grid[cells[i][0] - 1][cells[i][1] - 1] = 1;
            }

            // If we can reach bottom, no need to make values from "low to mid" to 0
            if (canReachBottom()) {
                low = mid + 1;
            }
            // If can't reach bottom, we must set "low to mid" values to 0, as we don't know the exact day.
            else{
                high = mid - 1;
                for (int i = low; i <= mid; i++){
                    grid[cells[i][0] - 1][cells[i][1] - 1] = 0;
                }
            }
        }
        return high + 1;
    }

    public boolean canReachBottom(){
        boolean[][] visited = new boolean[row][col];
        for (int j = 0; j < col; j++){
            if (dfs(0, j, visited))
                return true;
        }
        return false;
    }

    public boolean dfs(int i, int j, boolean[][] visited){
        if (i < 0 || j < 0 || i >= row || j >= col || grid[i][j] != 0 || visited[i][j])
            return false;
        if (i == row - 1)
            return true;

        visited[i][j] = true;
        return  dfs(i + 1, j, visited) || dfs(i - 1, j, visited) ||
                dfs(i, j + 1, visited) || dfs(i, j - 1, visited);
    }
}
