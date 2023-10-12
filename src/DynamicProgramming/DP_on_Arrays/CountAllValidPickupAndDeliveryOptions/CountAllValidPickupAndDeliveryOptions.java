package DynamicProgramming.DP_on_Arrays.CountAllValidPickupAndDeliveryOptions;

// https://leetcode.com/problems/count-all-valid-pickup-and-delivery-options/description/

public class CountAllValidPickupAndDeliveryOptions {
    /************************************* Memoization *******************************************
     * Intuition:
        * Recursion
     * Time Complexity: O(n*n)
        * Two DP states
     * Space Complexity: O(n*n)
     */
    public int countOrders__memo(int n) {
        Long[][] dp = new Long[n+1][n+1];
        return (int)f(n, n, dp);
    }
    private long f(int P, int D, Long[][] dp){
        if (P == 0 && D == 0)
            return 1;
        if (dp[P][D] != null)
            return dp[P][D];
        long purchase = P > 0 ? P * f(P - 1, D, dp) : 0;
        long delivery = P < D ? (D - P) * f(P, D - 1, dp) : 0;
        return dp[P][D] = (purchase + delivery) % 1000000007;
    }
}
