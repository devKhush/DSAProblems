package GreedyAlgorithms.NonOverlappingIntervals;
import java.util.Arrays;

// https://leetcode.com/problems/non-overlapping-intervals/description/
// https://leetcode.com/problems/non-overlapping-intervals/editorial/

public class NonOverlappingIntervals {
    /********************************** Greedy Solution *************************************
     * Intuition:
        * We need to minimize the no. of intervals to be removed to make events non-overlapping
        * In other words, we need to maximize the no. of non-overlapping events.
        * For this, we need to complete events as early as possible.
        * So, sort intervals wrt end time.
        * And take the events that are non-overlapping, using this we will maximize the no. of
            non-overlapping events.
        * So, n-max  will be the no. of events to be removed.

     * Time Complexity: O(n * log(n))
     * Space Complexity: O(1)
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        int n = intervals.length;
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);

        int take = 0;
        int end = -(int)1e9;
        for (int[] event : intervals){
            if (end <= event[0]){
                end = event[1];
                take++;
            }
        }
        return n - take;
    }
}
