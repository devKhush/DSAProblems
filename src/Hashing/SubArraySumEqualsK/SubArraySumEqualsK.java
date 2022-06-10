package Hashing.SubArraySumEqualsK;
import java.util.HashMap;

// MUST READ these articles
// https://youtu.be/20v8zSo2v18
// https://youtu.be/HbbYPQc-Oo4
// https://www.geeksforgeeks.org/number-subarrays-sum-exactly-equal-k/
// https://www.codingninjas.com/codestudio/library/count-number-of-subarrays-with-sum-k

public class SubArraySumEqualsK {

    // ********************************** Brute Force **********************************
    // Calculates the sum of all sub-array & increment the count if sub-array's sum is equal to k
    // TC -> O(n*n)
    // SC -> O(1)
    public int subarraySum_BruteForce(int[] arr, int k) {
        int subarrayCount = 0;

        for (int i = 0; i < arr.length; i++){
            int sum = 0;

            for (int j = i; j < arr.length; j++){
                sum += arr[j];

                if (sum == k)
                    subarrayCount++;
            }
        }
        return subarrayCount;
    }



    // ******************************** Efficient Solution using Hashing ********************************
    // Intuition is similar to that of "Largest subarray with Zero Sum" (Hashing technique)
    // TC -> O(n)
    // SC -> O(n)
    public int subarraySum(int[] arr, int k) {
        int subArraysWithSumK = 0;
        int prefixSum = 0;

        // Hashmap stores the "Prefix-Sum" as "KEY"  & "No. of times that prefix sum occurs" as "VALUE"
        HashMap<Integer, Integer> prefixSumMap = new HashMap<>();

        for (int i = 0; i < arr.length; i++){
            prefixSum += arr[i];

            // If prefix sum is same as 'k', then increment the count of sub-arrays
            if (prefixSum == k)
                subArraysWithSumK++;

            // If map contains subarray with sum "prefixSum - k", then updates 'count' by no. of
            // times that sub-array occurs previously. For more clarity see example below!
            if (prefixSumMap.containsKey(prefixSum - k))
                subArraysWithSumK += prefixSumMap.get(prefixSum - k);

            // add the count of prefixSum in the HashMap & update the count if it is already present in array
            prefixSumMap.put(prefixSum, prefixSumMap.getOrDefault(prefixSum, 0) + 1);
        }

        return subArraysWithSumK;
    }
}


/*
If you are confused why the count was increased by myMap[prevSum - k] instead of just count+=1 ?

It was because prefix sum can be same because of some negative values in the array, so out next
occurring k will also pair  with those negative values also .

consider this case A : [3  4  7    2   -3   1   4   2   1 ]       for k = 7
              preSum : [3  7  14   16  13   14  18  20  21]

you can see 14 occurred twice  because of the subsequence [2 -3 1] their sum is 0.
so When you are at the last index with value 1 . you have curr - k = 21 - 7 = 14 .
you check for 14 it has occurred twice. So we need to consider subsequences [2 -3 1 4 2 1] and [4 2 1] . Hope this helps
 */


/*
Another version of code:

int findSubarraySum(int arr[], int n, int sum)
{
        // HashMap to store number of arrays starting from index zero having particular value of sum.
        HashMap<Integer, Integer> prevSum = new HashMap<>();
        // This condition will handle the case when prefixSum == k
        prevSum.put(0,1);

        int res = 0;

        // Sum of elements so far.
        int currSum = 0;

        for (int i = 0; i < n; i++) {

            // Add current element to sum so far.
            currSum += arr[i];

            //calculate the sum that have to be removed, so that we can get the desired sum
            int removeSum=currSum-sum;

           //get count of occurrences of that sum that have to removed and add it to res value
            if (prevSum.containsKey(removeSum))
                res += prevSum.get(removeSum);

            // Add currsum value to count of different values of sum.
            prevSum.put(currSum, prevSum.getOrDefault(currSum, 0) + 1);
        }
        return res;
    }
 */