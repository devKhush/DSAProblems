package DynamicProgramming.CherryPickup_II_3D_DP;

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

        // We only store the columns j1 & j2 in DP array & not row numbers
        int[][] dp = new int[n][n];

        // Base cases
        // if j1==j2 (both person came to same position in last row), then only one person can pick up the cherry from that position
        // else (both person are at diff. position in last row), then both person can pick up the cherry from their position
        for (int j1 = 0; j1 < n; j1++) {
            for (int j2 = 0; j2 < n; j2++)
                dp[j1][j2] = j1 == j2 ? grid[m-1][j1] : grid[m-1][j1] + grid[m-1][j2];
        }

        // Other cases
        // Inner nested loops to try out 9 options
        for (int i = m-2; i >=0; i--){
            int[][] tempDP = new int[n][n];

            for (int j1 = 0; j1 < n; j1++) {
                for (int j2 = 0; j2 < n; j2++) {

                    int maxCherryPicked = -1;

                    for (int dj1 = -1; dj1 <=1; dj1++){
                        for (int dj2 = -1; dj2 <= 1; dj2++){

                            boolean isOutOfBoundary = j1 + dj1 < 0 || j2 + dj2 < 0 || j1 + dj1 >=n || j2 + dj2 >= n;
                            if (isOutOfBoundary)
                                continue;

                            int currCherryPicked;
                            if (j1 == j2)
                                currCherryPicked = grid[i][j1] + dp[j1 + dj1][j2 + dj2];
                            else
                                currCherryPicked = grid[i][j1] + grid[i][j2] + dp[j1 + dj1][j2 + dj2];

                            maxCherryPicked = Math.max(maxCherryPicked, currCherryPicked);
                        }
                    }

                    tempDP[j1][j2] = maxCherryPicked;
                }
            }
            dp = tempDP;
        }

        // Both person starts from here, one from (0,0) & other from (0,n-1)
        // So, answer will be stored in dp[0][n-1]
        return dp[0][n-1];
    }
}
