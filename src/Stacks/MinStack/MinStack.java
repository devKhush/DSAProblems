package Stacks.MinStack;

class Node{
    public int value;
    public int MinValue;
    public Node next;
    public Node(int value, int minValue) {
        this.value = value;
        MinValue = minValue;
    }

}

public class MinStack {
    public Node top;

    public MinStack() {
    }

    public void push(int value) {
        if (top == null) {
            top = new Node(value, value);
            return;
        }
        int min = Math.min(value, this.top.MinValue);
        Node temp = new Node(value, min);
        temp.next = this.top;
        this.top = temp;
    }

    public void pop() {
        if (top==null)
            return;
        Node temp = top;
        top = top.next;
        temp.next = null;
    }

    public int top() {
        return top.value;
    }

    public int getMin() {
        return top.MinValue;
    }
}
