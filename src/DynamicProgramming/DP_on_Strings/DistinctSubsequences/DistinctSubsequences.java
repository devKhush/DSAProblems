package DynamicProgramming.DP_on_Strings.DistinctSubsequences;

// This is so far best String DP Problem
// https://leetcode.com/problems/distinct-subsequences/description/
// https://youtu.be/nVG7eTiD2bY
// https://takeuforward.org/data-structure/distinct-subsequences-dp-32/

public class DistinctSubsequences {
    /********************************* Recursive Solution ***********************************************
     * Intuition: Basic Recursion Thinking (Try out all possible ways)

     * Time Complexity: O(m * 2^n)  ~  Exponential
        * All the 2^n subsequence of string 't' will be generated
        * String 's' is reduced by one character for every 2^n subsequence of string 't'
     * Space Complexity: O(m + n)
        * Recursion stack space of 'm+n'. In worst case, both the string will get exhausted.
     */
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        return f(m-1, n-1, s, t);
    }

    private int f(int i, int j, String s, String t){
        if (j < 0)
            return 1;
        if (i < 0)
            return 0;

        if (s.charAt(i) == t.charAt(j))
            return f(i-1, j-1, s, t)  +  f(i-1, j, s, t);
        else
            return f(i-1, j, s, t);
    }


    /********************************* Recursive Solution: Shifting of Indices ******************************************
     * Time Complexity: O(m * 2^n)  ~  Exponential
        * All the 2^n subsequence of string 't' will be generated
        * String 's' is reduced by one character for every 2^n subsequence of string 't'
     * Space Complexity: O(m + n)
        * Recursion stack space of 'm+n'. In worst case, both the string will get exhausted.
     */
    public int num_Distinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        return _f(m, n, s, t);
    }

    private int _f(int i, int j, String s, String t){
        if (j == 0)
            return 1;
        if (i == 0)
            return 0;

        if (s.charAt(i - 1) == t.charAt(j - 1))
            return _f(i-1, j-1, s, t)  +  _f(i-1, j, s, t);
        else
            return _f(i-1, j, s, t);
    }


    /*********************************** Memoization Solution *****************************************
     * Time Complexity: O(m * n)
        * Two states of indices
     * Space Complexity: O(m * n) + O(m + n)
        * DP_Array + Recursive_Stack_Space
     */
    public int num_Distinct_SubSequence(String s, String t) {
        int m = s.length();
        int n = t.length();
        Integer[][] dp = new Integer[m + 1][n + 1];

        return f(m, n, s, t, dp);
    }

    private int f(int i, int j, String s, String t, Integer[][] dp){
        if (j == 0)
            return 1;
        if (i == 0)
            return 0;
        if (dp[i][j] != null)
            return dp[i][j];

        if (s.charAt(i - 1) == t.charAt(j - 1))
            return dp[i][j] =  f(i-1, j-1, s, t, dp) + f(i-1, j, s, t, dp);
        else
            return dp[i][j] = f(i-1, j, s, t, dp);
    }


    /*************************************** Tabulation Solution ***************************************
     * Time Complexity: O(m * n)
        * Two states of indices
     * Space Complexity: O(m * n)
        * DP_Array
     */
    public int num_distinct_subSequence(String s, String t){
        int m = s.length();
        int n = t.length();
        int[][] dp = new int[m + 1][n + 1];

        // Base case for j==0
        for (int i = 0; i <= m; i++)
            dp[i][0] = 1;

        // No need for base case for i==0 (By-default values are 0)
        // Other States for DP
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++){
                if (s.charAt(i - 1) == t.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }
        return dp[m][n];
    }


    /************************************** Space Optimization ******************************************
     * Time Complexity: O(m * n)
        * Two states of indices
     * Space Complexity: O(2*n) ~ O(n)
        * only two array
     */
    public int num_distinct_subsequence(String s, String t){
        int m = s.length();
        int n = t.length();

        int[] dp = new int[n + 1];
        dp[0] = 1;      // Base case for j==0

        // No need for base case for i==0 (By-default values are 0)
        // Other States for DP
        for (int i = 1; i <= m; i++) {
            int[] currDP = new int[n + 1];
            currDP[0] = 1;

            for (int j = 1; j <= n; j++){
                if (s.charAt(i - 1) == t.charAt(j - 1))
                    currDP[j] = dp[j - 1] + dp[j];
                else
                    currDP[j] = dp[j];
            }
            dp = currDP;
        }
        return dp[n];
    }


    /************************************** 1D-Space Optimization **************************************
     * Time Complexity: O(m * n)
        * Two states of indices
     * Space Complexity: O(n)
        * only one array
     */
    public int num_Distinct_subsequence(String s, String t){
        int m = s.length();
        int n = t.length();

        int[] dp = new int[n + 1];
        dp[0] = 1;      // Base case for j==0

        for (int i = 1; i <= m; i++) {
            for (int j = n; j >= 1; j--){
                if (s.charAt(i - 1) == t.charAt(j - 1))
                    dp[j] = dp[j - 1] + dp[j];
            }
        }
        return dp[n];
    }
}
