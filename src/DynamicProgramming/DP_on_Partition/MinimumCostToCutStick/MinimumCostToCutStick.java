package DynamicProgramming.DP_on_Partition.MinimumCostToCutStick;
import java.util.Arrays;

// https://leetcode.com/problems/minimum-cost-to-cut-a-stick/description/
// https://takeuforward.org/data-structure/minimum-cost-to-cut-the-stick-dp-50/
// https://youtu.be/xwomavsC86c

public class MinimumCostToCutStick {
    /************************************** Recursion: Partition *****************************************
     * Intuition:
        * Earlier designed 4D DP solution of this problem, but got memory limit error
        * Watch video
        * Recursion and partition idea is there.
        * But Main Thing is "how to keep track of ongoing stick length while cutting and without adding
            more parameters and states".

     * Time Complexity: Exponential
     * Space Complexity: O(n)
        * Because in each call partitions will get smaller, so the max recursion stack space is O(n)
     */
    public int minCost_recur(int stickLength, int[] cuts) {
        int n = cuts.length;
        // Sort the Array to make the partition in left and right independent of each other (watch video)
        Arrays.sort(cuts);

        // Add 0 to front and stickLength to the end, to keep track of length of the cut piece of stick
        int[] new_cuts = new int[n + 2];
        System.arraycopy(cuts, 0, new_cuts, 1, n);
        new_cuts[0] = 0;
        new_cuts[n + 1] = stickLength;

        // Start with the entire array f(1,stickLength)
        return f(1, n, new_cuts);
    }

    private int f( int i, int j, int[] cuts){
        if (i > j)
            return 0;

        // Try all the partitions and return the min. possible answer of the two partitions
        int minCost = (int)1e10;
        for (int k = i; k <= j; k++){
            int cost = (cuts[j+1] - cuts[i-1])  +  f(i, k-1, cuts)  +  f(k+1, j, cuts);
            minCost = Math.min(minCost, cost);
        }
        return minCost;
    }

    /**************************************** Memoization ********************************************
     * Memoization of above recursive solution

     * Time Complexity: O(n*n*n)
        * There are two changing states, i & j.
        * Another loop for k will run for each states on average n time
     * Space Complexity: O(n*n) + O(n)
        * DP Array + Recursion Stack space
     */
    public int minCost_memo(int stickLength, int[] cuts) {
        int n = cuts.length;
        // Sort the Array to make the partition in left and right independent of each other (watch video)
        Arrays.sort(cuts);

        // Add 0 to front and stickLength to the end, to keep track of length of the cut piece of stick
        int[] new_cuts = new int[n + 2];
        System.arraycopy(cuts, 0, new_cuts,1, n);
        new_cuts[0] = 0;
        new_cuts[n + 1] = stickLength;

        // DP Array
        Integer[][] dp = new Integer[n+1][n+1];

        // Start with the entire array f(1,stickLength)
        return f(1, n, new_cuts, dp);
    }
    private int f(int i, int j, int[] cuts, Integer[][] dp){
        if (i > j)
            return 0;
        if (dp[i][j] != null)
            return dp[i][j];

        // Try all the partitions and return the min. possible answer of the two partitions
        int minCost = (int)1e10;
        for (int k = i; k <= j; k++){
            int cost = (cuts[j+1] - cuts[i-1]) + f(i, k-1, cuts, dp) + f(k+1, j, cuts, dp);
            minCost = Math.min(minCost, cost);
        }
        return dp[i][j] = minCost;
    }


    /**************************************** Tabulation ********************************************
     * Tabulation of above memoization
     * Tricky part here is to determine the direction of for loop.
     * We call for function f(1,n) in memoization
     * 'i' starts from 1 in function. So, 'i' goes from n to 1 in tabulation.
     * 'j' starts from n in function. But since, in each partition 'j' is always greater than or equal
     to 'i'. State 'j' varies from i+1 to n-1.
     * Else Copy the code from memoization.

     * Time Complexity: O(n*n*n)
        * Two changing states: i & j
        * One loop inside for each state
     * Space Complexity: O(n*n)
        * DP Array
     */
    private int minCost(int stickLength, int[] ccuts){
        int n = ccuts.length;

        // Sort the Array to make the partition in left and right independent of each other (watch video)
        Arrays.sort(ccuts);

        // Add 0 to front and stickLength to the end, to keep track of length of the cut piece of stick
        int[] cuts = new int[n + 2];
        System.arraycopy(ccuts, 0, cuts,1, n);
        cuts[0] = 0;
        cuts[n + 1] = stickLength;

        // DP Array: No base case for i>j (0 by-default)
        int[][] dp = new int[n + 2][n + 2];

        // Changing states inside loops in opposite directions
        for (int i = n; i >= 1; i--){
            for (int j = i; j <= n; j++){
                int minCost = (int)1e10;
                for (int k = i; k <= j; k++){
                    int cost = (cuts[j+1] - cuts[i-1]) + dp[i][k-1] + dp[k+1][j];
                    minCost = Math.min(minCost, cost);
                }
                dp[i][j] = minCost;
            }
        }
        return dp[1][n];
    }
}
