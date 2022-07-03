
class MinStack {
    private Node top;
    public MinStack() {
        this.top = null;
    }

    // Function to add another element equal to num at the top of stack.
    public void push(int num) {
        if (top == null)
            top = new Node(num, num);
        else{
            Node newTop = new Node(num, Math.min(top.minValue, num));
            newTop.next = top;
            top = newTop;
        }
    }

    // Function to remove the top element of the stack.
    public int pop() {
        if (top == null)
            return -1;
        Node topOfStack = top;
        top = top.next;
        topOfStack.next = null;
        return topOfStack.data;
    }

    // Function to return the top element of stack if it is present. Otherwise, return -1.
    public int top() {
        return top != null ? top.data : -1;
    }

    // Function to return minimum element of stack if it is present. Otherwise, return -1.
    public int getMin() {
        return top != null ? top.minValue : -1;
    }

    static class Node{
        public int data, minValue;
        public Node next;
        public Node(int data, int minValue){
            this.data = data;
            this.minValue = minValue;
        }
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */