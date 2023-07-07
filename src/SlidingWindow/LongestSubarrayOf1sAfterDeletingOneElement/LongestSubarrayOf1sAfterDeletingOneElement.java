package SlidingWindow.LongestSubarrayOf1sAfterDeletingOneElement;

// https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/description/

public class LongestSubarrayOf1sAfterDeletingOneElement {
    /*************************************** Sliding window **************************************
     * Time Complexity: O(2*n) ~ O(n)
     * Space Complexity: O(1)
     */
    public int longestSubarray(int[] nums) {
        int maxSize = 0;
        int left = 0;
        int zeroCount = 0;

        for (int right = 0; right < nums.length; right++){
            if (nums[right] == 0)
                zeroCount++;
            while (zeroCount > 1){
                if (nums[left] == 0)
                    zeroCount--;
                left++;
            }
            maxSize = Math.max(maxSize, right - left + 1 - zeroCount);
        }
        return maxSize == nums.length ? maxSize - 1 : maxSize;
    }
}
