package DynamicProgramming.DP_on_Partition;

// https://practice.geeksforgeeks.org/problems/matrix-chain-multiplication0303/1
// https://takeuforward.org/dynamic-programming/matrix-chain-multiplication-dp-48/
// https://takeuforward.org/data-structure/matrix-chain-multiplication-tabulation-method-dp-49/
// Recursion + Memoization: https://youtu.be/vRVfmbCFW7Y
// Tabulation: https://youtu.be/pDCXsbAw5Cg

public class MatrixChainMultiplication {
    /*************************************** Recursion: Partition ****************************************
     * Intuition: Basic Recursion Thinking
        * Watch Video

     * Time Complexity:  ~ Exponential
     * Space Complexity: O(n)
        * We reduce the partition in every call, by f(i,k) or f(k+1,j). Hence, at max depth will be O(n). Think.
     */
    private int matrixMultiplication_recur(int N, int[] arr) {
        int n = arr.length;

        // Start with the entire block/array and mark it with i,j
        return f(1, n - 1, arr);
    }
    private int f(int i, int j, int[] arr){
        if (i == j)
            return 0;

        int min = (int)1e9;
        // Try out all the partitions
        for (int k = i; k <= j - 1; k++){
            int multiplications = arr[i-1]*arr[k]*arr[j] + f(i , k, arr) + f(k + 1, j, arr);
            min = Math.min(min, multiplications);
        }
        // Run the best possible answer of the two partitions
        return min;
    }


    /*************************************** Recursion: Partition ****************************************
     * Memoization of above recursion
     * Memoization is done for overlapping sub-problems

     * Time Complexity:  O(n*n*n)
        * There are two changing states, i & j.
        * Another loop for k will run for each states on average n time
     * Space Complexity: O(n*n) + O(n)
        * DP Array + Recursion Stack space
     */
    private int matrixMultiplication_memo(int N, int[] arr) {
        int n = arr.length;

        Integer[][] dp = new Integer[n][n];
        // Start with the entire block/array and mark it with i,j
        return f(1, n - 1, arr, dp);
    }
    private int f(int i, int j, int[] arr, Integer[][] dp){
        if (i == j)
            return 0;
        if (dp[i][j] != null)
            return dp[i][j];

        int min = (int)1e9;
        // Try out all the partitions
        for (int k = i; k <= j - 1; k++){
            int multiplications = arr[i-1]*arr[k]*arr[j] + f(i , k, arr, dp) + f(k + 1, j, arr, dp);
            min = Math.min(min, multiplications);
        }
        // Run the best possible answer of the two partitions
        return dp[i][j] = min;
    }


    /************************************** Tabulation Solution *************************************
     * Tabulation of above memoization
     * Tricky part here is to determine the direction of for loop.
     * We call for function f(1,n-1) in memoization
     * 'i' starts from 1 in function. So, 'i' goes from n-1 to 1 in tabulation.
     * 'j' starts from n-1 in function. But since, in each partition 'i' is always greater than or equal
        to 'j'. State 'j' varies from i+1 to n-1.
     * Else Copy the code from memoization.

     * Time Complexity: O(n*n*n)
        * Two changing states: i & j
        * One loop inside for each state
     * Space Complexity: O(n*n)
        * DP Array
     */
    private int matrixMultiplication_tabu(int[] arr){
        int n = arr.length;

        int[][] dp = new int[n][n];
        // No need for base case for i==j (by default zero)

        // Write the changing states in loop in opposite order
        for (int i = n - 1; i >= 1; i--){
            // In every partition, 'i' is always greater than 'j' (equality hold for base case)
            for (int j = i + 1; j < n; j++){
                int min = (int)1e9;

                for (int k = i; k <= j - 1; k++){
                    int multiplications = arr[i-1]*arr[k]*arr[j] + dp[i][k] + dp[k+1][j];
                    min = Math.min(min, multiplications);
                }
                dp[i][j] = min;
            }
        }
        return dp[1][n-1];
    }
}
