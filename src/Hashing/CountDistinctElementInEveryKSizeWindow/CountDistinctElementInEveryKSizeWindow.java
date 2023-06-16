package Hashing.CountDistinctElementInEveryKSizeWindow;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;

// https://www.codingninjas.com/codestudio/problems/920336
// https://www.interviewbit.com/problems/distinct-numbers-in-window/

public class CountDistinctElementInEveryKSizeWindow {
    /********************************** Brute Force *******************************************
     * Simple Brute Force Solution
     * Time Complexity: O((n-k)*k)  ~  O(n*k)
     * Space Complexity: O(k)
        * HashSet (of at most size 'k') is used to store distinct elements.
     */
    public int[] dNums_brute(int[] arr, int k) {
        int n = arr.length;
        int[] distinctNum = new int[n - k + 1];

        for (int i = 0; i <= n - k; i++){
            HashSet<Integer> set = new HashSet<>();
            for (int j = i; j < i + k; j++)
                set.add(arr[j]);
            distinctNum[i] = set.size();
        }
        return distinctNum;
    }


    /******************************************* HashMap + Queue Solution *******************************
     * Intuition:
        * Recall problem "Minimum in every window of size k" of queue solution
        * Used the same concept here.
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public int[] distinctNumbers(int[] arr, int k) {
        int n = arr.length;
        int[] distinctNum = new int[n - k + 1];

        // Store the count of distinct elements
        int distinct = 0;
        HashMap<Integer, Integer> countMap = new HashMap<>();
        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < n; i++){
            // If window extends the size of k, remove the first element
            if (!queue.isEmpty()  &&  i - queue.peek() >= k){
                int index = queue.remove();
                countMap.put(arr[index], countMap.get(arr[index]) - 1);
                if (countMap.get(arr[index]) == 0)
                    distinct--;
            }

            // Add current value to the window
            countMap.put(arr[i], countMap.getOrDefault(arr[i], 0) + 1);
            if (countMap.get(arr[i]) == 1)
                distinct++;
            queue.add(i);

            // Write the distinct count of current window to answer
            if (i + 1 - k >= 0)
                distinctNum[i + 1 - k] = distinct;
        }
        return distinctNum;
    }


    /******************************************* HashMap Solution *******************************
     * Intuition:
        * Recall problem "Minimum in every window of size k" of queue solution
        * Just an improvement of previous solution, we don't really need an Queue for this purpose
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public int[] countDistinctNumbers(int[] arr, int k) {
        int n = arr.length;
        int[] distinctNum = new int[n - k + 1];

        // Store the count of distinct elements
        int distinct = 0;
        HashMap<Integer, Integer> countMap = new HashMap<>();

        for (int i = 0; i < n; i++){
            // If window extends the size of k, remove the first element
            if (i - k >= 0){
                countMap.put(arr[i - k], countMap.get(arr[i - k]) - 1);
                if (countMap.get(arr[i - k]) == 0)
                    distinct--;
            }

            // Add current value to the window
            countMap.put(arr[i], countMap.getOrDefault(arr[i], 0) + 1);
            if (countMap.get(arr[i]) == 1)
                distinct++;

            // Write the distinct count of current window to answer
            if (i + 1 - k >= 0)
                distinctNum[i + 1 - k] = distinct;
        }
        return distinctNum;
    }
}
