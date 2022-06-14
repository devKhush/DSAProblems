package DynamicProgramming.DP_on_Strings.LongestCommonSubsequences;

// https://youtu.be/NPZn9jBrX8U
// https://takeuforward.org/data-structure/longest-common-subsequence-dp-25/

public class LCS_SpaceOptimization {

    // ********************************* Space Optimization ********************************************
    // Space Optimization Using Shifting of Index
    // Using shifting of Indices
    // Since dp[j-1]  will never give any IndexOutOfBoundException as base case of i & j are 0
    // as in case of memoization.

    // Time Complexity: O(N*M)
    //Reason: There are two nested loops.
    //Space Complexity: O(M)
    //Reason: We are using an external array of size ‘M+1’ to store only two rows.

    private int LCS_SpaceOptimized(String str1, String  str2){
        int m = str1.length(), n = str2.length();

        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        // Here, dp[j] denotes LCS of string1[0:i] & string2[0:j]
        // (just like normal string indexing)
        // 'dp' array values of stores previous row i.e, values of 'i-1'
        int[] dp = new int[n + 1];


        // No need to handle base cases for i==0 || j==0 as default values of Arrays are always 0
        // Other cases that starts from i = [1:m] & j = [1:n]
        for (int i = 1; i <= m; i++){

            // 'dp' array values of stores current row i.e, values of 'i'
            int[] tempDP = new int[n + 1];

            for (int j = 1; j <= n; j++){

                if (s1[i - 1] == s2[j - 1]) {
                    tempDP[j] = 1 + dp[j - 1];      // dp[j-1] = dp[i-1][j-1]
                    continue;
                }

                // dp[j] = dp[i-1][j]
                // tempDP[j-1] = dp[i][j-1]     as i belongs to current row
                tempDP[j] = Math.max(dp[j], tempDP[j-1]);
            }
            dp = tempDP;
        }

        // dp[m][n] denotes LCS of string1[0:m] & string2[0:n] (just like normal string indexing)
        // dp[n] = dp[m][n], since dp is replaced with tempDP
        return dp[n];
    }
}
