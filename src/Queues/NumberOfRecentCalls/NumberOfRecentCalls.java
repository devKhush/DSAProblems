package Queues.NumberOfRecentCalls;
import java.util.ArrayDeque;
import java.util.Queue;

public class NumberOfRecentCalls {
    private final Queue<Integer> queue;

    public NumberOfRecentCalls() {
        queue = new ArrayDeque<>();
    }

    // Time Complexity: O(1)
    // On average, Time complexity is O(1), Amortized Complexity
    // Space Complexity: O(3000) = O(1)
    // Queue size will be at most 3000
    public int ping_(int time) {
        int startRange = time - 3000;

        while (!queue.isEmpty()  &&  queue.peek() < startRange)
            queue.remove();

        queue.add(time);
        return queue.size();
    }
}
