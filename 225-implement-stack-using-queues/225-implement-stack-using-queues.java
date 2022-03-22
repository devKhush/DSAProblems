class MyStack {
    
    ArrayList<Integer> queue;
    int front;
    int last;

    public MyStack() {
        queue = new ArrayList<>();
    }
    
    public void push(int x) {
        queue.add(x);
    }
    
    public int pop() {
        return queue.remove(queue.size()-1);
    }
    
    public int top() {
        return queue.get(queue.size()-1);
    }
    
    public boolean empty() {
        return queue.isEmpty();
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