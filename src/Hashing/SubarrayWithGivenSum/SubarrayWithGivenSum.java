package Hashing.SubarrayWithGivenSum;
import java.util.ArrayList;
import java.util.HashMap;

// https://youtu.be/XzwUBIkR9pA
// https://www.geeksforgeeks.org/find-subarray-with-given-sum-in-array-of-integers/

// ********************** Find the First Sub-array with given Sum **********************


class SubarrayWithGivenSum {

    // ************************************ Efficient Hashing Solution ************************************
    // Concept is similar to that of CDF of random varaible
    // TC -> O(n)
    // SC -> O(n)

    public static ArrayList<Integer> subarraySum_Hashing(int[] arr, int n, int sum) {

      ArrayList<Integer> answer = new ArrayList<>();
      HashMap<Integer, Integer> prefixSumMap = new HashMap<>();
      int prefixSum = 0;
      
      for (int i = 0; i < arr.length; i++){
          prefixSum += arr[i];

          // When prefix sum becomes equal to target sum, then it will be the answer
          if (prefixSum == sum){
              // starting index in case prefix sum becomes target sum (1-based indexing)
              answer.add(1);
              // ending index in case prefix sum becomes target sum (1-based indexing)
              answer.add(i + 1);
              return answer;
          }


          // Here the concept of CDF of RV is applied, this happens when the required subarray is
          // in between the array
          if (prefixSumMap.containsKey(prefixSum - sum)) {

              // starting index in case of sub array in middle of array (+1 for 1-based indexing & +1 for range)
              answer.add(prefixSumMap.get(prefixSum - sum) + 2);
              // ending index in case of sub array in middle of array
              answer.add(i + 1);
              return answer;
          }
          
          prefixSumMap.put(prefixSum, i);
      }
      
      answer.add(-1);
      return answer;
    }



    // **************************************** Brute force ***********************************
    // TC -> O(n*n)
    // SC -> O(1)

    public static ArrayList<Integer> subarraySum_BruteForce(int[] arr, int n, int sum) {
        ArrayList<Integer> answer = new ArrayList<>();

        for (int i = 0; i < arr.length; i++){
            int prefixSum = 0;

            for (int j = i; j < arr.length; j++){
                prefixSum += arr[j];

                if (prefixSum == sum){
                    answer.add(i+1);
                    answer.add(j+1);
                    return answer;
                }
            }
        }
        answer.add(-1);
        return answer;
    }
    
}