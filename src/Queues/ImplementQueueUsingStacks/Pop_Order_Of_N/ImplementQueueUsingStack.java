package Queues.ImplementQueueUsingStacks.Pop_Order_Of_N;

public class ImplementQueueUsingStack {
    private MyStack stack1;
    private MyStack stack2;
    private int front = -1;

    public ImplementQueueUsingStack() {
        stack1 = new MyStack();
        stack2 = new MyStack();
    }
    
    public void push(int x) {
        if (stack1.isEmpty())
            front = x;
        stack1.push(x);
    }
    
    public int pop() {
        while (!stack1.isEmpty())
            stack2.push(stack1.pop());
        
        int pop = stack2.pop();
        
        if (!stack2.isEmpty())
            front = stack2.peek();
        
        while (!stack2.isEmpty())
            stack1.push(stack2.pop());
        
        return pop;
    }
    
    public int peek() {
        return front;
    }
    
    public boolean empty() {
        return stack1.isEmpty();
    }
}


class Node{
    public int data;
    public Node next;
    
    public Node(int x){
        this.data = x;
        this.next = null;
    }
}


class MyStack{
    public Node top;
    public MyStack(){
        top = null;
    }
    
    public boolean isEmpty(){
        return top==null;
    }
    
    public int peek(){
        return top.data;
    }
    
    public int pop(){
        Node temp = top;
        top = top.next;
        temp.next = null;
        return temp.data;
    }
    
    public void push(int x){
        Node temp = new Node(x);
        if (top==null)
            top = temp;
        else{
            temp.next = top;
            top = temp;
        }
    }
}