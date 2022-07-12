package Queues.MovingAverageFromDataStream;

// https://www.lintcode.com/problem/642/
// https://www.youtube.com/watch?v=E-kjYOZEBxY


import java.util.ArrayDeque;
import java.util.Queue;

public class MovingAverageFromDataStream {
    private Queue<Integer> queue;
    private int size, sum;

    public MovingAverageFromDataStream(int size) {
        this.queue = new ArrayDeque<>();
        this.sum = 0;
        this.size = size;
    }

    public double next(int val) {
        if (queue.size() == size)
            sum -= queue.remove();

        sum += val;
        queue.add(val);
        return sum / (double) queue.size();
    }
}
