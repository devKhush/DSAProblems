package Recursions_And_BackTracking.MaximumNumberOfAchievableTransferRequests;

// https://leetcode.com/problems/maximum-number-of-achievable-transfer-requests/description/
// https://leetcode.com/problems/maximum-number-of-achievable-transfer-requests/editorial/

public class MaximumNumberOfAchievableTransferRequests {
    /************************************* BackTracking **************************************************\
     * Intuition:
        * 0/1 Knapsack
     * Time Complexity: O(n * 2^m)
        * n -> no. of buildings, m -> no. of requests
        * Total of 2^m combinations of requests
     * Space Complexity: O(m + n)
        * InDegree array of n size
        * Stack space of m size
     */
    public int maximumRequests(int n, int[][] requests) {
        int[] inDegree = new int[n];

        return maxRequests(requests.length - 1, 0, requests, inDegree);
    }

    private int maxRequests(int i, int requestConsidered, int[][] requests, int[] inDegree){
        if (i < 0){
            for (int net : inDegree){
                if (net != 0)
                    return 0;
            }
            return requestConsidered;
        }
        inDegree[requests[i][0]]--;
        inDegree[requests[i][1]]++;
        int takeRequest = maxRequests(i - 1, requestConsidered + 1, requests, inDegree);
        inDegree[requests[i][1]]--;
        inDegree[requests[i][0]]++;

        int notTakeRequest = maxRequests(i - 1, requestConsidered, requests, inDegree);

        return Math.max(takeRequest, notTakeRequest);
    }


    /************************************** Power Set ******************************************
     * Same PowerSet solution
     * Time Complexity: O(n * 2^m)
        * n -> no. of buildings, m -> no. of requests
        * Total of 2^m combinations of requests
     * Space Complexity: O(n)
        * InDegree array of n size
     */
    public int maximumRequests_ps(int n, int[][] requests) {
        int maxRequests = 0;

        for (int combination = 0; combination < (1 << requests.length); combination++){
            int[] inDegree = new int[n];

            // No. of requests taken == no. of set bits
            int requestTaken = 0;
            for (int i = 0; i < requests.length; i++){
                if (((combination >> i) & 1) == 1){
                    requestTaken++;
                    inDegree[requests[i][0]]--;
                    inDegree[requests[i][1]]++;
                }
            }
            boolean netTransferIsZero = true;
            for (int net : inDegree){
                if (net != 0) {
                    netTransferIsZero = false;
                    break;
                }
            }
            if (netTransferIsZero)
                maxRequests = Math.max(maxRequests, requestTaken);
        }
        return maxRequests;
    }
}
