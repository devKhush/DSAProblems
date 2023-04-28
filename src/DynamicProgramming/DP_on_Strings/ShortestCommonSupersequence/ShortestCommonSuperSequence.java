package DynamicProgramming.DP_on_Strings.ShortestCommonSupersequence;

// https://leetcode.com/problems/shortest-common-supersequence/description/
// https://takeuforward.org/data-structure/shortest-common-supersequence-dp-31/
// https://www.geeksforgeeks.org/shortest-common-supersequence/
// https://youtu.be/xElxAuBcvsU

public class ShortestCommonSuperSequence {
    /************************************ Tabulation Solution *****************************************
     * The Longest common Super-Sequence is the string obtained by adding both string, after removing LCS
        once.
     * length(longest common Super-Sequence) = m + n - lcs

     * Time Complexity: O(m * n) + O(m + n)
        * O(m*n) to find LCS
        * O(m+n) to find the common super-sequence in worst case.
     * Space Complexity: O(m * n)
        * Space Optimization solution is not possible, as we need entire DP array
     */
    public String shortestCommonSuperSequence(String s1, String s2) {
        // Find the LCS of the string
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++){
            for (int j = 1; j <= n; j++){
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
            }
        }

        // Find the Shortest Common SuperSequence, in same way as printing the LCS
        int lcs = dp[m][n];
        char[] superSequence = new char[m + n - lcs];
        int i = m, j = n;
        int ptr = (m + n - lcs) - 1;

        while (i > 0 && j > 0){
            if (s1.charAt(i - 1) == s2.charAt(j - 1)){
                superSequence[ptr--] = s1.charAt(i - 1);
                i--;
                j--;
            }
            else if (dp[i - 1][j] > dp[i][j - 1]){
                superSequence[ptr--] = s1.charAt(i - 1);
                i--;
            }
            else { // dp[i - 1][j] <= dp[i][j - 1]
                superSequence[ptr--] = s2.charAt(j - 1);
                j--;
            }
        }
        while (i > 0){
            superSequence[ptr--] = s1.charAt(i - 1);
            i--;
        }
        while (j > 0){
            superSequence[ptr--] = s2.charAt(j - 1);
            j--;
        }
        return new String(superSequence);
    }
}
