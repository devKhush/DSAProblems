package Queues.ImplementQueueUsingStacks.QueueUsingArray;

import java.util.Arrays;

public class QueueUsingArray {
    private int front;
    private int rear;
    private int size;
    private final int capacity;
    private final int[] arr;

    public QueueUsingArray(int capacity){
        this.front = 0;
        this.rear = 0;
        this.size = 0;
        this.capacity = capacity;
        this.arr = new int[capacity];
    }

    public void enqueue(int x){
        if (this.isFull())
            return;
        this.arr[this.rear] = x;
        this.rear = (this.rear + 1) % this.capacity;
        this.size++;
    }

    public int dequeue(){
        if (this.isEmpty())
            return Integer.MIN_VALUE;
        int x = this.arr[this.front];
        this.arr[this.front] = 0;
        this.front = (this.front + 1) % this.capacity;
        this.size--;
        return x;
    }

    public int front(){
        if (this.size <= 0)
            return Integer.MIN_VALUE;
        return this.arr[this.front];
    }

    public int rear(){
        if (this.size <= 0)
            return Integer.MIN_VALUE;
        return this.arr[this.rear];
    }

    public boolean isEmpty(){
        return this.size==0;
    }

    public boolean isFull(){
        return this.size == this.capacity;
    }

    public int getSize() {
        return size;
    }

    public int[] getArr() {
        return arr;
    }
}

class QueueTest{
    public static void main(String[] args) {
        QueueUsingArray queue = new QueueUsingArray(5);

        for (int i = 1; i < 7; i++) {
            queue.enqueue(i);
            System.out.println(queue.getSize());
            System.out.println("is Full ? " +queue.isFull());
            System.out.println(Arrays.toString(queue.getArr()));
            System.out.println();
        }

        for (int i = 1; i < 8; i++) {
            if (i==2){
                queue.enqueue(i*20);
                System.out.println(queue.getSize());
                System.out.println("is Full ? " +queue.isFull());
                System.out.println(Arrays.toString(queue.getArr()));
                System.out.println();
                continue;
            }
            queue.dequeue();
            System.out.println(queue.getSize());
            System.out.println("is Empty ? " + queue.isEmpty());
            System.out.println(Arrays.toString(queue.getArr()));
            System.out.println();
        }

    }
}
