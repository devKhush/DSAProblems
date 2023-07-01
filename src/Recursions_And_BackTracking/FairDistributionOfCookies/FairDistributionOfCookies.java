package Recursions_And_BackTracking.FairDistributionOfCookies;

// https://leetcode.com/problems/fair-distribution-of-cookies/
// https://leetcode.com/problems/fair-distribution-of-cookies/editorial/
// If the candies could be taken sequentially (i.e, take ith before (i+1)th), then problem would be
// Allocate Minimum no. of pages ==> Binary Search

public class FairDistributionOfCookies {
    /********************************** Back-Tracking *********************************************
     * Time Complexity: O(k^n)
        * k times recursion calls in every state of i(= 0 to n)
        * Similar to 2^n for array subsequence
        * The algorithm attempts to distribute each of the n cookies to each of the k children,
            resulting in at most O(k^n) distinct distributions.
     * Space Complexity: O(k + n)
        * Recursion stack space of size n
        * Distribute array of size k
     */
    public int distributeCookies(int[] cookies, int k) {
        int n = cookies.length;
        int[] distribute = new int[k];
        return f(n-1, k, cookies, distribute);
    }

    /****************************** Faster Backtracking ************************/
    private int f(int i, int childLeft, int[] cookies, int[] distribute){
        if (i < 0){
            int max = 0;
            for (int candies : distribute)
                max = Math.max(max, candies);
            return max;
        }
        // This condition is added to make backtracking process a bit faster (though it is not needed)
        // Intuition behind this is that every student must have atleast one candy (num_of_child = zero_count)
        if (childLeft > i + 1)
            return (int)1e9;

        int min = (int)1e9;
        for (int j = 0; j < distribute.length; j++){
            if (distribute[j] == 0)
                childLeft--;
            distribute[j] += cookies[i];
            min = Math.min(min, f(i - 1, childLeft, cookies, distribute));
            distribute[j] -= cookies[i];
            if (distribute[j] == 0)
                childLeft++;
        }
        return min;
    }



    /****************************** Brute force Backtracking ************************/
    private int f(int i, int[] cookies, int[] distribute){
        if (i < 0){
            int max = 0;
            for (int candies : distribute)
                max = Math.max(max, candies);
            return max;
        }

        int min = Integer.MAX_VALUE;
        for (int j = 0; j < distribute.length; j++){
            distribute[j] += cookies[i];
            min = Math.min(min, f(i-1, cookies, distribute));
            distribute[j] -= cookies[i];
        }
        return min;
    }
}
