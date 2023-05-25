package DynamicProgramming.DP_on_Partition.PalindromePartitioning_II;

// Pre-requisite: "Palindrome Partitioning - I"
// https://takeuforward.org/data-structure/palindrome-partitioning-ii-front-partition-dp-53/
// https://leetcode.com/problems/palindrome-partitioning-ii/
// https://youtu.be/_H8V5hJUGd0

public class PalindromePartitioning_II {
    /****************************************** Recursion *******************************************
     * Intuition:
        * Basic Recursion Thinking
        * Same way as Palindrome Partitioning - I

     * Time Complexity: Exponential
     * Space Complexity: O(n)
        * Because in each call partitions will get smaller, so the max recursion stack space is O(n)
     */
    public int minCut_rec(String s) {
        int n = s.length();
        return f(0, s, n) - 1;
    }
    private int f(int i, String s, int n){
        if (i == n)
            return 0;

        int minCuts = (int)1e9;
        for (int j = i; j < n; j++){
            if (isPalindrome(i, j, s)){
                int cuts = 1 + f(j + 1, s, n);
                minCuts = Math.min(minCuts, cuts);
            }
        }
        return minCuts;
    }

    /**************************************** Memoization ********************************************
     * Memoization of above recursive solution

     * Time Complexity: O(n*n)
        * There is only one changing states, i
        * Another loop for j will run for each states on average n time
     * Space Complexity: O(n) + O(n)
        * DP Array + Recursion Stack space
     */
    public int minCut_memo(String s) {
        int n = s.length();

        // DP Array
        Integer[] dp = new Integer[n];
        return f(0, s, n, dp) - 1;
    }
    private int f(int i, String s, int n, Integer[] dp){
        if (i == n)
            return 0;
        if (dp[i] != null)
            return dp[i];

        int minCuts = (int)1e9;
        for (int j = i; j < n; j++){
            if (isPalindrome(i, j, s)){
                int cuts = 1 + f(j + 1, s, n, dp);
                minCuts = Math.min(minCuts, cuts);
            }
        }
        return dp[i] = minCuts;
    }


    /**************************************** Tabulation ********************************************
     * Tabulation of above memoization

     * Time Complexity: O(n*n)
        * One changing states: i
        * One partition loop for each state
     * Space Complexity: O(n)
        * DP Array
     */
    public int minCut(String s) {
        int n = s.length();

        // DP Array (no base case for i == n)
        int[] dp = new int[n + 1];

        for (int i = n-1; i >= 0; i--){
            int minCuts = (int)1e9;
            for (int j = i; j < n; j++){
                if (isPalindrome(i, j, s)){
                    int cuts = 1 + dp[j + 1];
                    minCuts = Math.min(minCuts, cuts);
                }
            }
            dp[i] = minCuts;
        }
        return dp[0] - 1;
    }

    private boolean isPalindrome(int low, int high, String s){
        while (low < high){
            if (s.charAt(low) != s.charAt(high))
                return false;
            low++;high--;
        }
        return true;
    }
}
