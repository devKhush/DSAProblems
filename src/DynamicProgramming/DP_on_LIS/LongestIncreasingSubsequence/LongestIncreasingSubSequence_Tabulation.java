package DynamicProgramming.DP_on_LIS.LongestIncreasingSubsequence;
import java.util.Arrays;

// https://youtu.be/IFfYfonAFGc
// https://takeuforward.org/data-structure/printing-longest-increasing-subsequence-dp-42/
// https://leetcode.com/problems/longest-increasing-subsequence/description/

public class LongestIncreasingSubSequence_Tabulation {
    /************************************* Another Tabulation Method ***********************************
     * This is an intuitive DP solution without memoization.
     * dp[i] -> indicates the length of LIS, by including nums[i] into the sub-sequence

     * Time Complexity: O(n*n)
        * Two loops
     * Space Complexity: O(n)
        * One DP Array
     */
    public int longestLIS(int[] nums, int n){
        // dp[i] -> indicates the length of LIS, by including nums[i] into the sub-sequence
        int[] dp = new int[n];

        // fill 1 for every element, indicating the longest subsequence including itself only
        Arrays.fill(dp, 1);

        // Stores the length of LIS
        int lengthLIS = 0;

        // Intuition
        for (int i = 0; i < n; i++){
            for (int prevSmallIndex = 0; prevSmallIndex < i; prevSmallIndex++){
                if (nums[i] > nums[prevSmallIndex])
                    dp[i] = Math.max(dp[i], dp[prevSmallIndex] + 1);
            }
            lengthLIS = Math.max(lengthLIS, dp[i]);
        }
        return lengthLIS;
    }


    /************************************* Print LIS ***********************************
     * This is an intuitive DP solution without memoization.
     * dp[i] -> indicates the length of LIS, by including nums[i] into the sub-sequence
     * indexLIS[i] -> stores the index of previous element to nums[i] in LIS (coming before nums[i])

     * Time Complexity: O(n*n)
     * Space Complexity: O(2*n)
     */
    public int[] printLongestLIS(int[] nums, int n){
        int[] dp = new int[n];
        int[] indexLIS = new int[n];

        int lengthLIS = 0;          // Stores the length of LIS
        int endingIndexLIS = -1;    // Stores the index of last element in LIS

        for (int i = 0; i < n; i++){
            // Initialize Dp[i] Arrays with 1 and index array with -1
            dp[i] = 1;
            indexLIS[i] = -1;

            for (int prevSmallIndex = 0; prevSmallIndex < i; prevSmallIndex++){
                if (nums[i] > nums[prevSmallIndex]  &&  dp[i] < 1 + dp[prevSmallIndex]) {
                    dp[i] = 1 + dp[prevSmallIndex];
                    indexLIS[i] = prevSmallIndex;
                }
            }
            // Find the length of LIS and the index where it ends
            if (dp[i] > lengthLIS){
                lengthLIS = dp[i];
                endingIndexLIS = i;
            }
        }

        // Printing the LIS
        int[] LIS = new int[lengthLIS];
        int ind = lengthLIS - 1;
        int i = endingIndexLIS;
        while (indexLIS[i] != -1){
            LIS[ind--] = nums[i];
            i = indexLIS[i];
        }
        LIS[ind] = nums[i];
        return LIS;
    }
}
