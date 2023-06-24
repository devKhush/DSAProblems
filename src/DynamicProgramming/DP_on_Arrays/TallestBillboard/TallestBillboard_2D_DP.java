package DynamicProgramming.DP_on_Arrays.TallestBillboard;

// https://leetcode.com/problems/tallest-billboard/description/
// https://youtu.be/805umyRq7cE

public class TallestBillboard_2D_DP {
    /************************************** Memoization *******************************************
     * Time Complexity: O(n * 2*sum) ~ O(n*sum)
        * Reduced three states  to two states
        * Beacuse we only care the condition s1==s2, so, the difference should be 0.
     * Space Complexity: O(n*2*sum) + O(n)
        * DP_Array + Stack Space
     */
    int offset;
    public int tallestBillboard(int[] rods) {
        int n = rods.length;
        int sum = 0;
        for (int val : rods)
            sum += val;
        offset = sum;

        Integer[][] dp = new Integer[n][2*sum + 1];
        return f(0, 0, rods, dp);
    }

    private int f(int i, int diff, int[] rods, Integer[][] dp){
        if (i == rods.length)
            return diff == 0 ? 0 : -(int)1e9;
        if (dp[i][diff + offset] != null)
            return dp[i][diff + offset];

        int notTake = f(i + 1, diff, rods, dp);
        int takeRod1 = rods[i] + f(i + 1, diff + rods[i], rods, dp);
        int takeRod2 = f(i + 1, diff - rods[i], rods, dp);
        return dp[i][diff + offset] = Math.max(notTake, Math.max(takeRod1, takeRod2));
    }
}
