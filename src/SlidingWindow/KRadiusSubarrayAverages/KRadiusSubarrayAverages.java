package SlidingWindow.KRadiusSubarrayAverages;

// https://leetcode.com/problems/k-radius-subarray-averages/description/

public class KRadiusSubarrayAverages {
    /*********************************** Optimal Solution *****************************************
     * Brute Force:
        * easy to think of n^2 time solution

     * Prefix Sum solution
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int[] getAverages(int[] nums, int k) {
        int n = nums.length;
        int[] kAverage = new int[n];
        long prefixSum = 0;
        for (int i = 0; i < n; i++){
            prefixSum += nums[i];

            if (i >= 2*k){
                kAverage[i - k] = (int)(prefixSum / (2*k + 1));
                prefixSum -= nums[i - 2*k];
            }
            if (i < k || n-1 - i < k)
                kAverage[i] = -1;
        }
        return kAverage;
    }
}
