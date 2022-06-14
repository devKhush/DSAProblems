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

        if (lastTaskDone != 3)
            dp[day][lastTaskDone] = maximumPointsEarned;

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

    public static int tabulationApproach(int[][] arr){
        int day = arr.length;
        int[][] dp = new int[day][3];

        // Base case as in case of memoization
        dp[0][0] = Math.max(arr[0][1], arr[0][2]);
        dp[0][1] = Math.max(arr[0][0], arr[0][2]);
        dp[0][2] = Math.max(arr[0][0], arr[0][1]);

        /*
        // For loop which handles base case is:
        for (int prevTask = 0; prevTask <= 2; prevTask++){
            for (int currTask = 0; currTask <= 2; currTask++) {
                if (prevTask != currTask)
                    dp[0][prevTask] = Math.max(dp[0][prevTask], arr[0][currTask]);
            }
        }
         */
        // just a dummy variable to assign lastDay result, it ranges form [0, 2]
        int var = 0;

        for (int dayNumber = 1; dayNumber < day; dayNumber++) {

            for (int lastTaskDone = 0; lastTaskDone <= 2; lastTaskDone++) {
                if (dayNumber == day-1)
                    lastTaskDone = 3;

                int maximumPointsEarned = 0;

                for (int task = 0; task <= 2; task++) {
                    if (task != lastTaskDone) {
                        int pointsEarned = dp[dayNumber-1][task] + arr[dayNumber][task];
                        maximumPointsEarned = Math.max(pointsEarned, maximumPointsEarned);
                    }
                }

                if (lastTaskDone != 3)
                    dp[dayNumber][lastTaskDone] = maximumPointsEarned;
                else
                    dp[dayNumber][var++] = maximumPointsEarned;
            }
        }

        int maxPoints1 = dp[day-1][0];
        int maxPoints2 = dp[day-1][1];
        int maxPoints3 = dp[day-1][2];
        return Math.max(maxPoints1, Math.max(maxPoints2, maxPoints3));
    }




    // *************************************** Space Optimization ***************************************
    // T.C ==> O(N*3*3) = O(N)      Three Nested loops
    // S.C ==> O(3) = O(1)

    private static int constantSpaceSolution(int[][] arr){
        int totalDays = arr.length;

        // dp array of constant size
        int[] prevMaxPoints = new int[3];

        // Base cases
        prevMaxPoints[0] = Math.max(arr[0][1], arr[0][2]);
        prevMaxPoints[1] = Math.max(arr[0][0], arr[0][2]);
        prevMaxPoints[2] = Math.max(arr[0][0], arr[0][1]);

        // just a dummy variable to assign lastDay result, it ranges form [0, 2]
        int var = 0;

        for (int day = 1; day < totalDays; day++){
            int[] dp = new int[3];      // temp array

            for (int lastTask = 0; lastTask < 3; lastTask++) {
                if (day == totalDays-1)
                    lastTask = 3;

                int maximumPointsEarned = 0;

                for (int task = 0; task < 3; task++) {
                    if (task != lastTask) {
                        int pointsEarned = arr[day][task] + prevMaxPoints[task];
                        maximumPointsEarned = Math.max(pointsEarned, maximumPointsEarned);
                    }
                }

                if (lastTask != 3)
                    dp[lastTask] = maximumPointsEarned;
                else
                    dp[var++] = maximumPointsEarned;
            }
            prevMaxPoints = dp;
        }

        return Math.max(prevMaxPoints[0], Math.max(prevMaxPoints[1], prevMaxPoints[2]));
    }




    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t  = sc.nextInt();

        for (int i = 0; i < t; i++) {
            int n = sc.nextInt();
            int[][] arr = new int[n][3];

            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 3; k++) {
                    arr[j][k] = sc.nextInt();
                }
            }
            System.out.println(tabulationApproach(arr));
        }
    }
}
