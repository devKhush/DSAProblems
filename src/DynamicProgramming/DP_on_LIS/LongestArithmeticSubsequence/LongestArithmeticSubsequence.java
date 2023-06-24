package DynamicProgramming.DP_on_LIS.LongestArithmeticSubsequence;
import java.util.HashMap;

// Pre-requisite: Intuitive Tabulation solution of "Longest Increasing Subsequence (LIS)"
// https://leetcode.com/problems/longest-arithmetic-subsequence/description/
// Read editorial
// https://leetcode.com/problems/longest-arithmetic-subsequence/editorial/

public class LongestArithmeticSubsequence {
    /********************************** Brute Force Solution ****************************************
     * Intuition:
        * Choose any two numbers/pairs in the array as elements of AP.
        * And try to extend the sequence based on their difference
     * Time Complexity: O(n^3)
     * Space Complexity: O(1)
     */
    public int longestArithmeticSeqLength_brute(int[] nums) {
        int n = nums.length;
        int lengthAP = 0;

        for (int left = 0; left < n; left++){
            for (int right = left + 1; right < n; right++){
                int diff = nums[right] - nums[left];
                int length = 2;
                int next = nums[right] + diff;
                for (int i = right + 1; i < n; i++){
                    if (nums[i] == next){
                        next = nums[i] + diff;
                        length++;
                    }
                }
                lengthAP = Math.max(lengthAP, length);
            }
        }
        return lengthAP;
    }

    /************************************* Intuitive Tabulation Solution *******************************
     * Intuition:
        * Tabulation of LIS
        * See Editorial for detailed discussion
        * Instead, we can keep track of all possible arithmetic sequences that end with each element in
            the array, along with their common difference and length.
        * Assume we already know which arrays end at left and what their common difference and length are.
        * Then, we just need to search among these arrays to find if there is an array with a common
            difference diff = nums[right] - nums[left] and what its length is!
        * If there is, we can extend it to a new arithmetic sequence by appending nums[right] and
            updating the length of the longest arithmetic sequence seen so far.

     * Time Complexity: O(n^2)
     * Space Complexity: O(n^2)
     */
    public int longestArithmeticSeqLength(int[] nums) {
        int n = nums.length;
        HashMap<Integer, Integer>[] dp = new HashMap[n];
        int lengthAP = 0;

        for (int i = 0; i < n; i++){
            dp[i] = new HashMap<>();
            for (int j = 0; j < i; j++){
                int diff = nums[i] - nums[j];
                dp[i].put(diff, dp[j].getOrDefault( diff, 1) + 1);
                lengthAP = Math.max(lengthAP, dp[i].get(diff));
            }
        }
        return lengthAP;
    }

}
