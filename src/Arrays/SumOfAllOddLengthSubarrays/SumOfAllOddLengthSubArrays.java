package Arrays.SumOfAllOddLengthSubarrays;

// https://www.geeksforgeeks.org/sum-of-all-odd-length-subarrays/

public class SumOfAllOddLengthSubArrays {

    /*
    ************************************** Efficient Solution ***********************************
    * TC -> O(n)
    * SC -> O(1)
    * https://www.geeksforgeeks.org/sum-of-all-odd-length-subarrays/
    *
    * Intuition/Thought Process:
    * Efficient Approach: To optimize the above approach, the idea is to observe the following pattern
      after generating all the sub-arrays:
    * For any element at index 'i' there are ('i' + 1) choices of sub-arrays on the left side of it
      and (N – 'i') choices of sub-arrays on the right side of it.
    * Therefore, for any element arr[i], the count of arr[i] is (i + 1) * (N – i) in all the sub-arrays.
    * So, for an element arr[i], there are ((i + 1) * (N – i) + 1) / 2 sub-arrays with odd length.
    * Finally, arr[i] will have a total of ((i + 1) * (n – i) + 1) / 2 frequency in the sum.
    * Therefore, to find the sum of all elements of all the sub-arrays of odd length, the idea is to
      iterate over the array and for every ith array element, add [((i + 1) * (n – i) + 1) / 2] * arr[i] to the sum.
     */
    public int sumOddLengthSubArrays(int[] arr) {
        int n = arr.length;
        int sumOfOddLengthSubarray = 0;
        
        for (int i = 0; i < n; i++)
             sumOfOddLengthSubarray +=  ((i + 1) * (n - i) + 1)/2  * arr[i];
        
        return sumOfOddLengthSubarray;
    }
    
    
    /*
    ********************************* Brute Force Solution ************************************
    * TC -> O(n^2)
    * SC -> O(1)
    * Selecting only those sub-arrays whose lengths are odd
     */
    public int sumOddLengthSubArrays_BruteForce(int[] arr) {
        int sumOfOddLengthSubarray = 0;
        
        for (int i = 0; i < arr.length; i++){
            int subArraySum = 0;
            
            for (int j = i; j < arr.length; j++){
                subArraySum += arr[j];
                
                if ((j-i+1) % 2 == 1)
                    sumOfOddLengthSubarray += subArraySum;
            }     
        }
        return sumOfOddLengthSubarray;
    }
}