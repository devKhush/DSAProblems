package DynamicProgramming.DP_on_Arrays.SolvingQuestionsWithBrainpower;

public class SolvingQuestionsWithBrainpower {
    /********************************** Recursion / Memoization *************************************
     * Intuition: Basic Take and notTake approach
     * Time Complexity: O(n)
        * Only 'n' calls will be made at max
     * Space Complexity: O(n)
        * DP_Array + Recursion_Stack_Space
     */
    public long mostPoints_memo(int[][] questions) {
        Long[] dp = new Long[questions.length];
        return f(0, questions, dp);
    }

    private long f(int i, int[][] questions, Long[] dp){
        if (i >= questions.length)
            return 0L;
        if (dp[i] != null)
            return dp[i];

        long take = questions[i][0] + f(i + 1 + questions[i][1], questions, dp);
        long dontTake = f(i + 1, questions, dp);
        return dp[i] = Math.max(take, dontTake);
    }

    /*************************************** Tabulation *******************************************
     * Time Complexity: O(n)
        * Only one state of size 'n'
     * Space Complexity: O(n)
        * DP_Array
     */
    public long mostPoints(int[][] questions){
        int n = questions.length;
        long[] dp = new long[n + 1];

        for (int i = n - 1; i >= 0; i--){
            // Solve the current question and move to the next question as specified by jump
            long take = questions[i][0];
            if (i + 1 + questions[i][1] < n)
                take += dp[i + 1 + questions[i][1]];

            // Skip the current question
            long dontTake = dp[i + 1];

            dp[i] = Math.max(take, dontTake);
        }
        return dp[0];
    }

    // Space Optimization is not possible, because the step size on taking case is not fixed.
}
