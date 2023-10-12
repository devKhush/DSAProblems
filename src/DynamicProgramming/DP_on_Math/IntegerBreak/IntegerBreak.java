package DynamicProgramming.DP_on_Math.IntegerBreak;

// https://leetcode.com/problems/integer-break/description/

public class IntegerBreak {
    /****************************************** Memoization ******************************************
     * Intuition:
        * Recursion
     * Time Complexity: O(n)
        * 1 DP state
     * Space Complexity: O(2*n) ~ O(n)
        * 1 DP state + Stack space
     */
    public int integerBreak_memo(int n){
        Integer[] dp = new Integer[n + 1];
        return f(n, false, dp);
    }
    public int f(int n, boolean considerN, Integer[] dp) {
        if (n == 0)
            return 1;
        if (dp[n] != null)
            return dp[n];

        int maxProduct = 1;
        for (int i = 1; i <= n - (considerN ? 0 : 1); i++){
            maxProduct = Math.max(maxProduct, i * f(n - i, true, dp));
        }
        return dp[n] = maxProduct;
    }

    /****************************************** Tabulation ******************************************
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public int integerBreak(int N){
        int[] dp = new int[N + 1];
        dp[0] = dp[1] = 1;

        for (int n = 2; n <= N; n++){
            for (int i = 1; i <= n - (i == N ? 1 : 0); i++){
                dp[n] = Math.max(dp[n], i * dp[n - i]);
            }
        }
        return dp[N];
    }
}
