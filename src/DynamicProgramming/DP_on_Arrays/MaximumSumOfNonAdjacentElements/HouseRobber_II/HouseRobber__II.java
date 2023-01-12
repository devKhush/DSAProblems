package DynamicProgramming.DP_on_Arrays.MaximumSumOfNonAdjacentElements.HouseRobber_II;

class HouseRobber__II {
    // Tabulation Code
    // TC -> O(2*n) = O(n)
    // SC -> O(n)
    public static long houseRobber(int[] valueInHouse) {
        if (valueInHouse.length == 1)
            return valueInHouse[0];
        return Math.max(solve(0, valueInHouse), solve(1, valueInHouse));
    }

    private static long solve(int ind, int[] arr) {
        long[] dp = new long[arr.length];
        dp[ind] = arr[ind];

        for (int i = ind + 1; i < arr.length - 1 + ind; i++) {
            long take = dp[i - 1];
            long notTake = i > 1 ? arr[i] + dp[i - 2] : arr[i];
            dp[i] = Math.max(take, notTake);
        }
        return dp[arr.length - 1 + ind - 1];
    }


    // Space Optimization
    // TC -> O(2n) = O(n)
    // SC -> O(n)
    private static long solveSpaceOptimized(int ind, int[] arr) {
        long prev = arr[ind];
        long prev2 = 0;

        for (int i = ind + 1; i < arr.length - 1 + ind; i++) {
            long take = prev;
            long notTake = i > 1 ? arr[i] + prev2 : arr[i];
            long curr = Math.max(take, notTake);

            prev2 = prev;
            prev = curr;
        }
        return prev;
    }
}