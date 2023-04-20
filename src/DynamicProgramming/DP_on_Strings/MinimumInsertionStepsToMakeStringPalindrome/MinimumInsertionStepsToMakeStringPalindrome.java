package DynamicProgramming.DP_on_Strings.MinimumInsertionStepsToMakeStringPalindrome;

// https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/description/
// https://takeuforward.org/data-structure/minimum-insertions-to-make-string-palindrome-dp-29/
// https://youtu.be/xPBLEj41rFU
// Pre-Requisite: Longest Common SubSequence

public class MinimumInsertionStepsToMakeStringPalindrome {
    /************************************** Tabulation Solution ******************************************
     * Intuition: To minimize the no. of insertions to make string palindrome, we keep the longest
        palindrome subsequence in the string intact, and add the remaining characters.

     * Time Complexity: O(n*n)
        * Same as Longest Common Subsequence
     * Space Complexity: O(n*n) or O(n)
        * Same as LCS (depending upon Tabulation or Space-optimization)
     */
    public int minInsertions(String s) {
        int n = s.length();
        String reverse = new StringBuilder(s).reverse().toString();

        return n - lcs(s, reverse);
    }

    private int lcs(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[m][n];
    }
}
