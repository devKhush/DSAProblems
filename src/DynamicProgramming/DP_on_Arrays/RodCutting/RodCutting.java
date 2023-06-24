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
    /*
    Another Solution...
    private int f(int i, int length, int[] prices, Integer[][] dp){
        if (length == 0)
            return 0;
        if (i < 0)
            return -(int)1e9;
        if (dp[i][length] != null)
            return dp[i][length];
        int take = length >= i+1 ? prices[i] + f(i, length - (i+1), prices, dp) : -(int)1e9;
        int notTake = f(i-1, length, prices, dp);
        return dp[i][length] = Math.max(take, notTake);
    }*/


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
    /*
    Another solution...
    public int cutRod(int prices[], int length) {
        int n = prices.length;

        int[][] dp = new int[n + 1][length + 1];
        Arrays.fill(dp[0], -(int)1e9);
        dp[0][0] = 0;

        for (int i = 1; i <= n; i++){
            for (int len = 0; len <= length; len++){
                int take = len >= i ? prices[i-1] + dp[i][len - i] : -(int)1e9;
                int notTake = dp[i-1][len];
                dp[i][len] = Math.max(take, notTake);
            }
        }
        return dp[n][length];
    }*/


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
    /*
    Another Solution...
    public int cutRod(int prices[], int length) {
        int n = prices.length;

        int[] dp = new int[length + 1];
        Arrays.fill(dp, -(int)1e9);
        dp[0] = 0;

        for (int i = 1; i <= n; i++){
            for (int len = 0; len <= length; len++){
                int take = len >= i ? prices[i-1] + dp[len - i] : -(int)1e9;
                int notTake = dp[len];
                dp[len] = Math.max(take, notTake);
            }
        }
        return dp[length];
    }*/
}