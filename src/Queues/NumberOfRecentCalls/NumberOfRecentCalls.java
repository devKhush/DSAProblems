package Queues.NumberOfRecentCalls;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfRecentCalls {
    private Queue<Integer> queue;

    public NumberOfRecentCalls() {
        queue = new LinkedList<>();
    }

    // O(N)
    public int ping(int t) {
        int requests = 1;
        int startRange = t - 3000;
        int endRange = t;

        while (!queue.isEmpty()){
            if (queue.peek() >= startRange && queue.peek() <= endRange){
                requests += queue.size();
                break;
            }
            else
                queue.remove();
        }
        queue.add(t);
        return requests;
    }

    // Another approach (Faster & easier than previous one)
    public int ping_(int time) {
        int startRange = time -3000;
        this.queue.add(time);

        while (this.queue.peek() < startRange)
            this.queue.remove();

        return this.queue.size();
    }
}
