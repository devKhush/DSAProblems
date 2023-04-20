package DynamicProgramming.DP_on_Strings.MinimumInsertionsAndDeletionsToConvertString;

// https://leetcode.com/problems/delete-operation-for-two-strings/description/
// https://takeuforward.org/data-structure/minimum-insertions-deletions-to-convert-string-dp-30/
// https://youtu.be/yMnH0jrir0Q
// Pre-Requisite: Longest Common SubSequence

public class MinimumInsertionsAndDeletionsToConvertString {
    /************************************** Tabulation Solution ******************************************
     * Intuition: To minimize the operations (insertions and deletions), we first need to find the
            longest common subsequence in both the strings s1 and s2.
        * So, the minimum operations would be
            * Answer  =  (m - lcs) + (n - lcs)
            * m,n -> length of two string
            * Remove (m - lcs) no. of characters from string 1.
            * Add required (n - lcs) no. of characters to resultant string 2.

     * Time Complexity: O(m*n)
        * Same as Longest Common Subsequence
     * Space Complexity: O(m*n) or O(n)
        * Same as LCS (depending upon Tabulation or Space-optimization)
     */
    public int minDistance(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int lcs = lcs(s1, s2);

        return m + n - 2*lcs;
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
