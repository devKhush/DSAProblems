package Arrays.FindScoreOfAllPrefixesOfArray;

// https://leetcode.com/problems/find-the-score-of-all-prefixes-of-an-array/

public class FindScoreOfAllPrefixesOfArray {
    /*********************************** Solution 1 ********************************************
     * TC -> O(n)
     * SC -> O(n)
     */
    public long[] findPrefixScore(int[] nums) {
        int n = nums.length;
        long[] prefixMax = new long[n];
        long[] conversion = new long[n];
        long[] score = new long[n];
        long prefixScoreSum = 0;

        for (int i = 0; i < n; i++) {
            if (i == 0)
                prefixMax[0] = nums[0];
            else
                prefixMax[i] = Math.max(prefixMax[i - 1], nums[i]);

             conversion[i] = nums[i] + prefixMax[i];
             prefixScoreSum += conversion[i];
             score[i] = prefixScoreSum;
        }
        return score;
    }



    /*********************************** Solution 2 ********************************************
     * TC -> O(n)
     * SC -> O(n)
     */
    public long[] findPrefixScore_v2(int[] nums) {
        int n = nums.length;

        long[] prefixMax = new long[n];
        long[] score = new long[n];
        long prefixScoreSum = 0;

        for (int i = 0; i < n; i++) {
            if (i == 0)
                prefixMax[0] = nums[0];
            else
                prefixMax[i] = Math.max(prefixMax[i - 1], nums[i]);

            prefixScoreSum += nums[i] + prefixMax[i];
            score[i] += prefixScoreSum;
        }
        return score;
    }
}
