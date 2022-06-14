class Solution {
    public int minDistance(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        
        // length of longest common subsequence
        int lenLCS = LCS(str1, str2, m, n);
        
        return m + n - 2 * lenLCS;
    }
    
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