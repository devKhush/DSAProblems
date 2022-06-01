package DynamicProgramming.CherryPickup_II_3D_DP;
import java.util.Arrays;

// https://youtu.be/QGfn7JeXK54
// https://takeuforward.org/data-structure/3-d-dp-ninja-and-his-friends-dp-13/

public class CherryPickUp_DP {

    // ******************************* Memoization Solution ***********************************
    // See the number of parameters that are changing in the recursion call, that
    // number will determine the size of DP array.

    // Overlapping sub-problems will take O(1) recursive call
    // Time Complexity ==> O(number of different states) = O(M * N * N) * 9 = O(M * N * N)   (9 due to two for loops)
    // One call for each different value of i, j1, j2  (i ranges from [1,M], j1 & j2 ranges from [1,N])
    // Total no. of states or different sub-problems are M*N*N. So, atleast one calls will
    // be made for each of these sub-problems

    // Space Complexity ==> O(M*N*N) + O(N)     DP array & Recursion stack space

    public int cherryPickup(int[][] grid) {
        // m is no. of rows
        int m = grid.length;

        // n is no. of columns
        int n = grid[0].length;

        int[][][] dp = new int[m][n][n];

        for (int[][] arr1 : dp){
            for (int[] arr2 : arr1)
                Arrays.fill(arr2, -1);
        }

        return memoizationSolution(0, 0, n-1, grid, dp, m, n);
    }

    // f(i,j1,j2) will give us the maximum number of chocolates collected by Alice and Bob
    // from their current positions to the last position.

    public int memoizationSolution(int i, int j1, int j2, int[][] arr, int[][][] dp, int m, int n){
        if (j1 < 0 || j1 >= n || j2 < 0 || j2 >= n)
            return Integer.MIN_VALUE;

        if (i == m-1){
            if (j1 == j2)
                return arr[i][j1];
            else
                return arr[i][j1] + arr[i][j2];
        }

        if (dp[i][j1][j2] != -1)
            return dp[i][j1][j2];

        int maxCherryPicked = Integer.MIN_VALUE;

        for (int deltaJ1 = -1; deltaJ1 <= 1; deltaJ1++){
            for (int deltaJ2 = -1; deltaJ2 <= 1; deltaJ2++){

                int currCherryPicked;

                if (j1 == j2)
                    currCherryPicked = arr[i][j1] +  memoizationSolution(i + 1, j1 + deltaJ1, j2 + deltaJ2, arr, dp, m, n);
                else
                    currCherryPicked = arr[i][j1] + arr[i][j2] + memoizationSolution(i + 1, j1 + deltaJ1, j2 + deltaJ2, arr, dp, m, n);

                maxCherryPicked  = Math.max(maxCherryPicked, currCherryPicked);
            }
        }

        return dp[i][j1][j2] = maxCherryPicked;
    }



    // *********************************** Tabulation Solution ***********************************
    // See the number of parameters that are changing in the recursion call, that
    // number will determine the size of DP array.

    // Overlapping sub-problems will take O(1) time to get from DP array
    // There are three for loops for i, j1, j2
    // Time Complexity ==> O(number of different states) = O(M * N * N) * 9 = O(M * N * N)   (9 due to two for loops)
    // Space Complexity ==> O(M*N*N)   DP array

    public int tabulationSolution(int[][] grid){
        int m = grid.length;
        int n = grid[0].length;

        int[][][] dp = new int[m][n][n];

        // Base case
        // if j1==j2 (both person came to same position in last row), then only one person can pick up the cherry from that position
        // else (both person are at diff. position in last row), then both person can pick up the cherry from their position
        for (int j1 = 0; j1 < n; j1++){
            for (int j2 = 0; j2 < n; j2++)
                dp[m-1][j1][j2] =  j1 == j2 ? grid[m-1][j1] : grid[m-1][j1] + grid[m-1][j2];
        }

        // Other cases
        // Inner nested loops to try out 9 options
        for (int i = m-2; i >= 0; i--){
            for (int j1 = 0; j1 < n; j1++){
                for (int j2 = 0; j2 < n; j2++){

                    int maxCherryPicked = Integer.MIN_VALUE;

                    for (int dj1 = -1; dj1 <= 1; dj1++){
                        for (int dj2 = -1; dj2 <= 1; dj2++) {

                            boolean anyOneOutOfBoundary = j1 + dj1 < 0 || j2 + dj2 < 0 || j1 + dj1 >= n || j2 + dj2 >= n;
                            if (anyOneOutOfBoundary)
                                continue;

                            int currCherryPicked;

                            if (j1 == j2)
                                currCherryPicked = grid[i][j1] + dp[i+1][j1 + dj1][j2 + dj2];
                            else
                                currCherryPicked = grid[i][j1] + grid[i][j2] + dp[i+1][j1 + dj1][j2 + dj2];

                            maxCherryPicked = Math.max(maxCherryPicked, currCherryPicked);
                        }
                    }

                    dp[i][j1][j2] = maxCherryPicked;
                }
            }
        }

        // Both person starts from here, one from (0,0) & other from (0,n-1)
        // So, answer will be stored in dp[0][0][n-1]
        return dp[0][0][n-1];
    }
}
