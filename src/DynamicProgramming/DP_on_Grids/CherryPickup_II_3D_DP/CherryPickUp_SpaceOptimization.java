package DynamicProgramming.DP_on_Grids.CherryPickup_II_3D_DP;

// https://youtu.be/QGfn7JeXK54
// https://takeuforward.org/data-structure/3-d-dp-ninja-and-his-friends-dp-13/

public class CherryPickUp_SpaceOptimization {

    // *********************************** Space Optimization ***********************************
    // Overlapping sub-problems will take O(1) time to get from DP array
    // There are three for loops for i, j1, j2
    // Time Complexity ==> O(number of different states) = O(M * N * N) * 9 = O(M * N * N)   (9 due to two for loops)
    // One observation that we need only next row, to compute current row values
    // Space Complexity ==> O(N*N)   DP array (Space Optimization)

    private int cherryPickup_SpaceOptimized(int[][] grid){
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[n][n];

        for (int j1 = 0; j1 < n; j1++) {
            for (int j2 = 0; j2 < n; j2++) {
                dp[j1][j2] = j1 != j2 ? grid[m - 1][j1] + grid[m - 1][j2] : grid[m - 1][j1];
            }
        }

        for (int i = m - 2; i >= 0; i--) {
            int[][] currDP = new int[n][n];

            for (int j1 = 0; j1 < n; j1++) {
                for (int j2 = 0; j2 < n; j2++) {
                    int maxCherries = Integer.MIN_VALUE;

                    for (int dj1 = -1; dj1 <= 1; dj1++) {
                        for (int dj2 = -1; dj2 <= 1; dj2++) {
                            if (j1 + dj1 < 0 || j1 + dj1 >= n || j2 + dj2 < 0 || j2 + dj2 >= n)
                                continue;
                            int currCherries = j1 != j2 ? grid[i][j1] + grid[i][j2] : grid[i][j1];
                            int nextCherries = dp[j1 + dj1][j2 + dj2];
                            maxCherries = Math.max(maxCherries, currCherries + nextCherries);
                        }
                    }
                    currDP[j1][j2] = maxCherries;
                }
            }
            dp = currDP;
        }
        return dp[0][n-1];
    }
}
