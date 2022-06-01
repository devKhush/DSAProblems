class Solution {
    public int cherryPickup(int[][] grid) {
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
    
    
    
    
    public int recursiveSolution(int i, int j1, int j2, int[][] arr, int[][][] dp, int m, int n){
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
                    currCherryPicked = arr[i][j1] +  recursiveSolution(i + 1, j1 + deltaJ1, j2 + deltaJ2, arr, dp, m, n);
                else
                    currCherryPicked = arr[i][j1] + arr[i][j2] + recursiveSolution(i + 1, j1 + deltaJ1, j2 + deltaJ2, arr, dp, m, n);

                maxCherryPicked  = Math.max(maxCherryPicked, currCherryPicked);
            }
        }
        
        return dp[i][j1][j2] = maxCherryPicked;
    }
}