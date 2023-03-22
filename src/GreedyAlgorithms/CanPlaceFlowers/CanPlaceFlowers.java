package GreedyAlgorithms.CanPlaceFlowers;

// https://leetcode.com/problems/can-place-flowers/description/

public class CanPlaceFlowers {
    /*************************************** Greedy Solution *************************************
     * Brute Force Greedy Approach
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int len = flowerbed.length;
        int i = 0, j = len - 1;
        int flowersPlaced = 0;
        int startZeros = 0, backZeros = 0;
        while (i < len && flowerbed[i] == 0) {
            startZeros++;
            i++;
        }
        if (startZeros == len) {
            return (startZeros + 1) / 2 >= n;
        }
        while (j >= 0 && flowerbed[j] == 0) {
            backZeros++;
            j--;
        }
        flowersPlaced += startZeros / 2;
        flowersPlaced += backZeros / 2;

        while (i < j) {
            if (flowerbed[i] == 0) {
                int k = i;
                int cnt = 0;
                while (k < len && flowerbed[k] == 0) {
                    cnt++;
                    k++;
                }
                flowersPlaced += (cnt - 1) / 2;
                i = k;
            }
            i++;
        }
        return flowersPlaced >= n;
    }
}
