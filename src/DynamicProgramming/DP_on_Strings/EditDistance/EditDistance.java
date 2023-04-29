package DynamicProgramming.DP_on_Strings.EditDistance;

// This is so far best String Recursion Problem
// https://leetcode.com/problems/edit-distance/description/
// https://takeuforward.org/data-structure/edit-distance-dp-33/
// https://youtu.be/fJaKO8FbDdo

public class EditDistance {
    /************************************* Recursion ***********************************************
     * Intuition: Basic Recursion Thinking
     * Time Complexity: O(2^m * 2^n)
        * All subsequence will be explored
     * Space Complexity: O(m + n)
        * Recursion stack space of 'm+n'. In worst case, both the string will get exhausted.
     */
    public int minDistance_recursion(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        return f(m-1, n-1, word1, word2);
    }
    private int f(int i, int j, String w1, String w2){
        if (j < 0)
            return i + 1;
        if (i < 0)
            return j + 1;

        if (w1.charAt(i) == w2.charAt(j))
            return f(i - 1, j - 1, w1, w2);

        int add = 1 +  f(i, j - 1, w1, w2);
        int delete = 1 +  f(i - 1, j, w1, w2);
        int replace = 1 +  f(i - 1, j - 1, w1, w2);
        return Math.min(add, Math.min(delete, replace));
    }


    /************************************* Memoization ***********************************************
     * Time Complexity: O(m * n)
        * Two States for DP
     * Space Complexity: O(m * n) + O(m + n)
        * DP_Array + Recursion_stack_space
     */
    public int minDistance_memoization(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        Integer[][] dp = new Integer[m][n];

        return f(m-1, n-1, word1, word2, dp);
    }
    private int f(int i, int j, String w1, String w2, Integer[][] dp){
        if (j < 0)
            return i + 1;
        if (i < 0)
            return j + 1;
        if (dp[i][j] != null)
            return dp[i][j];

        if (w1.charAt(i) == w2.charAt(j))
            return dp[i][j] = f(i - 1, j - 1, w1, w2, dp);

        int add = 1 + f(i, j - 1, w1, w2, dp);
        int delete = 1 + f(i - 1, j, w1, w2, dp);
        int replace = 1 + f(i - 1, j - 1, w1, w2, dp);
        return dp[i][j] = Math.min(add, Math.min(delete, replace));
    }


    /******************************** Tabulation: Shifting Of Indices ***********************************
     * Time Complexity: O(m * n)
        * Two States for DP
     * Space Complexity: O(m * n)
        * DP_Array
     */
    public int minDistance_tabulation(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        // DP Array
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++)        // base case for j==0 in shifting of index
            dp[i][0] = i;
        for (int j = 0; j <= n; j++)        // base case for i==0 in shifting of index
            dp[0][j] = j;

        // Other States
        for (int i = 1; i <= m; i++){
            for (int j = 1; j <= n; j++){
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                else{
                    int add = 1 + dp[i][j - 1];
                    int delete = 1 + dp[i - 1][j];
                    int replace = 1 + dp[i - 1][j - 1];
                    dp[i][j] = Math.min(add, Math.min(delete, replace));
                }
            }
        }
        return dp[m][n];
    }


    /***************************** Space Optimization: Shifting of Indices ******************************
     * Time Complexity: O(m * n)
        * Two States for DP
     * Space Complexity: O(n)
        * Space Optimized DP_Array
     */
    public int minDistance_space_optimized(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        // DP Array
        int[] dp = new int[n + 1];
        for (int j = 0; j <= n; j++)   // base case for i==0 in shifting of index
            dp[j] = j;

        for (int i = 1; i <= m; i++){
            int[] currDP = new int[n + 1];
            currDP[0] = i;              // base case for j==0 in shifting of index

            for (int j = 1; j <= n; j++){
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    currDP[j] = dp[j - 1];
                }
                else{
                    int add = 1 + currDP[j - 1];
                    int delete = 1 + dp[j];
                    int replace = 1 + dp[j - 1];
                    currDP[j] = Math.min(add, Math.min(delete, replace));
                }
            }
            dp = currDP;
        }
        return dp[n];
    }
}

