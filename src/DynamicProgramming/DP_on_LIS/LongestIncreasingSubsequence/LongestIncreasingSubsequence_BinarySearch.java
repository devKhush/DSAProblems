package DynamicProgramming.DP_on_LIS.LongestIncreasingSubsequence;
import java.util.ArrayList;

// https://youtu.be/on2hvxBXJH4
// https://takeuforward.org/data-structure/longest-increasing-subsequence-binary-search-dp-43/
// https://leetcode.com/problems/longest-increasing-subsequence/description/

public class LongestIncreasingSubsequence_BinarySearch {
    /************************************* Efficient Binary Search Solution ********************************
     * For Intuition watch video and see blog.

     * Time Complexity: O(n*log(n))
        * n for Loop and log(n) for Binary Search
     * Space Complexity: O(n)
     */
    private int lengthOfLIS(int[] nums){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(nums[0]);
        int lengthLIS = 1;

        for (int i = 1; i < nums.length; i++){
            if (nums[i] > list.get(lengthLIS - 1)){
                list.add(nums[i]);
                lengthLIS++;
            }
            else{
                int index = lower_bound(list, lengthLIS, nums[i]);
                list.set(index, nums[i]);
            }
        }
        return lengthLIS;
    }

    private int lower_bound(ArrayList<Integer> list, int n, int val){
        int low = 0, high = n - 1;

        while (low <= high){
            int mid = low + ((high - low)>>1);
            if (list.get(mid) >= val)
                high = mid - 1;
            else
                low = mid + 1;
        }
        return low;
    }
}
