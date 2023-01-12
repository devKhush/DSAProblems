package DynamicProgramming.DP_on_Grids.TriangularGridMinimumPathSum;
import java.util.ArrayList;

// https://youtu.be/SrP-PiLSYC0
// https://takeuforward.org/data-structure/minimum-path-sum-in-triangular-grid-dp-11/

public class TriangularGridMinimumPathSum_Tabulation {

    // *********************************** Tabulation Solution ***********************************
    // T.C ==>  O(n*n) + O(n)  (two for loops:: one for loop (i) from 0 to n and inner for loop is from 0 to i)
    // S.C ==>  O(DP array) = O(n*n)

    private int tabulationSolution_V1(ArrayList<ArrayList<Integer>> triangle){
        int n = triangle.size();
        int[][] dp = new int[n][n];

        // We need to move only in isosceles right triangle, so outer loop runs (i) form 0 to n-1
        // and the inner loop (j) runs from 0 to i
        for (int i = 0; i < n; i++){
            for (int j = 0; j <= i; j++){

                // Base case
                if (i==0 && j==0){
                    dp[0][0] = triangle.get(0).get(0);
                    continue;
                }

                int minPathByMovingUp, minPathByMovingUpLeft;

                // We can only move up in the right isosceles triangle iff the j is within the boundary
                // which means that i-1 >= j (on moving up then also we should be inside the isosceles right triangle)
                // Also we don't need to condition of i>0 here, bcoz i==0 case is handles by base case (only one iteration)
                if (j <= i-1)
                    minPathByMovingUp = dp[i-1][j] + triangle.get(i).get(j);
                else
                    minPathByMovingUp = Integer.MAX_VALUE/2;

                if (j > 0)
                    minPathByMovingUpLeft = dp[i-1][j-1] + triangle.get(i).get(j);
                else
                    minPathByMovingUpLeft = Integer.MAX_VALUE/2;

                // Store the min path sum from (0,0) to (i,j) inside dp array
                dp[i][j] = Math.min(minPathByMovingUp, minPathByMovingUpLeft);
            }
        }

        // Since min path from (0,0) to bottom can take any element from the bottom of array (isosceles right triangle)
        // We take minimum of all the paths taken from (0,0) to reach bottom of all elements in the bottom
        int minPathFromBottomToTop = Integer.MAX_VALUE;

        for (int j = 0; j < n; j++)
            minPathFromBottomToTop = Math.min(minPathFromBottomToTop, dp[n-1][j]);

        return minPathFromBottomToTop;
    }



    // *********************************** Another Tabulation Solution ***********************************
    // Two for loops:: one for loop (i) from n-2 to 0 and inner for loop is from i to 0  && base case loop
    // T.C ==>  O(n*n) + O(n)
    // S.C ==> O(DP array) = O(n*n)

    private int tabulationSolution_V2(ArrayList<ArrayList<Integer>> triangle){
        int n = triangle.size();
        int[][] dp = new int[n][n];

        // Base case for going from top (0,0) to bottom of array, as per another memoization
        for (int j = 0; j < n; j++)
            dp[n-1][j] = triangle.get(n-1).get(j);

        // i = n-1 is handled by case
        for (int i = n-2; i >= 0; i--){
            for (int j = 0; j <= i; j++){
                // Minimum path by moving down
                int minPathByMovingDown = dp[i+1][j];
                // Minimum path by moving down & bottom (diagonally)
                int minPathByMovingDownBottom = dp[i+1][j+1];
                dp[i][j] = Math.min(minPathByMovingDown, minPathByMovingDownBottom) + triangle.get(i).get(j);
            }
        }
        // Answer for going from (0,0) to bottom is stored in dp[0][0]
        return dp[0][0];
    }
}
