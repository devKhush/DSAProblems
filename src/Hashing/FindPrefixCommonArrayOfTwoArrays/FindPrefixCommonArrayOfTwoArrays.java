package Hashing.FindPrefixCommonArrayOfTwoArrays;
import java.util.HashSet;

// https://leetcode.com/problems/find-the-prefix-common-array-of-two-arrays/description/

public class FindPrefixCommonArrayOfTwoArrays {
    /********************************** Brute Force Solution **************************************
     * Intuition: Keep track of visited elements in two different sets

     * Time Complexity: O(n)
     * Space Complexity: O(2*n) ~ O(n)
     */
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        HashSet<Integer> setA = new HashSet<>();
        HashSet<Integer> setB = new HashSet<>();

        int[] C = new int[A.length];
        C[0] = A[0] == B[0] ? 1 : 0;
        setA.add(A[0]);
        setB.add(B[0]);

        for (int i = 1; i < A.length; i++) {
            C[i] = C[i - 1];
            if (setB.contains(A[i]))    // When element of A[] is present in setB
                C[i]++;
            if (setA.contains(B[i]))    // When element of B[] is present in setA
                C[i]++;
            if (A[i] == B[i])           // When two elements are equal
                C[i]++;
            setA.add(A[i]);
            setB.add(B[i]);
        }
        return C;
    }


    /*********************************** Efficient Solution ****************************************
     * Intuition: Keep track of visited elements in only array/set/hashmap
     * Since array is a permutation, each element will be present only one in the array

     * Time Complexity: O(n)
     * Space Complexity: O(2*n) ~ O(n)
     */
    private int[] findThePrefixCommonArray_best(int[] A, int[] B) {
        // Get the Size of an Array
        int n = A.length;

        // Answer array
        int[] C = new int[n];

        // 'elementCommon' will store the number of elements found to be common yet.
        int elementCommon = 0;

        // Create frequency array which will store the running frequency
        // of each integer in both the arrays together. Since, they
        // are permutations, the frequency of each element will
        // reach 2 at max at any point during the iterations.
        int[] frequency = new int[n + 1];

        for (int i = 0; i < n; i++){
            // Increment frequency of element a[i], if frequency becomes 2 then
            // increment the 'elementCommon' counter.
            frequency[A[i]]++;
            if (frequency[A[i]] == 2) elementCommon++;

            // Increment frequency of element b[i], if frequency becomes 2 then
            // increment the 'elementCommon' counter.
            frequency[B[i]]++;
            if (frequency[B[i]] == 2) elementCommon++;

            // Because we are moving in one direction the common elements
            // will not be changed infact the count will remain same or increase
            // but will never get reduced and hence at index 'i' the number of
            // common elements will be 'elementCommon'
            C[i] = elementCommon;
        }
        return C;
    }
}
