class MyQueue {
    
    private MyStack stack1;
    private MyStack stack2;
    private int front = -1;

    public MyQueue() {
        stack1 = new MyStack();
        stack2 = new MyStack();
    }
    
    public void push(int x) {
        if (stack1.top==null)
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

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */