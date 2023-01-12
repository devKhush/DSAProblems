package Arrays.SortAnArrayOf_0s_1s_2s;
import java.util.ArrayList;

// Variant of Dutch-National-Flag algorithm
// https://www.codingninjas.com/codestudio/problem-details/move-all-negative-numbers-to-beginning-and-positive-to-end_1112620
// https://www.geeksforgeeks.org/move-negative-numbers-beginning-positive-end-constant-extra-space/

public class MoveAllNegativeNumbersToBeginning {
    public static ArrayList<Integer> separateNegativeAndPositive(ArrayList<Integer> arr) {
        int low = 0, high = arr.size() - 1;
        while (low < high){
            if (arr.get(low) < 0)
                low++;
            else{
                swap(arr, low, high);
                high--;
            }
        }
        return arr;
    }

    private static void swap(ArrayList<Integer> arr, int i, int j){
        int temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }
}
