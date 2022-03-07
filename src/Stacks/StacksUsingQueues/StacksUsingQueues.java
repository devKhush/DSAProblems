package Stacks.StacksUsingQueues;

// https://youtu.be/FbPIay0KN00

public class StacksUsingQueues {
    
    private MyQueue q1;
    private MyQueue q2;

    public StacksUsingQueues() {
        q1 = new MyQueue();
        q2 = new MyQueue();
    }
    
    public void push(int x) {
        q2.enqueue(x);
        while (!q1.isEmpty())
            q2.enqueue(q1.dequeue());
        MyQueue temp = q1;
        q1 = q2;
        q2 = temp;
    }
    
    public int pop() {
        return q1.dequeue();
    }
    
    public int top() {
        return q1.front();
    }
    
    public boolean empty() {
        return q1.isEmpty();
    }
}


class Node{
    int data;
    Node next;
    public Node(int data){
        this.data = data;
    }
}

class MyQueue{
    Node front;
    Node tail;
    
    public MyQueue(){
        front = null;
        tail = null;
    }
    
    public void enqueue(int x){
        Node data = new Node(x);
        if (front==null){
            tail = data;
            front = data;
            return;
        }
        tail.next = data;
        tail = tail.next;
    }
    
    public int dequeue(){
        Node delete = front;
        front = front.next;
        delete.next = null;
        return delete.data;
    }
    
    public int front(){
        return front.data;
    }
    
    public boolean isEmpty(){
        return front ==null;
    }
}
