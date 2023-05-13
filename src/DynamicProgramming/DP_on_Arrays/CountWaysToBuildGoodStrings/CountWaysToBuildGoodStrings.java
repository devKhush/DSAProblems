package DynamicProgramming.DP_on_Arrays.CountWaysToBuildGoodStrings;

// https://leetcode.com/problems/count-ways-to-build-good-strings/description/

public class CountWaysToBuildGoodStrings {
    private final int MOD = (int)(1e9+7);

    /********************************** Recursion *************************************************
     * Intuition: Basic Recursion Thinking
     * Time Complexity: O(2^high)   ~   exponential
        * Only one parameter is changing, which is length. Max. value of length is high
        * So, at each function call, we are calling same function two times.
        * So, the TC will be O(2^high) exponential.
     * Space Complexity: O(high)
        * Due to Recursion_Stack_space. Max depth will be O(high)
     */
    public int countGoodStrings_recursion(int low, int high, int zero, int one) {
        return f(0, low, high, zero, one);
    }

    private int f(int length, int low, int high, int zero, int one){
        if (length > high)      // Base case
            return 0;

        int addZero = f(length + zero, low, high, zero, one);
        int addOne = f(length + one, low, high, zero, one);

        // When length is less than low, explore all the possibility and sum them up
        if (length < low)
            return (addZero % MOD + addOne % MOD) % MOD;

        // when length is b/w low & high, explore all the possibility and sum them up
        // Also, add 1 for current good string
        else //if (low <= length  && length <= high)
            return (1 + addOne % MOD + addZero % MOD) % MOD;
    }


    /*************************************** Memoization ***************************************
     * Time Complexity: O(high)
        * Only one parameter is changing, which is length. Max. value of length is high
        * So, at max there are O(high) calls made.
     * Space Complexity: O(high)
        * DP_Array + Recursion_Stack_Space
     */
    public int countGoodStrings_memo(int low, int high, int zero, int one) {
        Integer[] dp = new Integer[high + 1];

        // Memoization Solution
        return f(0, low, high, zero, one, dp);
    }
    private int f(int length, int low, int high, int zero, int one, Integer[] dp){
        if (length > high)
            return 0;
        if (dp[length] != null)
            return dp[length];

        int addZero = f(length + zero, low, high, zero, one, dp);
        int addOne = f(length + one, low, high, zero, one, dp);

        if (length < low)
            return dp[length] = (addZero % MOD + addOne % MOD) % MOD;
        else
            return dp[length] = (1 + addOne % MOD + addZero % MOD) % MOD;
    }


    /************************************** Tabulation ******************************************
     * Tabulation version of Memoization Solution
     * Time Complexity: O(high)
        * Only one state, only one loop
     * Space Complexity: O(high)
        * DP_Array
     */
    public int countGoodStrings(int low, int high, int zero, int one) {
        int[] dp = new int[high + 1];
        // No need for base case for "length > high"

        for (int length = high; length >= 0; length--){
            int addZero = length + zero <= high ? dp[length + zero] : 0;
            int addOne = length + one <= high ? dp[length + one] : 0;

            if (length < low)
                dp[length] = (addOne % MOD + addZero % MOD) % MOD;
            else if (length >= low && length <= high)
                dp[length] = (1 + addOne % MOD + addZero % MOD) % MOD;
        }
        return dp[0];
    }

    /**************************************** Space Optimization *******************************
     * It is not possible bcoz of unknown step size of "length + zero" and "length + one"
     */
}
