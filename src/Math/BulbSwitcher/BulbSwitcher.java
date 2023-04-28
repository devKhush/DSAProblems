package Math.BulbSwitcher;
import java.util.Arrays;

// https://leetcode.com/problems/bulb-switcher/description/

public class BulbSwitcher {
    /*************************************** Brute Force **********************************************
     * TC -> O(n*n)
        * nearly n^2 solution
     * SC -> O(n)
     */
    public int bulbSwitch_bruteforce(int n) {
        if (n == 0 || n == 1)
            return n;

        boolean[] bulbs = new boolean[n + 1];
        Arrays.fill(bulbs, true);
        bulbs[0] = false;

        for (int round = 2; round <= n; round++){
            for (int bulb = round; bulb <= n; bulb += round){
                bulbs[bulb] = !bulbs[bulb];
            }
        }
        int turnedOn = 0;
        for (boolean bulbOn : bulbs){
            turnedOn += bulbOn ? 1 : 0;
        }
        return turnedOn;
    }


    /************************************ Solution 1 ***********************************************
     * Binary Search to find square root of n.

     * TC -> O(log(n))
     * SC -> O(1)
     */
    public int bulbSwitch_(int n) {
        long low = 0, high = n;

        while (low <= high){
            long mid = low + ((high - low)>>1);
            if (mid * mid <= n)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return (int)high;
    }


    /***********************************8 Solution 2 *********************************************/
    public int bulbSwitch(int n) {
        return (int)Math.sqrt(n);
    }
}
