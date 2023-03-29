package GreedyAlgorithms.ReducingDishes;
import java.util.Arrays;

// https://leetcode.com/problems/reducing-dishes/description/

public class ReducingDishes {
    /************************************** Greedy + Sorting Solution ********************************
     * TC -> O(n * log(n))
     * SC -> O(1)
     */
    public int maxSatisfaction(int[] satisfaction) {
        int n = satisfaction.length;
        Arrays.sort(satisfaction);

        int sum = 0;
        int timeCoefficient = 0;
        for (int i = 0; i < n; i++){
            sum += satisfaction[i];
            timeCoefficient += satisfaction[i] * (i + 1);
        }

        int maxTimeCoefficient = 0;             // If we discard all the dish, it will be 0
        for (int i = 0; i < n; i++){
            maxTimeCoefficient = Math.max(maxTimeCoefficient, timeCoefficient);
            timeCoefficient -= sum;
            sum -= satisfaction[i];
        }
        return maxTimeCoefficient;
    }


    /************************************** Greedy + Sorting Solution ********************************
     * TC -> O(n * log(n))
     * SC -> O(1)
     */
    public int maxSatisfaction_V2(int[] satisfaction) {
        int n = satisfaction.length;
        Arrays.sort(satisfaction);

        int suffixSum = 0;
        int sum = 0;
        int timeCoefficient = 0;
        for (int i = n - 1; i >= 0; i--){
            sum += satisfaction[i];
            suffixSum += sum;
            timeCoefficient = Math.max(timeCoefficient, suffixSum);
        }
        return timeCoefficient;
    }
}
