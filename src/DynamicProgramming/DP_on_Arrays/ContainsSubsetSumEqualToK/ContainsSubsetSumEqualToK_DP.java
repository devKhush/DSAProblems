package DynamicProgramming.DP_on_Arrays.ContainsSubsetSumEqualToK;

// https://youtu.be/fWX9xDmIzRI
// https://takeuforward.org/data-structure/subset-sum-equal-to-target-dp-14/
// https://www.geeksforgeeks.org/subset-sum-problem-dp-25/

class ContainsSubsetSumEqualToK_DP{
    // ********************************** Memoization Solution **********************************
    // Time Complexity: O(N*K)
    // Reason: There are N*K states therefore at max ‘N*K’ new problems will be solved.
    // Space Complexity: O(N*K) + O(N)
    // Reason: We are using a recursion stack space(O(N)) and a 2D array (O(N*K)).
    // 'Top' to ' Down' Approach
    // Total no. of unique states/sub-problems = no. of changing parameters (target * n here)
    // Total no. of unique states/sub-problems = Size of DP array
    public static boolean subsetSumToK(int n, int targetSum, int[] arr) {
        Boolean[][] dp = new Boolean[n][targetSum + 1];
        return f(n - 1, targetSum, arr, dp);
    }

    private static boolean f(int i, int target, int[] arr, Boolean[][] dp) {
        if (target == 0)
            return true;
        if (i == 0)
            return arr[0] == target;

        // dp[index][targetSum] means whether at index 'index' the subset whose sum is 'targetSum' is
        // possible or not
        if (dp[i][target] != null)
            return dp[i][target];

        boolean foundByTake = arr[i] <= target ? f(i - 1, target - arr[i], arr, dp) : false;
        boolean foundByNotTake = f(i - 1, target, arr, dp);

        return dp[i][target] = foundByTake || foundByNotTake;
    }



    // ********************************** Tabulation Solution **********************************
    // 'Bottom' to 'Up' Approach
    // Time Complexity: O(N*K)      Reason: There are two nested loops
    //Space Complexity: O(N*K)      Reason: We are using an external array of size ‘N*K’. Stack Space is eliminated.
    // Total no. of unique states/sub-problems = no. of changing parameters (target * n here)
    // Total no. of unique states/sub-problems = Size of DP array = No. of 'for loops' for iteration

    private boolean containsSubsetSumEqualToK_Tabulation(int[] arr, int targetSum){
        int n = arr.length;

        // dp[index][targetSum] means whether at index 'index' the subset whose sum is 'targetSum' is
        // possible or not
        boolean[][] dp = new boolean[n][targetSum + 1];

        // Base case when target == 0
        for (int i = 0; i < n; i++)
            dp[i][0] = true;

        // Base case when index == 0
        for (int target = 0; target <= targetSum; target++)
            dp[0][target] = (arr[0] == target);
        // Shorthand base case for index == 0, it means on index 0 can we format a target arr[0]
        if (arr[0] <= targetSum)
            dp[0][arr[0]] = true;


        // Other cases
        // At each index 'index' we determine whether we can obtain a 'target' subset sum.
        for (int i = 1; i < n; i++) {
            for (int target = 0; target <= targetSum; target++) {
                boolean foundByTake = arr[i] <= target ? dp[i - 1][target - arr[i]] : false;
                boolean foundByNotTake = dp[i - 1][target];

                dp[i][target] = foundByTake || foundByNotTake;
            }
        }
        return dp[n - 1][targetSum];
    }

}