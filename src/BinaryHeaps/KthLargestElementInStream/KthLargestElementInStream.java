package BinaryHeaps.KthLargestElementInStream;
import java.util.PriorityQueue;

// https://leetcode.com/problems/kth-largest-element-in-a-stream/description/

public class KthLargestElementInStream {
    /*************************************** Efficient MinHeap Solution *************************
     * Time Complexity: O(log(k))
        * O(k * log(k)) to build the constructor
        * O(log(k)) fo each add() call
     * Space Complexity: O(k)
        * size of minHeap is 'k'
     */
    PriorityQueue<Integer> minHeap;
    int k, size;
    public KthLargestElementInStream(int k, int[] nums) {
        this.k = k;
        this.size = 0;
        this.minHeap = new PriorityQueue<>((a, b) -> a - b);

        for (int num : nums) {
            if (size < k) {
                minHeap.add(num);
                size++;
            } else if (minHeap.peek() <= num) {
                minHeap.remove();
                minHeap.add(num);
                size++;
            }
        }
    }

    public int add(int val) {
        if (size < k){
            minHeap.add(val);
            size++;
        }
        else if (minHeap.peek() <= val){
            minHeap.remove();
            minHeap.add(val);
            size++;
        }
        return minHeap.peek();
    }
}
