package DynamicProgramming.DP_on_Grids.CherryPickup_II_3D_DP;

// https://youtu.be/QGfn7JeXK54
// https://takeuforward.org/data-structure/3-d-dp-ninja-and-his-friends-dp-13/

public class CherryPickUp_Recursion {

    // *********************************** Recursion solution ***************************************
    // See notes for explanation
    // At max both the person will travel the no. of rows, each of them have three possibilities to move
    // in thr grid (down, downLeft, downRight). So, total there are nine possibility of moving for both.
    // In each call (in every row), each person (has 3 options) will move down in three ways.
    // So, if these are m rows, each person will take 3^m time to reach the bottom row

    // Time complexity --> O(3^m * 3^m) = O(9^m) = exponential T.C.
    // Space complexity --> O(m) Recursion stack space, at most m calls (equal to m row)
    public int cherryPickup(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        return f(0, 0, n-1, m, n, grid);
    }

    public int f(int i, int j1, int j2, int m, int n, int[][] grid){
        if (j1 < 0 || j2 < 0 || j1 >= n || j2 >= n)
            return Integer.MIN_VALUE;

        if (i == m - 1)
            return j1 != j2 ? grid[i][j1] + grid[i][j2] : grid[i][j1];

        int maxCherry = Integer.MIN_VALUE;
        for (int dj1 = -1; dj1 <= 1; dj1++) {
            for (int dj2 = -1; dj2 <= 1; dj2++) {

                int currCherry = j1 != j2 ? grid[i][j1] + grid[i][j2] : grid[i][j1];
                int nextCherries = f(i + 1, j1 + dj1, j2 + dj2, m, n, grid);
                maxCherry = Math.max(maxCherry, nextCherries + currCherry);
            }
        }
        return maxCherry;
    }
}
