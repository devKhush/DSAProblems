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

    private int LCS_SpaceOptimized(String s1, String  s2){
        int m = s1.length(), n = s2.length();

        // Here, dp[j] denotes LCS of string1[0:i] & string2[0:j]
        // (just like normal string indexing)
        // 'dp' array values of stores previous row i.e, values of 'i-1'
        int[] dp = new int[n + 1];

        for (int i = 1; i <= m; i++){
            int[] tempDP = new int[n + 1];

            for (int j = 1; j <= n; j++){
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    tempDP[j] = 1 + dp[j - 1];

                else
                    tempDP[j] = Math.max(dp[j], tempDP[j - 1]);
            }
            dp = tempDP;
        }
        return dp[n];
    }
}
