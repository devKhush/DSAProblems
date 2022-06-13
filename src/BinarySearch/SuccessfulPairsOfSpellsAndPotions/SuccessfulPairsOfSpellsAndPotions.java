package BinarySearch.SuccessfulPairsOfSpellsAndPotions;
import java.util.Arrays;

class SuccessfulPairsOfSpellsAndPotions {

    // TC -> O(n * Log(m))
    // SC -> O(n) due to sorting & ignoring output array

    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int[] pairs = new int[spells.length];
        Arrays.sort(potions);

        for (int i = 0; i < pairs.length; i++){
            int spell = spells[i];
            int numOfSuccess = 0;

            int low = 0;
            int high = potions.length-1;

            while (low <= high){
                int mid = (low + high) >> 1;

                if ((long) potions[mid] * spell >= success){
                    high = mid - 1;
                }
                else
                    low = mid + 1;
            }

            pairs[i] = potions.length - low;
        }
        return pairs;
    }
}