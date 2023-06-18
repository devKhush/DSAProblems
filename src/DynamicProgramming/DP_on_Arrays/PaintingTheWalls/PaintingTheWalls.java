package DynamicProgramming.DP_on_Arrays.PaintingTheWalls;

// Intuition: 0/1 Knapsack Problem
// https://leetcode.com/problems/painting-the-walls/description/

public class PaintingTheWalls {
    /****************************************** Memoization ****************************************/
    public int paintWalls(int[] cost, int[] time) {
        int n = cost.length;
        Integer[][] dp = new Integer[n][2*n  + 1];
        return f(0, 0, cost, time, n, dp);
    }
    private int f(int i, int timeAvailable, int[] cost, int[] time, int n, Integer[][] dp) {
        if (i == n)
            return timeAvailable >= 0 ? 0 : (int) 1e9;
        if (dp[i][timeAvailable + n] != null)
            return dp[i][timeAvailable + n];

        int dontPaint = 0 + f(i + 1, timeAvailable - 1, cost, time, n, dp);
        int paint = cost[i] + f(i + 1, Math.min(timeAvailable + time[i], n), cost, time, n, dp);
        return dp[i][timeAvailable + n] = Math.min(paint, dontPaint);
    }


    /************************************ Solution 1 *******************************************/
    public int paintWalls_V1(int[] cost, int[] time) {
        int n = cost.length;
        int totalTime = 0;
        for (int t : time)
            totalTime += t;
        Integer[][] dp = new Integer[n][n + totalTime  + 1];
        return f(0, 0, n, cost, time, dp);
    }
    private int f(int i, int timeAvailable, int n, int[] cost, int[] time, Integer[][] dp) {
        if (i == n)
            return timeAvailable >= 0 ? 0 : (int) 1e9;
        if (dp[i][timeAvailable + n] != null)
            return dp[i][timeAvailable + n];

        int dontPaint = 0 + f(i + 1, timeAvailable - 1, n, cost, time, dp);
        int paint = cost[i] + f(i + 1, timeAvailable + time[i], n, cost, time, dp);
        return dp[i][timeAvailable + n] = Math.min(paint, dontPaint);
    }
}
