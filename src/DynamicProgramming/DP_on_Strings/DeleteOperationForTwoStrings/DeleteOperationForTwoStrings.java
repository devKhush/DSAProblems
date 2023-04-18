package DynamicProgramming.DP_on_Strings.DeleteOperationForTwoStrings;

// Pre requisite : Longest Common Subsequence

class DeleteOperationForTwoStrings {
    /****************************************** Efficient Solution ***********************************
     * TC -> O(m*n)
        * Finding LCS
     * SC -> O(m*n)
        * DP Array
     */
    public int minDistance(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        
        // length of the longest common subsequence
        int lenLCS = LCS(str1, str2, m, n);

        // This is required no. of operations
        return m + n - 2 * lenLCS;
    }

    // This code is same as that of the Longest Common Subsequence (LCS)
    public int LCS(String str1, String str2, int m, int n){
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        
        int[][] dp = new int[m + 1][n + 1];
        
        for (int i = 1; i <= m; i++){
            for (int j = 1; j <= n; j++){
                
                if (s1[i - 1] == s2[j - 1])
                    dp[i][j] = 1 + dp[i-1][j-1];
                else
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        return dp[m][n];
    }
}