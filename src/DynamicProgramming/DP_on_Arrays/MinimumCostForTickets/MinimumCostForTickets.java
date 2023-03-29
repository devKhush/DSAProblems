package DynamicProgramming.DP_on_Arrays.MinimumCostForTickets;

// https://leetcode.com/problems/minimum-cost-for-tickets/description/

public class MinimumCostForTickets {
    /************************************ Recursion **********************************************
     * Intuition: At every day (index), buy all the passes and take the minimum of all of them.
            * When we buy a pass, move to that index at we need to buy a pass again
     * Time Complexity: O(3^n)
        * Three possibility at each index
     * Space Complexity: O(n)
        * Recursion_stack_space
     */
    public int minCostTickets(int[] days, int[] costs) {
        int n = days.length;
        int[] duration = {1, 7, 30};
        return f(n - 1, days, costs, duration);
    }

    private int f(int ind, int[] days, int[] costs, int[] duration){
        if (ind < 0)
            return 0;
        if (ind == 0)
            return Math.min(costs[0], Math.min(costs[1], costs[2]));

        int totalCost = Integer.MAX_VALUE;
        for (int pass = 0; pass < 3; pass++){
            int j = ind - 1;
            while (j >= 0  &&  days[j] >= days[ind] - duration[pass] + 1)
                j--;
            int cost = costs[pass] + f(j, days, costs, duration);
            totalCost = Math.min(totalCost, cost);
        }
        return totalCost;
    }


    /*********************************** Memoization Solution *************************************
     * Intuition: At every day (index), buy all the passes and take the minimum of all of them.
            * When we buy a pass, move to that index at we need to buy a pass again

     * Time Complexity: O(3 * n)
        * We are trying all three tickets at each index
     * Space Complexity: O(n)
        * DP_Array + Recursion_stack_space
     */
    public int minCostTickets_Memoization(int[] days, int[] costs) {
        int n = days.length;
        int[] duration = {1, 7, 30};
        Integer[] dp = new Integer[n];
        return f(n - 1, days, costs, duration, dp);
    }

    private int f(int ind, int[] days, int[] costs, int[] duration, Integer[] dp){
        if (ind < 0)
            return 0;
        if (ind == 0)
            return Math.min(costs[0], Math.min(costs[1], costs[2]));
        if (dp[ind] != null)
            return dp[ind];

        int totalCost = Integer.MAX_VALUE;
        for (int pass = 0; pass < 3; pass++){
            int j = ind - 1;
            while (j >= 0  &&  (days[j] >= days[ind] - duration[pass] + 1))
                j--;
            int cost = costs[pass] + f(j, days, costs, duration, dp);
            totalCost = Math.min(totalCost, cost);
        }
        return dp[ind] = totalCost;
    }


    /************************************ Tabulation *********************************************
     * Conversion of Memoization to tabulation
     * Time Complexity: O(3 * n)
        * We are trying all three tickets at each index
     * Space Complexity: O(n)
        * DP_Array
     */
    public int minCostTickets_Tabulation(int[] days, int[] costs) {
        int n = days.length;
        int[] duration = {1, 7, 30};

        int[] dp = new int[n];
        dp[0] = Math.min(costs[0], Math.min(costs[1], costs[2]));

        for (int i = 1; i < n; i++){
            int totalCost = Integer.MAX_VALUE;
            for (int pass = 0; pass < 3; pass++){
                int j = i - 1;
                while (j >= 0 && (days[j] >= days[i] - duration[pass] + 1))
                    j--;
                int cost = j < 0 ? costs[pass] : costs[pass] + dp[j];
                totalCost = Math.min(totalCost, cost);
            }
            dp[i] = totalCost;
        }
        return dp[n - 1];
    }

    // Space Optimization is not possible in this solution
}
