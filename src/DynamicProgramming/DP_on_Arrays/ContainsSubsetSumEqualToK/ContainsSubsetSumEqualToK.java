package DynamicProgramming.DP_on_Arrays.ContainsSubsetSumEqualToK;

// https://youtu.be/fWX9xDmIzRI
// https://takeuforward.org/data-structure/subset-sum-equal-to-target-dp-14/
// https://www.geeksforgeeks.org/subset-sum-problem-dp-25/

public class ContainsSubsetSumEqualToK {

    // ************************************** Recursion  **************************************
    // T.C --> O(2^N)       In worst case, we may find last subsequences as the required one
    // S.C --> O(N)         Recursion Stack space
    public static boolean subsetSumToK(int n, int targetSum, int[] arr) {
        return f(n - 1, targetSum, arr);
    }

    private static boolean f(int i, int target, int[] arr) {
        if (target == 0)
            return true;
        if (i == 0)
            return arr[0] == target;

        boolean foundByTake = arr[i] <= target ? f(i - 1, target - arr[i], arr) : false;
        boolean foundByNotTake = f(i - 1, target, arr);

        return foundByTake || foundByNotTake;
    }
}