package Sorting.MinimumMovesToEqualArrayElements_II;
import java.util.Arrays;

// Interested Readings:
// https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/discuss/2215732/C%2B%2B-oror-3-Approaches-oror-Full-Explanation
// https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/discuss/2215782/Visual-Explanation-%2B-Interview-Tips-or-JAVA

class MinimumMovesToEqualArrayElements_II {
    /*
     ************************************** Approach 1 **************************************
     * https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/discuss/2215782/Visual-Explanation-%2B-Interview-Tips-or-JAVA
     *
     * Time Complexity; O(n * log(n)) + O(n)  =  O(n * log(n))
     * Space Complexity: O(1)
     */
    public int minMoves2_Solution1(int[] arr) {
        Arrays.sort(arr);
        
        int medianIndex = arr.length / 2;
        int low = 0, high = arr.length - 1;
        int moves = 0;
        
        while (low < medianIndex)
            moves += arr[medianIndex] - arr[low++];

        while(medianIndex < high)
            moves += arr[high--] - arr[medianIndex];

        return moves;
    }

    /*
     ************************************** Approach 2 **************************************
     * https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/discuss/2215782/Visual-Explanation-%2B-Interview-Tips-or-JAVA
     *
     * Time Complexity; O(n * log(n)) + O(n)  =  O(n * log(n))
     * Space Complexity: O(1)
     */
    public int minMoves2_Solution2(int[] nums) {
        Arrays.sort(nums);

        int median = nums[nums.length / 2];
        int moves = 0;

        for (int value : nums)
            moves += Math.abs(value - median);

        return moves;
    }


    /*
     ************************************** Approach 3 **************************************
     * https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/discuss/2215732/C%2B%2B-oror-3-Approaches-oror-Full-Explanation
     *
     * Time Complexity; O(n * log(n)) + O(n)  =  O(n * log(n))
     * Space Complexity: O(1)
     */
     public int minMoves2_Solution3(int[] arr) {
        Arrays.sort(arr);
        int low = 0, high = arr.length - 1;
        int moves = 0;
       
        while (low < high){
            moves += arr[high] - arr[low];
            low++;
            high--;
        }
        return moves;
    }
}