package DynamicProgramming.DP_on_Arrays.FrogJump;
import java.util.HashMap;
import java.util.Arrays;

// https://leetcode.com/problems/frog-jump/description/

public class FrogJump {
    /************************************** Memoization DP ********************************************
     * Intuition:
        * Recursion
        * Main thing is to Keep track of the indices of stone position we are visiting.
     * Time Complexity: O(n*n)
     * Space Complexity: O(n*n)
     */
    public boolean canCross_memo(int[] stones) {
        int n = stones.length;
        // To Keep track of the indices of stone position we are visiting.
        HashMap<Integer, Integer> locationMap = new HashMap<>();
        for (int i = 0; i < n; i++){
            locationMap.put(stones[i], i);
        }
        Boolean[][] dp = new Boolean[n][n];
        return f(0, 0, stones, locationMap, dp);
    }

    private boolean f(int i, int prevJump, int[] stones,  HashMap<Integer, Integer> locationMap, Boolean[][] dp){
        if (i == stones.length - 1)
            return true;
        if (dp[i][prevJump] != null)
            return dp[i][prevJump];

        boolean canReach = false;
        for (int nextJump = prevJump - 1; nextJump <= prevJump + 1; nextJump++){
            if (nextJump > 0 && locationMap.containsKey(stones[i] + nextJump))
                canReach |= f(locationMap.get(stones[i] + nextJump), nextJump, stones, locationMap, dp);
        }
        return dp[i][prevJump] = canReach;
    }


    /************************************** Tabulation DP ********************************************
     * Intuition:
        * Recursion
        * Main thing is to Keep track of the indices of stone position we are visiting.
     * Time Complexity: O(n*n)
     * Space Complexity: O(n*n)
     */
    public boolean canCross(int[] stones) {
        int n = stones.length;
        // Keep track of the indices of stone position we are visiting.
        HashMap<Integer, Integer> locationMap = new HashMap<>();
        for (int i = 0; i < n; i++){
            locationMap.put(stones[i], i);
        }

        // Dp with base case
        boolean[][] dp = new boolean[n][n + 1];
        Arrays.fill(dp[n-1], true);

        // For loops in opposite order
        for (int i = n - 2; i >= 0; i--){
            for (int prevJump = n - 1; prevJump >= 0; prevJump--){
                boolean canReach = false;
                for (int nextJump = prevJump - 1; nextJump <= prevJump + 1; nextJump++){
                    if (nextJump > 0 && locationMap.containsKey(stones[i] + nextJump))
                        canReach |= dp[locationMap.get(stones[i] + nextJump)][nextJump];
                }
                dp[i][prevJump] = canReach;
            }
        }
        return dp[0][0];
    }
}
