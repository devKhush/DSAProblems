package DynamicProgramming.DP_on_LIS.LongestBitonicSequence;
import java.util.ArrayList;

// Pre-requisite: Longest Increasing SubSequence
// https://practice.geeksforgeeks.org/problems/longest-bitonic-subsequence0824/1
// https://takeuforward.org/data-structure/longest-bitonic-subsequence-dp-46/
// https://youtu.be/y4vN0WNdrlg

public class LongestBitonicSequence {
    /************************************** DP Tabulation Solution ***************************************
     * Intuition:
        * We need to use LIS and LDS to compute the longest bi-tonic subsequence
        * So, compute both LIS and LDS

     * We need LIS (The Longest Increasing subsequence) and LDS (The Longest Decreasing subsequence)
     * dp_LIS[i] -> denotes the length of the "Longest Increasing subsequence" by including nums[i] in it
     * dp_LDS[i] -> denotes the length of the "Longest Decreasing subsequence" by including nums[i] in it

     * Time Complexity: O(n*n) + O(n*n) + O(n)  ~  O(n*n)
     * Space Complexity: O(2*n)
        * Two DP Arrays for longest increasing and decreasing subsequence
     */
    public int longestBitonicSequence_dp(int[] nums) {
        int n = nums.length;

        // dp[] to store longest increasing subsequence from left
        int[] dp_LIS = new int[n];
        // dp[] to store longest decreasing subsequence, LDS from left is same as LIS from right
        int[] dp_LDS = new int[n];

        // Calculate the longest increasing subsequence
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
        // Calculate max. length of the Longest Bi-tonic subsequence
        int bitonicSequence = 0;
        for (int i = 0; i < n; i++)
            bitonicSequence = Math.max(bitonicSequence, dp_LIS[i] + dp_LDS[i] - 1);
        return bitonicSequence;
    }


    /************************************** Binary Search Solution ***************************************
     * Intuition:
        * Calculate the LIS and LDS using Binary Search
        * For Intuition watch DP-43 (LIS using Binary Search) video and see blog.

     * Time Complexity: O(2*n*log(n)) + O(n)  ~  O(n*log(n))
     * Space Complexity: O(n)
         * Two temp arrays for LIS and LDS using binary search
     */
    public int longestBitonicSequence_bs(int[] nums){
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

        // FInd the length of the Longest BiTonic Subsequence
        int bitonicSequence = 0;
        for (int i = 0; i < n; i++){
            bitonicSequence = Math.max(bitonicSequence, LIS[i] + LDS[i] - 1);
        }
        return bitonicSequence;
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
