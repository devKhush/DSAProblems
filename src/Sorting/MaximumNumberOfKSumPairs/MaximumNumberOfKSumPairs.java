package Sorting.MaximumNumberOfKSumPairs;
import java.util.Arrays;

/*
Pre-requisite: "TWO SUM: ARRAY IS SORTED"
 */

class MaximumNumberOfKSumPairs {
    /*
    Time Complexity: O(n * log(n)) + O(n/2)   =  O(n * log(n))
    Space Complexity: O(1)
     */
    public int maxOperations(int[] arr, int k) {
        Arrays.sort(arr);
        
        int low = 0, high = arr.length - 1;
        int deleteOperations = 0;
        
        while (low < high){
            int sum = arr[low] + arr[high];
            if (sum == k){
                deleteOperations++;
                low++;
                high--;
            }
            else if (sum < k)
                low++;
            else if (sum > k)
                high--;
        }
        return deleteOperations;
    }
}