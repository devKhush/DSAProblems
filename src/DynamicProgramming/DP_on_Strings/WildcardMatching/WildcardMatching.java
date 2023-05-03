package DynamicProgramming.DP_on_Strings.WildcardMatching;

// Nice String DP Problem
// https://leetcode.com/problems/wildcard-matching/description/
// https://youtu.be/ZmlQ3vgAOMo
// https://takeuforward.org/data-structure/wildcard-matching-dp-34/

public class WildcardMatching {
    /************************************* Simple Recursion **************************************
     * Intuition: Simple Recursion Thinking (Try out all possible ways)

     * Time Complexity: O(2^m * 2^n)  ~  Exponential
        * All the 2^n subsequence of string 'p' will be generated
        * All the 2^m subsequence of string 's' will be generated
     * Space Complexity: O(m + n)
        * Recursion stack space of 'm+n'. In worst case, both the string will get exhausted.
     */
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        return f(m-1, n-1, s, p);
    }
    private boolean f(int i, int j, String text, String pattern){
        if (i < 0 && j < 0)
            return true;
        if (j < 0)
            return false;
        if (i < 0)              // for the case of all stars at the beginning
            return pattern.charAt(j) == '*' ? f(i, j-1, text, pattern) : false;

        if (pattern.charAt(j) == '?' || text.charAt(i) == pattern.charAt(j))
            return f(i-1, j-1, text, pattern);

        if (pattern.charAt(j) == '*')
            return f(i-1, j, text, pattern) ||  f(i, j-1, text, pattern);
        return false;
    }


    /*********************************** Memoization Solution *****************************************
     * Time Complexity: O(m * n)
        * Two states of indices
     * Space Complexity: O(m * n) + O(m + n)
        * DP_Array + Recursive_Stack_Space
     */
    public boolean isMatch_memo(String s, String p){
        int m = s.length();
        int n = p.length();
        Boolean[][] dp = new Boolean[m][n];
        return f(m-1, n-1, s, p, dp);
    }
    private boolean f(int i, int j, String text, String pattern, Boolean[][] dp){
        if (i < 0 && j < 0)
            return true;
        if (j < 0)
            return false;
        if (i < 0)              // for the case of all stars at the beginning
            return pattern.charAt(j) == '*' ? f(i, j-1, text, pattern, dp) : false;
        if (dp[i][j] != null)
            return dp[i][j];

        // Characters Match
        if (pattern.charAt(j) == '?' || pattern.charAt(j) == text.charAt(i))
            return dp[i][j] = f(i-1, j-1, text, pattern, dp);

        // Characters not match case
        // continue_star_sequence_case + stop_star_sequence_case
        if (pattern.charAt(j) == '*')
            return dp[i][j] = f(i-1, j, text, pattern, dp) || f(i,j-1, text, pattern, dp);

        return dp[i][j] = false;
    }


    /*************************************** Tabulation Solution ***************************************
     * Time Complexity: O(m * n)
        * Two states of indices
     * Space Complexity: O(m * n)
        * DP_Array
     */
    public boolean isMatch_tabu(String text, String pattern){
        int m = text.length();
        int n = pattern.length();
        boolean[][] dp = new boolean[m + 1][n + 1];

        // Base cases (No need for base case of j==0)
        dp[0][0] = true;
        for (int j = 1; j <= n; j++)                // Base case for i == 0
            dp[0][j] = pattern.charAt(j-1) == '*' ? dp[0][j-1] : false;

        // Other cases
        for (int i = 1; i <= m; i++){
            for (int j = 1; j <= n; j++){
                // Characters Match
                if (pattern.charAt(j-1) == '?' || text.charAt(i-1) == pattern.charAt(j-1))
                    dp[i][j] = dp[i - 1][j - 1];

                // Characters not match case (continue_star_sequence_case + stop_star_sequence_case)
                else if (pattern.charAt(j-1) == '*')
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];

                else dp[i][j] = false;
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
    public boolean isMatch_space_optimized(String text, String pattern){
        int m = text.length();
        int n = pattern.length();
        boolean[] dp = new boolean[n + 1];

        // Base cases (No need for base case of j==0)
        dp[0] = true;
        for (int j = 1; j <= n; j++)                // Base case for i == 0
            dp[j] = pattern.charAt(j-1) == '*' ? dp[j-1] : false;

        // Other cases
        for (int i = 1; i <= m; i++){
            boolean[] currDP = new boolean[n + 1];

            for (int j = 1; j <= n; j++){
                // Characters Match
                if (pattern.charAt(j-1) == '?' || text.charAt(i-1) == pattern.charAt(j-1))
                    currDP[j] = dp[j - 1];

                // Characters not match case (continue_star_sequence_case + stop_star_sequence_case)
                else if (pattern.charAt(j-1) == '*')
                    currDP[j] = dp[j] || currDP[j - 1];

                else currDP[j] = false;
            }
            dp = currDP;
        }
        return dp[n];
    }
}
