package BinarySearch.KthSmallestElementInRowColumnWiseSortedMatrix;
import java.util.PriorityQueue;

public class KthSmallestElementInSortedMatrix_HeapSolution {
    /******************************* Simple MinHeap Solution ************************************
     * Time Complexity : O(n^2 * log(n^2))
        * where no. of rows & columns are both 'n'
     * Space Complexity: O(n^2)
        * MinHeap will contain all the n^2 elements of the matrix
     */
    public int kthSmallest_MinHeap(int[][] matrix, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> a - b);

        // Add all elements in the MinHeap
        for (int[] row : matrix)
            for (int num : row)
                minHeap.add(num);

        // Remove the first "k - 1" elements from the minHeap
        for (int i = 1; i < k; i++)
            minHeap.remove();

        // Return the 'kth' smallest element, which is currently the peek of the MinHeap
        return minHeap.peek();
    }


    /******************************* Simple MaxHeap Solution ************************************
     * We can use a MaxHeap to limit the size of Heap to 'k'
     * We will use maxHeap to contain only the "k" smallest elements from the matrix

     * Time Complexity : O(n^2 * log(k))
        * where no. of rows & columns are both 'n'
     * Space Complexity: O(k)
        * MinHeap will contain only the k element of the matrix
     */
    public int kthSmallest_maxHeap(int[][] matrix, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> (b - a));
        int count = 0;

        for (int[] row : matrix){
            for (int num : row){
                // If the Size of MaxHeap is less than 'k', keep on adding elements to the maxHeap
                if (count < k){
                    maxHeap.add(num);
                    count++;
                }
                // If the Size of MaxHeap is >= 'k', insert the element into maxHeap only if the number
                // is smaller than peek() of MaxHeap. Bcoz, we want maxHeap to contain only the
                // "k" smallest numbers from matrix
                else if (count >= k  &&  num < maxHeap.peek()){
                    maxHeap.remove();
                    maxHeap.add(num);
                }
            }
        }

        // kth smallest number will be the peek() of the maxHeap, bcoz heap is MaxHeap
        return maxHeap.peek();
    }
}
