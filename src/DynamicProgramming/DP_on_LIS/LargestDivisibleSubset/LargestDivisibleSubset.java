package DynamicProgramming.DP_on_LIS.LargestDivisibleSubset;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

// Pre-requisite: Longest Increasing SubSequence
// https://takeuforward.org/data-structure/longest-divisible-subset-dp-44/
// https://leetcode.com/problems/largest-divisible-subset/description/
// https://youtu.be/gDuZwBW9VvM

public class LargestDivisibleSubset {
    /*********************************** DP Tabulation *********************************************
     * Intuition:
        * Since we care about only subsets and not subsequence, sorting will make life easier
        * After sorting we can easily make sure, if b % a == 0 and c % b == 0, then c % a == 0
        * This is only possible when the array is sorted, not for unsorted array bcoz of uniformity.
        * Then, we find the longest divisible sub-sequence in the same way as longest increasing sequence.
        * Longest divisible sub-sequence for sorted array is the longest divisible subset for original array.

     * Prerequisite: DP Tabulation of the Longest Increasing Subsequence
        * dp[i] -> denotes the length of the longest divisible sub-sequence by including nums[i] in it
        * indexLDS[i] -> stores the index of previous element to nums[i] in LDS (coming before nums[i])

     * Time Complexity: O(n*log(n)) + O(n*n) + O(n)
        * n*log(n) for sorting
        * n*n for DP Tabulation
        * n for backtracking to print "LDS"
     * Space Complexity: O(2*n)
        * DP_Array and Index_Array
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        // Sorting the array, as we want subset not subsequence
        Arrays.sort(nums);

        int n = nums.length;
        int[] dp = new int[n];
        int[] indexLDS = new int[n];

        int lengthLDS = 0;              // Stores the length of LDS
        int endingIndexLDS = -1;        // Stores the index of last element in LDS

        for (int i = 0; i < n; i++){
            // Initialize Dp[i] Arrays with 1 and index array with -1
            dp[i] = 1;
            indexLDS[i] = -1;

            for (int prev_index = 0; prev_index < i; prev_index++){
                if (nums[i] % nums[prev_index] == 0){
                    if (dp[i] < 1 + dp[prev_index]){
                        dp[i] = 1 + dp[prev_index];
                        indexLDS[i] = prev_index;
                    }
                }
            }
            // Find the length of LDS and the index where it ends
            if (dp[i] > lengthLDS){
                lengthLDS = dp[i];
                endingIndexLDS = i;
            }
        }

        // Print LDS
        ArrayList<Integer> LDS = new ArrayList<>();
        int i = endingIndexLDS;
        while (indexLDS[i] != -1){
            LDS.add(nums[i]);
            i = indexLDS[i];
        }
        LDS.add(nums[i]);
        return LDS;
    }

    /***
    Note: 1) If we want to just find the length of the "Longest Divisible Subset", we can do so using the
            Memoization, Tabulation, Space Optimization Code only after the sorting the array.
            Just the condition for divisibility will change (instead of increasing)
          2) The intuition remains the same.
     */
}
