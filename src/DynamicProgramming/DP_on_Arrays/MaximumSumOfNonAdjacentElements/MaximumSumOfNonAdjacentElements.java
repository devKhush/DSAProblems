package DynamicProgramming.DP_on_Arrays.MaximumSumOfNonAdjacentElements;
import java.util.Arrays;

// https://www.codingninjas.com/codestudio/problems/maximum-sum-of-non-adjacent-elements_843261?source=youtube&campaign=striver_dp_videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_dp_videos
// https://youtu.be/GrMBfJNk_NY
// https://takeuforward.org/data-structure/maximum-sum-of-non-adjacent-elements-dp-5/

public class MaximumSumOfNonAdjacentElements {

    // Memoization
    // T.C -> O(n)
    // S.C -> O(n)
    public static int getMaximumSumOfNonAdjacentElements(int[] arr, int[] dp, int index){
        if (index == 0)
            return dp[0];
        if (index < 0)
            return 0;
        if (dp[index] != 0)
            return dp[index];

        int maxSumByPickingCurrentElement =  arr[index] + getMaximumSumOfNonAdjacentElements(arr, dp, index-2);
        int maxSumByNotPickingCurrentElement = 0 + getMaximumSumOfNonAdjacentElements(arr, dp, index-1);

        dp[index] = Integer.max(maxSumByPickingCurrentElement, maxSumByNotPickingCurrentElement);
        return dp[index];
    }

    public static int memoizationApproach(int[] arr, int n){
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        dp[0] = arr[0];
        return getMaximumSumOfNonAdjacentElements(arr, dp, n-1);
    }

    // Tabulation
    // T.C --> O(n)
    // S.C. --> O(n)
    public static int tabulationApproach(int[] arr, int n){
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        dp[0] = arr[0];

        for (int i = 1; i < n; i++) {

            int maxSumByPicking = arr[i];
            if (i-2 >= 0)
                maxSumByPicking += dp[i-2];

            int maxSumByNonPicking = 0 + dp[i-1];

            dp[i] = Math.max(maxSumByNonPicking, maxSumByPicking);
        }
        return dp[n-1];
    }

    // Constant Space Solution
    public static int constantSpaceSolution(int[] arr, int n){
        int maxSumPrevTwoIndex = 0;
        int maxSumPrevOneIndex = arr[0];

        for (int i = 1; i < n; i++) {
            int maxSumByPicking = arr[i] + maxSumPrevTwoIndex;
            int maxSumByNonPicking = 0 + maxSumPrevOneIndex;

            int maxSumTill_i_index = Integer.max(maxSumByNonPicking, maxSumByPicking);

            maxSumPrevTwoIndex = maxSumPrevOneIndex;
            maxSumPrevOneIndex = maxSumTill_i_index;
        }

        // As answer is stored at dp[n-1] in tabulation
        return maxSumPrevOneIndex;
    }
}
