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
        // m is no. of rows
        int m = grid.length;

        // n is no. of columns
        int n = grid[0].length;

        return recursiveSolution(0, 0, n-1, grid, m, n);
    }

    public int recursiveSolution(int i, int j1, int j2, int[][] arr, int m, int n){
        if (j1 < 0 || j1 >= n || j2 < 0 || j2 >= n)
            return Integer.MIN_VALUE;

        if (i == m-1){
            if (j1 == j2)
                return arr[i][j1];
            else
                return arr[i][j1] + arr[i][j2];
        }

        int maxCherryPicked = Integer.MIN_VALUE;

        for (int deltaJ1 = -1; deltaJ1 <= 1; deltaJ1++){
            for (int deltaJ2 = -1; deltaJ2 <= 1; deltaJ2++){
                int currCherryPicked;

                if (j1 == j2)
                    currCherryPicked = arr[i][j1] +  recursiveSolution(i + 1, j1 + deltaJ1, j2 + deltaJ2, arr, m, n);
                else
                    currCherryPicked = arr[i][j1] + arr[i][j2] + recursiveSolution(i + 1, j1 + deltaJ1, j2 + deltaJ2, arr, m, n);

                maxCherryPicked  = Math.max(maxCherryPicked, currCherryPicked);
            }
        }

        return maxCherryPicked;
    }
}
