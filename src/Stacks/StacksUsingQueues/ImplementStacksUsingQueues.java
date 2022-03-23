package Stacks.StacksUsingQueues;
import java.util.ArrayList;
import java.util.LinkedList;

// Array List Implementation
class MyStack_Using_ArrayList {
    ArrayList<Integer> queue;
    int front;
    int last;

    public MyStack_Using_ArrayList() {
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

// See Queue folder for all Linked list method
// LinkedList Implementation
class MyStack_Using_LinkedList {
    LinkedList<Integer> queue;

    public MyStack_Using_LinkedList() {
        queue = new LinkedList<>();
    }

    public void push(int x) {
        // queue.addLast() also works, they are same
        queue.add(x);
    }

    public int pop() {
        return queue.removeLast();
    }

    public int top() {
        return queue.getLast();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}
