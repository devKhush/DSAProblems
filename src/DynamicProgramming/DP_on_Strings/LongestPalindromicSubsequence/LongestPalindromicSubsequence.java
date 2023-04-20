package DynamicProgramming.DP_on_Strings.LongestPalindromicSubsequence;

// https://leetcode.com/problems/longest-palindromic-subsequence/
// https://takeuforward.org/data-structure/longest-palindromic-subsequence-dp-28/
// https://youtu.be/6i_T5kkfv4A

public class LongestPalindromicSubsequence {
    /************************************ LCS Solution ******************************************
     * Time Complexity: O(m*n)
        * Same as Finding LCS
     * Space Complexity: O(m*n) or O(n)
        * Depending on Tabulation or Space-optimization
     */
    public int longestPalindromeSubsequence(String s) {
        String reverse = new StringBuilder(s).reverse().toString();
        return lcs(s, reverse);
    }

    private int lcs(String s1, String s2){
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++){
            for (int j = 1; j <= n; j++){
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
            }
        }
        return dp[m][n];
    }
}
