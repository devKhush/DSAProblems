package GreedyAlgorithms.MinimumCostToMakeArrayEqual;
import java.util.Arrays;

// https://leetcode.com/problems/minimum-cost-to-make-array-equal/description/
// https://leetcode.com/problems/minimum-cost-to-make-array-equal/editorial/
// MUST watch:
// https://youtu.be/8ERS_4tSx2U

public class MinimumCostToMakeArrayEqual {
    /*********************************** Efficient Sorting + Greedy Solution ***************************
     * Intuition: See Video
     * Time Complexity: O(n*log(n))
     * Space Complexity: O(n)
     */
    public long minCost_greedy(int[] nums, int[] cost) {
        int n = nums.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++){
            arr[i][0] = nums[i];
            arr[i][1] = cost[i];
        }

        // Sort the arrays based on nums array, to visualize/plot array elements on x-axis
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);

        // calculate sum of cost array
        long prefixSum = 0;
        for (int i = 0; i < n; i++){
            prefixSum += arr[i][1];
        }
        // Find the middle of array in terms of sum
        long sum = 0;
        long middle = 0;
        for (int i = 0; i < n && sum <= prefixSum/2; i++){
            middle = arr[i][0];
            sum += arr[i][1];
        }
        // Minimum cost will be obtained from the middle of the array
        long minCost = 0;
        for (int i = 0; i < n; i++){
            minCost += Math.abs(middle - arr[i][0]) * arr[i][1];
        }
        return minCost;
    }


    /*********************************** Efficient Binary Search Solution ***************************
     * Intuition: See Video
     * Time Complexity: O(n*log(k))
     * Space Complexity: O(1)
     */
    public long minCost(int[] nums, int[] cost) {
        int low = 0, high = (int)1e9;
        long ansEqualArray = 0;

        while (low <= high){
            int mid = low + ((high - low)>>1);
            long y1 = f(nums, cost, mid);
            long y2 = f(nums, cost, mid + 1);
            ansEqualArray = Math.min(y1, y2);
            if (y1 > y2)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return ansEqualArray;
    }
    private long f(int[] nums, int[] cost, long median){
        long ans = 0;
        for (int i = 0; i < nums.length; i++){
            ans += Math.abs(nums[i] - median) * cost[i];
        }
        return ans;
    }
}
