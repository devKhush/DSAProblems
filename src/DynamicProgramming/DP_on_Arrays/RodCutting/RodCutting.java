package DynamicProgramming.DP_on_Arrays.RodCutting;

// https://youtu.be/mO8XpGoJwuo
// Pre-requisite: "Unbounded knapsack"
// https://takeuforward.org/data-structure/rod-cutting-problem-dp-24/
// https://www.geeksforgeeks.org/cutting-a-rod-dp-13/

public class RodCutting {
    /************************************ Memoization *************************************
     * Time Complexity: O(n * (n+1))   =   O(n * n)
        * Number of states
     * Space Complexity: O(n) + O(n * n)
        * DP array + Recursion stack space
     */
    public static int cutRod(int[] price, int n) {
        Integer[][] dp = new Integer[n][n + 1];
        return f(n - 1, n, price, dp);
    }

    private static int f(int i, int rodLength, int[] price, Integer[][] dp) {
        if (rodLength == 0)
            return 0;
        if (i == 0)
            return rodLength * price[0];
        if (dp[i][rodLength] != null)
            return dp[i][rodLength];

        // At every index, we may or may not cut the rod by current length
        int rodPieceLength = i + 1;
        int cutRod = rodLength >= rodPieceLength ? f(i, rodLength - rodPieceLength, price, dp) + price[i] : 0;
        int notCutRod = f(i - 1, rodLength, price, dp);
        return dp[i][rodLength] = Math.max(cutRod, notCutRod);
    }


    /************************************ Tabulation *************************************
     * Time Complexity: O(n * n)
        * Number of states
     * Space Complexity: O(n * n)
        * DP array
     */
    public static int cutRod_tabu(int[] price, int n) {
        int[][] dp = new int[n][n + 1];

        // Base case
        for (int rodLength = 0; rodLength <= n; rodLength++) {
            dp[0][rodLength] = rodLength * price[0];
        }

        // remaining case
        for (int i = 1; i < n; i++) {
            for (int rodLength = 0; rodLength <= n; rodLength++) {
                int rodPieceLength = i + 1;
                int cutRod = rodLength >= rodPieceLength ? dp[i][rodLength - rodPieceLength] + price[i] : 0;
                int notCutRod = dp[i - 1][rodLength];
                dp[i][rodLength] = Math.max(cutRod, notCutRod);
            }
        }
        return dp[n - 1][n];
    }


    /************************************ Space optimization to 1D array *************************************
     * Time Complexity: O(n * n)
        * Number of states
     * Space Complexity: O(n)
        * 1D DP array
        * 2 1D DP space optimization can also be done
     */
    public static int cutRod_space(int[] price, int n) {
        int[] dp = new int[n + 1];

        // Base case
        for (int rodLength = 0; rodLength <= n; rodLength++) {
            dp[rodLength] = rodLength * price[0];
        }

        // remaining case
        for (int i = 1; i < n; i++) {
            for (int rodLength = 0; rodLength <= n; rodLength++) {
                int rodPieceLength = i + 1;
                int cutRod = rodLength >= rodPieceLength ? dp[rodLength - rodPieceLength] + price[i] : 0;
                int notCutRod = dp[rodLength];
                dp[rodLength] = Math.max(cutRod, notCutRod);
            }
        }
        return dp[n];
    }
}