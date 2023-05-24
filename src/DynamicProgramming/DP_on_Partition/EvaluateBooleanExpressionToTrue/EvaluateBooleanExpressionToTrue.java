package DynamicProgramming.DP_on_Partition.EvaluateBooleanExpressionToTrue;

// https://www.interviewbit.com/problems/evaluate-expression-to-true/
// https://takeuforward.org/data-structure/evaluate-boolean-expression-to-true-partition-dp-dp-52/
// https://youtu.be/MM7fXopgyjw

public class EvaluateBooleanExpressionToTrue {
    /****************************************** Recursion *******************************************
     * Intuition:
        * Watch video for detailed intuition

     * Time Complexity: Exponential
     * Space Complexity: O(n)
        * Because in each call partitions will get smaller, so the max recursion stack space is O(n)
     */
    public int countTrue_rec(String str) {
        int n = str.length();
        return f(0, n-1, 1, str);
    }
    private int f(int i, int j, int isTrue, String str){
        if (i == j){
            if (isTrue == 1) return str.charAt(i) == 'T' ? 1 : 0;
            else return str.charAt(i) == 'F' ? 1 : 0;
        }
        int totalWays = 0;
        for (int k = i+1; k <= j-1; k += 2){
            int leftTrue = f(i, k-1, 1, str);
            int leftFalse = f(i, k-1, 0, str);
            int rightTrue = f(k+1, j, 1, str);
            int rightFalse = f(k+1, j, 0, str);

            if (str.charAt(k) == '&'){
                if (isTrue == 1) totalWays += leftTrue*rightTrue;
                else totalWays += leftTrue*rightFalse + leftFalse*rightTrue + leftFalse*rightFalse;
            }
            else if (str.charAt(k) == '|'){
                if (isTrue == 1) totalWays += leftTrue*rightTrue + leftTrue*rightFalse + leftFalse*rightTrue;
                else totalWays += leftFalse*rightFalse;
            }
            else if (str.charAt(k) == '^'){
                if (isTrue == 1) totalWays += leftTrue*rightFalse + leftFalse*rightTrue;
                else totalWays += leftTrue*rightTrue + leftFalse*rightFalse;
            }
        }
        return totalWays;
    }

    /**************************************** Memoization ********************************************
     * Memoization of above recursive solution

     * Time Complexity: O(n*n*2 * n) ~ O(n*n*n)
        * There are three changing states, i & & isTrue (only two values).
        * Another loop for k will run for each states on average n time
     * Space Complexity: O(n*n*2) + O(n) ~ O(n*n)
        * DP Array + Recursion Stack space
     */
    public int countTrue_memo(String str) {
        int n = str.length();
        // 3D DP Array
        Integer[][][] dp = new Integer[n][n][2];

        return f(0, n-1, 1, str, dp);
    }
    private int f(int i, int j, int isTrue, String str, Integer[][][] dp){
        if (i == j){
            if (isTrue == 1) return str.charAt(i) == 'T' ? 1 : 0;
            else return str.charAt(i) == 'F' ? 1 : 0;
        }
        if (dp[i][j][isTrue] != null)
            return dp[i][j][isTrue];

        // Run a loop for all partitions at every operator
        int totalWays = 0;
        for (int k = i+1; k <= j-1; k+=2){
            int leftTrue = f(i, k-1, 1, str, dp);   // Ways to make left partition to true
            int leftFalse = f(i, k-1, 0, str, dp);  // Ways to make left partition to false
            int rightTrue = f(k+1, j, 1, str, dp);  // Ways to make right partition to true
            int rightFalse = f(k+1, j, 0, str, dp); // Ways to make right partition to false

            if (str.charAt(k) == '&'){
                if (isTrue == 1) totalWays += leftTrue*rightTrue;
                else totalWays += leftTrue*rightFalse + leftFalse*rightTrue + leftFalse*rightFalse;
            }
            else if (str.charAt(k) == '|'){
                if (isTrue == 1) totalWays += leftTrue*rightTrue + leftTrue*rightFalse + leftFalse*rightTrue;
                else totalWays += leftFalse*rightFalse;
            }
            else if (str.charAt(k) == '^'){
                if (isTrue == 1) totalWays += leftTrue*rightFalse + leftFalse*rightTrue;
                else totalWays += leftTrue*rightTrue + leftFalse*rightFalse;
            }
        }
        return dp[i][j][isTrue] = totalWays;
    }

    /**************************************** Tabulation ********************************************
     * Tabulation of above memoization

     * Time Complexity: O(n*n*2 * n) ~ O(n*n*n)
        * Three changing states: i & j & isTrue
        * One loop inside for each state
     * Space Complexity: O(n*n*2) ~ O(n*n)
        * DP Array
     */
    public int countTrue(String str) {
        int n = str.length();

        // DP Array
        int[][][] dp = new int[n][n][2];

        // Base case for "i==j"
        for (int i = 0; i < n; i++){
            dp[i][i][1] = str.charAt(i) == 'T' ? 1 : 0;
            dp[i][i][0] = str.charAt(i) == 'F' ? 1 : 0;
        }

        // Remaining states for i and j and isTrue
        for (int i = n-1; i >= 0; i--){
            for (int j = i + 1; j <= n-1; j++){
                for (int isTrue = 0; isTrue <= 1; isTrue++){
                    int totalWays = 0;

                    for (int k = i+1; k <= j-1; k += 2){
                        int leftTrue = dp[i][k-1][1];   // Ways to make left partition to true
                        int leftFalse = dp[i][k-1][0];  // Ways to make left partition to false
                        int rightTrue = dp[k+1][j][1];  // Ways to make right partition to true
                        int rightFalse = dp[k+1][j][0]; // Ways to make right partition to false

                        if (str.charAt(k) == '&'){
                            if (isTrue == 1) totalWays += leftTrue*rightTrue;
                            else totalWays += leftTrue*rightFalse + leftFalse*rightTrue + leftFalse*rightFalse;
                        }
                        else if (str.charAt(k) == '|'){
                            if (isTrue == 1) totalWays += leftTrue*rightTrue + leftTrue*rightFalse + leftFalse*rightTrue;
                            else totalWays += leftFalse*rightFalse;
                        }
                        else if (str.charAt(k) == '^'){
                            if (isTrue == 1) totalWays += leftTrue*rightFalse + leftFalse*rightTrue;
                            else totalWays += leftTrue*rightTrue + leftFalse*rightFalse;
                        }
                    }
                    dp[i][j][isTrue] = totalWays;
                }
            }
        }
        return dp[0][n-1][1];
    }
}
