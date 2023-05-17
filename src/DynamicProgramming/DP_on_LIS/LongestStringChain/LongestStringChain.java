package DynamicProgramming.DP_on_LIS.LongestStringChain;
import java.util.Arrays;

// Pre-requisite: Longest Increasing SubSequence
// https://leetcode.com/problems/longest-string-chain/description/
// https://takeuforward.org/data-structure/longest-string-chain-dp-45/
// https://youtu.be/YY8iBaYcc4g

public class LongestStringChain {
    /*********************************** DP Tabulation *********************************************
     * Intuition:
        * Since we care about only subsets and not subsequence, sorting will make life easier
        * Since we need to insert characters, we need to have shorter length strings first to make comparisons.
        * Comparisons are only possible when the array is sorted, not for unsorted array bcoz of uniformity.
        * Then, we find the "Longest String Chain Sub-sequence" in the same way as "Longest Increasing Sequence".
        * "Longest String Chain" sub-sequence for sorted array is the "Longest String Chain subset" for original array.

     * Prerequisite: DP Tabulation of the Longest Increasing Subsequence
        * dp[i] -> denotes the length of the "Longest String Chain sub-sequence" by including words[i] in it
        * indexLDS[i] -> stores the index of previous element to words[i] in LSC (coming before words[i])
        * LCS -> Longest String Chain

     * Time Complexity: O(n*log(n)) + O(n*n*len)
        * n*log(n) for sorting
        * n*n*len for DP Tabulation (len for string comparison check)
     * Space Complexity: O(n)
        * DP_Array
     */
    public int longestStrChain(String[] words) {
        int n = words.length;

        // Sort the Array based on lengths
        Arrays.sort(words, (a, b) -> (a.length() - b.length()));

        // Same Algorithm as Longest Increasing SubSequence
        int[] dp = new int[n];
        int lengthLSC = 0;         // Stores the length of LSC

        for (int i = 0; i < n; i++){
            dp[i] = 1;             // Initialize Dp[i] Arrays with 1

            for (int prev_word = 0; prev_word < i; prev_word++){
                if (canBeInserted(words[prev_word], words[i])){
                    dp[i] = Math.max(dp[i], 1 + dp[prev_word]);
                }
            }
            lengthLSC = Math.max(lengthLSC, dp[i]);          // Find the length of LIS
        }
        return lengthLSC;
    }

    // Check function for inserting a single character to smaller string to make it equal to bigger string
    private boolean canBeInserted(String smaller, String bigger){
        int smallLen = smaller.length();
        int bigLen = bigger.length();
        if (bigLen - smallLen != 1)
            return false;

        int j = 0;
        int inserted = 0;
        for (int i = 0; i < bigLen; i++){
            if (j < smallLen && smaller.charAt(j) == bigger.charAt(i))
                j++;
            else
                inserted++;
        }
        return inserted == 1;
    }

    /***
     Note:
        1) If we want to just find the length of the "Longest String Chain Subset",
            we can do so using the Memoization, Tabulation, Space Optimization Code only after the
            sorting the array acc. to length. Just the condition for String check will change (instead
            of increasing)
        2) The intuition remains the same.
        3) To print the Longest String chain, we can follow the same technique as LIS and LDS.
     */
}
