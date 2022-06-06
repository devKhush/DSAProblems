class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new IntervalComparator());

        ArrayList<int[]> arrangedInterval = new ArrayList<>();
        int[] currentInterval = intervals[0];

        arrangedInterval.add(currentInterval);

        for (int[] nextInterval : intervals){

            if (currentInterval[1] < nextInterval[0]){
                currentInterval = nextInterval;
                arrangedInterval.add(currentInterval);
            }

            else if (currentInterval[1] >= nextInterval[0])
                currentInterval[1] = Math.max(currentInterval[1], nextInterval[1]);

        }

        // We can return a list of arranged intervals if required
        int[][] arrangedIntervalArray = new int[arrangedInterval.size()][2];
        for (int i = 0; i < arrangedIntervalArray.length; i++)
            arrangedIntervalArray[i] = arrangedInterval.get(i);
        
        return arrangedIntervalArray;
    }
}




class IntervalComparator implements Comparator<int[]>{

    @Override
    public int compare(int[] a, int[] b) {
        // Sorting according to the Starting time of various Intervals
        
        // Case for a[0] > b[0]
        if (a[0] > b[0]) 
            return 1;

        // Case for a[0] < b[0]
        else if (a[0] < b[0]) {
            return -1;
        }

        // Case for a[0] == b[0]
        else {
            // Sorting according to the Ending time of various Intervals in case their starting time is same
            // Case for  a[1] > b[1]
            if (a[1] > b[1])
                return 1;
            
            // Case for  a[1] < b[1]
            else if (a[1] < b[1])
                return -1;

            // Case for  a[1] == b[1]
            else
                return 0;
        }
        
        // Shorthand for above code
        // return Integer.compare(a[0], b[0]);
    }
}