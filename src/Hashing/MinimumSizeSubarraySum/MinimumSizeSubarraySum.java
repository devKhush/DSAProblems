package Hashing.MinimumSizeSubarraySum;
import java.util.TreeMap;

// PRE-REQUISITE: "Sub Array with sum equals to K"
// https://leetcode.com/problems/minimum-size-subarray-sum/description/

public class MinimumSizeSubarraySum {
    /********************************* Brute Force ***************************************
     * Try out all SubArrays
     * Time Complexity: O(n*n)
     * Space Complexity: O(1)
     */

    /********************************* TreeMap Solution ************************************************
     * Intuition:
        * Similar intuition as in problem "Sub Array with sum equals to K"
        * Instead of hashmap use TreeMap as we need to find the floor key-value in the map

     * Time Complexity: O(n * log(n))
        * unlike hashmap, treemap uses log(n) for get and put operations
     * Space Complexity: O(n)
     */
    public int minSubArrayLen___TreeMap(int target, int[] nums) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int prefixSum = 0;
        int subArrayLength = (int)1e9;

        for (int i = 0; i < nums.length; i++){
            prefixSum += nums[i];
            if (prefixSum >= target){
                subArrayLength = Math.min(subArrayLength, i + 1);
            }
            if (map.floorKey(prefixSum - target) != null){
                subArrayLength = Math.min(subArrayLength, i - map.get(map.floorKey(prefixSum - target)));
            }
            map.put(prefixSum, i);
        }
        return subArrayLength == (int)1e9 ? 0 : subArrayLength;
    }


    /*************************************** Sliding Window ******************************************
     * Time Complexity: O(2*n) ~ O(n)
     * Space Complexity: O(1)
     */
    public int minSubArrayLen(int target, int[] nums) {
        int sum = 0;
        int left = 0;
        int subArrayLength = (int)1e9;

        for (int right = 0; right < nums.length; right++){
            sum += nums[right];
            while (sum - nums[left] >= target){
                sum -= nums[left];
                left++;
            }
            if (sum >= target)
                subArrayLength = Math.min(subArrayLength, right - left + 1);
        }
        return subArrayLength == (int)1e9 ? 0 : subArrayLength;
    }
}
