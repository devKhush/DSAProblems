package DynamicProgramming.DP_on_Arrays.UncrossedLines;

// https://leetcode.com/problems/uncrossed-lines/description/

public class UncrossedLines {
    /******************************* Recursion + Memoization ******************************************
     * Intuition: ON observing the problem, it appears to be the same as the LCS of two strings
     * Time Complexity: O(m*n)
        * Two states for two arrays
     * Space Complexity: O(m*n)
        * DP_Array + Recursion_stack_space
     */
    public int maxUncrossedLines_memo(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        Integer[][] dp = new Integer[m][n];

        return f(m - 1, n - 1, nums1, nums2, dp);
    }

    private int f(int i, int j, int[] arr1, int[] arr2, Integer[][] dp){
        if (i < 0)
            return 0;
        if (j < 0)
            return 0;
        if (dp[i][j] != null)
            return dp[i][j];

        if (arr1[i] == arr2[j])
            return 1 + f(i - 1, j - 1, arr1, arr2, dp);
        return dp[i][j] = Math.max(f(i - 1, j, arr1, arr2, dp), f(i, j - 1, arr1, arr2, dp));
    }

    /************************************ Tabulation *****************************************
     * Intuition: ON observing the problem, it appears to be the same as the LCS of two strings
     * Same as the LCS of two arrays
     * Time Complexity: O(m*n)
        * Two states
     * Space Complexity: O(m*n)
        * DP_Array
     */
    public int maxUncrossedLines_tabu(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++){
            for (int j = 1; j <= n; j++){
                if (nums1[i - 1] == nums2[j - 1])
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        return dp[m][n];
    }


    /************************************ Space Optimization *****************************************
     * Intuition: ON observing the problem, it appears to be the same as the LCS of two strings
     * Same as the LCS of two arrays
     * Time Complexity: O(m*n)
        * Two states
     * Space Complexity: O(n)
        * Only one DP_Array
     */
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] dp = new int[n + 1];

        for (int i = 1; i <= m; i++){
            int[] curr_DP = new int[n + 1];

            for (int j = 1; j <= n; j++){
                if (nums1[i - 1] == nums2[j - 1])
                    curr_DP[j] = 1 + dp[j - 1];
                else
                    curr_DP[j] = Math.max(dp[j], curr_DP[j-1]);
            }
            dp = curr_DP;
        }
        return dp[n];
    }
}
