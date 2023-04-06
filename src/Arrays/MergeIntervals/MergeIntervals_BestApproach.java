package Arrays.MergeIntervals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

// Must Watch Video (Notes not much helpful)
// https://youtu.be/2JzRBPFYbKE
// https://takeuforward.org/data-structure/merge-overlapping-sub-intervals/
// Question of Striver SDE Sheet

public class MergeIntervals_BestApproach {
    // *********************************** Fastest Approach ***********************************
    // T.C --> O(n * log(n)) + O(n)    Sorting + Traversal of Intervals
    // S.C --> O(n)  in worst case  due to arranges list
    public int[][] merge(int[][] intervals) {

        // Sorting intervals according to their starting time
         Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));

        // This is ArrayList of required non-overlapping intervals that cover all the intervals in the input
        ArrayList<int[]> mergedIntervals = new ArrayList<>();
        int prevEndTime = -1;

        for (int[] interval : intervals){
            // If the ending time of current interval is less than the starting time of next interval
            // then we don't need to change current interval, we can move to next interval
            if (interval[0] > prevEndTime){
                mergedIntervals.add(interval);
                prevEndTime = interval[1];
            }
            // If the ending time of current interval is >= than the starting time of next interval
            // then the both the current intervals are overlapping & are running in parallel
            // so we take the maximum ending time of both these intervals & updates it into the current interval
            else{
                int[] last = mergedIntervals.get(mergedIntervals.size() - 1);
                last[1] = Math.max(last[1], interval[1]);
                prevEndTime = last[1];
            }
        }

        // We can return a list of arranged intervals if required
        int[][] merged = new int[mergedIntervals.size()][2];
        for (int i = 0; i < merged.length; i++)
            merged[i] = mergedIntervals.get(i);
        return merged;
    }
}