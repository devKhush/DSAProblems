package DynamicProgramming.DP_on_Strings.LongestCommonSubsequences;

// https://youtu.be/NPZn9jBrX8U
// https://takeuforward.org/data-structure/longest-common-subsequence-dp-25/

public class LCS_Tabulation {
    /*
    Complexities:
    Time Complexity: O(N*M)
    Reason: There are two nested loops to find every (i,j) sub-problems

    Space Complexity: O(N*M)
    Reason: We are using an external array of size ‘N*M)’. Stack Space is eliminated.
    Ignoring char array. Even if we include char array, SC will be same as we just increase one column
    which is nothing as compared to m*n DP matrix
     */

    // ********************************** Tabulation Solution 1 ************************************
    // This is the version of Tabulation without Shifting of index
    // Since dp[i-1][j-1]  can give index out of bound Exception for i = -1 or j = -1
    // We have to check first whether i & j are greater than 0 or not, for taking answer of
    // previous sub-problems from DP array

    public int LCS_Tabulation_V1(String str1, String str2){
        int m = str1.length();
        int n = str2.length();
        // Converting string to char arrays bcoz array indexing are O(1) unlike string indexing that are O(n)
        // Run time will be faster
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        // Here, dp[i][j] denotes LCS of string1[0:i] & string2[0:j] (unlike normal string indexing)
        int[][] dp = new int[m][n];

        // No base cases to handle for i < 0 || j < 0 (as such index don't exists)
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){

                if (s1[i] == s2[j]){
                    if (i > 0 && j > 0)
                        dp[i][j] = 1 + dp[i-1][j-1];
                    else
                        dp[i][j] = 1;
                    continue;
                }

                // If both i & j are > 0, take maximum among the LCS of dp[i-1][j] & dp[i][j-1]
                if (i > 0  &&  j > 0)
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                else if (i > 0)     // else dp[i][j]  will be just dp[i][j]
                    dp[i][j] = dp[i-1][j];
                else if (j > 0)     // else dp[i][j]  will be just dp[i][j-1]
                    dp[i][j] = dp[i][j-1];
            }
        }
        // dp[m-1][n-1] denotes LCS of string1[0:m-1] & string2[0:n-1]
        return dp[m-1][n-1];
    }



    // ********************************** Tabulation Solution 2 ************************************
    // Using shifting of Indices
    // This is the version of Tabulation with Shifting of index
    // Since dp[i-1][j-1]  will never give any IndexOutOfBoundException as base case of i & j are 0
    // as in case of memoization.

    public int LCS_Tabulation(String str1, String str2){
        int m = str1.length();
        int n = str2.length();
        // Converting string to char arrays bcoz array indexing are O(1) unlike string indexing that are O(n)
        // Run time will be faster
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        // Here, dp[i][j] denotes LCS of string1[0:i] & string2[0:j]
        // (just like normal string indexing)
        int[][] dp = new int[m + 1][n + 1];

        // No need to handle base cases for i==0 || j==0 as default values of Arrays are always 0
        // Other cases that starts from i = [1:m] & j = [1:n]
        for (int i = 1; i <= m; i++){
            for (int j = 1; j <= n; j++){

                if (s1[i - 1] == s2[j - 1]){
                    dp[i][j] = 1 + dp[i-1][j-1];
                    continue;
                }

                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        // dp[m][n] denotes LCS of string1[0:m] & string2[0:n] (just like normal string indexing)
        return dp[m][n];
    }
}
