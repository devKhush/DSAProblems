class Solution {
    public int cherryPickup(int[][] grid) {
           int m = grid.length;
        int n = grid[0].length;

        int[][][] dp = new int[m][n][n];

        for (int j1 = 0; j1 < n; j1++){
            for (int j2 = 0; j2 < n; j2++)
                dp[m-1][j1][j2] =  j1 == j2 ? grid[m-1][j1] : grid[m-1][j1] + grid[m-1][j2];
        }


        for (int i = m-2; i >= 0; i--){
            for (int j1 = 0; j1 < n; j1++){
                for (int j2 = 0; j2 < n; j2++){

                    int maxCherryPicked = Integer.MIN_VALUE;
                    
                    for (int dj1 = -1; dj1 <= 1; dj1++){
                        for (int dj2 = -1; dj2 <= 1; dj2++) {
                            
                            if (j1 + dj1 < 0 || j2 + dj2 < 0 || j1 + dj1 >= n || j2 + dj2 >=n )
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
        return dp[0][0][n-1];
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