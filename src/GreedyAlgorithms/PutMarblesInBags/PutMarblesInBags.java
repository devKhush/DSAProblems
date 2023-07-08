package GreedyAlgorithms.PutMarblesInBags;
import java.util.Arrays;

// Problem is hard, but intuition is easy
// https://leetcode.com/problems/put-marbles-in-bags/description/
// https://leetcode.com/problems/put-marbles-in-bags/editorial/
// https://youtu.be/RyJpH8cghrE

public class PutMarblesInBags {
    /*********************************** Greedy Solution **********************************************
     * For Intuition:
        * Read editorial or watch video

     * Time Complexity: O(n * log(n))
        * Creating an array of all pair sum, sorting and fins min & max sum.
     * Space Complexity: O(n)
        * We create an auxiliary array pairWeights of size n - 1 to store the value of all pairs.
     */
    public long putMarbles(int[] weights, int k) {
        int n = weights.length;

        int[] pairWeights = new int[n - 1];
        for (int i = 0; i < n - 1; i++){
            pairWeights[i] = weights[i] + weights[i + 1];
        }

        Arrays.sort(pairWeights);

        long maxScoreSum = 0, minScoreSum = 0;
        for (int i = 0; i < k - 1; i++){
            maxScoreSum += pairWeights[n - 2 - i];
            minScoreSum += pairWeights[i];
        }
        return maxScoreSum - minScoreSum;
    }
}
