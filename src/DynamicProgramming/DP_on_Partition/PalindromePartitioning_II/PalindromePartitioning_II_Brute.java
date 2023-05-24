package DynamicProgramming.DP_on_Partition.PalindromePartitioning_II;

// Pre-requisite: MCM
// https://leetcode.com/problems/palindrome-partitioning-ii/

public class PalindromePartitioning_II_Brute {
    /****************************************** Recursion *******************************************
     * Intuition:
        * Basic Recursion Thinking
        * Watch video for detailed intuition

     * Time Complexity: Exponential
     * Space Complexity: O(n)
        * Because in each call partitions will get smaller, so the max recursion stack space is O(n)
     */
    public int minCut_rec(String s) {
        int n = s.length();
        if (isPalindrome(0, n-1, s))
            return 0;

        return f(0, n-1, s);
    }
    private int f(int i, int j, String s){
        if (i == j)
            return 0;

        int minCut = (int)1e9;
        for (int k = i; k < j; k++){
            int leftPalindromeCuts = isPalindrome(i, k, s) ? 0 : f(i, k, s);
            int rightPalindromeCuts = isPalindrome(k+1, j, s) ? 0 : f(k+1, j, s);
            minCut = Math.min(minCut, leftPalindromeCuts + rightPalindromeCuts + 1);
        }
        return minCut;
    }
    private boolean isPalindrome(int low, int high, String s){
        while (low < high){
            if (s.charAt(low) != s.charAt(high))
                return false;
            low++;high--;
        }
        return true;
    }

    /**************************************** Memoization ********************************************
     * Memoization of above recursive solution

     * Time Complexity: O(n*n*n)
        * There are two changing states, i & j.
        * Another loop for k will run for each states on average n time
     * Space Complexity: O(n*n) + O(n)
        * DP Array + Recursion Stack space
     */
    public int minCut_memo(String s) {
        int n = s.length();
        if (isPalindrome(0, n-1, s))
            return 0;

        // DP Array
        Integer[][] dp = new Integer[n][n];
        return f(0, n-1, s, dp);
    }
    private int f(int i, int j, String s, Integer[][] dp){
        if (i == j)
            return 0;
        if (dp[i][j] != null)
            return dp[i][j];

        int minCut = (int)1e9;
        for (int k = i; k < j; k++){
            int leftPalindromeCuts = isPalindrome(i, k, s) ? 0 : f(i, k, s, dp);
            int rightPalindromeCuts = isPalindrome(k+1, j, s) ? 0 : f(k+1, j, s, dp);
            minCut = Math.min(minCut, leftPalindromeCuts + rightPalindromeCuts + 1);
        }
        return dp[i][j] = minCut;
    }

    /**************************************** Tabulation ********************************************
     * Tabulation of above memoization

     * Time Complexity: O(n*n*n)
        * Two changing states: i & j
        * One loop inside for each state
     * Space Complexity: O(n*n)
        * DP Array
     */
    public int minCut_tabu(String s) {
        int n = s.length();
        if (isPalindrome(0, n-1, s))
            return 0;

        // DP Array (no base case for i==j)
        int[][] dp = new int[n][n];

        // Other states
        for (int i = n-1; i >= 0; i--){
            for (int j = i+1; j < n; j++){
                int minCuts = (int)1e9;

                for (int k = i; k < j; k++){
                    int leftPalindromeCuts = isPalindrome(i, k, s) ? 0 : dp[i][k];
                    int rightPalindromeCuts = isPalindrome(k+1, j, s) ? 0 : dp[k+1][j];
                    minCuts = Math.min(minCuts, leftPalindromeCuts + rightPalindromeCuts + 1);
                }
                dp[i][j] = minCuts;
            }
        }
        return dp[0][n-1];
    }

}
