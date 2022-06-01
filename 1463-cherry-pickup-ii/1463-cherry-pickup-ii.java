class Solution {
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
        
        return recursiveSolution(0, 0, n-1, grid, dp, m, n);
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