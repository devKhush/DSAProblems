package DynamicProgramming.DP_on_LIS.MinimumNumberOfRemovalsToMakeMountainArray;
import java.util.ArrayList;

// Pre-requisite: "Longest BiTonic Sequence"
// Pre-requisite: "Longest Increasing Subsequence"
// https://leetcode.com/problems/minimum-number-of-removals-to-make-mountain-array/description/

public class MinimumNumberOfRemovalsToMakeMountainArray {
    /************************************** DP Tabulation Solution ***************************************
     * Intuition:
        * To minimize the no. of removals, we need to maximize the length of mountain.
        * This is the same problem as Longest BiTonic Subsequence.

     * Approach:
        * We need to use LIS and LDS to compute the longest bi-tonic subsequence
        * We need LIS (The Longest Increasing subsequence) and LDS (The Longest Decreasing subsequence)
        * dp_LIS[i] -> denotes the length of the "Longest Increasing subsequence" by including nums[i] in it
        * dp_LDS[i] -> denotes the length of the "Longest Decreasing subsequence" by including nums[i] in it
        * As the BiTonic subsequence can also be LIS or LDS too (see DP-46 video, Longest BiTonic Subsequence),
            we need to make sure length of LIS and LDS is greater than 1, to ensure proper LIS and LDS to make
            mountain.

     * Time Complexity: O(n*n) + O(n*n) + O(n)  ~  O(n*n)
        * n*n fir LIS and LDS
        * n for mountain length calc.
     * Space Complexity: O(2*n)
        * Two DP Arrays for longest increasing and decreasing subsequence
     */
    public int minimumMountainRemovals_DP(int[] nums) {
        int n = nums.length;

        // dp[] to store longest increasing subsequence from left
        int[] dp_LIS = new int[n];
        // dp[] to store longest decreasing subsequence, LDS from left is same as LIS from right
        int[] dp_LDS = new int[n];

        // Calculate the longest increasing subsequence (from left)
        for (int i = 0; i < n; i++){
            dp_LIS[i] = 1;
            for (int prev_small = 0; prev_small < i; prev_small++){
                if (nums[prev_small] < nums[i]) {
                    dp_LIS[i] = Math.max(dp_LIS[i], 1 + dp_LIS[prev_small]);
                }
            }
        }
        // Calculate the longest decreasing subsequence (i.e, LIS from left)
        for (int i = n-1; i >= 0; i--){
            dp_LDS[i] = 1;
            for (int next_small = n-1; next_small > i; next_small--){
                if (nums[i] > nums[next_small]){
                    dp_LDS[i] = Math.max(dp_LDS[i], 1 + dp_LDS[next_small]);
                }
            }
        }
        // Calculate max. length of the Longest Mountain subsequence
        int longestMountain = 0;
        for (int i = 0; i < n; i++){
            // If the length of LIS or LDS is > 1, then only there is either of Increasing or Decreasing pattern
            if (dp_LIS[i] > 1 && dp_LDS[i] > 1)
                longestMountain = Math.max(longestMountain, dp_LIS[i] + dp_LDS[i] - 1);
        }
        return n - longestMountain;
    }


    /************************************** Binary Search Solution ***************************************
     * Intuition:
        * Calculate the LIS and LDS using Binary Search
        * For Intuition watch DP-43 (LIS using BInary Search) video and see blog.
        * To minimize the no. of removals, we need to maximize the length of mountain.
        * This is the same problem as Longest BiTonic Subsequence.

     * Approach:
        * We need to use LIS and LDS to compute the longest bi-tonic subsequence
        * We need LIS (The Longest Increasing subsequence) and LDS (The Longest Decreasing subsequence)
        * As the BiTonic subsequence can also be LIS or LDS too (see DP-46 video, Longest BiTonic Subsequence),
        we need to make sure length of LIS and LDS is greater than 1, to ensure proper LIS and LDS to make
        mountain.

     * Time Complexity: O(2*n*log(n)) + O(n)  ~  O(n*log(n))
     * Space Complexity: O(n)
         * Two temp arrays for LIS and LDS using binary search
     */
    public int minimumMountainRemovals_BS(int[] nums){
        int n = nums.length;
        ArrayList<Integer> temp = new ArrayList<>();

        // Compute the Longest Increasing SubSequence
        int[] LIS = new int[n];             // LIS[i] denotes LIS till index 'i'
        int lengthLIS = 0;
        for (int i = 0; i < n; i++){
            if (i == 0 || temp.get(lengthLIS - 1) < nums[i]){
                temp.add(nums[i]);
                lengthLIS++;
            }
            else{
                int index = lower_bound(temp, lengthLIS, nums[i]);
                temp.set(index, nums[i]);
            }
            LIS[i] = lengthLIS;
        }
        temp.clear();

        // Compute the Longest Decreasing SubSequence
        int[] LDS = new int[n];             // LDS[i] denotes LDS till index 'i'
        int lengthLDS = 0;
        for (int i = n - 1; i >= 0; i--){
            if (i == n - 1 || temp.get(lengthLDS - 1) < nums[i]){
                temp.add(nums[i]);
                lengthLDS++;
            }
            else{
                int index = lower_bound(temp, lengthLDS, nums[i]);
                temp.set(index, nums[i]);
            }
            LDS[i] = lengthLDS;
        }

        // FInd the length of the longest mountain
        int mountainLength = 0;
        for (int i = 0; i < n; i++){
            if (LIS[i] > 1 && LDS[i] > 1)
                mountainLength = Math.max(mountainLength, LIS[i] + LDS[i] - 1);
        }
        return n - mountainLength;
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
