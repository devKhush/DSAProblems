package SlidingWindow.MaxConsecutiveOnes_III;

// https://leetcode.com/problems/max-consecutive-ones-iii/description/

public class MaxConsecutiveOnes_III {
    /*********************************** Sliding Window ************************************
     * Sliding window approach usually has two nested loops (inner one is while loop)

     * Time Complexity: O(2*n) ~ O(n)
     * Space Complexity: O(1)
     */
    public int longestOnes(int[] nums, int k) {
        int left = 0;
        int right = 0;
        int zerosCount = 0;
        int maxConsecOnes = 0;

        while (right < nums.length){
            if (nums[right] == 0)
                zerosCount++;

            while (zerosCount > k){
                if (nums[left] == 0)
                    zerosCount--;
                left++;
            }
            maxConsecOnes = Math.max(maxConsecOnes, right - left + 1);
            right++;
        }
        return maxConsecOnes;
    }
}
