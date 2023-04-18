package DynamicProgramming.DP_on_Strings.LongestCommonSubstring;

// https://practice.geeksforgeeks.org/problems/longest-common-substring1452/1
// https://www.geeksforgeeks.org/longest-common-substring-dp-29/
// https://youtu.be/_wP9mWNPL5w
// https://takeuforward.org/data-structure/longest-common-substring-dp-27/

public class LongestCommonSubstring {
    /********************************* Tabulation Solution ******************************************
     * Time Complexity: O(m*n)
     * Space Complexity: O(m*n)
     */
    public int longestCommonSubstring(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        int longestSubstring = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)){
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    longestSubstring = Math.max(dp[i][j], longestSubstring);
                }
                // If we remove this, then it's also fine
                else
                    dp[i][j] = 0;
            }
        }
        return longestSubstring;
    }



    /********************************* Space Optimization Solution ******************************************
     * Time Complexity: O(m*n)
     * Space Complexity: O(n)
     */
    public int longestCommonSubstring_(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[] dp = new int[n + 1];
        int longestSubstring = 0;

        for (int i = 1; i <= m; i++) {
            int[] tempDP = new int[n + 1];

            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)){
                    tempDP[j] = 1 + dp[j - 1];
                    longestSubstring = Math.max(tempDP[j], longestSubstring);
                }
                // If we remove this, then it's also fine
                else tempDP[j] = 0;
            }
            dp = tempDP;
        }
        return longestSubstring;
    }
}
