package Hashing.SubArraySumEqualsK;

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

}
