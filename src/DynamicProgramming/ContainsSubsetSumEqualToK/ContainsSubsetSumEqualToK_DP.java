package DynamicProgramming.ContainsSubsetSumEqualToK;
import java.util.Arrays;

// https://youtu.be/fWX9xDmIzRI
// https://takeuforward.org/data-structure/subset-sum-equal-to-target-dp-14/

class ContainsSubsetSumEqualToK_DP{

    // ********************************** Memoization Solution **********************************
    // Time Complexity: O(N*K)
    // Reason: There are N*K states therefore at max ‘N*K’ new problems will be solved.

    // Space Complexity: O(N*K) + O(N)
    // Reason: We are using a recursion stack space(O(N)) and a 2D array (O(N*K)).

    // 'Top' to ' Down' Approach
    // Total no. of unique states/sub-problems = no. of changing parameters (target * n here)
    // Total no. of unique states/sub-problems = Size of DP array

    private Boolean isSubsetSum(int n, int[] arr, int targetSum){
        int[][] dp = new int[n][targetSum + 1];

        for (int[] row : dp)
            Arrays.fill(row, -1);

        return containsSubsetSumEqualsK_Memoization(n-1, targetSum, arr, dp);
    }

    private boolean containsSubsetSumEqualsK_Memoization(int index, int target, int[] arr, int[][] dp){
        if (target == 0)
            return true;

        if (index == 0)
            return arr[0] == target;

        // dp[index][targetSum] means whether at index 'index' the subset whose sum is 'targetSum' is
        // possible or not
        if (dp[index][target] != -1)
            return dp[index][target] == 1 ? true : false;

        boolean foundByPickingElement = false;
        if (arr[index] <= target)
            foundByPickingElement = containsSubsetSumEqualsK_Memoization(index-1, target - arr[index], arr, dp);

        if (foundByPickingElement){
            dp[index][target] = 1;
            return true;
        }

        boolean foundByNotPickingElement = containsSubsetSumEqualsK_Memoization(index-1, target, arr, dp);

        dp[index][target] = foundByNotPickingElement ? 1 : 0;

        return foundByNotPickingElement;
    }


    // ***************************** Another version of Memoization *****************************
    private boolean containsSubsetSumEqualsK_Memoization_(int index, int target, int[] arr, int[][] dp){
        if (target == 0)
            return true;

        if (index == 0)
            return arr[0] == target;

        // dp[index][targetSum] means whether at index 'index' the subset whose sum is 'targetSum' is
        // possible or not
        if (dp[index][target] != -1)
            return dp[index][target] == 1 ? true : false;

        boolean foundByPickingElement = false;
        if (arr[index] <= target)
            foundByPickingElement = containsSubsetSumEqualsK_Memoization(index-1, target - arr[index], arr, dp);

        boolean foundByNotPickingElement = containsSubsetSumEqualsK_Memoization(index-1, target, arr, dp);

        dp[index][target] = foundByNotPickingElement || foundByPickingElement ? 1 : 0;

        return foundByNotPickingElement || foundByPickingElement;
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
        for (int index = 0; index < n; index++)
            dp[index][0] = true;

        // Base case when index == 0
        for (int target = 0; target <= targetSum; target++)
            dp[0][target] = (arr[0] == target);
        // Shorthand base case for index == 0, it means on index 0 can we format a target arr[0]
        if (arr[0] <= targetSum)
            dp[0][arr[0]] = true;


        // Other cases
        // At each index 'index' we determine whether we can obtain a 'target' subset sum.
        for (int index = 1; index < n; index++) {
            for (int target = 1; target <= targetSum; target++) {

                boolean subsetMadeByPickingCurrElement = false;
                if (arr[index] <= target)
                    subsetMadeByPickingCurrElement = dp[index - 1][target - arr[index]];

                boolean subsetMadeByNotPickingCurrElement = dp[index-1][target];

                dp[index][target] = subsetMadeByNotPickingCurrElement || subsetMadeByPickingCurrElement;
            }
        }

        return dp[n-1][targetSum];
    }

}