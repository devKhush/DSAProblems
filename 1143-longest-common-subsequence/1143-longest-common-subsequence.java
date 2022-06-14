class Solution {
    
    public int longestCommonSubsequence(String str1, String str2){
        int m = str1.length();
        int n = str2.length();
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++){
            for (int j = 1; j <= n; j++){

                if (s1[i - 1] == s2[j - 1]){
                    dp[i][j] = 1 + dp[i-1][j-1];
                    continue;
                }

                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        return dp[m][n];
    }
    
    
    // Memoization *************************************************************************************************
     public int longestCommonSubsequence_Memoization(String s1, String s2){
        int m = s1.length();
        int n = s2.length();

        int[][] dp = new int[m+1][n+1];
        for (int[] row : dp)
            Arrays.fill(row, -1);

        return LCS_Memoization(m, n, dp, s1, s2);
    }

    
    private  int LCS_Memoization(int i, int j, int[][] dp, String s1, String s2){
        if (i == 0  || j == 0)
            return 0;

        if (dp[i][j] != -1)
            return dp[i][j];

        if (s1.charAt(i-1) == s2.charAt(j-1))
            return dp[i][j] = 1 + LCS_Memoization(i-1, j-1, dp, s1, s2);

        return dp[i][j] = 0 + Math.max(LCS_Memoization(i-1, j, dp, s1, s2), LCS_Memoization(i, j-1, dp, s1, s2));
    }
}