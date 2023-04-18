package DynamicProgramming.DP_on_Strings.PrintLongestCommonSubsequence;

// https://youtu.be/-zI4mrF2Pb4
// https://www.geeksforgeeks.org/printing-longest-common-subsequence/
// https://takeuforward.org/data-structure/print-longest-common-subsequence-dp-26/

public class PrintLongestCommonSubsequence {
    /************************************* Memoization Solution **********************************
     * Time Complexity: O(m * n)
     * Space Complexity: O((m+1) * (n+1) * min(len(s1),len(s2)))  +  O(m + n)
        * DP array
        * Size string in DP array can be at most min(len(s1),len(s2)).
        * Recursion stack space of O(m + n).
     */
    public static String lcs(String s, String t) {
        int m = s.length();
        int n = t.length();
        String[][] dp = new String[m+1][n+1];
        return lcs(m, n, dp, s, t);
    }

    private static String lcs(int i, int j, String[][] dp, String s, String t){
        if (i == 0 || j == 0)
            return "";
        if (dp[i][j] != null) return dp[i][j];

        if (s.charAt(i - 1) == t.charAt(j - 1))
            return lcs(i - 1, j - 1, dp, s, t) + s.charAt(i);

        String s1 = lcs(i - 1, j, dp, s, t);
        String s2 = lcs(i, j - 1, dp, s, t);
        return dp[i][j] = s1.length() >= s2.length() ? s1 : s2;
    }


    /********************************** Tabulation ***********************************************
     * Time Complexity: O(m * n)
     * Space Complexity: O((m+1) * (n+1) * min(len(s1),len(s2)))
        * DP Array
        * Size string in DP array can be at most min(len(s1),len(s2)).
     */
    private static String print_lcs(String s1, String s2){
        int m = s1.length(), n = s2.length();
        String[][] dp = new String[m + 1][n + 1];

        for (int i = 0; i <= m; i++)
            dp[i][0] = "";
        for (int j = 0; j <= n; j++)
            dp[0][j] = "";

        for (int i = 1; i <= m; i++){
            for (int j = 1; j <= n; j++){
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + s1.charAt(i - 1);
                }
                else{
                    String s = dp[i - 1][j];
                    String t = dp[i][j - 1];
                    dp[i][j] = s.length() >= t.length() ? s : t;
                }
            }
        }
        return dp[m][n];
    }


    /************************************** Best Solution *****************************************
     * Time Complexity: O(m * n)
     * Space Complexity: O((m+1) * (n+1)) + min(len(s1),len(s2)))
        * DP Array
        * SPace of "min(len(s1),len(s2)))" due to LCS string array
     */
    private static String printLCS(String s1, String s2){
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m+1][n+1];

        // Find out the length of LCS
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

        // Find the LCS in string form
        char[] longestLCS = new char[dp[m][n]];
        int i = m, j = n, index = dp[m][n] - 1;

        while (i > 0 && j > 0){
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                longestLCS[index--] = s1.charAt(i - 1);
                i--;
                j--;
            }
            else{
                if (dp[i - 1][j] >= dp[i][j - 1])
                    i--;
                else
                    j--;
            }
        }
        return new String(longestLCS);
    }
}
