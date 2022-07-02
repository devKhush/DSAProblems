package GreedyAlgorithms.MaximumAreaOfPieceOfCakeAfterHorizontalAndVerticalCuts;
import java.util.Arrays;

// https://leetcode.com/problems/maximum-area-of-a-piece-of-cake-after-horizontal-and-vertical-cuts/discuss/661995/Do-you-like-visual-explanation-You-got-it.-%3A-)-With-2-code-variations.
// https://leetcode.com/problems/maximum-area-of-a-piece-of-cake-after-horizontal-and-vertical-cuts/discuss/661644/C%2B%2BJava-Maximum-Gap-Between-Cuts

/** ************************************** INTUITION ********************************************
 * **************** Initial thought ****************
 * How could I find the maximum area? How to find some area which lies in the middle of the grid?
 * Develop thinking...
 * First, do not get overwhelmed by looking at the visual picture. You should know, the problem is made to solve.
 * You just have to change the thinking process.
 * Area consists of length * width
 * You see the problem now becomes simple.
 * For maximizing the area, you need to maximize the length and width.
 *
 * Conclusion:  So, now you just have to find the maximum gap from given horizontal and vertical cuts.
 *
 * **************** Key idea ****************
 * If we need the area, we must think of lengths and breadths of each cake piece.
 *
 * **************** Assume ****************
 * LENGTH as total horizontal length of the original cake.
 * HEIGHT as total vertical height of the original cake.
 * H as number of horizontal cuts. 'HCUTS' is array of horizontal cuts.
 * Similarly, V as number of vertical cuts and 'VCUTS' is an array of vertical cuts.
 *
 * **************** Solution ****************
 * 1) Lets only think of horizontal cuts. Each 'HCUTS[i]' would create a piece with length 'LENGTH'
      and height, say, heights[i]. As there are H cuts, there will be (H+1) pieces of length 'LENGTH'.
 * 2) Now each vertical cut 'VCUTS[i]' will cut each of the horizontal pieces that we got in step 1.
      We already know the height of each piece (step 1), now with each vertical cut, we will know the
      length of each piece as well.

 * Because we want to maximize the area, we must try to maximize the length and height of each piece.
 *
 * **************** Algorithm ****************
 * Find heights of pieces if we only perform the horizontal cuts. Say this array is heights[].
 * Find lengths of pieces if we only perform the vertical cuts. Say this arrays is lengths[].
 * Find max of heights[] and lengths[].
 * Multiply those two max and take mod 10e7.
 * Return the answer
 */

public class MaximumAreaOfPieceOfCakeAfterCuts {
    private int mod = 1000000007;

    /* ********************************* Solution 1 *****************************************
     * Complexity Analysis
        * Time:      O(v*log(v) + h*log(h))             due to sorting
             where v and h are the number of vertical and horizontal cuts.
        * Space: O(1),
          plus memory required by the sort algorithm (from O(log n) to O(n), where n is
          the number of elements we sort).
    */
    public int maxArea_V1(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        
        int hCuts = horizontalCuts.length;
        int vCuts = verticalCuts.length;
        
        long maxPieceHeight = Math.max(horizontalCuts[0], h - horizontalCuts[hCuts - 1]);
        long maxPieceWidth = Math.max(verticalCuts[0], w - verticalCuts[vCuts - 1]);
        
        for (int i = 0; i < hCuts - 1; i++)
            maxPieceHeight = Math.max(horizontalCuts[i + 1] - horizontalCuts[i], maxPieceHeight);
        
        for (int i = 0; i < vCuts - 1; i++)
            maxPieceWidth = Math.max(verticalCuts[i + 1] - verticalCuts[i], maxPieceWidth);
        
        // return (int) (((maxPieceWidth % mod) * (maxPieceHeight % mod)) % mod);
        return (int) ((maxPieceWidth * maxPieceHeight) % mod);
    }
    
    
    /* ********************************* Solution 2 *****************************************
     * Now in each Cuts we find the length of maximum cuts, problem is similar to
     * Same as above just code is compact
     * Complexity Analysis
        * Time:      O(v*log(v) + h*log(h))             due to sorting
             where v and h are the number of vertical and horizontal cuts.
        * Space: O(1),
          plus memory required by the sort algorithm (from O(log n) to O(n), where n is
          the number of elements we sort).
    */
    public int maxArea_CompactCode(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        long maxCakePieceHeight = getMaxLengthOfInterval(h, horizontalCuts);
        long maxCakePieceWidth = getMaxLengthOfInterval(w, verticalCuts);
        
        return (int) ((maxCakePieceHeight * maxCakePieceWidth) % mod);
    }
    
    private int getMaxLengthOfInterval(int totalLength, int[] intervals){
        Arrays.sort(intervals);
        
        int maxIntervalLength = Math.max(intervals[0], totalLength - intervals[intervals.length - 1]);
        
        for (int i = 0; i < intervals.length - 1; i++)
            maxIntervalLength = Math.max(intervals[i + 1] - intervals[i], maxIntervalLength);
        
        return maxIntervalLength;
    }
    
}