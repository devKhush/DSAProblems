package DynamicProgramming.DP_on_Partition.EggBreaking;

// See editorial
// One of the Hardest problem on "Partition on DP"
// https://practice.geeksforgeeks.org/problems/egg-dropping-puzzle-1587115620/1

public class EggBreaking {
    /************************************* Memoization ***********************************************
     * Try all the ways
        * See Editorial
     * Time Complexity : O(n*k*k)
        * n is number of eggs and k is number of floors.
     * Space Complexity : O(n*k)
        * since we used DP table of size (n+1)*(k+1).
     */
    Integer[][] dp;
    public int eggDrop_memo(int n, int k){
        dp = new Integer[n + 1][k + 1];
        return f(n, k);
    }
    private int f(int eggs, int floors){
        if (eggs == 1)
            return floors;
        if (floors == 0 || floors == 1)
            return floors;
        if (dp[eggs][floors] != null)
            return dp[eggs][floors];

        int minTrials = (int)1e9;
        for (int k = 1; k <= floors; k++){
            // eggs break from this floor , so try below floors
            int eggBreak = f(eggs - 1, k - 1);

            // eggs break from this floor , so try remaining above floors
            int eggNotBreak = f(eggs, floors - k);

            // stores minimum moves to find threshold floor in worst case
            int trails = 1 + Math.max(eggBreak, eggNotBreak);

            // we have to minimize the maximum answer
            minTrials = Math.min(minTrials, trails);
        }
        return dp[eggs][floors] = minTrials;
    }

    /************************************* Taulation ***********************************************
     * Time Complexity : O(n*k*k)
         * n is number of eggs and k is number of floors.
     * Space Complexity : O(n*k)
         * since we used DP table of size (n+1)*(k+1).
     */
    int eggDrop(int eggs, int floors){
        int[][] dp = new int[eggs + 1][floors + 1];

        // Base case for "eggs == 1"
        for (int floor = 0; floor <= floors; floor++) {
            dp[1][floor] = floor;
        }
        // Base case for "floors == 0 or 1"
        for (int egg = 0; egg <= eggs; egg++){
            dp[egg][0] = 0;
            dp[egg][1] = 1;
        }

        // Remaining states for egg and floor
        for (int egg = 2; egg <= eggs; egg++){
            for (int floor = 2; floor <= floors; floor++){
                int minTrials = (int)1e9;
                for (int k = 1; k <= floor; k++){
                    int eggBreak = dp[egg - 1][k - 1];
                    int eggNotBreak = dp[egg][floor - k];
                    minTrials = Math.min(minTrials, 1 + Math.max(eggBreak, eggNotBreak));
                }
                dp[egg][floor] = minTrials;
            }
        }
        return dp[eggs][floors];
    }
}
