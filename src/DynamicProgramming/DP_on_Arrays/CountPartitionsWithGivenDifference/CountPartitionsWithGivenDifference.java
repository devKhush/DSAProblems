package DynamicProgramming.DP_on_Arrays.CountPartitionsWithGivenDifference;
import java.util.Arrays;

// PRE_REQUISITE: "COUNT Subset sum equal to K"
// https://youtu.be/zoilQD1kYSg
// https://www.geeksforgeeks.org/count-of-subsets-with-given-difference/
// https://takeuforward.org/data-structure/count-partitions-with-given-difference-dp-18/
// https://www.codingninjas.com/codestudio/problem-details/partitions-with-given-difference_3751628

public class CountPartitionsWithGivenDifference {
    /********************************* Memoization Solution ****************************************
     * Time Complexity: O(sum) + O(n * sum)
        * number of states is n*K
     * Space Complexity: O(n * K) + O(n)
        * Dp array + recursion stack space
     */
    private static int f(int i, int target, int[] arr, int[][] dp){
        if (i < 0)
            return target == 0 ? 1 : 0;
        if (dp[i][target] != -1)
            return dp[i][target];

        int take = arr[i] <= target ? f(i - 1, target - arr[i], arr, dp) : 0;
        int notTake = f(i - 1, target, arr, dp);
        return dp[i][target] = (take + notTake) % (int)(1e9+7);
    }

    public static int countPartitions(int n, int D, int[] arr) {
        int totalSum = 0;
        for (int val : arr)
            totalSum += val;

        // Two condition for problem
        if (totalSum - D < 0 || (totalSum - D)%2 != 0)
            return 0;

        int[][] dp = new int[n][totalSum +1];
        for (int[] row : dp)
            Arrays.fill(row, -1);

        // We search for target sum "(totalSum - D)/2"
        return f(n - 1, (totalSum - D)/2, arr, dp);
    }


    /********************************* Tabulation Solution ****************************************
     * Time Complexity: O(sum) + O(n * sum)
        * number of states is n*sum
     * Space Complexity: O(n*sum)
        * Dp array
     */
    public static int countPartitions(int n, int[] arr, int D) {
        int totalSum = 0;
        for (int val : arr)
            totalSum += val;

        // Two condition for problem
        if (totalSum - D < 0 || (totalSum - D)%2 != 0)
            return 0;

        int[][] dp = new int[n][totalSum + 1];
        dp[0][0] = 1;
        if (arr[0] <= totalSum)
            dp[0][arr[0]] += 1;

        for (int i = 1; i < n; i++){
            for (int target = 0; target <= totalSum; target++){
                int take = arr[i] <= target ? dp[i-1][target - arr[i]] : 0;
                int notTake = dp[i-1][target];
                dp[i][target] = (take + notTake) % (int)(1e9+7);
            }
        }
        // We search for target sum "(totalSum - D)/2"
        return dp[n-1][(totalSum - D)/2];
    }

    /********************************* Space Optimized Solution ****************************************
     * Time Complexity: O(sum) + O(n * sum)
        * number of states is n*sum
     * Space Complexity: O(sum)
        * 'sum' size Dp array
     */
    public static int countPartitions(int[] arr, int n, int D) {
        int totalSum = 0;
        for (int val : arr)
            totalSum += val;

        // Two condition for problem
        if (totalSum - D < 0 || (totalSum - D)%2 != 0)
            return 0;

        int[] dp = new int[totalSum + 1];
        dp[0] = 1;
        if (arr[0] <= totalSum)
            dp[arr[0]] += 1;

        for (int i = 1; i < n; i++){
            int[] tempDP = new int[totalSum + 1];
            for (int target = 0; target <= totalSum; target++){
                int take = arr[i] <= target ? dp[target - arr[i]] : 0;
                int notTake = dp[target];
                tempDP[target] = (take + notTake) % (int)(1e9+7);
            }
            dp = tempDP;
        }
        // We search for target sum "(totalSum - D)/2"
        return dp[(totalSum - D)/2];
    }
}