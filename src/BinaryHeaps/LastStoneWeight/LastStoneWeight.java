package BinaryHeaps.LastStoneWeight;
import java.util.PriorityQueue;

// https://leetcode.com/problems/last-stone-weight/description/

public class LastStoneWeight {
    /********************************** PQ Solution ***************************************
     * TC -> O(n * log(n))
     * SC -> O(n)
     */
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b)->(b-a));
        for (int val : stones){
            maxHeap.add(val);
        }
        while (maxHeap.size() > 1){
            int A = maxHeap.remove();
            int B = maxHeap.remove();
            if (A != B)
                maxHeap.add(A - B);
        }
        return !maxHeap.isEmpty() ? maxHeap.peek() : 0;
    }
}
