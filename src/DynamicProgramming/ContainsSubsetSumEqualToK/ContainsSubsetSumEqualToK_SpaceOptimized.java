package DynamicProgramming.ContainsSubsetSumEqualToK;

// https://youtu.be/fWX9xDmIzRI
// https://takeuforward.org/data-structure/subset-sum-equal-to-target-dp-14/

public class ContainsSubsetSumEqualToK_SpaceOptimized {

    // **************************** Space Optimized Solution ****************************
    // Time Complexity: O(N*K)  Reason: There are three nested loops
    //Space Complexity: O(K)    Reason: We are using an external array of size ‘K+1’ to store only one row.

    // Total no. of unique states/sub-problems = no. of changing parameters (target * n here)
    // Total no. of unique states/sub-problems = Size of DP array = No. of 'for loops' for iteration
    // Space optimized if we see something as 'xyz - 1' in DP array

    private boolean subsetSumToK(int n, int targetSum, int[] arr){

        // DP array which is space optimized
        boolean[] dp = new boolean[targetSum + 1];

        // Base case for index == 0
        if (arr[0] <= targetSum)
            dp[arr[0]] = true;

        // Base case for target == 0
        dp[0] = true;


        // Other cases
        // At each index 'index' we determine whether we can obtain a 'target' subset sum.
        for (int index = 1; index < n; index++){
            boolean[] tempDP = new boolean[targetSum + 1];

            // Base case for target == 0
            tempDP[0] = true;

            for (int target = 1; target <= targetSum; target++){

                boolean foundByTakingNotCurrElement = dp[target];

                boolean foundByTakingCurrElement = false;
                if (arr[index] <= target)
                    foundByTakingCurrElement = dp[target - arr[index]];

                tempDP[target] = foundByTakingCurrElement || foundByTakingNotCurrElement;
            }

            dp = tempDP;
        }

        // Answer for dp[n-1][targetSum] is stored in this position
        return dp[targetSum];
    }
}