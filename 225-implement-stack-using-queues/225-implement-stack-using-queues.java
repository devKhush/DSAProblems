class MyStack {
    
    private MyQueue q;

    public MyStack() {
        q = new MyQueue();
    }
    
    public void push(int x) {
        q.enqueue(x);
    }
    
    public int pop() {
        int elementToPop = q.tail.data;
        while (q.front() != elementToPop)
            q.enqueue(q.dequeue());
        return q.dequeue();
    }
    
    public int top() {
        return q.tail.data;
    }
    
    public boolean empty() {
        return q.isEmpty();
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


/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */