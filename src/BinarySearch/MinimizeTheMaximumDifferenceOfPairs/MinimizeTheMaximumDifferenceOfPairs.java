package BinarySearch.MinimizeTheMaximumDifferenceOfPairs;
import java.util.Arrays;

// https://leetcode.com/problems/minimize-the-maximum-difference-of-pairs/description/
// https://leetcode.com/problems/minimize-the-maximum-difference-of-pairs/editorial/

public class MinimizeTheMaximumDifferenceOfPairs {
    /************************************** Binary Search ***********************************
     * Time Complexity: O(n*log(n))
     * Space Complexity: O(1)
     */
    public int minimizeMax(int[] nums, int p) {
        int n = nums.length;
        Arrays.sort(nums);

        int low = 0, high = nums[n-1] - nums[0];
        while (low <= high){
            int mid = low + ((high - low)>>1);

            if (pairsFormed(nums, mid) < p)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return low;
    }

    private int pairsFormed(int[] nums, int thresholdDiff){
        int pairs = 0;
        for (int i = 0; i < nums.length - 1; i++){
            if (nums[i + 1] - nums[i] <= thresholdDiff){
                pairs++;
                i++;
            }
        }
        return pairs;
    }
}
