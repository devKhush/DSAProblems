package BinaryHeaps.ReduceArraySizeToHalf;
import java.util.HashMap;
import java.util.PriorityQueue;

// https://leetcode.com/problems/reduce-array-size-to-the-half/

class ReduceArraySizeToHalf {
    /********************************* Efficient HashMap + MaxHeap Solution **************************
     * Intuition:
        * In order to minimize the numbers of element removed, so that at least half of
            the elements from the array are removed, we will remove those elements from the array
            whose frequency is maximum.
        * By removing the elements from array with maximum frequencies, we will minimize the no. of
            unique elements to be deleted from the array.

     * Steps:
        * Count the frequency of each integer in the array.
        * Start with an empty set, add to the set the integer with the maximum frequency.
        * Keep Adding the integer with the max frequency until you remove at least half of the integers.

     * Time Complexity: ~ O(n)
     * Space Complexity: O(n)
        * Due to HashMap & PriorityQueue used
     */
    public int minSetSize(int[] arr) {
        int n = arr.length;

        // HashMap to store the Frequency/Count of all the values in array
        HashMap<Integer, Integer> freqMap = new HashMap<>();

        // Calculate the frequency of all elements
        for (int val : arr){
            freqMap.put(val, freqMap.getOrDefault(val, 0) + 1);
        }

        // MaxHeap to store the Frequency/Count of All the values in array, in Decreasing order
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b)->(b - a));

        // Add Frequencies into MaxHeap
        for (int values : freqMap.keySet()){
            maxHeap.add(freqMap.get(values));
        }

        // Count of elements removed from array till now
        int elementRemoved = 0;
        // Store the minimum size of set (elements to be removed from array), so that at least half of
        // the integers of the array are removed.
        int setSize = 0;

        // Remove that Element from array with Max. frequency, so that size of set will be minimum
        while (elementRemoved < n/2){
            int frequency = maxHeap.remove();
            elementRemoved += frequency;
            setSize++;
        }
        return setSize;
    }
}