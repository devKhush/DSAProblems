
class Node{
    public int data;
    public Node next;
    
    public Node(int data){
        this.data = data;
    }
}
    
    
class MyCircularQueue {
    public Node head = null;
    public Node tail = null;
    public int size;
    public int maxSize;

    public MyCircularQueue(int maxSize) {
        this.maxSize = maxSize;
        this.size = 0;
    }
    
    public boolean enQueue(int value) {
        if (this.isFull())
            return false;
        
        Node node = new Node(value);
        
        if (this.head==null){
            this.head = node;
            this.tail = node;
        }
        else{
            this.tail.next = node;
            this.tail = this.tail.next;
        }
        this.tail.next = this.head;
        this.size++;
        return true;
    }
    
    public boolean deQueue() {
        if (this.isEmpty())
            return false;
        
        this.tail.next = null;
        Node node = this.head;
        this.head = this.head.next;
        node.next = null;
        this.size--;
        
        if (!this.isEmpty())
            this.tail.next = this.head;
        else
            this.tail = null;
        return true;
    }
    
    public int Front() {
        if (this.isEmpty())
            return -1;
        return this.head.data;
    }
    
    public int Rear() {
        if (this.isEmpty())
            return -1;
        return this.tail.data;
    }
    
    public boolean isEmpty() {
        return this.size == 0;
    }
    
    public boolean isFull() {
        return this.size == this.maxSize;
    }
}


/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */