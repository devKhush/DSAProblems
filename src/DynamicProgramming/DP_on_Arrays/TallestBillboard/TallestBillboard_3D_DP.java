package DynamicProgramming.DP_on_Arrays.TallestBillboard;

// https://leetcode.com/problems/tallest-billboard/description/
// https://youtu.be/805umyRq7cE

public class TallestBillboard_3D_DP {
    /************************************ Intuitive Recursion ************************************
     * Intuition:
        * Basic Recursion thinking
     * Time Complexity: O(3^n)
        * 3 calls in each calls
     * Space Complexity: O(n)
        * Stack Space
     */
    public int tallestBillboard_recur(int[] rods) {
        int n = rods.length;
        return f(n - 1, 0, 0, rods);
    }
    public int f(int i, int rod1, int rod2, int[] rods){
        if (i < 0)
            return rod1 == rod2 ? rod1 : 0;

        int notTake = f(i - 1, rod1, rod2, rods);
        int take1 = f(i - 1, rod1 + rods[i], rod2, rods);
        int take2 = f(i - 1, rod1, rod2 + rods[i], rods);
        return Math.max(notTake, Math.max(take1, take2));
    }

    /************************************  Memoization ************************************
     * Time Complexity: O(n*sum*sum)
        * 3 states for 3D DP (index, array_sum, array_sum)
     * Space Complexity: O(n*sum*sum) + O(n)
        * 3D_DP + Stack Space

     * Still we get TLE & MLE
     */
    public int tallestBillboard_memo(int[] rods) {
        int n = rods.length;
        int sum = 0;
        for (int val : rods)
            sum += val;

        Integer[][][] dp = new Integer[n][sum + 1][sum + 1];
        return f(n - 1, 0, 0, rods, dp);
    }
    public int f(int i, int rod1, int rod2, int[] rods, Integer[][][] dp){
        if (i < 0)
            return rod1 == rod2 ? rod1 : 0;
        if (dp[i][rod1][rod2] != null)
            return dp[i][rod1][rod2];

        int notTake = f(i - 1, rod1, rod2, rods, dp);
        int take1 = f(i - 1, rod1 + rods[i], rod2, rods ,dp);
        int take2 = f(i - 1, rod1, rod2 + rods[i], rods, dp);
        return dp[i][rod1][rod2] = Math.max(notTake, Math.max(take1, take2));
    }

    /************************************ Tabulation ********************************************
     * Time Complexity: O(n*sum*sum)
        * 3 states for 3D DP (index, array_sum, array_sum)
     * Space Complexity: O(n*sum*sum) + O(n)
        * Still we get TLE & MLE

     * On space optimization, we get TLE (and not MLE)
     */
    public int tallestBillBoard_tabu(int[] rods){
        int n = rods.length;
        int sum = 0;
        for (int val : rods)
            sum += val;

        int[][][] dp = new int[n + 1][sum + 1][sum + 1];
        for (int rod1 = 0; rod1 <= sum; rod1++)
            dp[0][rod1][rod1] = rod1;

        for (int i = 1; i <= n; i++){
            for (int rod1 = 0; rod1 <= sum; rod1++){
                for (int rod2 = 0; rod2 <= sum; rod2++){
                    int notTake = dp[i - 1][rod1][rod2];
                    int takeRod1 = rod1 + rods[i-1] <= sum ? dp[i - 1][rod1 + rods[i-1]][rod2] : 0;
                    int takeRod2 = rod2 + rods[i-1] <= sum ? dp[i - 1][rod1][rod2 + rods[i-1]] : 0;
                    dp[i][rod1][rod2] = Math.max(notTake, Math.max(takeRod1, takeRod2));
                }
            }
        }
        return dp[n][0][0];
    }
}
