package Sorting.CanMakeArithmeticProgressionFromSequence;
import java.util.Arrays;

// https://leetcode.com/problems/can-make-arithmetic-progression-from-sequence/description/
// https://youtu.be/jI_MW1J0zeg

public class CanMakeArithmeticProgressionFromSequence {
    /***************************************** Brute Force ******************************************
     * Brute Force Solution
     * Time Complexity: O(n*log(n))
     * Space Complexity: O(1)
     */
    public boolean canMakeArithmeticProgression_Brute(int[] arr) {
        int n = arr.length;
        Arrays.sort(arr);

        int difference = arr[1] - arr[0];
        for (int i = 1; i < n; i++){
            if (arr[i] - arr[i-1] != difference)
                return false;
        }
        return true;
    }


    /***************************************** MOst optimal Solution *******************************
     * Intuition: Math is involved in this solution. See video
     * Arrange the array in sorted order, as the algorithm proceeds

     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public boolean canMakeArithmeticProgression(int[] arr) {
        int n = arr.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int val : arr){
            min = Math.min(min, val);
            max = Math.max(max, val);
        }
        if ((max - min) % (n - 1) != 0)
            return false;

        // Parameters of AP: a_nth = a + (n - 1)*d
        int a = min;
        int d = (max - min) / (n - 1);

        for (int i = 0; i < n; i++){
            // When the AP element is as expected
            if (arr[i] == a + i*d)
                continue;
            // When the current element doesn't belong to the AP sequence
            else if ((arr[i] - a) % d != 0)
                return false;
            else{
                // Move the current element to its correct position in AP series
                int pos = (arr[i] - a)/d;
                if (arr[pos] == arr[i])     // Avoid the case of infinite loop due to duplicates
                    return false;
                swap(i, pos, arr);
                i--;                        // After, swapping ith element might also be at incorrect position
            }
        }
        return true;
    }

    private void swap(int i, int j, int[] arr){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
