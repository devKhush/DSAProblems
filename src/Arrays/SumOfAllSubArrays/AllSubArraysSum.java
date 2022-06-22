package Arrays.SumOfAllSubArrays;

// https://www.geeksforgeeks.org/sum-of-all-odd-length-subarrays/

public class AllSubArraysSum {
    /*
     ******************************* Simple Brute Force Approach *************************************
     * Generate all sub-arrays, find the Sum of all sub-arrays starting with any element arr[i]
     * Accumulate up the sum of all Sub arrays starting with the current element arr[i]
     * Do this for every element in the array 'arr'(every arr[i])
     * TC -> O(n^2)
     * SC -> O(1)
     */
    private int allSubArraySum_BruteForce(int[] arr) {
        int allSubArraySum = 0;

        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                // current Sub-array Sum
                sum += arr[j];

                // Accumulate up the sum of all Sub arrays starting with the current element arr[i]
                allSubArraySum += sum;
            }
        }
        return allSubArraySum;
    }


    /*
    ************************************** Efficient Solution ***********************************
    * TC -> O(n)
    * SC -> O(1)
    * https://www.geeksforgeeks.org/sum-of-all-odd-length-subarrays/
    *
    * Intuition/Thought Process:
    * Efficient Approach: To optimize the above approach, the idea is to observe the following pattern
      after generating all the sub-arrays.
    * For any element at index 'i' there are ('i' + 1) choices of sub-arrays on the left side of it
      and (N – 'i') choices of sub-arrays on the right side of it.
    * Therefore, for any element arr[i], the count of arr[i] is (i + 1) * (N – i) in all the sub-arrays.
    * Therefore, to find the sum of all elements of all the sub-arrays, the idea is to
      iterate over the array and for every ith array element, add [(i + 1) * (n – i)] * arr[i] to the sum.
     */
    private int allSubArraySum(int[] arr) {
        int allSubArraySum = 0;
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            allSubArraySum += (i + 1) * (n - i) * arr[i];
        }
        return allSubArraySum;
    }
}
