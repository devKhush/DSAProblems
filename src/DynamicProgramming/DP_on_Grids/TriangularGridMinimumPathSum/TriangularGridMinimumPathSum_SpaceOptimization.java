package DynamicProgramming.DP_on_Grids.TriangularGridMinimumPathSum;
import java.util.List;

// https://youtu.be/SrP-PiLSYC0
// https://takeuforward.org/data-structure/minimum-path-sum-in-triangular-grid-dp-11/

public class TriangularGridMinimumPathSum_SpaceOptimization {
    // ********************************** Space Optimized solution **********************************
    // T.C --> O(n*n)
    // S.C --> O(n)     (only one size DP array)

    public int spaceOptimizedSolution_V1(List<List<Integer>> triangle){
        int n = triangle.size();
        int[] dp = new int[n];

        // To calculate the min path from (0,0) to (i,j) we need previous top value (in previous row)
        // and previous top-left diagonal value (also in previous row)
        // One DP array will not work here, as then how will we store the min path of current row
        // We use one DP array 'dp' to get these required previous values.
        // And store the min path of current row values in another dp array 'tempDP'

        for (int i = 0; i < n; i++){
            int[] tempDP = new int[n];

            for (int j = 0; j <= i; j++){
                if (i==0 && j==0){
                    tempDP[0] = triangle.get(0).get(0);
                    continue;
                }

                int minPathByMovingUp = (j <= i-1) ? dp[j] + triangle.get(i).get(j) : Integer.MAX_VALUE/2;

                int minPathByMovingUpLeft = (j > 0) ? dp[j-1] + triangle.get(i).get(j) : Integer.MAX_VALUE/2;

                tempDP[j] = Math.min(minPathByMovingUpLeft, minPathByMovingUp);
            }
            dp = tempDP;
        }

        int minPathSum = Integer.MAX_VALUE;
        for (int pathSum : dp)
            minPathSum = Math.min(pathSum, minPathSum);

        return minPathSum;
    }



    // ******************************* Another Space Optimized solution *******************************
    // T.C --> O(n*n)
    // S.C --> O(n)     (only one size DP array)

    public int spaceOptimizedSolution_V2(List<List<Integer>> triangle){
        int n = triangle.size();
        int[] dp = new int[n];

        // To calculate the min path from (i,j) to (0,0) we need next bottom value (in next row)
        // and next right-bottom diagonal value (also in next row)
        // One DP array will not work here, as then how will we store the min path of current row
        // We use one DP array 'dp' to get these required next values.
        // And store the min path of current row values in another dp array 'tempDP'
        for (int j = 0; j < n; j++)
            dp[j] = triangle.get(n-1).get(j);

        for (int i= n-2; i >= 0; i--){
            int[] tempDP = new int[n];

            for (int j = 0; j <= i; j++) {
                int minPathByMovingDown = dp[j];
                int minPathByMovingDownRight = dp[j+1];
                tempDP[j] = Math.min(minPathByMovingDownRight, minPathByMovingDown) + triangle.get(i).get(j);
            }
            dp = tempDP;
        }
        return dp[0];
        /*
        int[] dp = new int[n];
        for (int j = 0; j < n; j++)
            dp[j] = triangle[n - 1][j];

        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                int downPath = dp[j];
                int downRightPath = dp[j + 1];
                dp[j] = Math.min(downPath, downRightPath) + triangle[i][j];
            }
        }
        return dp[0];
         */
    }
}
