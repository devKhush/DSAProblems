package Queues.MovingAverageFromDataStream;

// https://www.lintcode.com/problem/642/
// https://www.youtube.com/watch?v=E-kjYOZEBxY

import java.util.LinkedList;
import java.util.Queue;

public class MovingAverageFromDataStream {
    private Queue<Integer> queue;
    private int size;
    private double sum;

    public MovingAverageFromDataStream(int size) {
        queue = new LinkedList<>();
        this.sum = 0;
        this.size = size;
    }

    public double next(int val) {
        if (queue.size() == this.size){
            this.sum -= queue.remove();
        }
        this.sum += val;
        this.queue.add(val);
        return (this.sum + 0.0)/this.queue.size();
    }
}
