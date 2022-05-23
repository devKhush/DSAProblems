package DynamicProgramming.FrogJumpsMinimumEnergy;

// https://youtu.be/EgG3jsGoPvQ
// https://takeuforward.org/data-structure/dynamic-programming-frog-jump-dp-3/

import java.util.Arrays;

// Assume that you reached the (n-2)th index, now try to reach (n-1) index
// This function returns the minimum energy taken to reach any index from 0th index

public class FrogJumpsMinimumEnergy {

    // Solution By Memoization
    // T.C. --> O(n)
    // S.C --> O(n)
    private static int solveViaMemoization(int index, int[] arr, int[] dp){
        if (index == 0)
            return 0;
        if (dp[index] != -1)
            return dp[index];

        int NminusOneJump = solveViaMemoization(index-1, arr, dp) + Math.abs(arr[index] - arr[index-1]);
        int NminusTwoJump = Integer.MAX_VALUE;
        if (index > 1)
            NminusTwoJump = solveViaMemoization(index-2, arr, dp) + Math.abs(arr[index] - arr[index-2]);

        dp[index] = Integer.min(NminusOneJump, NminusTwoJump);
        return dp[index];
    }

    public static int frogJumpsMinimumEnergy(int n, int[] heights){
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        // This function returns the minimum energy taken to reach any index from 0th index
        // So calling it for (n-1)th last index
        return solveViaMemoization(n-1, heights, dp);
    }



    // Solution by Tabulation
    // T.C. --> O(n)
    // S.C --> O(n)
    public static int solveByTabulation(int n, int[] heights){
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        for (int i=1; i<n; i++){
            int NminusOneJump = dp[i-1] + Math.abs(heights[i] - heights[i-1]);
            int NminusTwoJump = Integer.MAX_VALUE;
            if (i >1)
                NminusTwoJump = dp[i-2] + Math.abs(heights[i] - heights[i-2]);

            dp[i] = Integer.min(NminusOneJump, NminusTwoJump);
        }
        return dp[n-1];
    }



    // T.C --> O(n)
    // S.C --> O(1)
    // Using same approach as Fibonacci numbers, keeping track of two variables
    public static int frogJumpConstantSpaceSolution(int n, int[] arr) {
        if (n==1) return 0;

        int NminusTwoJump = 0;
        int NminusOneJump = 0;
        int minEnergyAtIthJump = 0;

        for (int i = 1; i < n; i++){
            int NminusOne = NminusOneJump + Math.abs(arr[i] - arr[i-1]);
            int NminusTwo = Integer.MAX_VALUE;
            if (i > 1)
                NminusTwo = NminusTwoJump + Math.abs(arr[i] - arr[i-2]);

            minEnergyAtIthJump = Math.min(NminusOne, NminusTwo);

            NminusTwoJump = NminusOneJump;
            NminusOneJump = minEnergyAtIthJump;
        }
        return minEnergyAtIthJump;
    }

    public static void main(String[] args) {
        System.out.println(frogJumpConstantSpaceSolution(6, new int[]{30, 10, 60, 10, 60, 50}));
    }
}
