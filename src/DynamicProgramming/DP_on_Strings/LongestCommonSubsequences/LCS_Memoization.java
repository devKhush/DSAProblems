package DynamicProgramming.DP_on_Strings.LongestCommonSubsequences;
import java.util.Arrays;

// https://youtu.be/NPZn9jBrX8U
// https://takeuforward.org/data-structure/longest-common-subsequence-dp-25/

public class LCS_Memoization {

    // *********************************** Memoization Solution **************************************
    /*
    Time Complexity: O(N*M)
    Reason: There are N*M states therefore at max ‘N*M’ new problems will be solved.

    Space Complexity: O(N*M) + O(N+M)
    Reason: We are using an auxiliary recursion stack space(O(N+M))
    (see the recursive tree, in the worst case, we will go till N+M calls at a time)
    and a 2D array of DP O(N*M).
     */

    public int longestCommonSubsequence_V1(String s1, String s2){
        int m = s1.length();
        int n = s2.length();

        int[][] dp = new int[m][n];
        for (int[] row : dp)
            Arrays.fill(row, -1);

        // LCS_Memoization(m-1, n-1) means the longest common subsequence of string1[0:m-1] and string[0:n-1]
        return LCS_Memoization(m-1, n-1, s1, s2, dp);
    }

    // LCS_Memoization(i, j) means the longest common subsequence of string1[0:i] and string[0:j]
    // both lower & upper bound included
    private int LCS_Memoization(int i, int j, String s1, String s2, int[][] dp){
        if (i < 0 || j < 0)
            return 0;

        if (dp[i][j] != -1)
            return dp[i][j];

        // Case When the characters of current indices in both the string are equal
        if (s1.charAt(i) == s2.charAt(j))
            return  dp[i][j] = 1 + LCS_Memoization(i-1, j-1, s1, s2, dp);


        // When the characters of current indices in both the string are not equal
        // Then return the maximum of length of LCS found by decreasing both the indices one by one
        return  dp[i][j] = Math.max(LCS_Memoization(i, j-1, s1, s2, dp), LCS_Memoization(i-1, j, s1, s2, dp));
    }




    // ********************** Memoization Solution another version by shifting of index ***************
    /*
    Time Complexity: O(N*M)
    Reason: There are N*M states therefore at max ‘N*M’ new problems will be solved.

    Space Complexity: O((n+1) * (m+1)) + O(N + M) = O(N*M) + O(N+M)
    Reason: We are using an auxiliary recursion stack space(O(N+M))
    (see the recursive tree, in the worst case, we will go till N+M calls at a time)
    and a 2D array of DP O((n+1) * (m+1)).
     */

    private int longestCommonSubsequence_V2(String s1, String s2){
        int m = s1.length();
        int n = s2.length();

        int[][] dp = new int[m+1][n+1];
        for (int[] row : dp)
            Arrays.fill(row, -1);

        // LCS_Memoization(m, n) means the longest common subsequence of string1[0:m] and string[0:n]
        // upper bound not included (just like in string indexing)
        return LCS_Memoization(m, n, dp, s1, s2);
    }

    // LCS_Recursion_V2(i, j) means the longest common subsequence of string1[0:i] and string[0:j]
    // upper bound not included (just like in string indexing)
    private  int LCS_Memoization(int i, int j, int[][] dp, String s1, String s2){
        if (i == 0  || j == 0)
            return 0;

        if (dp[i][j] != -1)
            return dp[i][j];

        // Case When the characters of current indices in both the string are equal
        if (s1.charAt(i-1) == s2.charAt(j-1))
            return dp[i][j] = 1 + LCS_Memoization(i-1, j-1, dp, s1, s2);

        // When the characters of current indices in both the string are not equal
        // Then return the maximum of length of LCS found by decreasing both the indices one by one
        return dp[i][j] = 0 + Math.max(LCS_Memoization(i-1, j, dp, s1, s2), LCS_Memoization(i, j-1, dp, s1, s2));
    }

}
