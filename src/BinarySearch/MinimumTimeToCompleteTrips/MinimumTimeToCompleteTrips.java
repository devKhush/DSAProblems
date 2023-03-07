package BinarySearch.MinimumTimeToCompleteTrips;

public class MinimumTimeToCompleteTrips {
    /********************************* Binary Search Solution ********************************
     * Time Complexity: O(n * log(10^18)) ~ O(18 * n) ~ O(n)
     * Space Complexity: O(1)
     */
    public long minimumTime(int[] busTime, int totalTrips) {
        long timeReq = 0;
        long low = 1, high = (long)1e18;

        while (low <= high){
            long mid = low + ((high - low)>>1);

            if (canOrganizeTrips(mid, totalTrips, busTime)){
                timeReq = mid;
                high = mid - 1;
            }
            else
                low = mid + 1;
        }
        return timeReq;
    }

    private boolean canOrganizeTrips(long timeAvailable, int totalTrips, int[] busTime){
        long trips = 0;

        for (int i = 0; i < busTime.length; i++){
            trips += timeAvailable / busTime[i];
            if (trips >= totalTrips)
                return true;
        }
        return false;
    }
}
