package BinaryHeaps;
import java.util.PriorityQueue;


// Min heap & Max heap using Priority Queue

public class PriorityQueueDemo {
    public static void main(String[] args) {
        // Min heap using priority Queue
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        minHeap.add(10);
        minHeap.add(5);
        minHeap.add(20);
        System.out.println(minHeap);

        minHeap.remove(2);
        System.out.println(minHeap);
        System.out.println(minHeap.peek());

        minHeap.remove();
        System.out.println(minHeap);

        minHeap.remove();
        System.out.println(minHeap);    

        System.out.println(minHeap.isEmpty());
        if (minHeap.contains(100))
            minHeap.remove(100);

        minHeap.peek();
        minHeap.poll();
        minHeap.size();

        System.out.println();


        // Max heap using priority Queue
        // PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Collections.reverseOrder());
        // PriorityQueue<Integer> maxPQ = new PriorityQueue<>((a,b) -> b.compareTo(a));
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> b-a);

        maxHeap.add(10);
        maxHeap.add(5);
        maxHeap.add(20);
        System.out.println(maxHeap);

        maxHeap.remove();
        System.out.println(maxHeap);
        System.out.println(maxHeap.peek());

        maxHeap.remove();
        System.out.println(maxHeap);

        maxHeap.size();

        maxHeap.remove();
        System.out.println(maxHeap);

        System.out.println(maxHeap.isEmpty());
        maxHeap.remove(100);

    }
}
