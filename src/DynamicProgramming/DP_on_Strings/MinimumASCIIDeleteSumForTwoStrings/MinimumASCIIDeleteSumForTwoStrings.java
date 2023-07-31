package DynamicProgramming.DP_on_Strings.MinimumASCIIDeleteSumForTwoStrings;

// PRE-REQUISITE: LCS of two Strings
// https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/description/

public class MinimumASCIIDeleteSumForTwoStrings {
    /************************************ Memoization ********************************************
     * Intuition:
        * LCS (longest common subsequence) of two Strings
     * Time Complexity: O(m*n)
        * Two DP states of size m & n
     * Space Complexity: O(m*n)
        * DP Array
     */
    public int minimumDeleteSum__memo(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        Integer[][] dp = new Integer[m][n];

        return f(m - 1, n - 1, s1, s2, dp);
    }

    private int f(int i, int j, String s1, String s2, Integer[][] dp){
        if (i < 0 && j < 0)
            return 0;
        if (i < 0)
            return removeAll(s2, j);
        if (j < 0)
            return removeAll(s1, i);
        if (dp[i][j] != null)
            return dp[i][j];

        if (s1.charAt(i) == s2.charAt(j))
            return f(i-1, j-1, s1, s2, dp);
        int removeFromS1 = f(i-1, j, s1, s2, dp) + s1.charAt(i);
        int removeFromS2 = f(i, j-1, s1, s2, dp) + s2.charAt(j);
        return dp[i][j] = Math.min(removeFromS1, removeFromS2);
    }

    private int removeAll(String s1, int ind){
        int ascii = 0;
        for (int i = 0; i <= ind; i++){
            ascii += s1.charAt(i);
        }
        return ascii;
    }


    /************************************** Tabulation ******************************************
     * Tabulation of above memoized solution
     * Time Complexity: O(m*n)
        * Two DP states of size m & n
     * Space Complexity: O(m*n)
        * DP Array
     */
    public int minimumDeleteSum__tabu(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];         // Shifting of indices

        // Base cae for j < 0
        int asciiS1 = 0;
        for (int i = 1; i <= m; i++){
            asciiS1 += s1.charAt(i - 1);
            dp[i][0] = asciiS1;
        }
        // Base cae for i < 0
        int asciiS2 = 0;
        for (int j = 1; j <= n; j++){
            asciiS2 += s2.charAt(j - 1);
            dp[0][j] = asciiS2;
        }

        for (int i = 1; i <= m; i++){
            for (int j = 1; j <= n; j++){
                if (s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = dp[i - 1][j - 1];
                }
                else{
                    int removeFromS1 = dp[i-1][j] + s1.charAt(i-1);
                    int removeFromS2 = dp[i][j-1] + s2.charAt(j-1);
                    dp[i][j] = Math.min(removeFromS1, removeFromS2);
                }
            }
        }
        return dp[m][n];
    }


    /*************************************** Space Optimization **********************************
     * Time Complexity: O(m*n)
        * Two DP states of size m & n
     * Space Complexity: O(n)
        * 1D-DP Array
     */
    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[] dp = new int[n + 1];

        // Base case for i < 0
        int asciiS1 = 0;
        int asciiS2 = 0;
        for (int j = 1; j <= n; j++){
            asciiS2 += s2.charAt(j - 1);
            dp[j] = asciiS2;
        }

        for (int i = 1; i <= m; i++){
            int[] currDP = new int[n + 1];

            // Base case for j < 0
            asciiS1 += s1.charAt(i - 1);
            currDP[0] = asciiS1;

            for (int j = 1; j <= n; j++){
                if (s1.charAt(i-1) == s2.charAt(j-1)){
                    currDP[j] = dp[j - 1];
                }
                else{
                    int removeFromS1 = dp[j] + s1.charAt(i-1);
                    int removeFromS2 = currDP[j-1] + s2.charAt(j-1);
                    currDP[j] = Math.min(removeFromS1, removeFromS2);
                }
            }
            dp = currDP;
        }
        return dp[n];
    }
}
