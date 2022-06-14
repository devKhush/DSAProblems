package DynamicProgramming.DP_on_Arrays.FrogJumpsMinimumEnergy;

import java.util.Arrays;
import java.util.Scanner;

// https://youtu.be/Kmh3rhyEtB8
// https://takeuforward.org/data-structure/dynamic-programming-frog-jump-with-k-distances-dp-4/
// https://atcoder.jp/contests/dp/tasks/dp_b

public class FrogJumpsWithKSteps_MinimumEnergy {

    //Memoization Approach
    // T.C. --> O(N*K)
    // S.C --> O(N)
    private static int frogJumpsWithKSteps(int index, int[] arr, int[] dp, int k){
        if (index == 0)
            return 0;
        if (dp[index] != -1)
            return dp[index];

        int minEnergyRequiredToJump_toGivenStep = Integer.MAX_VALUE;
        for (int i = index-k; i < index; i++){
            if (i >= 0) {
                int minEnergy = frogJumpsWithKSteps(i, arr, dp, k) + Math.abs(arr[index] - arr[i]);
                minEnergyRequiredToJump_toGivenStep = Math.min(minEnergy, minEnergyRequiredToJump_toGivenStep);
            }
        }
        dp[index] = minEnergyRequiredToJump_toGivenStep;
        return dp[index];
    }

    private static int memoizationSolutionFrogJumpKSteps(int k, int n, int[] arr){
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        return frogJumpsWithKSteps(n-1, arr, dp, k);
    }


    // Tabulation Approach
    // T.C. --> O(N*K)
    // S.C --> O(N)
    private static int tabulationFrogJumpsKSteps(int k, int n, int[] arr){
        if (n == 1)
            return 0;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        for (int index = 1; index < n; index++) {

            int minEnergyFromToIndex = Integer.MAX_VALUE;
            for (int i = index-k; i < index; i++){
                if (i >= 0) {
                    int energyFromIthStepToIndex = dp[i] + Math.abs(arr[index] - arr[i]);
                    minEnergyFromToIndex = Math.min(minEnergyFromToIndex, energyFromIthStepToIndex);
                }
            }
            dp[index] = minEnergyFromToIndex;
        }
        return dp[n-1];
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(tabulationFrogJumpsKSteps(k, n, arr));
    }
}
