package DynamicProgramming.DP_on_Arrays.NinjaTraining;
import java.util.Scanner;

// https://youtu.be/AE39gJYuRog
// https://takeuforward.org/data-structure/dynamic-programming-ninjas-training-dp-7/
// https://www.codingninjas.com/codestudio/problems/ninja-s-training_3621003?source=youtube&campaign=striver_dp_videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_dp_videos&leftPanelTab=1

public class NinjaTraining_DP {

    // *************************************** Recursion ***************************************
    // This is code of how we can solve this using a simple recursion
    // using the logic shown in video/article
    public static int simpleRecursion(int day, int lastTaskDone, int[][] arr){
        if (day == 0){
            int maximumPointEarned = 0;
            for (int task=0; task<=2 ;task++){
                if (task != lastTaskDone)
                    maximumPointEarned = Math.max(arr[0][task] , maximumPointEarned);
            }
            return maximumPointEarned;
        }

        int maximumPointsEarned = 0;
        for (int task=0; task<=2; task++){
            if (lastTaskDone != task){
                int pointsEarned = arr[day][task] + simpleRecursion(day-1, task, arr);
                maximumPointsEarned = Math.max(pointsEarned , maximumPointsEarned);
            }
        }
        return maximumPointsEarned;
    }




    // *************************************** Memoization ***************************************
    // This is memoization of above recursion
    // T.C = O(3*3*n) = O(n)   (memoization is solving three tasks for each day)
    // S.C = O(3*N) (DP array) + O(N) (recursion stack space, at most n calls) = O(N)

    public static int memoizationApproach(int day, int lastTaskDone, int[][] arr, int[][] dp){
        if (day == 0){
            int maximumPointsEarned = 0;
            for (int task=0; task<=2 ;task++){
                if (task != lastTaskDone)
                    maximumPointsEarned = Math.max(arr[0][task] , maximumPointsEarned);
            }
            return maximumPointsEarned;
        }

        if (lastTaskDone != 3  && dp[day][lastTaskDone] != 0)
            return dp[day][lastTaskDone];

        int maximumPointsEarned = 0;
        for (int task=0; task<=2; task++) {
            if (lastTaskDone != task) {
                int pointsEarned = arr[day][task] + memoizationApproach(day - 1, task, arr, dp);
                maximumPointsEarned = Math.max(pointsEarned, maximumPointsEarned);
            }
        }

        if (lastTaskDone != 3) dp[day][lastTaskDone] = maximumPointsEarned;
        return maximumPointsEarned;
    }

    private static int memoization(int[][] arr){
        int n = arr.length;

        // no tasks performed on nth day (so assumed to be 3) on 0 based indexing
        return memoizationApproach(n-1, 3, arr, new int[n][3]);
    }




    // *************************************** Tabulation ***************************************
    // This is tabulation approach of above memoization
    // T.C ==> O(N*3*3) = O(N)      Three Nested loops
    // S.C ==> O(N*3) = O(N)

    public static int tabulationApproach(int[][] points){
        int n = points.length;
        int[][] dp = new int[n][3];

        // Base case as in case of memoization
        dp[0][0] = Math.max(points[0][1], points[0][2]);
        dp[0][1] = Math.max(points[0][0], points[0][2]);
        dp[0][2] = Math.max(points[0][0], points[0][1]);

        /*
        // For loop which handles base case is:
        for (int lastDayTask = 0; lastDayTask <= 2; lastDayTask++){
            for (int task = 0; task <= 2; task++) {
                if (lastDayTask != task)
                    dp[0][lastDayTask] = Math.max(dp[0][lastDayTask], points[0][task]);
            }
        }
        */

        // Previously f(n-1, 3) was being returned as final answer in memoization solution
        // Now, we are returning max of f(n-1,0), f(n-1,1) & f(n-1,2) in tabulation solution.
        // This will ensure correct answer is returned. Think...
        for (int day = 1; day < n; day++) {
            for (int lastDayTask = 0; lastDayTask < 3; lastDayTask++) {
                int max = 0;
                for (int task = 0; task < 3; task++) {
                    if (task != lastDayTask) {
                        int dayPoint = points[day][task] + dp[day - 1][task];
                        max = Math.max(max, dayPoint);
                    }
                }
                dp[day][lastDayTask] = max;
            }
        }
        return Math.max(dp[n - 1][0], Math.max(dp[n - 1][1], dp[n - 1][2]));
    }




    // *************************************** Space Optimization ***************************************
    // T.C ==> O(N*3*3) = O(N)      Three Nested loops
    // S.C ==> O(3) = O(1)

    private static int constantSpaceSolution(int[][] points){
        int n = points.length;
        int[] dp = new int[3];

        // Base cases
        dp[0] = Math.max(points[0][1], points[0][2]);
        dp[1] = Math.max(points[0][0], points[0][2]);
        dp[2] = Math.max(points[0][0], points[0][1]);
        /* // Base case code for space optimization
        for (int lastDayTask = 0; lastDayTask < 3; lastDayTask++) {
            int max = 0;
            for (int task = 0; task < 3; task++) {
                if (task != lastDayTask) {
                    max = Math.max(max, points[0][task]);
                }
            }
            dp[lastDayTask] = max;
        }
         */


        // Previously f(n-1, 3) was being returned as final answer in memoization solution
        // Now, we are returning max of f(n-1,0), f(n-1,1) & f(n-1,2) in tabulation solution.
        // This will ensure correct answer is returned. Think...
        for (int day = 1; day < n; day++) {
            int[] dp2 = new int[3];

            for (int lastDayTask = 0; lastDayTask < 3; lastDayTask++) {
                int max = 0;
                for (int task = 0; task < 3; task++) {
                    if (task != lastDayTask) {
                        int dayPoint = points[day][task] + dp[task];
                        max = Math.max(max, dayPoint);
                    }
                }
                dp2[lastDayTask] = max;
            }
            dp = dp2;
        }
        return Math.max(dp[0], Math.max(dp[1], dp[2]));
    }
}
