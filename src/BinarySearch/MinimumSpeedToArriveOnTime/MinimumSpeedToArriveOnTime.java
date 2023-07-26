package BinarySearch.MinimumSpeedToArriveOnTime;

// https://leetcode.com/problems/minimum-speed-to-arrive-on-time/description/

public class MinimumSpeedToArriveOnTime {
    /*********************************** Binary Search ********************************************
     * Intuition: Binary Search Solution
     * Time Complexity: O(n * log(k))
     * Space Complexity: O(1)
     */
    public int minSpeedOnTime(int[] dist, double hour) {
        int n = dist.length;

        int low = 1, high = (int)1e7;
        int minSpeed = -1;
        while (low <= high){
            int mid = low + ((high - low) >> 1);

            if (canReachOnTime(dist, mid, hour)){
                high = mid - 1;
                minSpeed = mid;
            }
            else
                low = mid + 1;
        }
        return minSpeed;
        // return low < (int)1e8 ? low : -1;
    }

    private boolean canReachOnTime(int[] dist, int speed, double hour){
        int n = dist.length;
        double time = 0;

        for (int i = 0; i < n; i++){
            if (i != n - 1)  time += Math.ceil(dist[i] / (double)speed);
            else  time += dist[i] / (double)speed;
        }
        return time <= hour;
    }
}
