package DynamicProgramming.DP_on_Strings.LongestCommonSubsequences;

// https://youtu.be/NPZn9jBrX8U
// https://takeuforward.org/data-structure/longest-common-subsequence-dp-25/

public class LCS_Tabulation {
    /********************************** Tabulation Solution ************************************
     * Using shifting of Indices
     * This is the version of Tabulation with Shifting of index
     * Since dp[i-1][j-1]  will never give any IndexOutOfBoundException as base case of i & j are 0, as in case of memoization.
     * Time Complexity: O(m*n)
     * Space Complexity: O((m+1) * (n+1)) = O(m*n)
     */
    public int LCS_Tabulation(String s1, String s2){
        // NOTE: No need to handle base cases for i==0 || j==0 manually as default values of Arrays
        // are always 0
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m+1][n+1];

        // Other cases that starts from i = [1:m] & j = [1:n]
        for (int i = 1; i <= m; i++){
            for (int j = 1; j <= n; j++){
                if (s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }
                else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        // dp[m][n] denotes LCS of string1[0:m] & string2[0:n] (just like normal string indexing)
        return dp[m][n];
    }
}
