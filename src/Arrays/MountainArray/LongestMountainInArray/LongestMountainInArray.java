package Arrays.MountainArray.LongestMountainInArray;

// https://leetcode.com/problems/longest-mountain-in-array/description/
// Follow up: Longest BiTonic subsequence (DP on LIS)

public class LongestMountainInArray {
    /*************************************** Two Pointer Solution **********************************
     * Intuition:
        * Figure out the pattern of "First increasing, then decreasing afterwards"
        * Here problem is simple bcoz, we need to find the max. length of subarray (and not subsequence)

     * Time Complexity: O(2*n)
        * In worst case two passes of array will be done, try to visualise.
        * Though bcoz of nested loops, it looks like it is O(n*n). But it is not.
        * The uniformity (mountain structure) will not be present anywhere in the array.
     * Space Complexity: O(1)
     */
    public int longestMountain(int[] nums) {
        int n = nums.length;
        int mountain = 0;

        for (int i = 1; i < n-1; i++){
            if (nums[i-1] < nums[i]  && nums[i] > nums[i + 1]){
                int start = i, end = i;
                while (start > 0  &&  nums[start - 1] < nums[start]) start--;
                while (end < n-1  &&  nums[end] > nums[end + 1]) end++;
                mountain = Math.max(mountain, end - start +1);
            }
        }
        return mountain;
    }
}
