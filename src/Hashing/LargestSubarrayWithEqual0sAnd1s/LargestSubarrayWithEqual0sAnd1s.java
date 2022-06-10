package Hashing.LargestSubarrayWithEqual0sAnd1s;
import java.util.HashMap;

// https://practice.geeksforgeeks.org/problems/largest-subarray-of-0s-and-1s/1/#
// https://www.geeksforgeeks.org/largest-subarray-with-equal-number-of-0s-and-1s/

// Pre-requisite : Largest Sub array with zero/k sum


public class LargestSubarrayWithEqual0sAnd1s {

    /*
     ********************************* Efficient Solution using Hashing ********************************
     1) There are two conditions under which we can get Sub array with sum
     One when the prefixSum at ith index itself becomes 0, then till the ith index (i+1) is required length
     of subarray with 0 sum
     2) Now let’s say we know that the sum of subarray(i, j) = S, and we also know that sum of
     subarray(i, x) = S where i < x < j. We can conclude that the sum of subarray(x+1, j) = 0.

     TC -> O(n)
     SC -> O(n)

     1) Either we can make all 0s of arrays as -1, then this problem will become
        "Largest Sub array with zero/k sum"  as to find sub-array with equal no. of 1s and 0s (-1s)
        their sum should be 0. So, we need to find max length of subarray with sum 0

     2) Otherwise, if we choose not to modify array, we can treat 0s as -1s in our prefix sum,
        and continue the same operation.
     */


    private int maxLengthSubArrayWith0Sum(int[] arr, int n){

        /*
        // Either modify array
        for (int i = 0; i < n; i++) {
            arr[i] = (arr[i] == 0) ? -1 : 1;
        }   */

        int maxSubArrayLength = 0;
        int prefixSum = 0;

        HashMap<Integer, Integer> prefixSumMap = new HashMap<>();

        for (int i = 0; i < arr.length; i++){

            // Otherwise, we can consider 0s as -1s in our prefix sum
            if (arr[i] == 1)
                prefixSum += arr[i];
            else if (arr[i] == 0)
                prefixSum += -1;

            // we don't meed Math.max() condition here, as if the entire prefix sum becomes 0 at any index 'i'
            // then it will be of maximum length till index 'i' (think)
            if (prefixSum == 0)
                maxSubArrayLength = i + 1;


            else if (prefixSumMap.containsKey(prefixSum)) {
                int startIndexOfSum = prefixSumMap.get(prefixSum);
                int endingIndexOfSameSum = i;
                maxSubArrayLength = Math.max(maxSubArrayLength, endingIndexOfSameSum - startIndexOfSum);
            }

            // We do not update the 'prefixSum' in HashMap if 'prefixSum' exits earlier in the map
            // because we want maximum Sub array length with sum 0 & not recent one
            // So we added a 'else block' here
            else
                prefixSumMap.put(prefixSum, i);
        }
        return maxSubArrayLength;
    }

}
