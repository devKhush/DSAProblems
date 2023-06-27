package DynamicProgramming.DP_on_Arrays.CountAllPossibleRoutes;

// Straight forward DP Problem
// https://youtu.be/GWMI6feD2rs
// https://leetcode.com/problems/count-all-possible-routes/description/

public class CountAllPossibleRoutes {
    /************************************ Memoization *******************************************
     * Intuition:
        * Basic Recursion Thinking

     * Time Complexity: O(n * fuel * n)
        * Two states for problems
        * In each recursive call, we run a loop of 'n' size
     * Space Complexity: O(n * fuel)
        * DP_Array + Recursion_Stack_Space
     */
    int MOD = (int)(1e9 + 7);
    Integer[][] dp;
    public int countRoutes(int[] locations, int start, int finish, int fuel) {
        int n = locations.length;
        dp = new Integer[n][fuel + 1];

        return dfs(start, fuel, finish, locations);
    }

    private int dfs(int node, int fuel, int finish, int[] locations){
        if (fuel == 0)
            return node == finish ? 1 : 0;
        if (dp[node][fuel] != null)
            return dp[node][fuel];

        int paths = node == finish ? 1 : 0;
        for (int v = 0; v < locations.length; v++){
            if (v == node)
                continue;
            if (fuel >= Math.abs(locations[node] - locations[v]))
                paths = (paths + dfs(v, fuel - Math.abs(locations[node] - locations[v]), finish, locations)) % MOD;
        }
        return dp[node][fuel] = paths;
    }
}
