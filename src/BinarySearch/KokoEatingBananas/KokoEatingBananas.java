package BinarySearch.KokoEatingBananas;

// https://leetcode.com/problems/koko-eating-bananas/description/

public class KokoEatingBananas {
    /************************************ Binary Search Solution **************************************
     * Time Complexity: O(n * log(10^11))   ~  O(11*n)  ~ O(n)
     * Space Complexity: O(1)
     */
    public int minEatingSpeed(int[] piles, int h) {
        int low = 1, high = (int)1e11;
        int minHrs = -1;

        while (low <= high){
            int mid = low + ((high - low)>>1);

            if (canFinishBananas(mid, piles, h)){
                minHrs = mid;
                high = mid - 1;
            }
            else low = mid + 1;
        }
        return minHrs;
    }

    private boolean canFinishBananas(int perHrs, int[] piles, int totalHrs){
        int hrs = 0;
        for (int pile : piles) {
            hrs += (int) Math.ceil(pile / (double) perHrs);
        }
        return hrs <= totalHrs;
    }
}
