package Hashing.LongestArithmeticSubsequenceOfGivenDifference;
import java.util.HashMap;

// https://leetcode.com/problems/longest-arithmetic-subsequence-of-given-difference/description/
// Intuition: "Two Sum" problem

public class LongestArithmeticSubsequenceOfGivenDifference {
    /************************************ Hashing + DP ************************************
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public int longestSubsequence(int[] arr, int difference) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int longestSequence = 1;

        for (int val : arr) {
            map.put(val, map.getOrDefault(val - difference, 0) + 1);
            longestSequence = Math.max(longestSequence, map.get(val));
        }
        return longestSequence;
    }
}
